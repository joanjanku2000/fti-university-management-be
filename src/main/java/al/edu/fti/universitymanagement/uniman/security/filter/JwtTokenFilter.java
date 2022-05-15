package al.edu.fti.universitymanagement.uniman.security.filter;

import al.edu.fti.universitymanagement.uniman.security.util.JwtUtil;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;


@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (null == header || header.isEmpty() || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        final String token = header.split(" ")[1].trim();

        if (!JwtUtil.tokenIsValid(token)) {
            chain.doFilter(request, response);
            return;
        }


        UserDto userDto = JwtUtil.extractDto(token);

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(userDto.getEmail());

        UsernamePasswordAuthenticationToken
                authentication =
                SecurityUtil.createAuthenticationFromUserDetails(userDetails);

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private void validateToken(String token) {

        try {
            DecodedJWT jwt = JWT.decode(token);
            JwkProvider provider = null;
            Jwk jwk = null;
            Algorithm algorithm = null;
            provider = new UrlJwkProvider(new URL("https://login.microsoftonline.com/common/discovery/keys"));
            jwk = provider.get(jwt.getKeyId());
            algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            algorithm.verify(jwt);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed Token");
        } catch (JwkException e) {
            throw new RuntimeException("Invalid token key");
        } catch (SignatureVerificationException e) {
            throw new RuntimeException("Invalid token signature");
        } catch (JWTDecodeException e) {
            throw new RuntimeException("Invalid token decode");

        }
    }
}

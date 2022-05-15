package al.edu.fti.universitymanagement.uniman.security.api;

import al.edu.fti.universitymanagement.uniman.security.util.JwtUtil;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import al.edu.fti.universitymanagement.uniman.core.user.user.service.UserService;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity authenticate(@Valid @RequestBody UserDto request) throws IOException {
        Authentication authenticationResult;

        // TODO REFACTOR + Solve Validation Problems
        Map<String, Claim> claimMap = validateToken(request.getMicrosoftAccessToken());
        String email = claimMap.get("unique_name").asString();
        request.setEmail(email);
        log.info("Login request from {}",email);

        if (!userService.existsByEmail(email)) {
            log.info("Creating user for the first time");
            userService.createUserAtFirstLogin(request);
        }

        UserDto user = userService
                .findByEmail(email);
        userService.updateLoginTokenPassword(user);

        user.setMicrosoftAccessToken(request.getMicrosoftAccessToken()); // to use as password

        log.info("Finding user by email");
        log.info("Set password to new token");

        authenticationResult = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(user.getEmail(),"password"));

        String token = JwtUtil.createJwt(user);
        log.info("Authentication result {}",(authenticationResult.getPrincipal()));
       return ResponseEntity.ok(SecurityUtil.toLoginResponse(user,token));

    }

    @PostMapping("/logout")
    public void logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private Map<String, Claim> validateToken(String token) {

        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaims();
//            JwkProvider provider = null;
//            Jwk jwk = null;
//            Algorithm algorithm = null;
//            provider = new UrlJwkProvider(new
//                    URL("https://login.microsoftonline.com/73744c82-802f-4f14-b3be-a97df0787d17/discovery/v2.0/keys"));
//            jwk = provider.get(jwt.getKeyId());
//            algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
//            algorithm.verify(jwt);

//        } catch (JwkException e) {
//            throw new RuntimeException("Invalid token key");
        } catch (SignatureVerificationException e) {
            throw new RuntimeException("Invalid token signature");
        }catch (JWTDecodeException e) {
            throw new RuntimeException("Invalid token decode");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("MalformedURLException");
//        }
        }
    }
}

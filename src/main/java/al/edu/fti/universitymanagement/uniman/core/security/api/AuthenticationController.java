package al.edu.fti.universitymanagement.uniman.core.security.api;

import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotFoundException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages;
import al.edu.fti.universitymanagement.uniman.core.security.user.FtiUser;
import al.edu.fti.universitymanagement.uniman.core.security.util.JwtUtil;
import al.edu.fti.universitymanagement.uniman.core.user.UserService;
import al.edu.fti.universitymanagement.uniman.core.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.entity.UserEntity;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity authenticate(@Valid @RequestBody UserDto request) throws IOException {
        Authentication authenticationRequest;
        Authentication authenticationResult;


        // TODO Validate Microsoft Token

//        if (!userService.existsByEmail(request.getEmail())) {
//            log.info("Creating user for the first time");
//            userService.createUserAtFirstLogin(request);
//        }

        UserDto user = userService
                .findByEmail(request.getEmail())
                ;
        user.setMicrosoftAccessToken(request.getMicrosoftAccessToken()); // to use as password
        log.info("Finding user by email");
        userService.updateLoginTokenPassword(user);
        log.info("Set password to new token");

        authenticationResult = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(user.getEmail(),request.getMicrosoftAccessToken()));
        String token = JwtUtil.createJwt(user);
        log.info("Authentication result {}",(authenticationResult.getPrincipal()));
       return ResponseEntity.ok(authenticationResult.getPrincipal());

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
        }catch (JWTDecodeException e) {
            throw new RuntimeException("Invalid token decode");

        }
    }
}

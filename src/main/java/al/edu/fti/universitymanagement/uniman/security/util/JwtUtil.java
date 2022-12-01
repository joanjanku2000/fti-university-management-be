package al.edu.fti.universitymanagement.uniman.security.util;

import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    private static String signingKey = "Administratori123++/@";
    private static Long timeToLive = 60 * 60 * 60L;

    public static String createJwt(UserDto userDto){
        JwtBuilder jwtBuilder
                = Jwts.builder().setId(userDto.getIdNumber())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(userDto.getEmail())
                .setClaims(generateClaims(userDto))
                .signWith(SignatureAlgorithm.HS256,
                        new SecretKeySpec(DatatypeConverter.parseBase64Binary(signingKey),
                        SignatureAlgorithm.HS256.getJcaName()))
                .setIssuer("FTI_APP_"+LocalDateTime.now())
                .setExpiration(Date.from(Instant.ofEpochSecond(System.currentTimeMillis()+timeToLive)));
        log.info("Created token {}",jwtBuilder.compact());
        log.info("Decoded token is {}",decodeJwt(jwtBuilder.compact()));
        return jwtBuilder.compact();

    }
    private static Claims decodeJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(signingKey))
                .parseClaimsJws(jwt)
                .getBody();
    }
    public static boolean tokenIsValid(String jwt){
        try {
            decodeJwt(jwt);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public static UserDto extractDto(String jwt){
        Claims claims = decodeJwt(jwt);
        UserDto userDto = new UserDto();
        userDto.setEmail(claims.get("email",String.class));
        userDto.setName(claims.get("name",String.class));
        userDto.setLastName(claims.get("lastName",String.class));
        userDto.setMicrosoftAccessToken(claims.get("microsoftAccessToken",String.class));
        return userDto;
    }

    private static Map<String,Object> generateClaims(UserDto userDto){
        Map<String,Object> claimsToReturn = new HashMap<>();
        claimsToReturn.put("name",userDto.getName());
        claimsToReturn.put("lastName",userDto.getName());
        claimsToReturn.put("email",userDto.getEmail());
        claimsToReturn.put("microsoftAccessToken",userDto.getMicrosoftAccessToken());
        return claimsToReturn;
    }
}

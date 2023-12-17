package dasanda.BE.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.token.access.secret}")
    private String accessSecret;

    @Value("${jwt.token.refresh.secret}")
    private String refreshSecret;

    @Value("${jwt.token.access.expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.token.refresh.expiration}")
    private long refreshTokenExpiration;

    @Transactional
    public String access(String email){

        String jwtAccessToken = JWT.create()
                .withSubject("access")
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .withClaim("email", email)
                .sign(Algorithm.HMAC512(accessSecret));

        return jwtAccessToken;
    }

    @Transactional
    public String refresh(String email){

        String jwtRefreshToken = JWT.create()
                .withSubject("refresh")
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .withClaim("email", email)
                .sign(Algorithm.HMAC512(refreshSecret));

        return jwtRefreshToken;
    }

    @Transactional
    public String verifyToken(String accessToken){

        String email = JWT
                .require(Algorithm.HMAC512(accessSecret))
                .build()
                .verify(accessToken)
                .getClaim("email")
                .asString();

        return email;
    }

    @Transactional
    public String refreshAccess(String refreshToken){

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(refreshSecret))
                .build()
                .verify(refreshToken);
        String email = decodedJWT.getClaim("email").asString();

        return email;
    }

}

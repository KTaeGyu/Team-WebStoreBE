package dasanda.BE.service.auth;

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
    public String access(Long memberId, String email){

        String jwtAccessToken = JWT.create()
                .withSubject("access")
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .withClaim("memberId", memberId)
                .withClaim("email", email)
                .sign(Algorithm.HMAC512(accessSecret));

        return jwtAccessToken;
    }

    @Transactional
    public String refresh(Long memberId, String email){

        String jwtRefreshToken = JWT.create()
                .withSubject("refresh")
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .withClaim("memberId", memberId)
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
    public Map<String, Object> refreshAccess(String refreshToken){

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(refreshSecret))
                .build()
                .verify(refreshToken);
        Long memberId = decodedJWT.getClaim("memberId").asLong();
        String email = decodedJWT.getClaim("email").asString();
        Map<String, Object> result = new HashMap<>();
        result.put("memberId", memberId);
        result.put("email", email);

        return result;
    }

    @Transactional
    public Long verifyMember(String accessToken){

        Long memberId = JWT
                .require(Algorithm.HMAC512(accessSecret))
                .build()
                .verify(accessToken)
                .getClaim("memberId")
                .asLong();

        return memberId;
    }

}

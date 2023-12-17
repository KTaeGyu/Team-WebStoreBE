package dasanda.BE.api.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dasanda.BE.repository.member.MemberRepository;
import dasanda.BE.service.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class JwtApiController {

    private final JwtService jwtService;

    @PostMapping("/api/refresh")
    public ResponseEntity<Object> refreshToken(@RequestHeader("Authorization") String authorizationHeader) {

        try {
            String jwtToken = authorizationHeader.replace("Bearer ", "");

            System.out.println(jwtToken);

            String email = jwtService.refreshAccess(jwtToken);

            String jwtAccessToken = jwtService.access(email);

            return ResponseEntity.ok().body(Map.of("access_token", jwtAccessToken));

        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "token is not valid"));

        }
    }
}

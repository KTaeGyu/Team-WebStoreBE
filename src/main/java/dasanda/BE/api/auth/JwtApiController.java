package dasanda.BE.api.auth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import dasanda.BE.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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

            Map<String, Object> memberInfoMap = jwtService.refreshAccess(jwtToken);

            String jwtAccessToken = jwtService.access((Long) memberInfoMap.get("memberId"), (String) memberInfoMap.get("email"));

            return ResponseEntity.ok().body(Map.of("access_token", jwtAccessToken));

        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "token is not valid"));

        }
    }
}

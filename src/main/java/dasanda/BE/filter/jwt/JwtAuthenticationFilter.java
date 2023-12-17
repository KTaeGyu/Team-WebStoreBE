package dasanda.BE.filter.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import dasanda.BE.config.auth.PrincipalDetails;
import dasanda.BE.dto.member.MemberLoginDto;
import dasanda.BE.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    throws AuthenticationException {

        // 1. username, password 받기
        try {
            ObjectMapper om = new ObjectMapper();
            MemberLoginDto member = om.readValue(request.getInputStream(), MemberLoginDto.class);
            System.out.println(member);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());

            // PrincipalDetailsService가 호출 loadUserByUsername() 함수 실행
            // DB에 있는 username과 password가 일치하는지
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println(principalDetails.getMember().getEmail());
            System.out.println("===============================");

            // authentication 객체가 session 영역에 저장됨
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // JWT를 만들어 request 요청한 사용자에게 토큰을 응답해준다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        System.out.println("인증이 완료되었다");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtAccessToken = jwtService.access(principalDetails.getMember().getEmail());
        String jwtRefreshToken = jwtService.refresh(principalDetails.getMember().getEmail());


        // JSON 응답을 생성
        Map<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("access_token", jwtAccessToken);
        tokenResponse.put("refresh_token", jwtRefreshToken);


        // 응답의 바디에 JSON을 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(tokenResponse));
    }
}

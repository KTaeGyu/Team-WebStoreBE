package dasanda.BE.filter.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dasanda.BE.config.auth.PrincipalDetails;
import dasanda.BE.domain.Member;
import dasanda.BE.repository.member.MemberRepository;
import dasanda.BE.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// 권한이나 인증이 필요한 특정 주소 요청 시 BasicAuthenticationFilter 필터를 타게 된다.
// 만약에 권한이나 인증이 필요
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    private JwtService jwtService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, JwtService jwtService) {

        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("인증이 필요합니다.");

        if (request.getRequestURI().equals("/api/join")
                || request.getRequestURI().equals("/api/refresh")
                || request.getRequestURI().equals("/api/sms/send")
                || request.getRequestURI().equals("/api/sms/verify")
                || request.getRequestURI().contains("/api/items")){
            System.out.println("인증 안할래");
            chain.doFilter(request, response);
            return;
        }

        try {
            String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");

            String email = jwtService.verifyToken(jwtToken);

            // 서명 완료
            Member member = memberRepository.findByEmail(email);

            PrincipalDetails principalDetails = new PrincipalDetails(member);
            // JWT 서명을 통해 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);

        } catch (JWTVerificationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid token");
            errorResponse.put("message", "The provided token is not valid.");

            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(errorResponse));
            writer.flush();
        }


    }
}

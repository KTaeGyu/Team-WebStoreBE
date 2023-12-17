package dasanda.BE.config;

import dasanda.BE.config.auth.PrincipalDetailsService;
import dasanda.BE.domain.Member;
import dasanda.BE.filter.jwt.JwtAuthenticationFilter;
import dasanda.BE.filter.jwt.JwtAuthorizationFilter;
import dasanda.BE.repository.member.MemberRepository;
import dasanda.BE.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;

    private final MemberRepository memberRepository;

    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http
//                .addFilterBefore(new MyFilter(), BasicAuthenticationFilter.class);

        http
                .csrf().disable()
                .cors().configurationSource(corsConfig.corsConfigurationSource());
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .anonymous().disable()
                .apply(new MyCustomDsl())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/join/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity>{
        @Override
        public void configure(HttpSecurity http) throws Exception{

            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtService))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository, jwtService));
        }
    }
}

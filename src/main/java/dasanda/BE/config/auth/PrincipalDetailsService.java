package dasanda.BE.config.auth;

import dasanda.BE.domain.Member;
import dasanda.BE.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 "/login" 요청이 오면 UserDetailsService 타입으로 IOC 되어 있는
// PrincipalDetailsService 함수가 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 시큐리티 session(Authentication(UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username);
        if (member != null){
            return new  PrincipalDetails(member);
        }
        return null;
    }
}

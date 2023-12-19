package dasanda.BE.config.auth;

import dasanda.BE.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/*
    로그인이 완료되면 시큐리티 session을 만들어준다. (Security ContextHolder)
    오브젝트 타입 => Authentication 타입 객체
    Authentication 안에 User 정보가 있어야 함
    User 오브젝트 타입 => UserDetails 타입 객체

    Security Sessio => Authentication => UserDetails
*/
public class PrincipalDetails implements UserDetails {

    private Member member;
    public PrincipalDetails(Member member){
        this.member = member;
    }

    public Member getMember(){
        return member;
    }

    // 해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        // 휴면 계정 관리 가능
        return true;
    }
}

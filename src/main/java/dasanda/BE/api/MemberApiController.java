package dasanda.BE.api;

import dasanda.BE.domain.Member;
import dasanda.BE.dto.member.MemberCreationDto;
import dasanda.BE.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    // 회원 저장
    @PostMapping("/api/members/signup")
    public ResponseEntity<Object> saveMember(@RequestBody @Valid MemberCreationDto memberCreationDto){

        try{
            String password = passwordEncoder.encode(memberCreationDto.getPassword());

            Member member = Member.builder()
                    .email(memberCreationDto.getEmail())
                    .name(memberCreationDto.getName())
                    .password(password)
                    .city(memberCreationDto.getCity())
                    .street(memberCreationDto.getStreet())
                    .zipcode(memberCreationDto.getZipcode())
                    .build();

            Member saveMember = memberService.join(member);

            return ResponseEntity.ok().body(saveMember);

        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Email is already in use"));
        }
    }

    // 단일 회원 조회
    @GetMapping("/api/find/{memberId}")
    public ResponseEntity<Object> findMember(@PathVariable("memberId") Long memberId){

        Member findMember = memberService.findMemberById(memberId);
        return ResponseEntity.ok().body(findMember);
    }
}

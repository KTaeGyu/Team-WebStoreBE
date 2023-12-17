package dasanda.BE.api.member;

import dasanda.BE.domain.Member;
import dasanda.BE.dto.member.MemberCreationDto;
import dasanda.BE.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    // 회원 저장
    @PostMapping("/api/join")
    public ResponseEntity<Object> saveMember(@RequestBody @Valid MemberCreationDto memberCreationDto){

        try{
            String password = passwordEncoder.encode(memberCreationDto.getPassword());

            Member member = Member.builder()
                    .email(memberCreationDto.getEmail())
                    .nickname(memberCreationDto.getNickname())
                    .password(password)
                    .city(memberCreationDto.getCity())
                    .street(memberCreationDto.getStreet())
                    .zipcode(memberCreationDto.getZipcode())
                    .build();

            Member saveMember = memberService.join(member);

            MemberCreationDto creationDto = MemberCreationDto.builder()
                    .email(saveMember.getEmail())
                    .nickname(saveMember.getNickname())
                    .city(saveMember.getAddress().getCity())
                    .street(saveMember.getAddress().getStreet())
                    .zipcode(saveMember.getAddress().getZipcode())
                    .build();

            return ResponseEntity.ok().body(creationDto);

        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Email is already in use"));
        }
    }


//     단일 회원 조회
    @GetMapping("/api/find/{id}")
    public ResponseEntity<Object> findMember(@PathVariable("id") Long id){

        Member findMember = memberService.findById(id);
        MemberCreationDto creationDto = MemberCreationDto.builder()
                .email(findMember.getEmail())
                .nickname(findMember.getNickname())
                .city(findMember.getAddress().getCity())
                .street(findMember.getAddress().getStreet())
                .zipcode(findMember.getAddress().getZipcode())
                .build();
        return ResponseEntity.ok().body(creationDto);
    }
}

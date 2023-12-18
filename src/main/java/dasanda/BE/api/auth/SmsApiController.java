package dasanda.BE.api.auth;

import dasanda.BE.dto.member.MemberCreationDto;
import dasanda.BE.service.auth.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class SmsApiController {

    private final SmsService smsService;
    private String secretCode;

    // 문자 인증 번호 전송
    @PostMapping("/api/sms/send")
    public ResponseEntity<Object> sendSms(@RequestBody MemberCreationDto memberCreationDto){

        secretCode = generateVerificationCode();
        smsService.sendSms(memberCreationDto.getPhone(), secretCode);

        return ResponseEntity.ok(Map.of("message", "check your verification code"));
    }

    // 인증 번호 검증
    @PostMapping("/api/sms/verify")
    public ResponseEntity<Object> verifySms(@RequestHeader("Authorization") String verificationCode){

        System.out.println(secretCode);

        Boolean result = smsService.verifyCode(verificationCode, secretCode);

        if (result){
            return ResponseEntity.ok(Map.of("message", "code is valid"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "code is not valid"));
        }
    }

    // 인증 번호 생성기
    public static String generateVerificationCode() {
        // 6자리 숫자 생성
        int codeLength = 6;
        StringBuilder verificationCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < codeLength; i++) {
            verificationCode.append(random.nextInt(10));
        }

        return verificationCode.toString();
    }
}

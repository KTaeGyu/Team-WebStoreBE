package dasanda.BE.service.auth;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Table;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class SmsService {

    @Value("${sms.api.key}")
    private String smsKey;
    @Value("${sms.api.secret}")
    private String smsSecret;
    @Value("${sms.from}")
    private String from;
    private DefaultMessageService messageService;

    @PostConstruct
    private void init(){
        this.messageService = NurigoApp.INSTANCE.initialize(smsKey, smsSecret, "https://api.coolsms.co.kr");
    }

    // 단일 메시지 발송
    public void sendSms(String to, String verificationCode){

        Message message = new Message();
        message.setFrom(from);
        message.setTo(to);
        message.setText("[Dasanda] 아래의 인증번호를 입력해주세요\n" + verificationCode);

        this.messageService.sendOne(new SingleMessageSendingRequest(message));
    }

    public Boolean verifyCode(String verificationCode, String secretCode){

        if (Objects.equals(verificationCode, secretCode)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
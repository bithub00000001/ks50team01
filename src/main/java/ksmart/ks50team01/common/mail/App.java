package ksmart.ks50team01.common.mail;

import ksmart.ks50team01.common.mail.*;

import java.util.Base64;

public class App {
    public static void main(String[] args) {
        Context context = new Context("메일 발송 테스트");
        context.addLine("메일의 첫 번째 내용입니다.")
                .addLine("메일의 두 번째 내용입니다")
                .addLine("메일의 세 번째 내용입니다.");

        MailSender sender = new MailSender(context, "khm3943@naver.com", "khm3943@naver.com");

        sender.send("khm3943", "YXNkZmFzZGYK");
    }
}
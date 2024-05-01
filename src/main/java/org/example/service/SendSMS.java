package org.example.service;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.example.Alert;

import java.net.URI;
import java.math.BigDecimal;
import java.util.List;

public class SendSMS {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = System.getenv("TwilioSID");
    public static final String AUTH_TOKEN = System.getenv("TwilioToken");

    public Boolean sendSMS(List<Alert> alert) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String header = alert.get(0).getHeader();
        String subheader = alert.get(0).getSubheader();
        String body = alert.get(0).getBodyContent();
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(System.getenv("PersonalphoneNumber")),
                        new com.twilio.type.PhoneNumber(System.getenv("TwiliophoneNumber")),
                        header + "\n" + subheader + "\n" + body)
                .create();

        System.out.println(message.getSid());
        return true;
    }
}

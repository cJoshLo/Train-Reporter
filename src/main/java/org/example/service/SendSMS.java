package org.example.service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.example.Alert;

import java.util.Date;
import java.util.List;

public class SendSMS {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = System.getenv("TwilioSID");
    public static final String AUTH_TOKEN = System.getenv("TwilioToken");

    public void sendSMS(List<Alert> alert) {
        Date now = new Date();
        System.out.println("Sending message, current time: " + now);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String header = alert.get(0).getHeader();
        String subheader = alert.get(0).getSubheader();
        String body = alert.get(0).getBodyContent();
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(System.getenv("PersonalphoneNumber")),
                        new com.twilio.type.PhoneNumber(System.getenv("TwiliophoneNumber")),
                        header + "\n" + subheader + "\n" + body)
                .create();
    }
}

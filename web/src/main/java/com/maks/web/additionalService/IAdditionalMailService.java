package com.maks.web.additionalService;

public interface IAdditionalMailService {

    void sendRegistrationEmail(String recipientEmail);

    void sendResetEmail(String recipientEmail, String tempPass);

    void replyOnUserMessage(String recipientEmail, String replyBody);

}

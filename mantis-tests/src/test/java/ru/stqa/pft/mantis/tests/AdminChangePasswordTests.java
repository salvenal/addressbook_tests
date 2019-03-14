package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


public class AdminChangePasswordTests extends TestBase {


  @Test
  public void adminChangePassword() throws ClassNotFoundException, MessagingException, IOException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@localhost.localdomain", now);
    app.james().createUser(user, password);
    app.registration().start(user, email);
    //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    app.james().drainEmail(user, password);
    app.administratorHelper().adminLogInAndChangePass(user);
    List<MailMessage> mailMessageForReset = app.mail().waitForMail(2, 10000);
    String confirmationLinkForReset = findConfirmationLink(mailMessageForReset, email);
    app.registration().finish(confirmationLinkForReset, password);
    app.administratorHelper().LogIn(user, password);
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}

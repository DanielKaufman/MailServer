import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Crunchify.com
 *
 */

public class GmailServer {

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    public static String EMAIL = "email";
    public static String ORDER = "order";
    public static String ITEM = "item";
    public static String COUNT = "count";

    public static void run(Map<String, String> queryParams) throws AddressException, MessagingException {
        generateAndSendEmail(queryParams);
        System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
    }

    public static void generateAndSendEmail(Map<String, String> queryParams) throws AddressException, MessagingException {


//
//    try{
        String email = queryParams.get(EMAIL);
        queryParams.remove(EMAIL);
        String order = queryParams.get(ORDER);
        queryParams.remove(ORDER);
        List<String> items = new ArrayList<String>();
        List<String> counts = new ArrayList<String>();
        for (int i = 1; i<= queryParams.size()/2; i++){
            items.add(queryParams.get(ITEM+i));
            counts.add(queryParams.get(COUNT+i));
        }




//        }catch(Exception e){
//            throw e;
//        }

//Step1		
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

//Step2		
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("mz695444@hotmail.com"));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
        generateMailMessage.setSubject("Greetings from Crunchify..");
        String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

//Step3		
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password (XXXa.shah@gmail.com)
        transport.connect("smtp.gmail.com", "paulina@daap-ads.com", "vote4pau.");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
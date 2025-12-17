package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.models.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Properties;

@Service
public class MailService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    public MailService() {

    }

    //we will setup the from address here
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl(); //it's a interface and has a child class Impl. i.e., JavaMailSenderImpl
        //As our API is going to send email
        //So, we need to configure emailID details in JavaMailSender object
        javaMailSender.setHost("smtp.gmail.com"); //the platform Gmail is owned and belongs to Gmail.com. So, the host will be smtp.gmail.com
        javaMailSender.setPort(587); // genrally to send mail from our computer we require some port number so, the port number which we will use is 587
        //my app name : Grocery Shop App NearBuy
        javaMailSender.setUsername("prince18test@gmail.com");// We will be sending email so, by what email our spring application will send mail to the users
        javaMailSender.setPassword("pscynbaadkomexxn"); // Password of the email.... It is app password, not actual gmail id account password
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true"); // Our springboot api will connect gmail to send email via password so, mail.smtp.auth is true
        props.put("mail.smtp.starttls.enable", "true"); // This property we are setting for secure connection
        return javaMailSender; //finally, as setting and configured we need to send this javaMailSender object
    }

        //we will setup the to address here
    public void sendInvitationEmailToAdmin(User admin, User maint) throws MessagingException {
        //To send Email, we require  javamailsender object
        // MimeMessage -> We will create an object of this class and then we will set all our mail content in this class
        JavaMailSender javaMailSender = getJavaMailSender();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage); //with the help of MimeMessageHelper class we will set all  our mail content
        //whom i want to send the mail? So, we need to set to address
        mimeMessageHelper.setTo(admin.getEmail()); //setting to address
        mimeMessageHelper.setSubject("NearBuy Admin Invitation"); //setting the email subject

        //To set the values for variables which we have defined inside the html email template, we use context library
        Context context = new Context(); //thymeleaf context
        context.setVariable("adminName", admin.getName());
        context.setVariable("invitedByName", maint.getName());
        context.setVariable("invitedByEmail", maint.getEmail());
        context.setVariable("acceptLink", "https://www.google.com/");
        context.setVariable("rejectLink", "https://www.google.com/");

        //TemplateEngine templateEngine = getTemplateEngine(); //we get the object of TemplateEngine by Autowired it in the top
        //To load the HTML email template in the java code by populating all the values
        //To load it, we require the object of template Engine
        //in this html template -> "admin-invitation-template", populate all the  values which are in context object
        //Finally, we will get the htmltemplate in a string format
        String htmlTemplate = templateEngine.process("admin-invitation-template", context); //(nameOfThe HTMLTemplate, contextObjectWhichHasAllTheValuesOfVariable) //populating all the values

        mimeMessageHelper.setText(htmlTemplate, true); //setting email body or email message
        javaMailSender.send(mimeMessage); //this line will send the email
    }
}

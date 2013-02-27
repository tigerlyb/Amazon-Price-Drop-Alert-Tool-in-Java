import javax.mail.*; 
import javax.mail.internet.*; 
import java.util.*; 

public class Email
{ 
  public void SendEmail(String EmailAddress, String WebLink) throws Exception 
	{ 
		String host = "smtp.gmail.com"; 						// define mail server host name
		String from = "yourgmail@gmail.com"; 					// define sending email address
		String to = EmailAddress; 								// define receiving email address
		String username = "yourgmail@gmail.com"; 				// define sending email username
		String password = "yourpassword";							// define sending email password
		
		Properties props = new Properties(); 					// Get system properties 
		props.put("mail.smtp.host", host);						// Setup mail server host name
		props.put("mail.smtp.port", "587");						// Setup mail server port
		props.put("mail.smtp.starttls.enable","true");			// Setup mail server SSL to true
		props.put("mail.smtp.auth", "true"); 					// Setup mail server authentication to true
		
		Session session = Session.getDefaultInstance(props); 	// Get session 
		
		MimeMessage message = new MimeMessage(session); 		// Define message 
		message.setFrom(new InternetAddress(from)); 			// Define message sender 
		message.addRecipient(Message.RecipientType.TO, 
							 new InternetAddress(to)); 			// Define message receiver
		message.setSubject("Amazon Price Drop Alert"); 				// Define message subject
		message.setText("Hi, here is the big price drop of what you want: "
			+ WebLink); 										// Define message text
		
		// Send message 
		message.saveChanges(); 
		Transport transport = session.getTransport("smtp"); 
		transport.connect(host, username, password); 			// Connect to mail server
		transport.sendMessage(message, message.getAllRecipients()); 
		transport.close(); 
	} 
}

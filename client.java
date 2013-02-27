import java.io.*;
import java.net.*;
import javax.mail.*; 
import javax.mail.internet.*; 
import java.util.*; 

public class client 
{
  public static void main(String args[]) throws Exception
	{
        String inputLine = null;				// define the input stream
        String priceString = null;				// define for reading price from textfile
        String priceThresholdString = null;		// define the prce threshold
        String urlString = null;				// define the url of the weblink
        String emailAddress = null;				// define the email address
        String tag = "priceLarge";				// define the tag for catch the price in weblink

		double priceThreshold = 0;				// define the price threshold in double
		double price = 0;						// define the price in double
		
		URL url = null;							// define the url
		URLConnection connection;				// define the connection
		DataInputStream dis;					// define the intput data stream
		
		// a non-stop loop to continue monitor the prices of some items
		while (true){
			Scanner scan = new Scanner(new FileReader("Price Test.txt"));		// scan the text file
			while (scan.hasNext()){
				urlString = scan.next ();										// read the webling from the textfile
				System.out.println ("WebLink: " + urlString);					// print the weblink
				priceThresholdString = scan.next ();							// read the price from the textfile
				System.out.println ("Your price: $" + priceThresholdString);	// print the price
				emailAddress = scan.next ();									// read the email address from the textfile
				System.out.println ("Your email address: " + emailAddress);		// print the email address
				
		      	try {
		            url = new URL (urlString);									// define the URL
		            connection = url.openConnection ();							// connect the weblink
		            dis = new DataInputStream(connection.getInputStream ());	// input data stream
		            
		            while ((inputLine = dis.readLine ()) != null) {				// read the input data stream
		            	if (inputLine.contains (tag)){							// catch the price using tag
		            		priceString = inputLine.substring (31,36);			// get price of the item from weblink
		            		price = Double.parseDouble (priceString);			// convert string to double
		            		priceThreshold = Double.parseDouble (priceThresholdString);
		            		System.out.println ();
		            		System.out.println ("The price of this item is: $" + price);	// print the price of weblink
		            	}
		            }
		            dis.close();												// close the input data stream
		        } catch (MalformedURLException me) {
		            System.out.println ("MalformedURLException: " + me);		// print information if MalformedURLException
		        } catch (IOException ioe) {
		            System.out.println ("IOException: " + ioe);					// print information if IOException
		        }
		        
		        Email email = new Email ();
		        // Compare the price, if the price is lower than the price you expected, sent you an email 
				if (price <= priceThreshold){
					System.out.println ("******************");	
					System.out.println ("Price Drop!");
					System.out.println ("Send email to you.");
					System.out.println ("******************");
					System.out.println ();
					email.SendEmail (emailAddress, urlString);	// email the weblink to the email address
				} else {
					// if not, keep waiting.
					System.out.println ("The price is still higher than you expected, keep waiting.");
					System.out.println ("----------------------------------------------------------");
					System.out.println ();
				}
			}
			
			// 1 minute delay
			try	{
				Thread.sleep(60000); // do nothing for 60000 miliseconds (60 seconds)
			} catch(InterruptedException e)	{
				e.printStackTrace();
			}
		} 
	}

}

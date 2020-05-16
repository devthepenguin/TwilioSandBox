package com.twilio;

import static spark.Spark.get;
import static spark.Spark.post;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class Whatsapp {
	public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID"); 
	public static final String AUTH_TOKEN = System.getenv("TWILIO_ACCOUNT_SID");
	public static void main(String[] args) { 
		// TODO Auto-generated method stub
		get("/", (req, res) -> "Welcome to Twilio SandBox!");
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
		post("/whatsapp", (req, res) -> {
			
			String body = req.queryParams("WhatsappBody");
			String To = "whatsapp:"+req.queryParams("To");
			String From = "whatsapp:"+req.queryParams("From");

			Message whatsappMessage = Message.creator( new
					com.twilio.type.PhoneNumber(To), new
					com.twilio.type.PhoneNumber(From), body) .create();

			return whatsappMessage.getSid();

		});

	}
}

package com.max.demo;

import org.apache.log4j.Logger;

import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.MessageCracker;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.ClOrdID;
import quickfix.field.ClearingAccount;

public class MaxClientApp2 extends MessageCracker implements Application {
	
	private static final Logger log = Logger.getLogger(MaxClientApp.class);

	@Override
	public void fromAdmin(Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fromApp(Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		// TODO Auto-generated method stub
		log.info("------ fromApp---------");
		crack(msg, sessionID);
		System.out.println(msg.toString());
	}

	@Override
	public void onCreate(SessionID sessionID) {
		// TODO Auto-generated method stub
		
	}
	
	public void onMessage(quickfix.fix42.IndicationofInterest message, SessionID sessionID){
		log.info("------ onMessage IndicationofInterest---------");
		System.out.println(message.toString());
	}

	@Override
	public void onLogon(SessionID sessionID) {
		// TODO Auto-generated method stub
		log.info("------ onLogon-------");
		MaxBusiness.marketDataRequest(sessionID);
	}

	@Override
	public void onLogout(SessionID sessionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toAdmin(Message msg, SessionID sessionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toApp(Message msg, SessionID sessionID) throws DoNotSend {
		// TODO Auto-generated method stub
		log.info("------ toApp-----------");
		System.out.println(msg.toString());
	}

}

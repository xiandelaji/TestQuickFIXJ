package com.max.demo;

import org.apache.log4j.Logger;

import com.max.demo.MessagePrinter;

import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DataDictionary;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.IntField;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.ExecID;
import quickfix.field.LastPx;
import quickfix.field.MDReqID;
import quickfix.field.MarketDepth;
import quickfix.field.OrdType;
import quickfix.field.Side;
import quickfix.field.SubscriptionRequestType;
import quickfix.field.Symbol;

/**
 * BTCChina FIX Client
 * @author BTCChina
 */
public class MaxClientApp implements Application {
	
	private static final Logger log = Logger.getLogger(MaxClientApp.class);
	
	public void fromAdmin(quickfix.Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
		log.info("------ fromAdmin--------");
		//System.out.println(msg.toString());
	}

	public void fromApp(quickfix.Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		log.info("------ fromApp---------");
		System.out.println(msg.toString());
		//MaxBusiness.process(msg, sessionID);
		/*
		try {
			DataDictionary dd = new DataDictionary("FIX42.xml");
			MessagePrinter MP = new MessagePrinter();
			MP.print(dd, (quickfix.fix42.Message)msg);
		} catch (ConfigError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		
		System.out.println(msg.getField(new ExecID()).getValue());
		System.out.println(msg.getField(new Symbol()).getValue());
		System.out.println(msg.getField(new LastPx()).getValue());
		System.out.println(msg.getField(new Side()));
		*/
	}

	public void onCreate(SessionID sessionID) {
		log.info("------ onCreate Session-------");
	}
	
	public void onLogon(final SessionID sessionID) {
		log.info("------ onLogon-------");
		MaxBusiness.marketDataRequest(sessionID);
		
	}

	public void onLogout(SessionID sessionID) {
		log.info("------ onLogout -------");
	}

	public void toAdmin(quickfix.Message msg, SessionID sessionID) {
		log.info("------ toAdmin---------");
		//System.out.println(msg.toString());
	}

	public void toApp(quickfix.Message msg, SessionID sessionID) throws DoNotSend {
		log.info("------ toApp-----------");
		System.out.println(msg.toString());
	}
}

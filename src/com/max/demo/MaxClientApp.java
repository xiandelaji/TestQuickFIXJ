package com.max.demo;

import org.apache.log4j.Logger;

import com.btcchina.fix.util.MessagePrinter;

import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DataDictionary;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.OrdType;
import quickfix.field.Side;

/**
 * BTCChina FIX Client
 * @author BTCChina
 */
public class MaxClientApp implements Application {
	
	private static final Logger log = Logger.getLogger(MaxClientApp.class);
	
	public void fromAdmin(quickfix.Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
		log.info("------ fromAdmin--------");
	}

	public void fromApp(quickfix.Message msg, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		log.info("------ fromApp---------");
		try {
			DataDictionary dd = new DataDictionary("FIX42.xml");
			MessagePrinter MP = new MessagePrinter();
			MP.print(dd, (quickfix.fix42.Message)msg);
		} catch (ConfigError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}

	public void onCreate(SessionID sessionID) {
		log.info("------ onCreate Session-------");
	}
	
	public void onLogon(final SessionID sessionID) {
		log.info("------ onLogon-------");
		MaxTrading trade = new MaxTrading();
		Message message = trade.createNewOrder(Side.BUY, OrdType.LIMIT, 38.29, 200, "AA");
		Session.lookupSession(sessionID).send(message);
		
	}

	public void onLogout(SessionID sessionID) {
		log.info("------ onLogout -------");
	}

	public void toAdmin(quickfix.Message msg, SessionID sessionID) {
		log.info("------ toAdmin---------");
	}

	public void toApp(quickfix.Message msg, SessionID sessionID) throws DoNotSend {
		log.info("------ toApp-----------");
	}
}

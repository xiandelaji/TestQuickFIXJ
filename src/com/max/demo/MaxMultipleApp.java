package com.max.demo;

import java.text.DecimalFormat;
import java.util.Random;

import org.apache.log4j.Logger;

import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.ClOrdID;
import quickfix.field.ClearingAccount;
import quickfix.field.OrdType;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.fix42.ExecutionReport;
import quickfix.fix42.IndicationofInterest;
import quickfix.fix42.MarketDataIncrementalRefresh;
import quickfix.fix42.MarketDataRequest;
import quickfix.fix42.MessageCracker;

public class MaxMultipleApp extends MessageCracker implements Application {
	
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
		
	}

	@Override
	public void onCreate(SessionID sessionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(IndicationofInterest message, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		// TODO Auto-generated method stub
		log.info("------ onMessage IndicationofInterest---------");
		String symbol = message.getField(new Symbol()).getValue();
		double price;
		if("M".compareTo(symbol)>0){
			price = (new Double(new DecimalFormat("##.0000").format(new Random().nextFloat()*100)));
			MaxBusiness.trade(sessionID, Side.BUY, OrdType.LIMIT, price, Math.abs(new Random().nextInt())%1000, symbol);
		}else{
			price = (new Double(new DecimalFormat("##.0000").format(new Random().nextFloat()*100)));
			MaxBusiness.trade(sessionID, Side.SELL, OrdType.LIMIT, price, Math.abs(new Random().nextInt())%1000, symbol);
		}
	}
	
	/*
	@Override
	public void onMessage(MarketDataIncrementalRefresh message, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		// TODO Auto-generated method stub
		log.info("------ onMessage MarketDataIncrementalRefresh---------");
		System.out.println(message.toString());
	}

	@Override
	public void onMessage(MarketDataRequest message, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		// TODO Auto-generated method stub
		log.info("------ onMessage MarketDataRequest---------");
		System.out.println(message.toString());
	}
	*/
	
	

	@Override
	public void onLogon(SessionID sessionID) {
		// TODO Auto-generated method stub
		log.info("------ onLogon-------");
		MaxBusiness.marketDataRequest(sessionID);
	}

	@Override
	public void onMessage(ExecutionReport message, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		// TODO Auto-generated method stub
		log.info("------ onMessage ExecutionReport---------");
		System.out.println(message.toString());
		
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

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
import quickfix.Session;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.AvgPx;
import quickfix.field.ClOrdID;
import quickfix.field.ClearingAccount;
import quickfix.field.CumQty;
import quickfix.field.IOIShares;
import quickfix.field.OrdStatus;
import quickfix.field.OrdType;
import quickfix.field.OrderID;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.fix42.ExecutionReport;
import quickfix.fix42.IndicationofInterest;
import quickfix.fix42.MarketDataIncrementalRefresh;
import quickfix.fix42.MarketDataRequest;
import quickfix.fix42.MessageCracker;
import quickfix.fix42.NewOrderSingle;

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
		//log.info("------ fromApp---------");
		/*if("MAX".equals(sessionID.getSenderCompID())){
			crack(msg, sessionID);
		}*/
		SessionID s = new SessionID("FIX.4.2","MAX","FIXIMULATOR");
		crack(msg, s);
	}

	@Override
	public void onCreate(SessionID sessionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(IndicationofInterest message, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		// TODO Auto-generated method stub
		//log.info("------ onMessage IndicationofInterest---------");
		String symbol = message.getField(new Symbol()).getValue();
		double pric = message.getField(new Price()).getValue();
		String quantity = message.getField(new IOIShares()).getValue();
		char side = message.getField(new Side()).getValue();
		System.out.println(">>>>"+sessionID.getSenderCompID()+">>>>IndicationofInterest");
		System.out.println("symbol:"+symbol+", price="+pric+", quantity="+quantity+", side="+side);
		double price;
		if("M".compareTo(symbol)>0){
			price = (new Double(new DecimalFormat("##.0000").format(new Random().nextFloat()*100)));
			MaxBusiness.trade(sessionID, Side.BUY, OrdType.LIMIT, price, Math.abs(new Random().nextInt())%10, symbol);
		}else{
			price = (new Double(new DecimalFormat("##.0000").format(new Random().nextFloat()*100)));
			MaxBusiness.trade(sessionID, Side.SELL, OrdType.LIMIT, price, Math.abs(new Random().nextInt())%10, symbol);
		}
	}
	
	

	@Override
	public void onMessage(MarketDataIncrementalRefresh message, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		// TODO Auto-generated method stub
		System.out.println(">>>>"+sessionID.getSenderCompID()+">>>>MarketDataIncrementalRefresh");
		//log.info("------onMessage MarketDataIncrementalRefresh-------");
		System.out.println(message.toString());
	}

	@Override
	public void onLogon(SessionID sessionID) {
		// TODO Auto-generated method stub
		System.out.println(">>>>"+sessionID.getSenderCompID()+">>>>onLogon");
		/*
		if("MAX".equals(sessionID.getSenderCompID())){
			//SessionID s = new SessionID("FIX.4.2","MAX","FIXIMULATOR");
			MaxBusiness.marketDataRequest(sessionID);
		}
		*/
		//MaxBusiness.marketDataRequest(sessionID);
		
		//SessionID s = new SessionID("FIX.4.2","MAX","FIXIMULATOR");
		//MaxBusiness.marketDataRequest(s);
		
	}

	@Override
	public void onMessage(NewOrderSingle message, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		// TODO Auto-generated method stub
		System.out.println(">>>>"+sessionID.getSenderCompID()+">>>>NewOrderSingle");
		System.out.println(message.toString());
	}

	
	@Override
	public void onMessage(ExecutionReport message, SessionID sessionID)
			throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
		// TODO Auto-generated method stub
		System.out.println(">>>>"+sessionID.getSenderCompID()+">>>>ExecutionReport");
		char ordStatus = message.getField(new OrdStatus()).getValue();
		String symbol = message.getField(new Symbol()).getValue();
		char side = message.getField(new Side()).getValue();
		//double orderQty = message.getField(new OrderQty()).getValue();
		//double price = message.getField(new Price()).getValue();
		double avgPx = message.getField(new AvgPx()).getValue();
		double cumQty = message.getField(new CumQty()).getValue();
		System.out.println("symbol="+symbol+", ordStatus="+ordStatus+", side="+side+", avgPx="+avgPx+", cumQty="+cumQty);
		//System.out.println(message.toString());
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
		System.out.println(">>>>"+sessionID.getSenderCompID()+">>>>toApp");
		System.out.println(msg.toString());
	}

}

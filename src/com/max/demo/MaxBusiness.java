package com.max.demo;

import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.field.MDReqID;
import quickfix.field.MarketDepth;
import quickfix.field.OrdType;
import quickfix.field.Side;
import quickfix.field.SubscriptionRequestType;
import quickfix.field.Symbol;

public class MaxBusiness {
	
	public static void marketDataRequest(SessionID sessionID){
        //quickfix.fix42.MarketDataRequest tickerRequest = new quickfix.fix42.MarketDataRequest();
        quickfix.fix42.MarketDataRequest tickerRequest = new quickfix.fix42.MarketDataRequest();
		
		quickfix.fix42.MarketDataRequest.NoRelatedSym noRelatedSym = new quickfix.fix42.MarketDataRequest.NoRelatedSym();
		noRelatedSym.set(new Symbol("AA"));
		tickerRequest.addGroup(noRelatedSym);
				
		tickerRequest.set(new MDReqID("123"));		
		tickerRequest.set(new SubscriptionRequestType('1'));
		tickerRequest.set(new MarketDepth(5));
		
		/*MaxCommon.addMDType(tickerRequest, '0');
		MaxCommon.addMDType(tickerRequest, '2');
		MaxCommon.addMDType(tickerRequest, '3');
		MaxCommon.addMDType(tickerRequest, '4');
		MaxCommon.addMDType(tickerRequest, '5');*/
		//MaxCommon.addMDType(tickerRequest, '6');
		/*MaxCommon.addMDType(tickerRequest, '7');
		MaxCommon.addMDType(tickerRequest, '8');
		MaxCommon.addMDType(tickerRequest, '9');
		MaxCommon.addMDType(tickerRequest, 'A');
		MaxCommon.addMDType(tickerRequest, 'B');
		MaxCommon.addMDType(tickerRequest, 'C');*/
		//MaxCommon.addMDType(tickerRequest, 'X');
		
		Session.lookupSession(sessionID).send(tickerRequest);
	}
	
	public static void trade(SessionID sessionID, char side, char ordertype, double price, double amount, String symbol){
		MaxTrading trade = new MaxTrading();
		Message message = trade.createNewOrder(side, ordertype, price, amount, symbol);
		Session.lookupSession(sessionID).send(message);
	}

}

package com.max.demo;

import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TransactTime;

public class MaxTrading {
	
	public quickfix.fix42.NewOrderSingle createNewOrder(char side, char ordertype, double price, double amount, String symbol){
		quickfix.fix42.NewOrderSingle newOrderSingle = new quickfix.fix42.NewOrderSingle();
		newOrderSingle.set(new ClOrdID("5"));
		newOrderSingle.set(new HandlInst('1'));
		newOrderSingle.set(new Symbol(symbol));
		newOrderSingle.set(new Side(side));
		newOrderSingle.set(new TransactTime());
		newOrderSingle.set(new OrdType(ordertype));
        newOrderSingle.set(new OrderQty(amount));
        newOrderSingle.set(new Price(price));
		
		return newOrderSingle;
	}
	
	

}

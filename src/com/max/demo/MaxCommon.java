package com.max.demo;

import quickfix.field.MDEntryType;

public class MaxCommon {
	
	public static void addMDType(quickfix.fix42.MarketDataRequest tickerRequest, char type) {
		quickfix.fix42.MarketDataRequest.NoMDEntryTypes g0 = new quickfix.fix42.MarketDataRequest.NoMDEntryTypes();
		g0.set(new MDEntryType(type));
	    tickerRequest.addGroup(g0);
	}

}

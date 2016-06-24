package com.max.demo;

import java.io.IOException;
import java.io.InputStream;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.DoNotSend;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.SessionNotFound;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;

public class MaxClient {

	public static void main(String args[]) throws ConfigError, DoNotSend, IOException, SessionNotFound, InterruptedException{
		//MaxClientApp app = new MaxClientApp();
		MaxClientApp2 app = new MaxClientApp2();
	    InputStream inputStream = MaxClient.class.getResourceAsStream("/client-exec.properties");
	    //InputStream inputStream = MaxClient.class.getResourceAsStream("/client-fiximulator.properties");
	    SessionSettings settings = new SessionSettings(inputStream);
	    MessageStoreFactory storeFactory = new FileStoreFactory(settings);
	    LogFactory logFactory = new FileLogFactory(settings);
	    MessageFactory messageFactory = new DefaultMessageFactory();
	    Initiator initiator = new SocketInitiator(app, storeFactory, settings, logFactory, messageFactory);
	    initiator.block();
	}
}

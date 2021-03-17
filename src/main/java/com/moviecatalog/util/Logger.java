package com.moviecatalog.util;

import org.slf4j.LoggerFactory;

public class Logger {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class);
	
	public static void info(String msg) {
		log.info(msg);
	}

}

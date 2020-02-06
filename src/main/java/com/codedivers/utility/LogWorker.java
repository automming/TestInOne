package com.codedivers.utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogWorker {

	Logger logger;

	public LogWorker(String testcase) {
		logger = Logger.getLogger(testcase);
		// configure log4j properties file
		PropertyConfigurator.configure("./config/log4j.properties");
	}

	public void info(String message) {
		logger.info(message);
	}

	public void warn(String message) {
		logger.warn(message);
	}

	public void error(String message) {
		logger.error(message);
	}

	public void fatal(String message) {
		logger.fatal(message);
	}

	public void trace(String message) {
		logger.trace(message);
	}

	public void debug(String message) {
		logger.debug(message);
	}

}

package com.pc.app.ui.context;

import com.pc.db.MDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener
{

	static Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

	public ApplicationListener() {
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("**************** contextDestroyed ****************");
		MDB.stopDB();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("**************** contextInitialized ****************");
		MDB.startDB();
	}

}

package com.chinasofti.ark.bdsdp.example;

import com.chinasofti.ark.bdsdp.example.components.server.ExampleServer;

import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class.
 */
public class AppMain {

  private static final Logger logger = LoggerFactory.getLogger(AppMain.class);

  /**
   * Main method.
   */
  public static void main(String[] args) throws Exception {
    String packageName = AppMain.class.getPackage().getName();
    Server server = new ExampleServer(packageName).build();

    // add shutdown hook
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        server.stop();
      } catch (Exception e) {
        logger.error("Stopping server.", e);
      }
    }));

    // start a new instance of jetty http server
    server.start();

    logger.info("Application started.");
    logger.info("Try out {}", server.getURI());
    logger.info("Stop the application using CTRL+C.");

    Thread.currentThread().join();
  }
}



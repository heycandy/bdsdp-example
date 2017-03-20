package com.chinasofti.ark.bdsdp.example.components.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

import jersey.repackaged.com.google.common.base.Joiner;

/**
 * Created by White on 2017/3/7.
 */
public class ExampleResponseFilter implements ClientResponseFilter {

  private final Logger logger = LoggerFactory.getLogger(ExampleResponseFilter.class);

  @Override
  public void filter(ClientRequestContext clientRequestContext,
                     ClientResponseContext clientResponseContext) throws IOException {
    logger.debug("=== CLIENT INBOUND ===");
    logger
        .debug("{} {}", clientRequestContext.getMethod(), clientRequestContext.getUri().getPath());

    Map<String, List<String>> headers = clientResponseContext.getHeaders();
    Set<String> keySet = headers.keySet();
    for (String key : keySet) {
      List<String> parts = headers.get(key);
      String value = Joiner.on(",").join(parts);
      logger.debug("{}: {}", key, value);
    }
  }
}

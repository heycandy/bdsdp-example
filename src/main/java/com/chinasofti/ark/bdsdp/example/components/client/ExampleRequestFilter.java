package com.chinasofti.ark.bdsdp.example.components.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import jersey.repackaged.com.google.common.base.Joiner;

/**
 * Created by White on 2017/3/7.
 */
public class ExampleRequestFilter implements ClientRequestFilter {

  private final Logger logger = LoggerFactory.getLogger(ExampleRequestFilter.class);

  @Override
  public void filter(ClientRequestContext clientRequestContext) throws IOException {
    logger.debug("=== CLIENT OUTBOUND ===");
    logger
        .debug("{} {}", clientRequestContext.getMethod(), clientRequestContext.getUri().getPath());

    Map<String, List<Object>> headers = clientRequestContext.getHeaders();
    Set<String> keySet = headers.keySet();
    for (String key : keySet) {
      List<Object> parts = headers.get(key);
      String value = Joiner.on(",").join(parts);
      logger.debug("{}: {}", key, value);
    }
  }
}

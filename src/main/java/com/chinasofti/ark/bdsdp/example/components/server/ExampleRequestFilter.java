package com.chinasofti.ark.bdsdp.example.components.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import jersey.repackaged.com.google.common.base.Joiner;

/**
 * Created by White on 2017/2/25.
 */
public class ExampleRequestFilter implements ContainerRequestFilter {

  private final Logger logger = LoggerFactory.getLogger(ExampleRequestFilter.class);

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    logger.debug("=== SERVER INBOUND ===");
    logger.debug("{} {}", containerRequestContext.getMethod(),
                 containerRequestContext.getUriInfo().getPath());

    Map<String, List<String>> headers = containerRequestContext.getHeaders();
    Set<String> keySet = headers.keySet();
    for (String key : keySet) {
      List<String> parts = headers.get(key);
      String value = Joiner.on(",").join(parts);
      logger.debug("{}: {}", key, value);
    }

  }
}

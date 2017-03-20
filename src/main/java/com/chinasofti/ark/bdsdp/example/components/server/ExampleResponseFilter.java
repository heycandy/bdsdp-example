package com.chinasofti.ark.bdsdp.example.components.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import jersey.repackaged.com.google.common.base.Joiner;

/**
 * Created by White on 2017/2/25.
 */
public class ExampleResponseFilter implements ContainerResponseFilter {

  private final Logger logger = LoggerFactory.getLogger(ExampleResponseFilter.class);

  @Override
  public void filter(ContainerRequestContext containerRequestContext,
                     ContainerResponseContext containerResponseContext) throws IOException {
    logger.debug("=== SERVER OUTBOUND ===");
    logger.debug("{} {}", containerRequestContext.getMethod(),
                 containerRequestContext.getUriInfo().getPath());

    Map<String, List<Object>> headers = containerResponseContext.getHeaders();
    Set<String> keySet = headers.keySet();
    for (String key : keySet) {
      List<Object> parts = headers.get(key);
      String value = Joiner.on(",").join(parts);
      logger.debug("{}: {}", key, value);
    }
  }
}

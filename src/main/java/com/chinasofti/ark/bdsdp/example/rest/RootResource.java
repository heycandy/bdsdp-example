package com.chinasofti.ark.bdsdp.example.rest;

import com.chinasofti.ark.bdsdp.example.components.ExampleConfiguration;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by White on 2017/3/21.
 */
@Path("/")
public class RootResource {

  @Inject
  private ExampleConfiguration configuration;

  @GET
  @Produces("application/json")
  public Object get() {
    return configuration.get("base");
  }
}

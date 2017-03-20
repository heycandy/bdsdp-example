package com.chinasofti.ark.bdsdp.example.rest;

import com.chinasofti.ark.bdsdp.example.core.Example;
import com.chinasofti.ark.bdsdp.example.core.bean.ExampleBean;
import com.chinasofti.ark.bdsdp.example.rest.bean.ResponseBody;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Root resource (exposed at "example" path)
 */
@Path("example")
public class ExampleResource {

  @Inject
  private Example example;

  /**
   * Method handling HTTP GET requests. The returned object will be sent to the client as
   * "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Produces("text/plain")
  public String getIt() {
    return "Get it!";
  }

  @POST
  @Produces("application/json")
  public ResponseBody postIt(ExampleBean bean) {
    ExampleBean result = example.postIt(bean);
    return new ResponseBody<>(0, "Post it!", result);
  }
}

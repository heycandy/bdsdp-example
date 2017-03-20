package com.chinasofti.ark.bdsdp.example.external;

import com.chinasofti.ark.bdsdp.example.components.client.ExampleClient;
import com.chinasofti.ark.bdsdp.example.external.bean.ExampleBean;
import com.chinasofti.ark.bdsdp.example.external.bean.ExampleResponseBody;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by White on 2017/3/7.
 */
@Service
public class ExampleService {

  @Inject
  private ExampleClient client;

  public String getIt() {
    Response response =
        client.target("web")
            .path("/service/v1/demo")
            .request()
            .get();

    return response.readEntity(String.class);
  }

  public ExampleBean postIt(ExampleBean bean) {
    Response response =
        client.target("web")
            .path("/service/v1/demo")
            .request()
            .post(Entity.entity(bean, MediaType.APPLICATION_JSON_TYPE));

    ExampleResponseBody entity = response.readEntity(ExampleResponseBody.class);

    return entity.getResult();
  }
}

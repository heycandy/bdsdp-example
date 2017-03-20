package com.chinasofti.ark.bdsdp.example.components.server;

import com.chinasofti.ark.bdsdp.example.components.ExampleBinder;
import com.chinasofti.ark.bdsdp.example.components.ExampleConfiguration;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.annotations.Service;

import java.net.URI;

/**
 * Created by White on 2017/3/20.
 */
@Service
public class ExampleServer extends ResourceConfig {

  private final ExampleBinder exampleBinder;
  private final ExampleConfiguration exampleConf;

  public ExampleServer(String packageName) throws Exception {
    exampleBinder = new ExampleBinder(packageName);
    exampleConf = new ExampleConfiguration();

    super.register(exampleBinder)
        .register(ExampleRequestFilter.class)
        .register(ExampleResponseFilter.class)
        .register(JacksonFeature.class)
        .packages(packageName);

  }

  /**
   * Starts Jerry HTTP server exposing JAX-RS resources defined in this application.
   *
   * @return Jerry HTTP server.
   */
  public Server build() {
    String baseEntry = exampleConf.getString("base.entry");
    String entryUrl = exampleConf.getString(baseEntry + ".url");

    return JettyHttpContainerFactory.createServer(URI.create(entryUrl), this);
  }
}

package com.chinasofti.ark.bdsdp.example.components.client;

import com.chinasofti.ark.bdsdp.example.components.ExampleConfiguration;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.jvnet.hk2.annotations.Service;

import java.net.URI;
import java.util.Map;

import javax.inject.Inject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by White on 2017/3/9.
 */
@Service
public class ExampleClient implements Client {

  private final Client client;

  @Inject
  private ExampleConfiguration conf;

  public ExampleClient() {
    this.client = ClientBuilder.newBuilder()
        .register(ExampleRequestFilter.class)
        .register(ExampleResponseFilter.class)
        .register(JacksonFeature.class)
        .build();
  }

  @Override
  public void close() {
    this.client.close();
  }

  @Override
  public WebTarget target(String serviceName) {
    String baseEntry = conf.getString("base.entry");
    String
        discoveryServiceModeNamespace =
        String.format("%s.discovery.%s.mode", baseEntry, serviceName);
    String
        discoveryServiceUrlNamespace =
        String.format("%s.discovery.%s.url", baseEntry, serviceName);

    String discoveryServiceMode = conf.getString(discoveryServiceModeNamespace);
    if (discoveryServiceMode.equals("simple")) {
      String discoveryServiceUrl = conf.getString(discoveryServiceUrlNamespace);
      return this.client.target(discoveryServiceUrl);
    } else {
      throw new RuntimeException(
          "Discovery service mode '" + discoveryServiceMode + "' is not supported.");
    }
  }

  @Override
  public WebTarget target(URI uri) {
    return this.client.target(uri);
  }

  @Override
  public WebTarget target(UriBuilder uriBuilder) {
    return this.client.target(uriBuilder);
  }

  @Override
  public WebTarget target(Link link) {
    return this.client.target(link);
  }

  @Override
  public Invocation.Builder invocation(Link link) {
    return this.client.invocation(link);
  }

  @Override
  public SSLContext getSslContext() {
    return this.client.getSslContext();
  }

  @Override
  public HostnameVerifier getHostnameVerifier() {
    return this.client.getHostnameVerifier();
  }

  @Override
  public Configuration getConfiguration() {
    return this.client.getConfiguration();
  }

  @Override
  public Client property(String s, Object o) {
    return this.client.property(s, o);
  }

  @Override
  public Client register(Class<?> aClass) {
    return this.client.register(aClass);
  }

  @Override
  public Client register(Class<?> aClass, int i) {
    return this.client.register(aClass, i);
  }

  @Override
  public Client register(Class<?> aClass, Class<?>... classes) {
    return this.client.register(aClass, classes);
  }

  @Override
  public Client register(Class<?> aClass, Map<Class<?>, Integer> map) {
    return this.client.register(aClass, map);
  }

  @Override
  public Client register(Object o) {
    return this.client.register(o);
  }

  @Override
  public Client register(Object o, int i) {
    return this.client.register(o, i);
  }

  @Override
  public Client register(Object o, Class<?>... classes) {
    return this.client.register(o, classes);
  }

  @Override
  public Client register(Object o, Map<Class<?>, Integer> map) {
    return this.client.register(o, map);
  }
}

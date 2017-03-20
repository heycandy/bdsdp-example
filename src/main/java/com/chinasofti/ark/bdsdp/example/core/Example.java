package com.chinasofti.ark.bdsdp.example.core;

import com.chinasofti.ark.bdsdp.example.core.bean.ExampleBean;
import com.chinasofti.ark.bdsdp.example.external.ExampleService;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by White on 2017/3/8.
 */
@Service
public class Example {

  private final Logger logger = LoggerFactory.getLogger(Example.class);

  @Inject
  private ExampleService service;

  public ExampleBean postIt(ExampleBean bean) {
    bean.setId(UUID.randomUUID().toString());
    bean.setTime(new Date());

    logger.info(service.getIt());

    return bean;
  }
}

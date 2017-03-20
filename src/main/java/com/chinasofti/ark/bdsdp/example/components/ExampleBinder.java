package com.chinasofti.ark.bdsdp.example.components;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.Service;
import org.reflections.Reflections;

import java.util.Set;

/**
 * Created by White on 2017/3/8.
 */
public class ExampleBinder extends AbstractBinder {

  final Object[] params;

  public ExampleBinder() {
    this.params = new Object[]{};
  }

  public ExampleBinder(Object... params) {
    this.params = params;
  }

  @Override
  protected void configure() {
    Reflections reflections = new Reflections(params);

    Set<Class<?>> contractClasses = reflections.getTypesAnnotatedWith(Contract.class);
    Set<Class<?>> serviceClasses = reflections.getTypesAnnotatedWith(Service.class);

    for (Class<?> serviceClass : serviceClasses) {
      Class<?> toCls = serviceClass;
      for (Class<?> contractClass : contractClasses) {
        if (serviceClass.isAssignableFrom(contractClass) && contractClass.isInterface()) {
          toCls = contractClass;
          break;
        }
      }
      bind(serviceClass).to(toCls);
    }

  }
}

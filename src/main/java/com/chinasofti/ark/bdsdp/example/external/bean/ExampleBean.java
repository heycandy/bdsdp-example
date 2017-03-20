package com.chinasofti.ark.bdsdp.example.external.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by White on 2017/3/7.
 */
public class ExampleBean {

  private String id;
  private String name;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date time;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "DemoBean{" +
           "id='" + id + '\'' +
           ", name='" + name + '\'' +
           ", time=" + time +
           '}';
  }
}

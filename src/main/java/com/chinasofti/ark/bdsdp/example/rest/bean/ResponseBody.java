package com.chinasofti.ark.bdsdp.example.rest.bean;

/**
 * Created by White on 2017/3/7.
 */
public class ResponseBody<T> {

  private int code;
  private String message;
  private T result;

  public ResponseBody() {
  }

  public ResponseBody(int code, String message, T result) {
    this.code = code;
    this.message = message;
    this.result = result;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}

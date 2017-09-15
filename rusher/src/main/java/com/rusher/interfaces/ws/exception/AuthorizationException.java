package com.rusher.interfaces.ws.exception;

/**
 * Author: Liam
 * Date: 2017/9/14
 */
public class AuthorizationException extends RuntimeException {
  private String code;

  public AuthorizationException(String message) {
    this(null, message);
  }

  public AuthorizationException(String code, String message) {
    super(message);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}

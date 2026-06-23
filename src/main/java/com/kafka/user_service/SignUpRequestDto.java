package com.kafka.user_service;

public class SignUpRequestDto {

  private String email;

  private String name;

  private String password;

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }
}

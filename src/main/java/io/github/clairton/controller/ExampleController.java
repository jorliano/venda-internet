package io.github.clairton.controller;

import javax.annotation.ManagedBean;
import javax.inject.Named;

@Named
@ManagedBean
public class ExampleController {
  
  private String username;
  private String password;
  
  public void login(){
    System.out.println("User = " + username);
    System.out.println("Password = " + password);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  
}

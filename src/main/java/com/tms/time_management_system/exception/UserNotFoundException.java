package com.tms.time_management_system.exception;

public class UserNotFoundException extends RuntimeException
{
  public UserNotFoundException(String message)
  {
    super(message);
  }
}

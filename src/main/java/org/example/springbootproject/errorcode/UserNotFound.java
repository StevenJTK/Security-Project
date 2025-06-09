package org.example.springbootproject.errorcode;

public class UserNotFound extends RuntimeException {

  public UserNotFound(Long id) {
      super("User with id " + id + " not found");
  }



    /*  private String errorCode;
    private String errorMessage;
    private int status;


    public UserNotFound(String errorCode, String errorMessage, int status) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    } */
}

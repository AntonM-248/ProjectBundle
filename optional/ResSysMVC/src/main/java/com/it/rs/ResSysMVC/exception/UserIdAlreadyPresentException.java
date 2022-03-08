package com.it.rs.ResSysMVC.exception;

public class UserIdAlreadyPresentException extends BootException{
    private static final long serialVersionUID = 1L;

    public UserIdAlreadyPresentException(String message) {
        super(message);
    }
}

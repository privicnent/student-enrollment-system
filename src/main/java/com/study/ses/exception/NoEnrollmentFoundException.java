package com.study.ses.exception;

public class NoEnrollmentFoundException extends RuntimeException {
    public NoEnrollmentFoundException(){
        super("No Enrollment Found");
    }
}

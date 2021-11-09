package com.study.ses.exception;

public class CourseIdNotFoundException extends RuntimeException {
    public CourseIdNotFoundException() {
        super("Course ID Not Found");
    }
}

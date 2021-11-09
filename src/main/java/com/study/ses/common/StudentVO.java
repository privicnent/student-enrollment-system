package com.study.ses.common;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class StudentVO {
    @NotBlank(message = "Email ID is mandatory")
    @Email private String emailId;
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    private String lastName;
    @NotBlank(message = "Date of Birth is mandatory ")
    private LocalDate dob;
    @NotBlank(message = "Location is mandatory")
    private String location;
    @NotBlank(message = "courseID is mandatory")
    private long courseId;

    @Override public String toString() {
        return "" +
                "emailId=" + emailId + '\n' +
                "firstName=" + firstName + '\n' +
                "lastName=" + lastName + '\n' +
                "dob=" + dob +
                "location=" + location + '\n' +
                "courseId=" + courseId;
    }
}

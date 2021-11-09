package com.study.ses.enrollment;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "STUDENT")
@Data
public class StudentEntity {
    @Id
    @Column(name = "EMAIL_ID")
    @NotNull @Email() private String emailId;
    @Column(name = "FIRST_NAME")
    @NotNull private String firstName;
    @Column(name = "LAST_NAME")
    @NotNull private String lastName;
    @Column(name = "DOB")
    @NotNull(message = "{user.birthday.notNull}")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;
    @Column(name = "LOCATION")
    @NotNull private String location;
    @Column(name = "COURSE_ID")
    @NotNull private long courseId;
}

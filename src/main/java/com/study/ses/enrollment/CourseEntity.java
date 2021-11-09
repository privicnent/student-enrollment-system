package com.study.ses.enrollment;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE")
@Data
public class CourseEntity {
    @Id
    @Column(name = "COURSE_ID")
    private Long courseId;
    @Column(name = "COURSE_NAME")
    private String courseName;
}

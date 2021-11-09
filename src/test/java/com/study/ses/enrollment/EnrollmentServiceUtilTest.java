package com.study.ses.enrollment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentServiceUtilTest {

    private EnrollmentServiceUtil enrollmentServiceUtil;

    @BeforeEach
    void setUp() {
        enrollmentServiceUtil = new EnrollmentServiceUtil();
    }

    @Test
    void beautifyCourseEntityList() {

        /*Arrange*/

        List<CourseEntity> courseEntities = new ArrayList<>();

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseId(Long.parseLong("1"));
        courseEntity.setCourseName("Java");
        courseEntities.add(courseEntity);

        courseEntity = new CourseEntity();
        courseEntity.setCourseId(Long.parseLong("2"));
        courseEntity.setCourseName("Spring");
        courseEntities.add(courseEntity);

        /*Act*/
        String courseList = enrollmentServiceUtil.beautifyCourseEntityList(courseEntities);

        /*Assert*/

        assertEquals("1 - Java\n2 - Spring",courseList);
    }
}
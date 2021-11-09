package com.study.ses.enrollment;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnrollmentServiceUtil {

    public String beautifyCourseEntityList(List<CourseEntity> courseEntities) {
        List<String> list = transformCourseEntitysToStringArray(courseEntities);
        return String.join("\n", list);
    }

    private List<String> transformCourseEntitysToStringArray(List<CourseEntity> courseEntities) {
        return courseEntities
                .stream()
                .map(this::transformCourseEntitytoString)
                .collect(Collectors.toList());
    }

    private String transformCourseEntitytoString(CourseEntity courseEntity) {
        return String.format("%s - %s", courseEntity.getCourseId(), courseEntity.getCourseName());
    }
}

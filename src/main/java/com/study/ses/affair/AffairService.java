package com.study.ses.affair;

import com.study.ses.common.StudentVO;
import com.study.ses.enrollment.EnrollmentService;
import com.study.ses.exception.CourseIdNotFoundException;
import com.study.ses.exception.NoEnrollmentFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AffairService {

    private final EnrollmentService enrollmentService;
    private final AffairServiceUtil affairServiceUtil;

    public void process(int processID) {
        ProcessId processId = ProcessId.determineProcessId(processID);

        if (processId == null) {
            System.out.println("Enter the valid option");
            return;
        }

        switch (processId) {
        case COURSE_LIST:
            displayAndCreateEnrollment();
            break;
        case VIEW:
            viewEnrollment();
            break;
        case UPDATE:
            updateEnrollment();
            break;
        case DELETE:
            deleteEnrollment();
            break;
        case EXIT:
            System.exit(0);
            break;
        default:
            System.exit(0);
            break;
        }
    }

    private void displayAndCreateEnrollment() {
        String courseList = enrollmentService.courseList();
        display(courseList,"Available Courses :");
        StudentVO studentVO = affairServiceUtil.createEnrollment();
        try {
            enrollmentService.createEnrollment(studentVO);
            System.out.println("Your enrollment is confirmed.");
        } catch (CourseIdNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void viewEnrollment() {
        try {
            StudentVO studentVO = enrollmentService.viewEnrollment(getEmailId());
            display(studentVO.toString(),"Enrollment Details :");
        } catch (NoEnrollmentFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private StudentVO updateEnrollment() {
        StudentVO studentVO = null;
        try {
            studentVO = enrollmentService.viewEnrollment(getEmailId());
            display(studentVO.toString(),"Enrollment Details :");
            display(enrollmentService.courseList(),"Available Courses :");
            studentVO.setCourseId(getCourseId());
            enrollmentService.updateEnrollment(studentVO);
            System.out.println("Your enrollment is updated.");
        } catch (CourseIdNotFoundException | NoEnrollmentFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return studentVO;
    }

    private void deleteEnrollment() {
        try {
            enrollmentService.deleteEnrollment(getEmailId());
            System.out.println("Your enrollment is removed.");
        } catch (NoEnrollmentFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String getEmailId() {
        return affairServiceUtil.getEmailId();
    }

    private Long getCourseId() {
        return affairServiceUtil.getCourseId();
    }

    private void display(String display,String screenName) {
        System.out.println();
        System.out.println(screenName);
        System.out.println();
        System.out.println(display);
        System.out.println();
    }

    public int getProcessID() {
        return affairServiceUtil.getProcessID();
    }
}

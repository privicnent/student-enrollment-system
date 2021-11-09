package com.study.ses.affair;

import com.study.ses.common.StudentVO;
import com.study.ses.enrollment.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AffairServiceTest {

    private AffairService affairService;
    private EnrollmentService enrollmentService;
    private AffairServiceUtil affairServiceUtil;

    @BeforeEach
    void setUp() {
        enrollmentService = mock(EnrollmentService.class);
        affairServiceUtil = mock(AffairServiceUtil.class);
        affairService = new AffairService(enrollmentService, affairServiceUtil);
    }

    @Test
    void processCourseList() {
        /*Arrange*/

        int courseListProcessID = 1;

        String courseList = "courseList";
        StudentVO studentVO = new StudentVO();
        studentVO.setCourseId(1);
        studentVO.setDob(LocalDate.now());
        studentVO.setEmailId("Test@Test.com");
        studentVO.setFirstName("Test");
        studentVO.setLastName("Test");
        studentVO.setLocation("Test");

        when(enrollmentService.courseList()).thenReturn(courseList);
        when(affairServiceUtil.createEnrollment()).thenReturn(studentVO);
        doNothing().when(enrollmentService).createEnrollment(studentVO);

        /*Act*/

        affairService.process(courseListProcessID);

        /*Assert*/

        verify(enrollmentService, times(1)).courseList();
        verify(affairServiceUtil, times(1)).createEnrollment();
        verify(enrollmentService, times(1)).createEnrollment(studentVO);

    }

    @Test
    void processViewEnrollment() {
        /*Arrange*/
        int processID = 2;

        String emailID = "Test@Test.com";

        StudentVO studentVO = new StudentVO();
        studentVO.setCourseId(1);
        studentVO.setDob(LocalDate.now());
        studentVO.setEmailId("Test@Test.com");
        studentVO.setFirstName("Test");
        studentVO.setLastName("Test");
        studentVO.setLocation("Test");

        when(affairServiceUtil.getEmailId()).thenReturn(emailID);
        when(enrollmentService.viewEnrollment(emailID)).thenReturn(studentVO);

        /*Act*/

        affairService.process(processID);

        /*Assert*/

        verify(affairServiceUtil, times(1)).getEmailId();
        verify(enrollmentService, times(1)).viewEnrollment(emailID);

    }

    @Test
    void processUpdateEnrollment() {
        /*Arrange*/
        int courseListProcessID = 3;

        String emailID = "Test@Test.com";

        String courseList = "courseList";
        StudentVO studentVO = new StudentVO();
        studentVO.setCourseId(1);
        studentVO.setDob(LocalDate.now());
        studentVO.setEmailId("Test@Test.com");
        studentVO.setFirstName("Test");
        studentVO.setLastName("Test");
        studentVO.setLocation("Test");

        when(affairServiceUtil.getEmailId()).thenReturn(emailID);
        when(enrollmentService.viewEnrollment(emailID)).thenReturn(studentVO);
        doNothing().when(enrollmentService).updateEnrollment(studentVO);
        when(enrollmentService.courseList()).thenReturn(courseList);

        /*Act*/

        affairService.process(courseListProcessID);

        /*Assert*/

        verify(affairServiceUtil, times(1)).getEmailId();
        verify(enrollmentService, times(1)).viewEnrollment(emailID);
        verify(enrollmentService, times(1)).updateEnrollment(studentVO);
        verify(enrollmentService, times(1)).courseList();

    }

    @Test
    void processDeleteEnrollment() {
        /*Arrange*/
        int courseListProcessID = 4;
        String emailID = "Test@Test.com";

        when(affairServiceUtil.getEmailId()).thenReturn(emailID);
        doNothing().when(enrollmentService).deleteEnrollment(emailID);

        /*Act*/

        affairService.process(courseListProcessID);

        /*Assert*/

        verify(affairServiceUtil, times(1)).getEmailId();
        verify(enrollmentService, times(1)).deleteEnrollment(emailID);

    }

    @Test
    void getProcessID() {
        /*Arrange*/

        when(affairServiceUtil.getProcessID()).thenReturn(1);

        /*Act*/

        int processID = affairService.getProcessID();

        /*Assert*/

        assertEquals(1, processID);
    }
}
package com.study.ses.enrollment;

import com.study.ses.common.StudentVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnrollmentServiceTest {

    private EnrollmentService enrollmentService;

    private EnrollmentServiceUtil enrollmentServiceUtil;
    private CourseDAO courseDAO;
    private StudentDAO studentDAO;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        enrollmentServiceUtil = mock(EnrollmentServiceUtil.class, CALLS_REAL_METHODS);
        courseDAO = mock(CourseDAO.class);
        studentDAO = mock(StudentDAO.class);
        modelMapper = new ModelMapper();
        enrollmentService = new EnrollmentService(enrollmentServiceUtil, courseDAO, studentDAO, modelMapper);
    }

    @Test
    void courseList() {
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

        when(courseDAO.findAll()).thenReturn(courseEntities);
        /*Act*/

        String courseList = enrollmentService.courseList();

        /*Assert*/

        assertEquals(enrollmentServiceUtil.beautifyCourseEntityList(courseEntities), courseList);
        verify(courseDAO, times(1)).findAll();
    }

    @Test
    void createEnrollment() {

        /*Arrange*/

        StudentVO studentVO = new StudentVO();
        studentVO.setCourseId(1);
        studentVO.setDob(LocalDate.now());
        studentVO.setEmailId("Test@Test.com");
        studentVO.setFirstName("Test");
        studentVO.setLastName("Test");
        studentVO.setLocation("Test");

        StudentEntity studentEntity = modelMapper.map(studentVO, StudentEntity.class);

        when(studentDAO.save(studentEntity)).thenReturn(studentEntity);

        /*Act*/

        enrollmentService.createEnrollment(studentVO);

        /*Assert*/

        verify(studentDAO).save(studentEntity);

    }

    @Test
    void viewEnrollment() {

        /*Arrange*/

        String emailID = "Test@Test.com";
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setCourseId(Long.parseLong("1"));
        studentEntity.setDob(LocalDate.now());
        studentEntity.setEmailId("Test@Test.com");
        studentEntity.setFirstName("Test");
        studentEntity.setLastName("Test");
        studentEntity.setLocation("Test");

        Optional<StudentEntity> studentEntityOptional = Optional.of(studentEntity);

        when(studentDAO.findById(emailID)).thenReturn(studentEntityOptional);

        /*Act*/

        StudentVO studentVO = enrollmentService.viewEnrollment(emailID);

        /*Assert*/
        assertEquals(studentEntity.getCourseId(), studentVO.getCourseId());
        assertEquals(studentEntity.getFirstName(), studentVO.getFirstName());
        assertEquals(studentEntity.getLastName(), studentVO.getLastName());
        assertEquals(studentEntity.getDob(), studentVO.getDob());
        assertEquals(studentEntity.getLocation(), studentVO.getLocation());

        verify(studentDAO).findById(emailID);
    }

    @Test
    void updateEnrollment() {
        /*Arrange*/

        StudentVO studentVO = new StudentVO();
        studentVO.setCourseId(1);
        studentVO.setDob(LocalDate.now());
        studentVO.setEmailId("Test@Test.com");
        studentVO.setFirstName("Test");
        studentVO.setLastName("Test");
        studentVO.setLocation("Test");

        StudentEntity studentEntity = modelMapper.map(studentVO, StudentEntity.class);

        when(studentDAO.save(studentEntity)).thenReturn(studentEntity);


        /*Act*/

        enrollmentService.updateEnrollment(studentVO);


        /*Assert*/

        verify(studentDAO).save(studentEntity);

    }

    @Test
    void deleteEnrollment() {

        /*Arrange*/

        String emailID = "Test@Test.com";

        /*Act*/

        enrollmentService.deleteEnrollment(emailID);

        /*Assert*/

        verify(studentDAO).deleteById(emailID);
    }

}
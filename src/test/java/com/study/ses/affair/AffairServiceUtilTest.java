package com.study.ses.affair;

import com.study.ses.common.StudentVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AffairServiceUtilTest {

    private static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private AffairServiceUtil affairServiceUtil;

    @BeforeEach
    void setUp() {
        affairServiceUtil = new AffairServiceUtil();
    }

    @Test
    void createEnrollment() {
        /*Arrange*/

        String courceID = "1";
        String FirstName = "Test";
        String LastName = "Test";
        String dob = "22/12/2000";
        String emailID = "Test@Test.com";
        String location = "Chennai";

        String data = courceID + "\n" + FirstName + "\n" + LastName + "\n" + dob + "\n" + emailID + "\n" + location;

        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);

        /*Act*/

        StudentVO studentVO = affairServiceUtil.createEnrollment();

        /*Assert*/

        assertEquals(Long.parseLong(courceID), studentVO.getCourseId());
        assertEquals(FirstName, studentVO.getFirstName());
        assertEquals(LastName, studentVO.getLastName());
        assertEquals(convertStringToLocalDate(dob), studentVO.getDob());
        assertEquals(emailID, studentVO.getEmailId());
        assertEquals(location, studentVO.getLocation());
    }

    @Test
    void createEnrollmentInValidEmailID() {
        /*Arrange*/

        String courceID = "1";
        String FirstName = "Test";
        String LastName = "Test";
        String dob = "22/12/2000";
        String inValidEmailID = "TestTest.com";
        String emailID = "Test@Test.com";
        String location = "Chennai";

        String data =
                courceID + "\n" + FirstName + "\n" + LastName + "\n" + dob + "\n" + inValidEmailID + "\n" + emailID +
                        "\n" + location;

        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);

        /*Act*/

        StudentVO studentVO = affairServiceUtil.createEnrollment();

        /*Assert*/

        assertEquals(Long.parseLong(courceID), studentVO.getCourseId());
        assertEquals(FirstName, studentVO.getFirstName());
        assertEquals(LastName, studentVO.getLastName());
        assertEquals(convertStringToLocalDate(dob), studentVO.getDob());
        assertEquals(emailID, studentVO.getEmailId());
        assertEquals(location, studentVO.getLocation());
    }

    @Test
    void createEnrollmentInValidCourceID() {
        /*Arrange*/

        String inValidcourceID = "inValidcourceID";
        String courceID = "1";
        String FirstName = "Test";
        String LastName = "Test";
        String dob = "22/12/2000";
        String emailID = "Test@Test.com";
        String location = "Chennai";

        String data = inValidcourceID + "\n" +
                courceID + "\n" + FirstName + "\n" + LastName + "\n" + dob + "\n" + emailID +
                "\n" + location;

        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);

        /*Act*/

        StudentVO studentVO = affairServiceUtil.createEnrollment();

        /*Assert*/

        assertEquals(Long.parseLong(courceID), studentVO.getCourseId());
        assertEquals(FirstName, studentVO.getFirstName());
        assertEquals(LastName, studentVO.getLastName());
        assertEquals(convertStringToLocalDate(dob), studentVO.getDob());
        assertEquals(emailID, studentVO.getEmailId());
        assertEquals(location, studentVO.getLocation());
    }

    @Test
    void createEnrollmentInValidDateOfBirth() {
        /*Arrange*/

        String courceID = "1";
        String FirstName = "Test";
        String LastName = "Test";
        String inValidDOB = "12/22/2000";
        String dob = "22/12/2000";
        String emailID = "Test@Test.com";
        String location = "Chennai";

        String data =
                courceID + "\n" + FirstName + "\n" + LastName + "\n" + inValidDOB + "\n" + dob + "\n" + emailID +
                        "\n" + location;

        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);

        /*Act*/

        StudentVO studentVO = affairServiceUtil.createEnrollment();

        /*Assert*/

        assertEquals(Long.parseLong(courceID), studentVO.getCourseId());
        assertEquals(FirstName, studentVO.getFirstName());
        assertEquals(LastName, studentVO.getLastName());
        assertEquals(convertStringToLocalDate(dob), studentVO.getDob());
        assertEquals(emailID, studentVO.getEmailId());
        assertEquals(location, studentVO.getLocation());
    }

    @Test
    void getCourseId() {
        /*Arrange*/

        String courceID = "1";
        InputStream in = new ByteArrayInputStream(courceID.getBytes());
        System.setIn(in);

        /*Act*/

        Long courceIDValue = affairServiceUtil.getCourseId();

        /*Assert*/

        assertEquals(Long.parseLong(courceID), courceIDValue);

    }

    @Test
    void getEmailId() {
        /*Arrange*/

        String emailID = "Test@Tet.com";
        InputStream in = new ByteArrayInputStream(emailID.getBytes());
        System.setIn(in);

        /*Act*/

        String emailIDValue = affairServiceUtil.getEmailId();

        /*Assert*/

        assertEquals(emailID, emailIDValue);

    }

    @Test
    void getProcessID() {
        /*Arrange*/

        String processID = "1";
        InputStream in = new ByteArrayInputStream(processID.getBytes());
        System.setIn(in);

        /*Act*/

        int processIDValue = affairServiceUtil.getProcessID();

        /*Assert*/

        assertEquals(Integer.parseInt(processID), processIDValue);
    }

    private LocalDate convertStringToLocalDate(String date) {
        try {
            return LocalDate.parse(date, DATEFORMATTER);
        } catch (DateTimeParseException ex) {
            System.out.println("please enter Date for birth in this Format (DD-MM-YYYY) :");
        }
        return null;
    }
}
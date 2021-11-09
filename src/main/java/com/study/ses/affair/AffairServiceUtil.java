package com.study.ses.affair;

import com.study.ses.common.StudentVO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AffairServiceUtil {

    private static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String EMAIL_VALIDATOR = "^(.+)@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_VALIDATOR);

    public StudentVO createEnrollment() {
        Scanner scanner = new Scanner(System.in);
        StudentVO studentVO = new StudentVO();
        studentVO.setCourseId(getCourseId(scanner));
        studentVO.setFirstName(getFirstName(scanner));
        studentVO.setLastName(getLastName(scanner));
        studentVO.setDob(getDateOfBirth(scanner));
        studentVO.setEmailId(getEmailId(scanner));
        studentVO.setLocation(getLocation(scanner));
        return studentVO;
    }

    private String getInputFromUser(Scanner scanner) {
        return scanner.next();
    }

    private String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private String getFirstName(Scanner scanner) {
        System.out.println("Enter the First Name :");
        return getInputFromUser(scanner);
    }

    private String getLastName(Scanner scanner) {
        System.out.println("Enter the Last Name :");
        return getInputFromUser(scanner);
    }

    private String getLocation(Scanner scanner) {
        System.out.println("Enter the Location :");
        return getInputFromUser(scanner);
    }

    private Long getCourseId(Scanner scanner) {
        System.out.println("Enter the Course Id :");
        Long courseID = null;
        try {
            courseID = Long.valueOf(getInputFromUser(scanner));
        } catch (NumberFormatException e) {
            System.out.println("Enter the Valid Course Id :");
            courseID = getCourseId(scanner);
        }
        return courseID;
    }

    public Long getCourseId() {
        System.out.println("Enter the Course Id :");
        Long courseID = null;
        try {
            courseID = Long.valueOf(getInputFromUser());
        } catch (NumberFormatException e) {
            System.out.println("Enter the Valid Course Id :");
            courseID = getCourseId();
        }
        return courseID;
    }

    private String getEmailId(Scanner scanner) {
        System.out.println("Enter the Email ID :");
        String emailID = getInputFromUser(scanner);
        Matcher matcher = pattern.matcher(emailID);
        if (!matcher.matches()) {
            System.out.println("please enter validate Email Id :");
            emailID = getEmailId(scanner);
        }
        return emailID;
    }

    public String getEmailId() {
        System.out.println("Enter the Email ID :");
        String emailID = getInputFromUser();
        Matcher matcher = pattern.matcher(emailID);
        if (!matcher.matches()) {
            System.out.println("please enter validate Email Id :");
            emailID = getEmailId();
        }
        return emailID;
    }

    private LocalDate getDateOfBirth(Scanner scanner) {
        System.out.println("Enter the Date of birth (DD/MM/YYYY) :");
        LocalDate dateOfBirth = convertStringToLocalDate(getInputFromUser(scanner));
        if (dateOfBirth == null) {
            dateOfBirth = getDateOfBirth(scanner);
        }
        return dateOfBirth;
    }

    private LocalDate convertStringToLocalDate(String date) {
        try {
            return LocalDate.parse(date, DATEFORMATTER);
        } catch (DateTimeParseException ex) {
            System.out.println("please enter Date for birth in this Format (DD-MM-YYYY) :");
        }
        return null;
    }

    public int getProcessID() {
        Scanner userInput = new Scanner(System.in);
        return validateProcessID(userInput.next());
    }

    private int validateProcessID(String processID) {
        int processIDValue;
        try {
            processIDValue = Integer.parseInt(processID);
        } catch (NumberFormatException e) {
            System.out.println("Enter the valid option :");
            processIDValue = getProcessID();
        }
        return processIDValue;
    }

}

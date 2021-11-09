package com.study.ses;

import com.study.ses.affair.AffairService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class StudentEnrollmentSystemApplication implements CommandLineRunner {

    private final AffairService affairService;

    public static void main(String[] args) {
        SpringApplication.run(StudentEnrollmentSystemApplication.class, args);
    }

    @Override public void run(String... args) throws Exception {

        System.out.println();
        System.out.println("1.Course Enrollment");
        System.out.println("2.View Enrollment");
        System.out.println("3.Update Enrollment");
        System.out.println("4.Delete Enrollment");
        System.out.println("5.Exit");
        System.out.println();

        System.out.print("Select any one of the option from the above list : ");
        affairService.process(affairService.getProcessID());
        run();
    }

}

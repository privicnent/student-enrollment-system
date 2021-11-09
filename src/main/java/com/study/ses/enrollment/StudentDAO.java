package com.study.ses.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<StudentEntity,String> {
}

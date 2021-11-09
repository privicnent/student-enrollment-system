package com.study.ses.enrollment;

import com.study.ses.common.StudentVO;
import com.study.ses.exception.CourseIdNotFoundException;
import com.study.ses.exception.EnrollmentException;
import com.study.ses.exception.NoEnrollmentFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnrollmentService {

    private final EnrollmentServiceUtil enrollmentServiceUtil;
    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;
    private final ModelMapper modelMapper;

    public String courseList() {
        List<CourseEntity> courseEntities = courseDAO.findAll();
        return enrollmentServiceUtil.beautifyCourseEntityList(courseEntities);
    }

    public void createEnrollment(StudentVO studentVO) {
        StudentEntity studentEntity = modelMapper.map(studentVO, StudentEntity.class);
        try {
            studentDAO.save(studentEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new CourseIdNotFoundException();
        } catch (EnrollmentException ex) {
            throw new EnrollmentException(ex.getMessage());
        }

    }

    public StudentVO viewEnrollment(String emailId) {
        Optional<StudentEntity> studentEntity = studentDAO.findById(emailId);
        if (studentEntity.isPresent()) {
            return modelMapper.map(studentEntity.get(), StudentVO.class);
        }
        throw new NoEnrollmentFoundException();
    }

    public void updateEnrollment(StudentVO studentVO) {
        StudentEntity studentEntity = modelMapper.map(studentVO, StudentEntity.class);
        try {
            studentDAO.save(studentEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new CourseIdNotFoundException();
        } catch (Exception ex) {
            throw new EnrollmentException(ex.getMessage());
        }
    }

    public void deleteEnrollment(String emailId) {
        try {
            studentDAO.deleteById(emailId);
        } catch (EmptyResultDataAccessException Em) {
            throw new NoEnrollmentFoundException();
        } catch (Exception ex) {
            throw new EnrollmentException(ex.getMessage());
        }

    }

}

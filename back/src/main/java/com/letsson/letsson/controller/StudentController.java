package com.letsson.letsson.controller;

import com.letsson.letsson.exception.ResourceNotFoundException;
import com.letsson.letsson.model.StudentDao;
import com.letsson.letsson.repository.StudentRepository;
import com.letsson.letsson.security.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final StudentRepository studentRepository;

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> student) {
        return studentRepository.save(StudentDao.builder().phone(student.get("phone"))
                .password(passwordEncoder.encode(student.get("password"))).mail(student.get("mail"))
                .location(student.get("location")).name(student.get("name")).nickname(student.get("nickname"))
                .roles(Collections.singletonList("ROLE_STUDENT")).build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> student) {
        StudentDao member = studentRepository.findByPhone(student.get("phone"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Phone 입니다"));
        if (!passwordEncoder.matches(student.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }

    // get all students
    @GetMapping("")
    public List<StudentDao> getALLStudents() {
        return this.studentRepository.findAll();
    }

    // get student by id
    @GetMapping("/{id}")
    public StudentDao getStudentById(@PathVariable(value = "id") Long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not found with id :" + id));
    }

    // create new student
    // @PostMapping("")
    // public StudentDao createStudent(@RequestBody StudentDao studentDao){
    // return this.studentRepository.save(studentDao);
    // }
    // update student by id..mail,name,location update..
    @PutMapping("/{id}")
    public StudentDao updateStudent(@RequestBody StudentDao studentDao, @PathVariable("id") Long id) {
        StudentDao existingStudentDao = this.studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not found with id :" + id));
        existingStudentDao.setName(studentDao.getName());
        existingStudentDao.setMail(studentDao.getMail());
        existingStudentDao.setLocation(studentDao.getLocation());
        return this.studentRepository.save(existingStudentDao);

    }

    // delete student by id
    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDao> deleteStudent(@PathVariable("id") Long id) {
        StudentDao existingStudentDao = this.studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not found with id :" + id));
        this.studentRepository.delete(existingStudentDao);
        return ResponseEntity.ok().build();

    }
}

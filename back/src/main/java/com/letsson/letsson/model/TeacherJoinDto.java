package com.letsson.letsson.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TeacherJoinDto {


    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String name;

    @Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$", message = "000-0000-0000")
    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String tel;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    private String region;

    private String gender;

    private String pay;

    private String contact;

    private String university;

    private String major;

    private String is_attend;

    private String prove_image;

    private String subject;

    private String intro;

    private String role;










}

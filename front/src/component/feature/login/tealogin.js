import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";

const Log = styled.form`
  display: relative;
  background-color: white;
  position: absolute;
  width: 400px;
  height: 180px;
  margin-left: -200px;
  margin-top: -90px;
  left: 70%;
  top: 43%;
  padding-top: 30px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  border: solid 1px black;
`;
const LogText1 = styled.span`
  text-align: right;
  padding-right: 30px;
  margin-left: 35px;
`;
const LogText2 = styled.span`
  text-align: right;
  padding-right: 45px;
  margin-left: 35px;
`;
const LogInput = styled.input`
  background-color: white;
  border: solid 1px black;
  margin-right: 0;
  width: 55%;
  height: 100%;
`;
const LogBtns = styled.div`
  margin-top: 10px;
  margin-left: 3%;
`;
const LogBtn = styled.input`
  height: 50px;
  color: white;
  padding-left: 150px;
  padding-right: 150px;
  border-radius: 50px;
  font-size: 16px;
  border: none;
  margin-top: 20px;
  margin-left: 30px;
  margin-right: 30px;
  background: linear-gradient(
    to right,
    rgba(104, 104, 104, 1) 18%,
    rgba(43, 62, 104, 1) 70%
  ); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#686868', endColorstr='#2b3e68',GradientType=1 ); /* IE6-9 */
`;

const TeaLogin = () => {
  const [tel, setTel] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    if (tel.length === 10) {
      setTel(tel.replace(/(\d{3})(\d{3})(\d{4})/, "$1-$2-$3"));
    }
    if (tel.length === 13) {
      setTel(
        tel.replace(/-/g, "").replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3")
      );
    }
  }, [tel]);

  const PhoneInput = (e) => {
    const regex = /^[0-9\b -]{0,13}$/;
    if (regex.test(e.target.value)) {
      setTel(e.target.value);
    }
  };

  const PasswordInput = (e) => {
    setPassword(e.target.value);
  };

  const TeaLoged = (e) => {
    console.log(tel);
    console.log(password);

    axios
      .post("http://localhost:8080/teachers/login", {
        tel,
        password,
      })
      .then((res) => {
        console.log(res);
        localStorage.setItem("token", res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <Log>
      <label className="phoneNum">
        <LogText1>휴대폰번호</LogText1>
        <LogInput
          type="text"
          value={tel}
          onChange={PhoneInput}
          placeholder="010-0000-0000"
        ></LogInput>
      </label>
      <br></br>
      <label className="password">
        <LogText2>비밀번호</LogText2>
        <LogInput
          type="password"
          value={password}
          onChange={PasswordInput}
          placeholder="8글자 이상"
        ></LogInput>
      </label>

      <LogBtns>
        <Link to="/loginsuccess">
          <LogBtn type="submit" onClick={TeaLoged} value="확인"></LogBtn>
        </Link>
      </LogBtns>
    </Log>
  );
};

export default TeaLogin;
import { createContext, useContext, useState } from "react";
import { executeBasicAuthenticationService } from "../api/HelloWorldApiService";
//* 인증 관련 로직
//1. 컨텍스트 생성
//!. 다른 컴포넌트에서 접근을 가능하게 해주기 위해 export를 사용
export const AuthContext = createContext();

//~> 이렇게 AuthContext로 부터 가져오는 공통적인 인증 부분을 사용자 정의 hook으로 구현
// const authContext = useContext(AuthContext); -> HeaderComponent에서 가져오던 코드
export const useAuth = () => useContext(AuthContext);

//2. 다른 컴포넌트와 생성된 컨텍스트를 공유
//! 이 프로바이더로 앱을 감싼다.
export default function AuthProvider({ children }) {
  //3. 컨텍스트에 state 추가
  const [isAuthenticated, setAuthenticated] = useState(false);

  const [username, setUsername] = useState(null);

  // function login(username, password) {
  //   if (username === "nareun130" && password === "1234") {
  //     setAuthenticated(true);
  //     setUsername(username);
  //     return true;
  //   } else {
  //     setAuthenticated(false);
  //     setUsername(null);
  //     return false;
  //   }
  // }

  //? 기본 인증
  function login(username, password) {
    //*Base64로 인코딩된 아스키2 문자열 반환
    const basicToken = "Basic " + window.btoa(username + ":" + password);
    executeBasicAuthenticationService(basicToken)
      .then((response) => console.log(response))
      .catch((error) => console.log(error));

    setAuthenticated(false);

    // if (username === "nareun130" && password === "1234") {
    //   setAuthenticated(true);
    //   setUsername(username);
    //   return true;
    // } else {
    //   setAuthenticated(false);
    //   setUsername(null);
    //   return false;
    // }
  }
  function logout() {
    setAuthenticated(false);
  }
  //?-> AuthProvider아래 모든 자식 컴포넌트가 childer에 들어간다.
  return <AuthContext.Provider value={{ isAuthenticated, login, logout, username }}>{children}</AuthContext.Provider>;
}

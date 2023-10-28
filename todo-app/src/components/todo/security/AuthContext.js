import { createContext, useContext, useState } from "react";
import { apiClient } from "../api/ApiClient";
import { executeJwtAuthenticationService } from "../api/AuthenticationApiService";
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

  const [token, setToken] = useState(null);
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
  //! 인증 작업이 끝나고 나서 set Authenticated를 해주기 위해 async, await을 사용
  //~> login의 handleSubmit도 async를 사용해준다.
  // async function login(username, password) {
  //   //*Base64로 인코딩된 아스키2 문자열 반환 -> 배어러 - 리소스에 대한 소유권 방식이 있다. 대표적인게 jwt, oauth
  //   //* Basic authentication은 많이 쓰지 않는다. 보통 1회성 -> 매번 헤더에 담고 가기가 부담스러워서

  //   const basicToken = "Basic " + window.btoa(username + ":" + password); //* atob는 반대로 디코딩

  //   try {
  //     const response = await executeBasicAuthenticationService(basicToken);
  //     if (response.status == 200) {
  //       setAuthenticated(true);
  //       setUsername(username);
  //       setToken(basicToken);

  //       //* 모든 Request의 header에 인증토큰을 넣어준다.
  //       apiClient.interceptors.request.use((config) => {
  //         console.log("intercepting and adding a token");
  //         config.headers.Authorization = basicToken;
  //         return config;
  //       });

  //       return true;
  //     } else {
  //       logout();
  //       return false;
  //     }
  //   } catch (error) {
  //     console.log(error);
  //     logout();
  //     return false;
  //   }
  // }
  // function logout() {
  //   setAuthenticated(false);
  //   setToken(null);
  //   setUsername(null);
  // }

//* jwt이용 로그인
  async function login(username, password) {
    
    try {
      const response = await executeJwtAuthenticationService(username,password);
      if (response.status == 200) {
        const jwtToken = 'Bearer ' + response.data.token;

        setAuthenticated(true);
        setUsername(username);
        setToken('Bearer ' + response.data.token);

        //* 모든 Request의 header에 인증토큰을 넣어준다.
        apiClient.interceptors.request.use((config) => {
          console.log("intercepting and adding a token");
          config.headers.Authorization = jwtToken;
          return config;
        });

        return true;
      } else {
        logout();
        return false;
      }
    } catch (error) {
      console.log(error);
      logout();
      return false;
    }
  }
  function logout() {
    setAuthenticated(false);
    setToken(null);
    setUsername(null);
  }

  //?-> AuthProvider아래 모든 자식 컴포넌트가 childer에 들어간다.
  return <AuthContext.Provider value={{ isAuthenticated, login, logout, username, token }}>{children}</AuthContext.Provider>;
}

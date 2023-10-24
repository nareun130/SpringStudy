import { createContext, useContext, useState } from "react";
//* 인증 부분
//1. 컨텍스트 생성
//!. 다른 컴포넌트에서 접근을 가능하게 해주기 위해 export를 사용
export const AuthContext = createContext();

//~> 이렇게 AuthContext로 부터 가져오는 공통적인 인증 부분을 사용자 정의 hook으로 구현
// const authContext = useContext(AuthContext); -> HeaderComponent에서 가져오던 코드
export const useAuth = () => useContext(AuthContext);

//3. 다른 컴포넌트와 생성된 컨텍스트를 공유
//! 이 프로바이더로 앱을 감싼다.
export default function AuthProvider({ children }) {
  //2. 컨텍스트에 state 추가
  const [number, setNumber] = useState(10);

  const [isAuthenticated, setAuthenticated] = useState(false);
  //* 이런 방법이 있다 정도만. -> 실제 프로젝트에서는 이렇게 사용하지 않는다.
  //   const valueToBeShared = {number, isAuthenticated, setAuthenticated}
  //   setInterval(() => setNumber(number + 1), 10000);
  
  //?-> AuthProvider아래 모든 자식 컴포넌트가 childer에 들어간다.
  return <AuthContext.Provider value={{ number, isAuthenticated, setAuthenticated }}>{children}</AuthContext.Provider>;
}

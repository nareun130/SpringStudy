import axios from "axios";
import { apiClient } from "./ApiClient";
export const retrieveHelloWorldBean = () => apiClient.get("http://localhost:8080/hello-world-bean");

//~> Response to preflight request doesn't pass access control check
export const retrieveHelloWorldPathVariable = (username, token) =>
  apiClient.get(
    `http://localhost:8080/hello-world/path-variable/${username}`
    // {
    //   //! 이 토큰을 모든 API에서 공유해야 하는데 어떻게 할까? -> LoginComponent에서 처리
    //   headers: {
    //     // Authorization: "Basic bmFyZXVuMTMwOjEyMzQ=",
    //     Authorization: token,
    //   },
    // }
  );


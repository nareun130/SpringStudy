import { apiClient } from "./ApiClient";

export const executeBasicAuthenticationService = (token) =>
  apiClient.get("/basicauth", {
    //! 이 토큰을 모든 API에서 공유해야 하는데 어떻게 할까? -> LoginComponent에서 처리
    headers: {
      Authorization: token,
    },
  });

export const executeJwtAuthenticationService = (username, password) =>
  apiClient.post("/authenticate", {
    username,
    password,
  });

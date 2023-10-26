import axios from "axios";

// export function retrieveHelloWorldBean() {
//   return axios.get("http://localhost:8080/hello-world-bean");
// }

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});
export const retrieveHelloWorldBean = () => apiClient.get("http://localhost:8080/hello-world-bean");

//~> Response to preflight request doesn't pass access control check
export const retrieveHelloWorldPathVariable = (username) =>
  apiClient.get(`http://localhost:8080/hello-world/path-variable/${username}`, {
    //! 이 토큰을 모든 API에서 공유해야 하는데 어떻게 할까? -> LoginComponent에서 처리
    headers: {
      Authorization: "Basic bmFyZXVuMTMwOjEyMzQ=",
    },
  });

export const executeBasicAuthenticationService = (token) =>
  apiClient.get("/basicauth", {
    //! 이 토큰을 모든 API에서 공유해야 하는데 어떻게 할까? -> LoginComponent에서 처리
    headers: {
      Authorization: token,
    },
  });

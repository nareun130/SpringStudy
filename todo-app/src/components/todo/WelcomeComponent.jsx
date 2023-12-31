import { useState } from "react";
import { Link, useParams } from "react-router-dom";
import { retrieveHelloWorldPathVariable } from "./api/HelloWorldApiService";
import { useAuth } from "./security/AuthContext";
export default function WelcomeComponent() {
  //기본 Java 식
  // const params = useParams();
  // console.log(params.username);
  //*JS에서 많이 쓰는 방법 -> 객체를 받는 대신 객체의 값을 받는 것
  const { username } = useParams();

  const [message, setMessage] = useState(null);

  const authContext = useAuth();

  function callHelloWorldRestApi() {
    console.log("called");
    //axios
    //? 비동기적 접근법으로 promise를 받아와서 받아온 promise를 다룬다.
    retrieveHelloWorldPathVariable("nareun", authContext.token)
      .then((response) => successfulResponse(response))
      .catch((error) => errorResponse(error))
      .finally(() => console.log("cleanup"));
  }

  function successfulResponse(response) {
    console.log(response);
    setMessage(response.data.message);
  }
  function errorResponse(error) {
    console.log(error);
  }
  return (
    <div className="WelcomeComponent">
      <h1>Welcome {username}</h1>
      <div>
        Manage your todos - <Link to="/todos">Go here</Link>
      </div>
      <div>
        <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>
          Call Hello World
        </button>
      </div>
      <div className="text-info">{message}</div>
    </div>
  );
}

import { Link, useParams } from "react-router-dom";

export default function WelcomeComponent() {
    //기본 Java 식
    // const params = useParams();
    // console.log(params.username);
    //*JS에서 많이 쓰는 방법 -> 객체를 받는 대신 객체의 값을 받는 것
    const { username } = useParams();
    return (
      <div className="WelcomeComponent">
        <h1>Welcome {username}</h1>
        <div>
          Manage your todos - <Link to="/todos">Go here</Link>
        </div>
      </div>
    );
  }
  
  
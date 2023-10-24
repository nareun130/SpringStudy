import { useState } from "react";
import { BrowserRouter, Link, Route, Routes, useNavigate, useParams } from "react-router-dom";
import "./TodoApp.css";
export default function TodoApp() {
  return (
    <div className="TodoApp">
      <HeaderComponent />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginComponent />} />
          <Route path="/login" element={<LoginComponent />} />
          <Route path="/welcome/:username" element={<WelcomeComponent />} />
          <Route path="/todos" element={<ListTodosComponent />} />
          <Route path="/logout" element={<LogoutComponent />} />
          <Route path="*" element={<ErrorComponent />} />
        </Routes>
      </BrowserRouter>
      <FooterComponent />
    </div>
  );
}

function LoginComponent() {
  const [username, setUsername] = useState("nareun");

  const [password, setPassword] = useState("");

  const [showSuccessMessage, setShowSuccessMessage] = useState(false);

  const [showErrorMessage, setShowErrorMessage] = useState(false);

  const navigate = useNavigate();

  function handleUsernameChange(event) {
    setUsername((username) => event.target.value);
  }
  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }
  function handleSubmit() {
    console.log(username);
    console.log(password);
    if (username === "nareun" && password === "1234") {
      console.log("successs");
      setShowSuccessMessage(true);
      setShowErrorMessage(false);
      //* url을 통해 라우터에 값 넘겨주기
      navigate(`/welcome/${username}`);
    } else {
      console.log("failed");
      setShowSuccessMessage(false);
      setShowErrorMessage(true);
    }
  }

  return (
    <div className="Login">
      <h1>Time to Login</h1>
      {showSuccessMessage && <div className="successMessage">Authenticated Successfully</div>}
      {showErrorMessage && <div className="errorMessage">Authenticated Failed. Please check your credentials.</div>}
      <div className="LoginForm">
        <div>
          <label>UserName:</label>
          <input type="text" name="username" value={username} onChange={handleUsernameChange} />
        </div>
        <div>
          <label>PassWord:</label>
          <input type="password" name="password" value={password} onChange={handlePasswordChange} />
        </div>
        <div>
          <button type="button" name="login" onClick={handleSubmit}>
            login
          </button>
        </div>
      </div>
    </div>
  );
}

function WelcomeComponent() {
  //기본 Java 식
  // const params = useParams();
  // console.log(params.username);
  //*JS에서 많이 쓰는 방법 -> 객체를 받는 대신 객체의 값을 받는 것
  const { username } = useParams();
  console.log(username);
  return (
    <div className="WelcomeComponent">
      <h1>Welcome {username}</h1>
      <div>
        Manage your todos - <Link to="/todos">Go here</Link>
      </div>
    </div>
  );
}

function ErrorComponent() {
  return (
    <div className="ErrorComponent">
      <h1>We are working really hard!</h1>
      <div>Apologies for the 404. Reach out to our team at ABC-DEF-GHIJ.</div>
    </div>
  );
}

function ListTodosComponent() {
  const today = new Date();
  const targetDate = new Date(today.getFullYear() + 12, today.getMonth(), today.getDate());
  const todos = [
    { id: 1, description: "Learn AWS", done: false, targetDate: targetDate },
    { id: 2, description: "Learn Full Stack Dev", done: false, targetDate: targetDate },
    { id: 3, description: "Learn DevOps", done: false, targetDate: targetDate },
  ];
  return (
    <div className="container">
      <h1>Things You Want To Do!</h1>
      <div>Todo Details</div>
      <table className="table">
        <thead>
          <tr>
            <td>id</td>
            <td>description</td>
            <td>Is Done?</td>
            <td>Target Date</td>
          </tr>
        </thead>
        <tbody>
          {todos.map((todo) => (
            <tr key={todo.id}>
              <td>{todo.id}</td>
              <td>{todo.description}</td>
              <td>{todo.done.toString()}</td>
              <td>{todo.targetDate.toDateString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
function HeaderComponent() {
  return (
    <div className="header">
      Header <hr />
    </div>
  );
}
function FooterComponent() {
  return (
    <div className="footer">
      <hr />
      Footer
    </div>
  );
}
function LogoutComponent() {
  return (
    <div className="LogoutComponent">
      <h1>You are logged out!</h1>
      <div>Thank you for using our App. come back soon!</div>
    </div>
  );
}

import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function LoginComponent() {
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

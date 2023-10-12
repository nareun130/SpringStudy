import { useState } from "react";
import { PropTypes } from "prop-types";
import "./Counter.css";

export default function Counter({ by }) {
  const [count, setCount] = useState(0);
  console.log(by);
  function incrementCounterFunction() {
    setCount((count) => count + by);
  }

  function decrementCounterFunction() {
    console.log("increment clicked");
    setCount((count) => count - by);
  }

  return (
    <div className="Counter">
      <span className="count">{count}</span>
      <button className="counterButton" onClick={incrementCounterFunction}>
        +{by}
      </button>
      <button className="counterButton" onClick={decrementCounterFunction}>
        -{by}
      </button>
    </div>
  );
}

// * prop의 타입 정해주기
Counter.propTypes = {
  by: PropTypes.number,
};

// * prop의 기본값 정해주기
Counter.defaultProps = {
  by: 50,
};

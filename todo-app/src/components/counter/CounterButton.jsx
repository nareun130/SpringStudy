import { PropTypes } from "prop-types";
import "./Counter.css";

export default function CounterButton({ by, incrementMethod, decrementMethod }) {
  //   function incrementCounterFunction() {
  //     incrementMethod(by);
  //   }

  //   function decrementCounterFunction() {
  //     decrementMethod(by);
  //   }

  return (
    <div className="Counter">
      <br />
      <button className="counterButton" onClick={() => incrementMethod(by)}>
        +{by}
      </button>
      <button className="counterButton" onClick={() => decrementMethod(by)}>
        -{by}
      </button>
    </div>
  );
}

// * prop의 타입 정해주기
CounterButton.propTypes = {
  by: PropTypes.number,
};

// * prop의 기본값 정해주기
CounterButton.defaultProps = {
  by: 50,
};

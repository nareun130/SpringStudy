import { useState } from "react";
import CounterButton from "./CounterButton";

export default function Counter() {
  const [count, setCount] = useState(0);
  function incrementCounterParentFunction(by) {
    setCount((count) => count + by);
  }
  function decrementCounterParentFunction(by) {
    setCount((count) => count - by);
  }
  function resetCounter() {
    setCount((count) => 0);
  }

  // ! 하위 컴포넌트에서는 상위 컴포넌트로 접근 x, 상위-> 하위로 메서드 참조값을넣어 전달
  return (
    <>
      <span className="totalCount">{count}</span>
      <CounterButton by={1} incrementMethod={incrementCounterParentFunction} decrementMethod={decrementCounterParentFunction} />
      <CounterButton by={2} incrementMethod={incrementCounterParentFunction} decrementMethod={decrementCounterParentFunction} />
      <CounterButton by={5} incrementMethod={incrementCounterParentFunction} decrementMethod={decrementCounterParentFunction} />
      <button className="resetButton" onClick={resetCounter}>
        RESET
      </button>
    </>
  );
}

import "./App.css";
import Counter from "./component/counter/Counter";

function App() {
  return (
    <div className="App">
      {/* <PlayingWithProps property1="value1" property2="value2" /> */}
      <Counter />
      <Counter by={2} />
      <Counter by={3} />
    </div>
  );
}
//* 옛날방식
// function PlayingWithProps(properties) {
//   console.log(properties);
//   console.log(properties.property1);
//   return <div></div>;
// }
//* modernJS 방식
function PlayingWithProps({ property1, property2 }) {
  console.log(property1);
  console.log(property2);
  return <div></div>;
}

export default App;

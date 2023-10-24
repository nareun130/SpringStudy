import "./App.css";
import TodoApp from "./components/todo/TodoApp";
function App() {
  return (
    <div className="App">
      <TodoApp />
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
// function PlayingWithProps({ property1, property2 }) {
//   console.log(property1);
//   console.log(property2);
//   return <div></div>;
// }

export default App;

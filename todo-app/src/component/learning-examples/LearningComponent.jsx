import FirstComponent from "./FirstComponent";
import SecondComponet from "./SecondComponent";
import ThirdComponent from "./ThirdComponent";
import FourthComponent, { FifthComponent } from "./FourthComponent";
import LearningJavaScript from "./LearningJavaScript";

export default function LearningComponent() {
  return (
    <div className="App">
      <FirstComponent />
      <SecondComponet />
      <ThirdComponent />
      <FourthComponent />
      <FifthComponent />
      <LearningJavaScript/>
    </div>
  );
}

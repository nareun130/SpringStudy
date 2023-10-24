const person = {
  name: "Nareun",
  address: {
    city: "Seoul",
    country: "South Korea",
  },
  profiles: ["instagram", "github"],
  printProfile: () => {
    person.profiles.map((profile) => console.log(profile));
  },
};

// function printProfile(){
//     console.log(person.profiles[0]);
// }
export default function LearningJavaScript() {
  return (
    <>
      <div>{person.name}</div>
      <div>{person.address.city}</div>
      <div>{person.profiles[1]}</div>
      <div>{person.printProfile()}</div>
    </>
  );
}

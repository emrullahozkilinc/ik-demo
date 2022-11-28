import './style/css/app.css'
import NavBar from "./NavBar";
import data from './users.json'
import EmployeeList from "./EmployeeList";

function App() {

  return (
    <div className="App">
        <NavBar/>

        <EmployeeList allEmployees={data} employeeContacts={{
            address:"Kartaltepe, Malazgirt Cd. No:2-4, 34295 Küçükçekmece/İstanbul",
            city:"İstanbul",
            country:"Turkey",
            code:"34252",
        }}/>
    </div>
  );
}

export default App;

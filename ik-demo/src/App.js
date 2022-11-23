import {useState} from "react"
import Users from "./components/Users"

function App() {

    const [workers, setWorkers] = useState([{
        firstName: "",
        lastname: "",
        natId: "",
        position: "",
        dateOfStart: new Date(628021800000),
        salary: 0,
        department: "",
        email: ""
    }])

  return (
    <div className="App">
        <Users workers={workers}/>
    </div>
  );
}

export default App;

import './style/css/app.css'
import NavBar from "./NavBar";
import EmployeeList from "./EmployeeList";
import {Routes, Route} from "react-router-dom"
import Dayoffs from "./components/Dayoffs";
import Spendings from "./components/Spendings";
import Shifts from "./components/Shifts";
import Login from "./components/auth/Login";
import {useAuth} from "./components/auth/AuthContext";

function App() {
    const {user} = useAuth();

  return (
    <div className="App">
        {user.token.startsWith("ey") ? <NavBar/> : <div/>}

        <Routes>
            <Route path="/login" element={<Login/>} exact/>
            <Route path="/employees" element={<EmployeeList/>}/>
            <Route path="/dayoffs" element={<Dayoffs/>}/>
            <Route path="/spendings" element={<Spendings/>}/>
            <Route path="/shifts" element={<Shifts/>}/>
        </Routes>
    </div>
  );
}

export default App;

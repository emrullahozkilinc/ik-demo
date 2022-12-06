import './style/css/app.css'
import NavBar from "./NavBar";
import data from './users.json'
import dayoffs from "./dayoffs.json";
import spendings from "./spendings.json";
import shifts from "./shifts.json";
import EmployeeList from "./EmployeeList";
import {Routes, Route, BrowserRouter} from "react-router-dom"
import Dayoffs from "./components/Dayoffs";
import Spendings from "./components/Spendings";
import Shifts from "./components/Shifts";
import Login from "./components/auth/Login";
import {AuthProvider, useAuth} from "./components/auth/AuthContext";

function App() {
    const {user} = useAuth();

  return (
    <div className="App">
        {user.token.startsWith("ey") ? <NavBar/> : <div/>}

        <Routes>
            <Route path="/login" element={<Login/>} exact/>
            <Route path="/employees" element={<EmployeeList allEmployees={data}/>}/>
            <Route path="/dayoffs" element={<Dayoffs allDayoffs={dayoffs}/>}/>
            <Route path="/spendings" element={<Spendings allSpendings={spendings}/>}/>
            <Route path="/shifts" element={<Shifts allShifts={shifts}/>}/>
        </Routes>
    </div>
  );
}

export default App;

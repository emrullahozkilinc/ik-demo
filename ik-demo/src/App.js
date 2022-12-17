import './style/css/app.css'
import NavBar from "./NavBar";
import EmployeeList from "./EmployeeList";
import {Routes, Route} from "react-router-dom"
import Dayoffs from "./components/Dayoffs";
import Spendings from "./components/Spendings";
import Shifts from "./components/Shifts";
import Login from "./components/auth/Login";
import {useAuth} from "./components/auth/AuthContext";
import History from "./components/History";

function App() {
    const {user} = useAuth();

  return (
    <div className="App">
        {user.token.startsWith("ey") ? <NavBar/> : <div/>}

        <Routes>
            <Route path="/login" element={ user.token.startsWith("ey") ? <div/> : <Login/>} exact/>
            <Route path="/" element={user.token.startsWith("ey") ? <History/> : <Login/>}/>
            <Route path="/employees" element={user.token.startsWith("ey") ? <EmployeeList/> : <Login/>}/>
            <Route path="/dayoffs" element={user.token.startsWith("ey") ? <Dayoffs/> : <Login/>}/>
            <Route path="/spendings" element={user.token.startsWith("ey") ? <Spendings/> : <Login/>}/>
            <Route path="/shifts" element={user.token.startsWith("ey") ? <Shifts/> : <Login/>}/>
        </Routes>
    </div>
  );
}

export default App;

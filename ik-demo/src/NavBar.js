import {Col, Row} from "reactstrap";
import {useAuth} from "./components/auth/AuthContext";

const NavBar = () => {
    const {logout} = useAuth();

    return(
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <a className="navbar-brand" href="src/NavBar#">ManageIK</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
                aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarColor01">
            <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                    <a className="nav-link" href="/">Home <span className="sr-only">(current)</span></a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="/employees">Employees</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="/dayoffs">Dayoffs</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="/spendings">Spendings</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="/shifts">Shifts</a>
                </li>
            </ul>
            <form className="form-inline">
                <Row>
                    <Col><button className="btn btn-outline-info my-2 my-sm-0" onClick={logout}>Logout</button></Col>
                </Row>
            </form>
        </div>
    </nav>
    );
}

export default NavBar;
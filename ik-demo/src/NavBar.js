import {Col, Row} from "reactstrap";

const NavBar = () => {
    return(
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <a className="navbar-brand" href="src/NavBar#">Navbar</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
                aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarColor01">
            <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                    <a className="nav-link" href="src/NavBar#">Home <span className="sr-only">(current)</span></a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="src/NavBar#">Features</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="src/NavBar#">Pricing</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="src/NavBar#">About</a>
                </li>
            </ul>
            <form className="form-inline">
                <Row>

                    <Col><input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"/></Col>
                    <Col><button className="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button></Col>

                </Row>

            </form>
        </div>
    </nav>
    );
}

export default NavBar;
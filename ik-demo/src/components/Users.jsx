import React from 'react';
import {Col, Row, Table} from "reactstrap";
import DayoffModal from './modals/dayoff/DayoffModal'
import SpendingModal from './modals/spending/SpendingModal'
import ShiftModal from './modals/shift/ShiftModal'
import AddressModal from './modals/address/AddressModal'
import AddEmployeeModal from "./modals/employee/AddEmployeeModal";
import EditEmployeeModal from "./modals/employee/EditEmployeeModal";
import DeleteEmployeeModal from "./modals/employee/DeleteEmployeeModal";
import MyPagination from "../Pagination";
import '../style/css/tables.css'

class Users extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            employeesPerPage: 6,
            currentPage: 1,
            workers: this.props.workers,
            usercontacts: {
                address:"Kartaltepe, Malazgirt Cd. No:2-4, 34295 Küçükçekmece/İstanbul",
                city:"İstanbul",
                country:"Turkey",
                code:"34252",
            },
            currentEmployees : []

        }

        this.indexOfLastEmployee = this.state.currentPage * this.state.employeesPerPage;
        this.indexOfFirstEmployee = this.indexOfLastEmployee - this.state.employeesPerPage;
        this.state.currentEmployees = this.state.workers.slice(this.indexOfFirstEmployee, this.indexOfLastEmployee);
        this.totalPagesNum = Math.ceil(this.state.workers.length/this.state.employeesPerPage);
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        console.log(prevState)
        this.indexOfLastEmployee = this.state.currentPage * this.state.employeesPerPage;
        this.indexOfFirstEmployee = this.indexOfLastEmployee - this.state.employeesPerPage;
        this.totalPagesNum = Math.ceil(this.state.workers.length/this.state.employeesPerPage);
    }

    toPrevPage = () => {
        if(0 < this.state.currentPage) {
            this.setState((prevState, props) => (
                {currentPage: prevState.currentPage-1,
                    currentEmployees : this.state.workers.slice(this.indexOfFirstEmployee, this.indexOfLastEmployee)}));
        }
    }

    toNextPage = () => {
        if(this.state.currentPage <= this.maxPage) {
            this.setState((prevState, props) => (
            {currentPage: prevState.currentPage+1,
                currentEmployees : this.state.workers.slice(this.indexOfFirstEmployee, this.indexOfLastEmployee)}));
        }
    }

    render() {

        return (
            <div className="employee-table">
                <div className="table-wrapper">
                    <div className="table-title">
                        <Row>
                            <Col md={{size:10}}><h2>Employees</h2></Col>
                            <Col>
                                <AddEmployeeModal
                                    positions={["backend","frontend"]}
                                    levels={["junior", "medium", "senior"]}
                                    departments={["Finance", "Human Resources", "Development"]}
                                />
                            </Col>
                        </Row>
                    </div>
                <Table responsive hover size="" className="employee-table2">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>National Id</th>
                        <th>Position</th>
                        <th>Date of Start</th>
                        <th>Salary</th>
                        <th>Level</th>
                        <th>Title</th>
                        <th>Born Date</th>
                        <th>Department</th>
                        <th>Email</th>
                        <th>Add Dayoff</th>
                        <th>Add Spending</th>
                        <th>Add Shift</th>
                        <th>Contact Details</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    {this.state.currentEmployees.map((worker, i) => {
                        return (
                        <tr key={i}>
                            <th scope="row">{i+1}</th>
                            <td>{worker.firstName}</td>
                            <td>{worker.lastname}</td>
                            <td>{worker.natId}</td>
                            <td>{worker.position}</td>
                            <td>{new Date(worker.dateOfStart).toLocaleDateString()}</td>
                            <td>{worker.salary}</td>
                            <td>{worker.level}</td>
                            <td>{worker.title}</td>
                            <td>{new Date(worker.bornDate).toLocaleDateString()}</td>
                            <td>{worker.department}</td>
                            <td>{worker.email}</td>
                            <td><DayoffModal/></td>
                            <td><SpendingModal/></td>
                            <td><ShiftModal/></td>
                            <td><AddressModal usercontacts={this.state.usercontacts}/></td>
                            <td>
                                <Row>
                                <Col>
                                <EditEmployeeModal
                                    positions={["backend","frontend"]}
                                    levels={["junior", "medium", "senior"]}
                                    departments={["Finance", "Human Resources", "Development"]}
                                />
                                </Col>
                                <Col>
                                <DeleteEmployeeModal/>
                                </Col>
                                </Row>
                            </td>
                        </tr>
                        )}
                    )}
                    </tbody>
                </Table>
                    {this.maxPage = Math.ceil(this.state.workers.length / this.state.employeesPerPage)}
                    <MyPagination maxPage={this.maxPage} toNextPage={this.toNextPage} toPrevPage={this.toPrevPage} />
                </div>
            </div>
        );
    }
}

export default Users;
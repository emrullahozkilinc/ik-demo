import React, {useEffect, useState} from "react";
import {Col, Row, Table} from "reactstrap";
import AddEmployeeModal from "./components/modals/employee/AddEmployeeModal";
import DayoffModal from "./components/modals/dayoff/DayoffModal";
import SpendingModal from "./components/modals/spending/SpendingModal";
import ShiftModal from "./components/modals/shift/ShiftModal";
import AddressModal from "./components/modals/address/AddressModal";
import EditEmployeeModal from "./components/modals/employee/EditEmployeeModal";
import DeleteEmployeeModal from "./components/modals/employee/DeleteEmployeeModal";
import MyPagination from "./Pagination";
import './style/css/tables.css'
import {useAuth} from "./components/auth/AuthContext";
import axios from "axios";

const EmployeeList = ({allEmployees, employeeContacts}) => {
    const [employees, setEmployees] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [employeesPerPage] = useState(6);

    const {user} = useAuth();

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/app/employees",
            {
                headers: {
                    'Authorization': "Bearer " + user.token
                }
            })
            .then(r => {
                if (JSON.stringify(employees)!==JSON.stringify(r.data)) {
                    setEmployees(r.data)
                }
            })
    },[employees])

    const indexOfLastEmployee = currentPage * employeesPerPage;
    const indexOfFirstEmployee = indexOfLastEmployee - employeesPerPage;
    const currentEmployees = employees.slice(indexOfFirstEmployee, indexOfLastEmployee);
    const totalPages = Math.ceil(employees.length/employeesPerPage);

    return (
        <div className="general-table">
            <div className="table-wrapper">
                <div className="table-title">
                    <Row>
                        <Col md={{size:10}}><h2>Employees</h2></Col>
                        <Col>
                            <AddEmployeeModal
                                positions={["backend","frontend"]}
                                levels={["J1", "J2", "J3"]}
                                departments={["Finance", "Human Resources", "Development"]}
                                setEmployees={setEmployees}
                                employees={employees}
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
                        <th>Phone</th>
                        <th>Add Dayoff</th>
                        <th>Add Spending</th>
                        <th>Add Shift</th>
                        <th>Contact Details</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    {currentEmployees.map((worker, i) => {
                        return (
                            <tr key={i}>
                                <th scope="row">{indexOfFirstEmployee+i+1}</th>
                                <td>{worker.firstName}</td>
                                <td>{worker.lastName}</td>
                                <td>{worker.nationalId}</td>
                                <td>{worker.position}</td>
                                <td>{worker.startDate}</td>
                                <td>{new Intl.NumberFormat('tr-TR', { style: 'currency', currency: 'TRY' }).format(worker.salary)}</td>
                                <td>{worker.level}</td>
                                <td>{worker.title}</td>
                                <td>{worker.bornDate}</td>
                                <td>{worker.department}</td>
                                <td>{worker.email}</td>
                                <td>{worker.phone}</td>
                                <td><DayoffModal employeeNationalId={worker.nationalId}/></td>
                                <td><SpendingModal employeeNationalId={worker.nationalId}/></td>
                                <td><ShiftModal employeeNationalId={worker.nationalId}/></td>
                                <td><AddressModal usercontacts={worker.address}/></td>
                                <td>
                                    <Row>
                                        <Col>
                                            <EditEmployeeModal
                                                positions={["backend","frontend"]}
                                                levels={["J1", "J2", "J3"]}
                                                departments={["Finance", "Human Resources", "Development"]}
                                                worker={worker}
                                            />
                                        </Col>
                                        <Col>
                                            <DeleteEmployeeModal
                                                workerNatId={worker.nationalId}
                                                setWorkers={setEmployees}
                                                workers={employees}
                                            />
                                        </Col>
                                    </Row>
                                </td>
                            </tr>
                        )}
                    )}
                    </tbody>
                </Table>
                <MyPagination maxPage={totalPages} setPage={setCurrentPage}/>
            </div>
        </div>
    );

}

export default EmployeeList;
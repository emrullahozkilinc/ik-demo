import React, {useEffect, useState} from "react";
import {Col, Input, Row, Table} from "reactstrap";
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

const EmployeeList = () => {
    const [employees, setEmployees] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [employeesPerPage] = useState(6);
    const [searchInput, setSearchInput] = useState("");
    const [sortInput, setSortInput] = useState("")

    const {user} = useAuth();

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/app/employees",
            {
                headers: {
                    'Authorization': "Bearer " + user.token
                }
            })
            .then(r => {
                setEmployees(r.data)
            })
    },[])

    const indexOfLastEmployee = currentPage * employeesPerPage;
    const indexOfFirstEmployee = indexOfLastEmployee - employeesPerPage;
    let currentEmployees = employees.slice(indexOfFirstEmployee, indexOfLastEmployee);
    const totalPages = Math.ceil(employees.length/employeesPerPage);

    const searchChange = (e) => {
        setSearchInput(e.target.value);
    }

    const sortChange = (e) => {
        setSortInput(e.target.value);
    }

    return (
        <div className="general-table">
            <div className="table-wrapper">
                <div className="table-title">
                    <Row>
                        <Col><h2>Employees</h2></Col>
                        <Col md={{size:3}}><Input type="search" placeholder="Search by National Id" aria-label="Search" onChange={searchChange}/></Col>
                        <Col md={{size:2}}>
                            <Input type={"select"} onChange={sortChange}>
                                <option disabled selected value>Sort By</option>
                                <option value="firstName">First Name</option>
                                <option value="lastName">Last Name</option>
                                <option value="nationalId">National Id</option>
                                <option value="position">Position</option>
                                <option value="startDate">Date of Start</option>
                                <option value="salary">Salary</option>
                                <option value="level">Level</option>
                                <option value="title">Title</option>
                                <option value="bornDate">Born Date</option>
                                <option value="department">Department</option>
                                <option value="email">Email</option>
                                <option value="phone">Phone</option>
                            </Input>
                        </Col>
                        <Col md={{size:2}} >
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

                    {currentEmployees
                        .filter(employee => {
                            if (searchInput !== "") {
                                return (employee.nationalId + '').includes(searchInput);
                            }else
                                return true;
                        })
                        .sort((employee1,employee2) => (employee1[sortInput] > employee2[sortInput] ? 1 : -1))
                        .map((worker, i) => {
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
                                                setWorkers={setEmployees}
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
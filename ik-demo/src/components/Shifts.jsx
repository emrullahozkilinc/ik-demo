import {Col, Input, Row, Table} from "reactstrap";
import MyPagination from "../Pagination";
import React, {useEffect, useState} from "react";
import '../style/css/tables.css'
import EditShiftModal from "./modals/shift/EditShiftModal";
import DeleteShiftModal from "./modals/shift/DeleteShiftModal";
import axios from "axios";
import {useAuth} from "./auth/AuthContext";

const Shifts = () => {
    const [shifts, setShifts] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [shiftsPerPage] = useState(6);
    const [searchInput, setSearchInput] = useState("");
    const [sortInput, setSortInput] = useState("")

    const {user} = useAuth();

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/app/shifts",
            {
                headers: {
                    'Authorization': "Bearer " + user.token
                }
            })
            .then(r => {
                if (JSON.stringify(shifts) !== JSON.stringify(r.data)){
                    setShifts(r.data)
                }
            })
            .catch(e => {
                console.log(e)
            })
    }, [shifts])

    const indexOfLastShift = currentPage * shiftsPerPage;
    const indexOfFirstShift = indexOfLastShift - shiftsPerPage;
    let currentShifts = shifts.slice(indexOfFirstShift, indexOfLastShift);
    const totalPages = Math.ceil(shifts.length/shiftsPerPage);

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
                        <Col><h2>Shifts</h2></Col>
                        <Col md={{size:3}}><Input type="search" placeholder="Search by National Id" aria-label="Search" onChange={searchChange}/></Col>
                        <Col md={{size:2}}>
                            <Input type={"select"} onChange={sortChange}>
                                <option disabled selected value>Sort By</option>
                                <option value="employeeNationalId">National Id</option>
                                <option value="date">Shift Date</option>
                                <option value="hours">Shift Time (Hours)</option>
                                <option value="description">Description</option>
                            </Input>
                        </Col>
                    </Row>
                </div>
                <Table responsive hover size="" className="employee-table2">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>National Id</th>
                        <th>Shift Date</th>
                        <th>Shift Time (Hours)</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    {currentShifts
                        .filter(shift => {
                            if (searchInput !== "") {
                                return shift.employeeNationalId == searchInput
                            }else
                                return true;
                        })
                        .sort((shift1,shift2) => (shift1[sortInput] > shift2[sortInput] ? 1 : -1))
                        .map((shift, i) => {
                        return (
                            <tr key={i}>
                                <th scope="row">{indexOfFirstShift+i+1}</th>
                                <td>{shift.employeeNationalId}</td>
                                <td>{new Date(shift.date).toLocaleDateString()}</td>
                                <td>{shift.hours}</td>
                                <td>{shift.description}</td>
                                <td>
                                    <Row>
                                        <Col>
                                            <EditShiftModal
                                                setShifts={setShifts}
                                                shift={shift}
                                                shifts={shifts}
                                            />
                                        </Col>
                                        <Col>
                                            <DeleteShiftModal
                                                shiftId={shift.id}
                                                setShifts={setShifts}
                                                shifts={shifts}
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

export default Shifts;
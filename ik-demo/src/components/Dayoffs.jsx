import {Col, Input, Row, Table} from "reactstrap";
import MyPagination from "../Pagination";
import React, {useEffect, useState} from "react";
import '../style/css/tables.css'
import EditDayoffModal from "./modals/dayoff/EditDayoffModal";
import DeleteDayoffModal from "./modals/dayoff/DeleteDayoffModal";
import {useAuth} from "./auth/AuthContext";
import axios from "axios";

const Dayoffs = () => {
    const [dayoffs, setDayoffs] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [dayoffsPerPage] = useState(6);
    const [searchInput, setSearchInput] = useState("");
    const [sortInput, setSortInput] = useState("")

    const {user} = useAuth();

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/app/dayoffs",
            {
                headers: {
                    'Authorization': "Bearer " + user.token
                }
            })
            .then(r => {
                setDayoffs(r.data)
            })
            .catch(e => {
                console.log(e)
            })
    }, [])

    const indexOfLastDayoff = currentPage * dayoffsPerPage;
    const indexOfFirstDayoff = indexOfLastDayoff - dayoffsPerPage;
    let currentDayoffs = dayoffs.slice(indexOfFirstDayoff, indexOfLastDayoff);
    const totalPages = Math.ceil(dayoffs.length/dayoffsPerPage);

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
                        <Col ><h2>Dayoffs</h2></Col>
                        <Col md={{size:3}}><Input type="search" placeholder="Search by National Id" aria-label="Search" onChange={searchChange}/></Col>
                        <Col md={{size:2}}>
                                <Input type={"select"} onChange={sortChange}>
                                    <option disabled selected value>Sort By</option>
                                    <option value="employeeNationalId">National Id</option>
                                    <option value="leaveType">Leave Type</option>
                                    <option value="daysOfLeave">Days Of Leave</option>
                                    <option value="dateOfStart">Start Date</option>
                                    <option value="dateOfEnd">End Date</option>
                                    <option value="description">Description</option>
                                    <option value="dateOfReturn">Return Date</option>
                                </Input>
                        </Col>

                    </Row>
                </div>
                <Table responsive hover size="" className="employee-table2">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>National Id</th>
                        <th>Leave Type</th>
                        <th>Days Of Leave</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Description</th>
                        <th>Return Date</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    {currentDayoffs
                        .filter(dayoff => {
                            if (searchInput !== "") {
                                return (dayoff.employeeNationalId + '').includes(searchInput);
                            }else
                                return true;
                        })
                        .sort((dayoff1,dayoff2) => (dayoff1[sortInput] > dayoff2[sortInput] ? 1 : -1))
                        .map((dayoff, i) => {
                            return (
                                <tr key={i}>
                                    <th scope="row">{indexOfFirstDayoff+i+1}</th>
                                    <td>{dayoff.employeeNationalId}</td>
                                    <td>{dayoff.leaveType}</td>
                                    <td>{dayoff.daysOfLeave}</td>
                                    <td>{new Date(dayoff.dateOfStart).toLocaleDateString()}</td>
                                    <td>{new Date(dayoff.dateOfEnd).toLocaleDateString()}</td>
                                    <td>{dayoff.description}</td>
                                    <td>{new Date(dayoff.dateOfReturn).toLocaleDateString()}</td>
                                    <td>
                                        <Row>
                                            <Col>
                                                <EditDayoffModal
                                                    setDayoffs={setDayoffs}
                                                    dayoff={dayoff}
                                                    dayoffs={dayoffs}/>
                                            </Col>
                                            <Col>
                                                <DeleteDayoffModal
                                                    dayoffId={dayoff.id}
                                                    setDayoffs={setDayoffs}
                                                    dayoffs={dayoffs}
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

export default Dayoffs;
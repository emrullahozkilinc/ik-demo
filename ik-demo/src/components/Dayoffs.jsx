import {Col, Row, Table} from "reactstrap";
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

    const {user} = useAuth();

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/app/dayoffs",
            {
                headers: {
                    'Authorization': "Bearer " + user.token
                }
            })
            .then(r => {
                if (JSON.stringify(dayoffs) !== JSON.stringify(r.data)){
                    setDayoffs(r.data)
                }
            })
            .catch(e => {
                console.log(e)
            })
    }, [dayoffs])

    const indexOfLastDayoff = currentPage * dayoffsPerPage;
    const indexOfFirstDayoff = indexOfLastDayoff - dayoffsPerPage;
    const currentDayoffs = dayoffs.slice(indexOfFirstDayoff, indexOfLastDayoff);
    const totalPages = Math.ceil(dayoffs.length/dayoffsPerPage);

    return (
        <div className="general-table">
            <div className="table-wrapper">
                <div className="table-title">
                    <Row>
                        <Col md={{size:10}}><h2>Dayoffs</h2></Col>
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

                    {currentDayoffs.map((dayoff, i) => {
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
import {Col, Row, Table} from "reactstrap";
import MyPagination from "../Pagination";
import React, {useEffect, useState} from "react";
import '../style/css/tables.css'
import EditDayoffModal from "./modals/dayoff/EditDayoffModal";
import DeleteDayoffModal from "./modals/dayoff/DeleteDayoffModal";

const Dayoffs = ({allDayoffs}) => {
    const [dayoffs, setDayoffs] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [dayoffsPerPage] = useState(6);

    useEffect(() => {
        setDayoffs(allDayoffs);
    })

    const indexOfLastDayoff = currentPage * dayoffsPerPage;
    const indexOfFirstDayoff = indexOfLastDayoff - dayoffsPerPage;
    const currentDayoffs = allDayoffs.slice(indexOfFirstDayoff, indexOfLastDayoff);
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
                                <td>{dayoff.natId}</td>
                                <td>{dayoff.leaveType}</td>
                                <td>{dayoff.daysOfLeave}</td>
                                <td>{new Date(dayoff.startDate).toLocaleDateString()}</td>
                                <td>{new Date(dayoff.endDate).toLocaleDateString()}</td>
                                <td>{dayoff.description}</td>
                                <td>{new Date(dayoff.returnDate).toLocaleDateString()}</td>
                                <td>
                                    <Row>
                                        <Col>
                                            <EditDayoffModal/>
                                        </Col>
                                        <Col>
                                            <DeleteDayoffModal/>
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
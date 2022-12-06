import {Col, Row, Table} from "reactstrap";
import MyPagination from "../Pagination";
import React, {useEffect, useState} from "react";
import '../style/css/tables.css'
import EditShiftModal from "./modals/shift/EditShiftModal";
import DeleteShiftModal from "./modals/shift/DeleteShiftModal";

const Shifts = ({allShifts}) => {
    const [shifts, setShifts] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [shiftsPerPage] = useState(6);

    useEffect(() => {
        setShifts(allShifts);
    })

    const indexOfLastShift = currentPage * shiftsPerPage;
    const indexOfFirstShift = indexOfLastShift - shiftsPerPage;
    const currentShifts = allShifts.slice(indexOfFirstShift, indexOfLastShift);
    const totalPages = Math.ceil(shifts.length/shiftsPerPage);

    return (
        <div className="general-table">
            <div className="table-wrapper">
                <div className="table-title">
                    <Row>
                        <Col md={{size:10}}><h2>Shifts</h2></Col>
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

                    {currentShifts.map((shift, i) => {
                        return (
                            <tr key={i}>
                                <th scope="row">{indexOfFirstShift+i+1}</th>
                                <td>{shift.natId}</td>
                                <td>{new Date(shift.date).toLocaleDateString()}</td>
                                <td>{shift.hours}</td>
                                <td>{shift.description}</td>
                                <td>
                                    <Row>
                                        <Col>
                                            <EditShiftModal shift={shift}/>
                                        </Col>
                                        <Col>
                                            <DeleteShiftModal/>
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
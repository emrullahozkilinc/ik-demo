import React, {useEffect, useState} from "react";
import {useAuth} from "./auth/AuthContext";
import axios from "axios";
import {Col, Row, Table} from "reactstrap";
import MyPagination from "../Pagination";

const History = () => {
    const [histories, setHistories] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [historyPerPage] = useState(6);

    const {user} = useAuth();

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/app/history",
            {
                headers: {
                    'Authorization': "Bearer " + user.token
                }
            })
            .then(r => {
                if (JSON.stringify(histories) !== JSON.stringify(r.data)){
                    setHistories(r.data);
                }
            })
            .catch(e => {
                console.log(e);
            })
    }, [])

    const indexOfLastHistory = currentPage * historyPerPage;
    const indexOfFirstHistory = indexOfLastHistory - historyPerPage;
    let currentHistories = histories.slice(indexOfFirstHistory, indexOfLastHistory);
    const totalPages = Math.ceil(histories.length/historyPerPage);

    return (
        <div className="general-table">
            <div className="table-wrapper">
                <div className="table-title">
                    <Row>
                        <Col ><h2>History</h2></Col>
                    </Row>
                </div>
                <Table responsive hover size="" className="employee-table2">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Time</th>
                        <th>Process</th>
                        <th>Unit</th>
                    </tr>
                    </thead>
                    <tbody>

                    {currentHistories.reverse().map((history, i) => {
                        return (
                                <tr key={i}>
                                    <th scope="row">{indexOfFirstHistory+i+1}</th>
                                    <td>{history.time}</td>
                                    <td>{history.historyType}</td>
                                    <td>{history.entityName}</td>
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

export default History;
import {Col, Row, Table} from "reactstrap";
import MyPagination from "../Pagination";
import React, {useEffect, useState} from "react";
import '../style/css/tables.css'
import EditSpendingModal from "./modals/spending/EditSpendingModal";
import DeleteSpendingModal from "./modals/spending/DeleteSpendingModal";

const Spendings = ({allSpendings}) => {
    const [spendings, setSpendings] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [spendingsPerPage] = useState(6);

    useEffect(() => {
        setSpendings(allSpendings);
    })

    const indexOfLastSpending = currentPage * spendingsPerPage;
    const indexOfFirstSpending = indexOfLastSpending - spendingsPerPage;
    const currentSpendings = allSpendings.slice(indexOfFirstSpending, indexOfLastSpending);
    const totalPages = Math.ceil(spendings.length/spendingsPerPage);

    return (
        <div className="general-table">
            <div className="table-wrapper">
                <div className="table-title">
                    <Row>
                        <Col md={{size:10}}><h2>Spendings</h2></Col>
                    </Row>
                </div>
                <Table responsive hover size="" className="employee-table2">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>National Id</th>
                        <th>Spending Type</th>
                        <th>Amount</th>
                        <th>Receipt Date</th>
                        <th>Tax Rate</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    {currentSpendings.map((spending, i) => {
                        return (
                            <tr key={i}>
                                <th scope="row">{indexOfFirstSpending+i+1}</th>
                                <td>{spending.natId}</td>
                                <td>{spending.type}</td>
                                <td>{new Intl.NumberFormat('tr-TR', { style: 'currency', currency: 'TRY' }).format(spending.amount)}</td>
                                <td>{new Date(spending.receiptDate).toLocaleDateString()}</td>
                                <td>{spending.taxRate}</td>
                                <td>{spending.description}</td>
                                <td>
                                    <Row>
                                        <Col>
                                            <EditSpendingModal/>
                                        </Col>
                                        <Col>
                                            <DeleteSpendingModal/>
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

export default Spendings;
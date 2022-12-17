import {Col, Input, Row, Table} from "reactstrap";
import MyPagination from "../Pagination";
import React, {useEffect, useState} from "react";
import '../style/css/tables.css'
import EditSpendingModal from "./modals/spending/EditSpendingModal";
import DeleteSpendingModal from "./modals/spending/DeleteSpendingModal";
import {useAuth} from "./auth/AuthContext";
import axios from "axios";

const Spendings = () => {
    const [spendings, setSpendings] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [spendingsPerPage] = useState(6);
    const [searchInput, setSearchInput] = useState("");
    const [sortInput, setSortInput] = useState("")

    const {user} = useAuth();

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/app/spendings",
            {
                headers: {
                    'Authorization': "Bearer " + user.token
                }
            })
            .then(r => {
                setSpendings(r.data)
            })
            .catch(e => {
                console.log(e)
            })
    }, [])

    const indexOfLastSpending = currentPage * spendingsPerPage;
    const indexOfFirstSpending = indexOfLastSpending - spendingsPerPage;
    let currentSpendings = spendings.slice(indexOfFirstSpending, indexOfLastSpending);
    const totalPages = Math.ceil(spendings.length/spendingsPerPage);

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
                        <Col><h2>Spendings</h2></Col>
                        <Col md={{size:3}}><Input type="search" placeholder="Search by National Id" aria-label="Search" onChange={searchChange}/></Col>
                        <Col md={{size:2}}>
                            <Input type={"select"} onChange={sortChange}>
                                <option disabled selected value>Sort By</option>
                                <option value="employeeNationalId">National Id</option>
                                <option value="spendingType">Spending Type</option>
                                <option value="amount">Amount</option>
                                <option value="receiptDate">Receipt Date</option>
                                <option value="taxRate">Tax Rate</option>
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
                        <th>Spending Type</th>
                        <th>Amount</th>
                        <th>Receipt Date</th>
                        <th>Tax Rate</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    {currentSpendings
                        .filter(spending => {
                            if (searchInput !== "") {
                                return (spending.employeeNationalId + '').includes(searchInput);
                            }else
                                return true;
                        })
                        .sort((spending1,spending2) => (spending1[sortInput] > spending2[sortInput] ? 1 : -1))
                        .map((spending, i) => {
                        return (
                            <tr key={i}>
                                <th scope="row">{indexOfFirstSpending+i+1}</th>
                                <td>{spending.employeeNationalId}</td>
                                <td>{spending.spendingType}</td>
                                <td>{new Intl.NumberFormat('tr-TR', { style: 'currency', currency: 'TRY' }).format(spending.amount)}</td>
                                <td>{new Date(spending.receiptDate).toLocaleDateString()}</td>
                                <td>{spending.taxRate}</td>
                                <td>{spending.description}</td>
                                <td>
                                    <Row>
                                        <Col>
                                            <EditSpendingModal
                                                setSpendings={setSpendings}
                                                spending={spending}
                                                spendings={spendings}
                                            />
                                        </Col>
                                        <Col>
                                            <DeleteSpendingModal
                                                spendingId={spending.id}
                                                setSpendings={setSpendings}
                                                spendings={spendings}
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

export default Spendings;
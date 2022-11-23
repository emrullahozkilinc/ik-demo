import React from 'react';
import {Table} from "reactstrap";
import DayoffModal from './DayoffModal'
import SpendingModal from './SpendingModal'
import ShiftModal from './ShiftModal'

class Users extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            workers: []
        }
    }

    componentDidMount() {
        this.setState({workers: this.props.workers})
    }

    render() {

        return (
            <div>
                <Table responsive hover size="">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>National Id</th>
                        <th>Position</th>
                        <th>Date of Start</th>
                        <th>Salary</th>
                        <th>Department</th>
                        <th>Email</th>
                        <th>Add Dayoff</th>
                        <th>Add Spending</th>
                        <th>Add Shift</th>
                    </tr>
                    </thead>
                    <tbody>

                    {this.state.workers.map((worker, i) => {
                        return (
                        <tr key={i}>
                            <th scope="row">{i+1}</th>
                            <td>{worker.firstName}</td>
                            <td>{worker.lastname}</td>
                            <td>{worker.natId}</td>
                            <td>{worker.position}</td>
                            <td>{worker.dateOfStart.toLocaleDateString()}</td>
                            <td>{worker.salary}</td>
                            <td>{worker.department}</td>
                            <td>{worker.email}</td>
                            <td><DayoffModal/></td>
                            <td><SpendingModal/></td>
                            <td><ShiftModal/></td>
                        </tr>
                        )}
                    )}
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default Users;
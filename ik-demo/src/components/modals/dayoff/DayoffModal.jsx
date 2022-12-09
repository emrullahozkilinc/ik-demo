import React, {useState} from 'react';
import {
    Button,
    Col,
    Form,
    FormGroup,
    Input,
    Label,
    Modal,
    ModalHeader,
    ModalBody,
    ModalFooter,
    Row,
    UncontrolledAlert
} from 'reactstrap'
import {useAuth} from "../../auth/AuthContext";
import axios from "axios";

function DayoffModal(props) {

    const {user} = useAuth();

    const [errors, setErrors] = useState([]);
    const [isOpen, setIsOpen] = useState(false);
    const [dayoff, setDayoff] = useState({
        'leaveType' : '',
        'daysOfLeave' : 0,
        'dateOfStart' : new Date(),
        'dateOfEnd' : new Date(),
        'dateOfReturn' : new Date(),
        'description' : ''
    });

    const toggle = (e) => {
        if (e.target.name === 'addDayoffSuccess'){
            axios.post("http://localhost:8080/api/v1/app/dayoffs",
                {
                        'employeeNationalId' : props.employeeNationalId,
                        'leaveType' : dayoff.leaveType,
                        'daysOfLeave' : dayoff.daysOfLeave,
                        'dateOfStart' : dayoff.dateOfStart,
                        'dateOfEnd' : dayoff.dateOfEnd,
                        'dateOfReturn' : dayoff.dateOfReturn,
                        'description' : dayoff.description
                    }, {
                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                }
            )
                .then(r => {
                    console.log(r)
                    setErrors([]);
                    setIsOpen(!isOpen);
                })
                .catch(e => {
                    console.log(e);
                    setErrors(e.response.data);
                })
        } else
            setIsOpen(!isOpen);
    }

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...dayoff};
        item[name] = value;
        setDayoff(item);
    }

    return (
        <div>
            <Button color="primary" outline onClick={toggle} name="addDayoffButton">Add Dayoff</Button>
            <Modal isOpen={isOpen} toggle={toggle} centered={true}>
            <ModalHeader>Add Dayoff</ModalHeader>
            <ModalBody>
                {errors.map(err =>
                    <UncontrolledAlert color="danger">
                        {err.message}
                    </UncontrolledAlert>
                )}
                <Form>
                    <Row>
                        <Col>
                            <FormGroup floating>
                                <Input id="leaveType" name="leaveType" placeholder="Dayoff Type" type="select" onChange={handleChange}>
                                    <option disabled selected value> -- select an option -- </option>
                                    <option>ANNUAL_PERMIT</option>
                                    <option>MATERNITY_LEAVE</option>
                                    <option>SICK_LEAVE</option>
                                </Input>
                                <Label for="leaveType">Leave Type</Label>
                            </FormGroup>
                        </Col>
                        <Col>
                            <FormGroup floating>
                                <Input id="daysOfLeave" name="daysOfLeave" placeholder="Days of Leave" type="number" onChange={handleChange}/>
                                <Label for="daysOfLeave">Days of Leave</Label>
                            </FormGroup>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <FormGroup floating>
                                <Input id="dateOfStart" name="dateOfStart" placeholder="Date Of Start" type="datetime-local" onChange={handleChange}/>
                                <Label for="dateOfStart">Date Of Start</Label>
                            </FormGroup>
                        </Col>
                        <Col>
                            <FormGroup floating>
                                <Input id="dateOfEnd" name="dateOfEnd" placeholder="Date Of End" type="datetime-local" onChange={handleChange}/>
                                <Label for="dateOfEnd">Date Of End</Label>
                            </FormGroup>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <FormGroup floating>
                                <Input id="description" name="description" placeholder="Description" type="text-area" onChange={handleChange}/>
                                <Label for="description">Description Of Leave</Label>
                            </FormGroup>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <FormGroup floating>
                                <Input id="dateOfReturn" name="dateOfReturn" placeholder="Date Of Return" type="datetime-local" onChange={handleChange}/>
                                <Label for="dateOfReturn">Date Of Return</Label>
                            </FormGroup>
                        </Col>
                    </Row>
                </Form>
            </ModalBody>

            <ModalFooter>
                <Button color="success" name="addDayoffSuccess" onClick={toggle}>Add Leave</Button>{' '}
                <Button color="secondary" onClick={toggle}>Cancel</Button>
            </ModalFooter>
        </Modal>
        </div>
    );
}

export default DayoffModal;
import React, {useState} from 'react';
import '../../../style/css/add-button.css'
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody, ModalFooter, Row} from 'reactstrap'
import {useAuth} from "../../auth/AuthContext";
import axios from "axios";

function ShiftModal(props) {

    const {user} = useAuth();

    const [isOpen, setIsOpen] = useState(false);
    const [shift, setShift] = useState({
        'date': new Date(),
        'hours': 0,
        'description': ''
    })

    const toggle = (e) => {
        if (e.target.name === 'addShiftSuccess'){
            axios.post("http://localhost:8080/api/v1/app/shifts",
                {
                    'employeeNationalId' : props.employeeNationalId,
                    'date': shift.date,
                    'hours': shift.hours,
                    'description': shift.description
                }, {
                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                })
                .then(r => console.log(r))
                .catch(e => console.log(e))
        }
        setIsOpen(!isOpen)
    }

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...shift};
        item[name] = value;
        setShift(item);
    }

    return (
        <div>
            <Button color="primary" outline onClick={toggle}>Add Shift</Button>
            <Modal isOpen={isOpen} toggle={toggle} centered={true}>
                <ModalHeader>Add Shift</ModalHeader>
                <ModalBody>
                    <Form>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfShift" placeholder="Date Of Shift" type="datetime-local" name="date" onChange={handleChange}/>
                                    <Label for="dateOfShift">Date Of Shift</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="shiftHours" placeholder="Shift Hours" type="number" name="hours" onChange={handleChange}/>
                                    <Label for="shiftHours">Shift Time (Hours)</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="descriptionOfShift" placeholder="Description" type="text-area" name="description" onChange={handleChange}/>
                                    <Label for="descriptionOfShift">Description Of Shift</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>

                <ModalFooter>
                    <Button color="success" onClick={toggle} name="addShiftSuccess">Add Shift</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default ShiftModal;
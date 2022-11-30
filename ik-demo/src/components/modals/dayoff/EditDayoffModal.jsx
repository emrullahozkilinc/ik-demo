import React, {useState} from 'react';
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody, ModalFooter, Row} from 'reactstrap'
import '../../../style/css/add-employee.css'

function EditDayoffModal() {

    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => {setIsOpen(!isOpen)}

    return (
        <div>
            <a href="#!" onClick={toggle} className="editEmployeeButton">
                <i className="fa-solid fa-pencil"></i>
            </a>
            <Modal isOpen={isOpen} toggle={toggle} centered={true}>
                <ModalHeader>Edit Dayoff</ModalHeader>
                <ModalBody>
                    <Form>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="leaveType" placeholder="Dayoff Type" type="select" >
                                        <option>Annual Permit</option>
                                        <option>Maternity Leave</option>
                                        <option>Sick Leave</option>
                                    </Input>
                                    <Label for="leaveType">Leave Type</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="daysOfLeave" placeholder="Days of Leave" type="number"/>
                                    <Label for="daysOfLeave">Days of Leave</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfStart" placeholder="Date Of Start" type="datetime-local" />
                                    <Label for="dateOfStart">Date Of Start</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfEnd" placeholder="Date Of End" type="datetime-local" />
                                    <Label for="dateOfEnd">Date Of End</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="description" placeholder="Description" type="text-area" />
                                    <Label for="description">Description Of Leave</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfReturn" placeholder="Date Of Return" type="datetime-local" />
                                    <Label for="dateOfReturn">Date Of Return</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>

                <ModalFooter>
                    <Button color="primary" onClick={toggle}>Edit Leave</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default EditDayoffModal;
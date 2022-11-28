import React, {useState} from 'react';
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody, ModalFooter, Row} from 'reactstrap'
import '../../style/css/add-employee.css'

function AddEmployeeModal(props) {

    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => {setIsOpen(!isOpen)}

    return (
        <div>
            <Button id="add-employee" color="success" onClick={toggle}>Add Employee</Button>
            <Modal isOpen={isOpen} toggle={toggle} centered={true} size={"lg"}>
                <ModalHeader>Add Employee</ModalHeader>
                <ModalBody>
                    <Form>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addFirstName" placeholder="Firstname" type="text" />
                                    <Label for="addFirstName">Firstname</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addLastName" placeholder="Lastname" type="text" />
                                    <Label for="addLastName">Lastname</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addNationalId" placeholder="National Id" type="number" />
                                    <Label for="addNationalId">National Id</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addPosition" placeholder="Position" type="select" >
                                        {props.positions.map( (position, i) => {
                                            return (
                                            <option key={i}>{position}</option>
                                            )
                                        })}
                                    </Input>
                                    <Label for="addPosition">Position</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addDateOfStart" placeholder="Date Of Start" type="date" />
                                    <Label for="addDateOfStart">Date Of Start</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addBornDate" placeholder="Born Date" type="date" />
                                    <Label for="addBornDate">Born Date</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addSalary" placeholder="Add Salary" type="number" />
                                    <Label for="addSalary">Add Salary (TL)</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addLevel" placeholder="Level" type="select" >
                                        {props.levels.map( (level, i) => {
                                            return (<option key={i}>{level}</option>)
                                        })}
                                    </Input>
                                    <Label for="addLevel">Level</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addTitle" placeholder="Title" type="text" />
                                    <Label for="addTitle">Title</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addDepartment" placeholder="Department" type="select" >
                                        {props.departments.map( (department, i) => {
                                            return (<option key={i}>{department}</option>)
                                        })}
                                    </Input>
                                    <Label for="addDepartment">Department</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addEmail" placeholder="Email" type="email" />
                                    <Label for="addEmail">Email</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addPhone" placeholder="Phone" type="tel" />
                                    <Label for="addPhone">Phone</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addAddress" placeholder="Address" type="text-area" />
                                    <Label for="addAddress">Address</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addCountry" placeholder="Country" type="text" />
                                    <Label for="addCountry">Country</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addCity" placeholder="City" type="text" />
                                    <Label for="addCity">City</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addCode" placeholder="Postal Code" type="number" />
                                    <Label for="addCode">Postal Code</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>
                <ModalFooter>
                    <Button color="success" onClick={toggle}>Add Employee</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>

        </div>
    );
}

export default AddEmployeeModal;
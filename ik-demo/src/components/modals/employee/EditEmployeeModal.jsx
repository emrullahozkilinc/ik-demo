import React, {useState} from 'react';
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody, ModalFooter, Row} from 'reactstrap'
import '../../../style/css/add-employee.css'

function EditEmployeeModal(props) {


    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => {setIsOpen(!isOpen)}

    return (
        <div>
            <a href="#!" onClick={toggle} className="editEmployeeButton">
                <i className="fa-solid fa-pencil"></i>
            </a>
            <Modal isOpen={isOpen} toggle={toggle} centered={true} size={"lg"}>
                <ModalHeader>Edit Employee</ModalHeader>
                <ModalBody>
                    <Form>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editFirstName" placeholder="Firstname" type="text" />
                                    <Label for="editFirstName">Firstname</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editLastName" placeholder="Lastname" type="text" />
                                    <Label for="editLastName">Lastname</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editNationalId" placeholder="National Id" type="number" />
                                    <Label for="editNationalId">National Id</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editPosition" placeholder="Position" type="select" >
                                        {props.positions.map( (position, i) => {
                                            return (
                                                <option key={i}>{position}</option>
                                            )
                                        })}
                                    </Input>
                                    <Label for="editPosition">Position</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editDateOfStart" placeholder="Date Of Start" type="date" />
                                    <Label for="editDateOfStart">Date Of Start</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editBornDate" placeholder="Born Date" type="date" />
                                    <Label for="editBornDate">Born Date</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editSalary" placeholder="Edit Salary" type="number" />
                                    <Label for="editSalary">Edit Salary (TL)</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editLevel" placeholder="Level" type="select" >
                                        {props.levels.map( (level, i) => {
                                            return (<option key={i}>{level}</option>)
                                        })}
                                    </Input>
                                    <Label for="editLevel">Level</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editTitle" placeholder="Title" type="text" />
                                    <Label for="editTitle">Title</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editDepartment" placeholder="Department" type="select" >
                                        {props.departments.map( (department, i) => {
                                            return (<option key={i}>{department}</option>)
                                        })}
                                    </Input>
                                    <Label for="editDepartment">Department</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editEmail" placeholder="Email" type="email" />
                                    <Label for="editEmail">Email</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editPhone" placeholder="Phone" type="tel" />
                                    <Label for="editPhone">Phone</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editEditress" placeholder="Editress" type="text-area" />
                                    <Label for="editEditress">Editress</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editCountry" placeholder="Country" type="text" />
                                    <Label for="editCountry">Country</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editCity" placeholder="City" type="text" />
                                    <Label for="editCity">City</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editCode" placeholder="Postal Code" type="number" />
                                    <Label for="editCode">Postal Code</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>
                <ModalFooter>
                    <Button color="success" onClick={toggle}>Edit Employee</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>

        </div>
    );
}

export default EditEmployeeModal;
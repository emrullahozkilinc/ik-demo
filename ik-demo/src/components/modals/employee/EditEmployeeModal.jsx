import React, {useState} from 'react';
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody, ModalFooter, Row} from 'reactstrap'
import '../../../style/css/add-button.css'
import axios from "axios";
import {useAuth} from "../../auth/AuthContext";

function EditEmployeeModal(props) {

    const [isOpen, setIsOpen] = useState(false);
    let employee = props.worker;

    const {user} = useAuth();

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...employee};
        if (name === 'address' || name === 'country' || name === 'city' || name === 'postalCode')
            item['address'][name] = value;
        else
            item[name] = value;
        employee = item;
    }

    const toggle = (e) => {
        setIsOpen(!isOpen)
        if (e.target.name==='editEmpSuccess') {
            axios.put("http://localhost:8080/api/v1/app/employees/" + props.worker.nationalId,
                {
                    'firstName': employee.firstName,
                    'lastName': employee.lastName,
                    'nationalId': employee.nationalId,
                    'position': employee.position,
                    'startDate': employee.startDate,
                    'bornDate': employee.bornDate,
                    'salary': employee.salary,
                    'level': employee.level,
                    'title': employee.title,
                    'department': employee.department,
                    'email': employee.email,
                    'phone': employee.phone,
                    'address': {
                        'address': employee.address.address,
                        'city': employee.address.city,
                        'country': employee.address.country,
                        'postalCode': employee.address.postalCode

                    }}, {

                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                }
            )
                .then(r => {
                    props.setEmployees([...props.employees,r.data])
                })
                .catch(e => {
                    console.log(e)
                })
        }
    }

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
                                    <Input id="editFirstName" placeholder="Firstname" type="text" name={"firstName"}
                                           defaultValue={props.worker.firstName} onChange={handleChange}></Input>
                                    <Label for="editFirstName">Firstname</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editLastName" placeholder="Lastname" type="text" name={"lastName"} defaultValue={props.worker.lastName} onChange={handleChange}/>
                                    <Label for="editLastName">Lastname</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editNationalId" placeholder="National Id" type="number" name={"nationalId"} defaultValue={props.worker.nationalId} onChange={handleChange}/>
                                    <Label for="editNationalId">National Id</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editPosition" placeholder="Position" type="select" name={"position"} defaultValue={props.worker.position} onChange={handleChange}>
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
                                    <Input id="editDateOfStart" placeholder="Date Of Start" type="date" name={"startDate"} defaultValue={new Date(props.worker.startDate).toLocaleDateString("sv-SE")} onChange={handleChange}/>
                                    <Label for="editDateOfStart">Date Of Start</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editBornDate" placeholder="Born Date" type="date" name={"bornDate"} defaultValue={new Date(props.worker.bornDate).toLocaleDateString("sv-SE")} onChange={handleChange}/>
                                    <Label for="editBornDate">Born Date</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editSalary" placeholder="Edit Salary" type="number" name={"salary"} defaultValue={props.worker.salary} onChange={handleChange}/>
                                    <Label for="editSalary">Salary (TL)</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editLevel" placeholder="Level" type="select" name={"level"} defaultValue={props.worker.level} onChange={handleChange}>
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
                                    <Input id="editTitle" placeholder="Title" type="text" name={"title"} defaultValue={props.worker.title} onChange={handleChange}/>
                                    <Label for="editTitle">Title</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editDepartment" placeholder="Department" type="select" name={"department"} defaultValue={props.worker.department} onChange={handleChange}>
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
                                    <Input id="editEmail" placeholder="Email" type="email" name={"email"} defaultValue={props.worker.email} onChange={handleChange}/>
                                    <Label for="editEmail">Email</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editPhone" placeholder="Phone" type="tel" name={"phone"} defaultValue={props.worker.phone} onChange={handleChange}/>
                                    <Label for="editPhone">Phone</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editAddress" placeholder="Address" type="text-area" name={"address"} defaultValue={props.worker.address.address} onChange={handleChange}/>
                                    <Label for="editAddress">Address</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editCountry" placeholder="Country" type="text" name={"country"} defaultValue={props.worker.address.country} onChange={handleChange}/>
                                    <Label for="editCountry">Country</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editCity" placeholder="City" type="text" name={"city"} defaultValue={props.worker.address.city} onChange={handleChange}/>
                                    <Label for="editCity">City</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="editCode" placeholder="Postal Code" type="number" name={"postalCode"} defaultValue={props.worker.address.postalCode} onChange={handleChange}/>
                                    <Label for="editCode">Postal Code</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>
                <ModalFooter>
                    <Button color="success" onClick={toggle} name="editEmpSuccess">Edit Employee</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>

        </div>
    );
}

export default EditEmployeeModal;
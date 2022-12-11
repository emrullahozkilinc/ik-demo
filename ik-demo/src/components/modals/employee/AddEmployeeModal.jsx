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
import '../../../style/css/add-button.css'
import axios from "axios";
import {useAuth} from "../../auth/AuthContext";

function AddEmployeeModal(props) {

    const {user} = useAuth();

    const [errors, setErrors] = useState([]);
    const [isOpen, setIsOpen] = useState(false);
    const [worker, setWorker] = useState({
        'firstName' : '',
        'lastName' : '',
        'nationalId' : 0,
        'position' : '',
        'startDate' : new Date(),
        'bornDate' : new Date(),
        'salary' : 0.0,
        'level' : '',
        'title' : '',
        'department' : '',
        'email' : '',
        'phone' : '',
        'address' : '',
        'city' : '',
        'country' : '',
        'postalCode' : 0

    });

    const toggle = (e) => {
        if (e.target.name==='addEmpSuccess') {
            axios.post("http://localhost:8080/api/v1/app/employees",
                {
                        'firstName': worker.firstName,
                        'lastName': worker.lastName,
                        'nationalId': worker.nationalId,
                        'position': worker.position,
                        'startDate': worker.startDate,
                        'bornDate': worker.bornDate,
                        'salary': worker.salary,
                        'level': worker.level,
                        'title': worker.title,
                        'department': worker.department,
                        'email': worker.email,
                        'phone': worker.phone,
                        'address': {
                            'address': worker.address,
                            'city': worker.city,
                            'country': worker.country,
                            'postalCode': worker.postalCode

                    }}, {

                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                }
                )
                .then(r => {
                    setErrors([]);
                    setIsOpen(!isOpen);
                })
                .catch(e => {
                    setErrors(e.response.data);
                })
        } else
            setIsOpen(!isOpen);
    }

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...worker};
        item[name] = value;
        setWorker(item);
    }

    return (
        <div>
            <button className="btn btn-primary btn-block add-button" color="success" onClick={toggle} style={{paddingRight:'21%',paddingLeft:'21%'}}>Add Employee</button>
            <Modal isOpen={isOpen} toggle={toggle} centered={true} size={"lg"}>
                <ModalHeader>Add Employee</ModalHeader>
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
                                    <Input id="addFirstName" name="firstName" placeholder="Firstname" type="text" onChange={handleChange}/>
                                    <Label for="addFirstName">Firstname</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addLastName" name="lastName" placeholder="Lastname" type="text" onChange={handleChange}/>
                                    <Label for="addLastName">Lastname</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addNationalId" name="nationalId" placeholder="National Id" type="number" onChange={handleChange}/>
                                    <Label for="addNationalId">National Id</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addPosition" name="position" placeholder="Position" type="select" onChange={handleChange}>
                                        <option disabled selected value> -- select an option -- </option>
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
                                    <Input id="addDateOfStart" name="startDate" placeholder="Date Of Start" type="date" onChange={handleChange}/>
                                    <Label for="addDateOfStart">Date Of Start</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addBornDate" name="bornDate" placeholder="Born Date" type="date" onChange={handleChange}/>
                                    <Label for="addBornDate">Born Date</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addSalary" name="salary" placeholder="Add Salary" type="number" onChange={handleChange}/>
                                    <Label for="addSalary">Add Salary (TL)</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addLevel" name="level" placeholder="Level" type="select" onChange={handleChange}>
                                        <option disabled selected value> -- select an option -- </option>
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
                                    <Input id="addTitle" name="title" placeholder="Title" type="text" onChange={handleChange}/>
                                    <Label for="addTitle">Title</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addDepartment" name="department" placeholder="Department" type="select" onChange={handleChange}>
                                        <option disabled selected value> -- select an option -- </option>
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
                                    <Input id="addEmail" name="email" placeholder="Email" type="email" onChange={handleChange}/>
                                    <Label for="addEmail">Email</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addPhone" name="phone" placeholder="Phone" type="tel" onChange={handleChange}/>
                                    <Label for="addPhone">Phone</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addAddress" name="address" placeholder="Address" type="text-area" onChange={handleChange}/>
                                    <Label for="addAddress">Address</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addCountry" name="country" placeholder="Country" type="text" onChange={handleChange}/>
                                    <Label for="addCountry">Country</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addCity" name="city" placeholder="City" type="text" onChange={handleChange}/>
                                    <Label for="addCity">City</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="addCode" name="postalCode" placeholder="Postal Code" type="number" onChange={handleChange}/>
                                    <Label for="addCode">Postal Code</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>
                <ModalFooter>
                    <Button color="success" onClick={toggle} name="addEmpSuccess">Add Employee</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>

        </div>
    );
}

export default AddEmployeeModal;
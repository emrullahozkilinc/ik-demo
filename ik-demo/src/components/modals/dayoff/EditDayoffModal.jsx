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
import {useAuth} from "../../auth/AuthContext";
import axios from "axios";

function EditDayoffModal(props) {

    const [errors, setErrors] = useState([]);
    const [isOpen, setIsOpen] = useState(false);
    let dayoff = props.dayoff;

    const {user} = useAuth();

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...dayoff};
        item[name] = value;
        dayoff = item;
    }

    const toggle = async (e) => {
        if (e.target.name === 'editDayoffSuccess'){
            await axios.put("http://localhost:8080/api/v1/app/dayoffs/" + props.dayoff.id,
                {
                    'leaveType' : dayoff.leaveType,
                    'daysOfLeave' : dayoff.daysOfLeave,
                    'dateOfStart' : dayoff.dateOfStart,
                    'dateOfEnd' : dayoff.dateOfEnd,
                    'dateOfReturn' : dayoff.dateOfReturn,
                    'description' : dayoff.description
                },
                {
                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                }).then(r => {
                    setErrors([]);
                    setIsOpen(!isOpen);
                }).catch(e => {
                    setErrors(e.response.data);
                })
        } else
            setIsOpen(!isOpen);
    }

    return (
        <div>
            <a href="#!" onClick={toggle} className="editEmployeeButton">
                <i className="fa-solid fa-pencil"></i>
            </a>
            <Modal isOpen={isOpen} toggle={toggle} centered={true}>
                <ModalHeader>Edit Dayoff</ModalHeader>
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
                                    <Input id="leaveType" placeholder="Dayoff Type" type="select" onChange={handleChange}
                                           defaultValue={props.dayoff.leaveType} name="leaveType">
                                        <option>ANNUAL_PERMIT</option>
                                        <option>MATERNITY_LEAVE</option>
                                        <option>SICK_LEAVE</option>
                                    </Input>
                                    <Label for="leaveType">Leave Type</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="daysOfLeave" placeholder="Days of Leave" type="number" onChange={handleChange}
                                           defaultValue={props.dayoff.daysOfLeave} name="daysOfLeave"/>
                                    <Label for="daysOfLeave">Days of Leave</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfStart" placeholder="Date Of Start" type="datetime-local" onChange={handleChange}
                                           defaultValue={new Date(props.dayoff.dateOfStart).toLocaleString("sv-SE")}
                                           name="dateOfStart"/>
                                    <Label for="dateOfStart">Date Of Start</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfEnd" placeholder="Date Of End" type="datetime-local" onChange={handleChange}
                                           defaultValue={new Date(props.dayoff.dateOfEnd).toLocaleString("sv-SE")}
                                           name="dateOfEnd"/>
                                    <Label for="dateOfEnd">Date Of End</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="description" placeholder="Description" type="text-area" onChange={handleChange}
                                           defaultValue={props.dayoff.description} name="description"/>
                                    <Label for="description">Description Of Leave</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfReturn" placeholder="Date Of Return" type="datetime-local" onChange={handleChange}
                                           defaultValue={new Date(props.dayoff.dateOfReturn).toLocaleString("sv-SE")}
                                           name="dateOfReturn"/>
                                    <Label for="dateOfReturn">Date Of Return</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>

                <ModalFooter>
                    <Button color="primary" onClick={toggle} name="editDayoffSuccess">Edit Leave</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default EditDayoffModal;
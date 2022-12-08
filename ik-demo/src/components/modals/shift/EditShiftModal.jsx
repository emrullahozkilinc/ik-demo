import React, {useState} from 'react';
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody, ModalFooter, Row} from 'reactstrap'
import '../../../style/css/add-button.css'
import {useAuth} from "../../auth/AuthContext";
import axios from "axios";

function EditShiftModal(props) {

    const [isOpen, setIsOpen] = useState(false);
    let shift = props.shift;

    const {user} = useAuth();

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...shift};
        item[name] = value;
        shift = item;
    }

    const toggle = async (e) => {
        setIsOpen(!isOpen);
        if (e.target.name === 'editShiftSuccess'){
            await axios.put("http://localhost:8080/api/v1/app/shifts/" + props.shift.id,
                {
                    'date': shift.date,
                    'hours': shift.hours,
                    'description': shift.description
                },
                {
                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                }).then(r => {
                // props.setDayoffs([...props.dayoffs, r.data])
            })
        }
    }

    return (
        <div>
            <a href="#!" onClick={toggle} className="editEmployeeButton">
                <i className="fa-solid fa-pencil"></i>
            </a>
            <Modal isOpen={isOpen} toggle={toggle} centered={true}>
                <ModalHeader>Edit Shift</ModalHeader>
                <ModalBody>
                    <Form>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfShift" placeholder="Date Of Shift" type="datetime-local"
                                           defaultValue={new Date(props.shift.date).toLocaleString("sv-SE")}
                                           onChange={handleChange} name="date"/>
                                    <Label for="dateOfShift">Date Of Shift</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="shiftHours" placeholder="Shift Hours" type="number" onChange={handleChange}
                                           defaultValue={props.shift.hours} name="hours"/>
                                    <Label for="shiftHours">Shift Time (Hours)</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="descriptionOfShift" placeholder="Description" type="text-area"
                                           defaultValue={props.shift.description} onChange={handleChange} name="description"/>
                                    <Label for="descriptionOfShift">Description Of Shift</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>

                <ModalFooter>
                    <Button color="primary" onClick={toggle} name="editShiftSuccess">Edit Shift</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default EditShiftModal;
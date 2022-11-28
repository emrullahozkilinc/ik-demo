import React, {useState} from 'react';
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody, ModalFooter, Row} from 'reactstrap'

function ShiftModal(props) {

    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => {setIsOpen(!isOpen)}

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
                                    <Input id="dateOfShift" placeholder="Date Of Shift" type="datetime-local" />
                                    <Label for="dateOfShift">Date Of Shift</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="shiftHours" placeholder="Shift Hours" type="number"/>
                                    <Label for="shiftHours">Shift Time (Hours)</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="descriptionOfShift" placeholder="Description" type="text-area" />
                                    <Label for="descriptionOfShift">Description Of Shift</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>

                <ModalFooter>
                    <Button color="success" onClick={toggle}>Add Shift</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default ShiftModal;
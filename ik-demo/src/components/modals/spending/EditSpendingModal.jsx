import React, {useState} from 'react';
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody, ModalFooter, Row} from 'reactstrap'
import '../../../style/css/add-employee.css'

function EditSpendingModal() {

    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => {setIsOpen(!isOpen)}

    return (
        <div>
            <a href="#!" onClick={toggle} className="editEmployeeButton">
                <i className="fa-solid fa-pencil"></i>
            </a>
            <Modal isOpen={isOpen} toggle={toggle} centered={true}>
                <ModalHeader>Edit Spending</ModalHeader>
                <ModalBody>

                    <Form>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="spendingType" placeholder="Spending Type" type="select" >
                                        <option>Food</option>
                                        <option>Educational</option>
                                        <option>Other</option>
                                    </Input>
                                    <Label for="spendingType">Spending Type</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="spendingAmount" placeholder="Spending Amount" type="number"/>
                                    <Label for="spendingAmount">Spending Amount (TL)</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfReceipt" placeholder="Date Of Receipt" type="date" />
                                    <Label for="dateOfReceipt">Date Of Receipt</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="taxRate" placeholder="Tax Rate" type="select" >
                                        <option>0</option>
                                        <option>1</option>
                                        <option>8</option>
                                        <option>18</option>
                                    </Input>
                                    <Label for="taxRate">Tax Rate (%)</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="descriptionOfSpending" placeholder="Description" type="text-area" />
                                    <Label for="descriptionOfSpending">Description Of Spending</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>

                <ModalFooter>
                    <Button color="primary" onClick={toggle}>Edit Spending</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default EditSpendingModal;
import React, {useState} from 'react';
import {Button, Col, Form, FormGroup, Input, Label, Modal, ModalHeader, ModalBody, ModalFooter, Row} from 'reactstrap'

function SpendingModal(props) {

    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => {setIsOpen(!isOpen)}

    return (
        <div>
            <Button color="primary" outline onClick={toggle}>Add Spending</Button>
            <Modal isOpen={isOpen} toggle={toggle} centered={true}>
                <ModalHeader>Add Spending</ModalHeader>
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
                    <Button color="success" onClick={toggle}>Add Spending</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default SpendingModal;
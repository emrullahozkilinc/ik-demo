import React, {useState} from 'react';
import '../../../style/css/add-button.css'
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
import axios from "axios";
import {useAuth} from "../../auth/AuthContext";

function SpendingModal(props) {

    const {user} = useAuth();

    const [errors, setErrors] = useState([]);
    const [isOpen, setIsOpen] = useState(false);
    const [spending, setSpending] = useState({
        'spendingType': '',
        'amount': 0,
        'receiptDate': new Date(),
        'taxRate': 0,
        'description': ''
    })

    const toggle = async (e) => {
        setIsOpen(!isOpen)
        if (e.target.name === 'addSpendingSuccess'){
            console.log({
                'employeeNationalId': props.employeeNationalId,
                'spendingType': spending.spendingType,
                'amount': spending.amount,
                'receiptDate': spending.receiptDate,
                'taxRate': spending.taxRate,
                'description': spending.description
            })
            await axios.post("http://localhost:8080/api/v1/app/spendings",
                {
                    'employeeNationalId': props.employeeNationalId,
                    'spendingType': spending.spendingType,
                    'amount': spending.amount,
                    'receiptDate': spending.receiptDate,
                    'taxRate': spending.taxRate,
                    'description': spending.description
                },
                {
                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                })
                .then(r => {
                    console.log(r)
                    setErrors([]);
                    setIsOpen(!isOpen);
                })
                .catch(e => {
                    console.log(e);
                    setErrors(e.response.data);
                })
        } else
            setIsOpen(!isOpen);
    }

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...spending};
        item[name] = value;
        setSpending(item);
    }

    return (
        <div>
            <Button color="primary" outline onClick={toggle}>Add Spending</Button>
            <Modal isOpen={isOpen} toggle={toggle} centered={true}>
                <ModalHeader>Add Spending</ModalHeader>
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
                                    <Input id="spendingType" placeholder="Spending Type" type="select" onChange={handleChange} name="spendingType">
                                        <option>FOOD</option>
                                        <option>EDUCATIONAL</option>
                                        <option>OTHER</option>
                                    </Input>
                                    <Label for="spendingType">Spending Type</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="spendingAmount" placeholder="Spending Amount" type="number" onChange={handleChange} name="amount"/>
                                    <Label for="spendingAmount">Spending Amount (TL)</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfReceipt" placeholder="Date Of Receipt" type="date" onChange={handleChange} name="receiptDate"/>
                                    <Label for="dateOfReceipt">Date Of Receipt</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="taxRate" placeholder="Tax Rate" type="select" onChange={handleChange} name="taxRate">
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
                                    <Input id="descriptionOfSpending" placeholder="Description" type="text-area" onChange={handleChange} name="description"/>
                                    <Label for="descriptionOfSpending">Description Of Spending</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>

                <ModalFooter>
                    <Button color="success" onClick={toggle} name="addSpendingSuccess">Add Spending</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default SpendingModal;
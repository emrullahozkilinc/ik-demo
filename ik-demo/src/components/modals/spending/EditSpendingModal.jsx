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

function EditSpendingModal(props) {

    const [errors, setErrors] = useState([]);
    const [isOpen, setIsOpen] = useState(false);
    let spending = props.spending;

    const {user} = useAuth();

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...spending};
        item[name] = value;
        spending = item;
    }

    const toggle = async (e) => {
        setIsOpen(!isOpen);
        if (e.target.name === 'editSpendingSuccess'){
            await axios.put("http://localhost:8080/api/v1/app/spendings/" + props.spending.id,
                {
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
                    setErrors([]);
                    setIsOpen(!isOpen);
                    axios.get("http://localhost:8080/api/v1/app/spendings",
                        {
                            headers: {
                                'Authorization': "Bearer " + user.token
                            }
                        })
                        .then(res => {
                            props.setSpendings(res.data)
                        })
                        .catch(e => {
                            console.log(e)
                        })
                })
                .catch(e => {
                    console.log(e);
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
                <ModalHeader>Edit Spending</ModalHeader>
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
                                    <Input id="spendingType" placeholder="Spending Type" type="select" onChange={handleChange}
                                           defaultValue={props.spending.spendingType} name="spendingType">
                                        <option>FOOD</option>
                                        <option>EDUCATIONAL</option>
                                        <option>OTHER</option>
                                    </Input>
                                    <Label for="spendingType">Spending Type</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="spendingAmount" placeholder="Spending Amount" type="number" onChange={handleChange}
                                           defaultValue={props.spending.amount} name="amount"/>
                                    <Label for="spendingAmount">Spending Amount (TL)</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormGroup floating>
                                    <Input id="dateOfReceipt" placeholder="Date Of Receipt" type="date" onChange={handleChange}
                                           defaultValue={new Date(props.spending.receiptDate).toLocaleDateString("sv-SE")}
                                           name="receiptDate"/>
                                    <Label for="dateOfReceipt">Date Of Receipt</Label>
                                </FormGroup>
                            </Col>
                            <Col>
                                <FormGroup floating>
                                    <Input id="taxRate" placeholder="Tax Rate" type="select" onChange={handleChange}
                                           defaultValue={props.spending.taxRate} name="taxRate">
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
                                    <Input id="descriptionOfSpending" placeholder="Description" type="text-area" onChange={handleChange}
                                           defaultValue={props.spending.description} name="description"/>
                                    <Label for="descriptionOfSpending">Description Of Spending</Label>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Form>
                </ModalBody>

                <ModalFooter>
                    <Button color="primary" onClick={toggle} name="editSpendingSuccess">Edit Spending</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default EditSpendingModal;
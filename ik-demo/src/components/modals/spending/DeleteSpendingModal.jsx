import React, {useState} from 'react';
import {Button, Modal, ModalHeader, ModalFooter} from 'reactstrap'
import {useAuth} from "../../auth/AuthContext";
import axios from "axios";

function DeleteSpendingModal(props) {

    const [isOpen, setIsOpen] = useState(false);

    const {user} = useAuth();

    const toggle = (e) => {
        setIsOpen(!isOpen)
        if (e.target.name==='delSpendingSuccess') {
            axios.delete("http://localhost:8080/api/v1/app/spendings/" + props.spendingId,
                {
                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                })
                .then(r => {
                    axios.get("http://localhost:8080/api/v1/app/spendings",
                        {
                            headers: {
                                'Authorization': "Bearer " + user.token
                            }
                        })
                        .then(res => {
                            props.setShifts(res.data)
                        })
                        .catch(e => {
                            console.log(e)
                        })
                })
                .catch(e => {
                    console.log(e)
                })
        }
    }

    return (
        <div>
            <a href="#!" id="delete-employee" onClick={toggle}>
                <i className="fa-solid fa-xmark"></i>
            </a>
            <Modal isOpen={isOpen} toggle={toggle} centered={true} size={"sm"}>
                <ModalHeader>Delete This Spending?</ModalHeader>
                <ModalFooter>
                    <Button color="danger" onClick={toggle} name="delSpendingSuccess">Delete Spending</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default DeleteSpendingModal;
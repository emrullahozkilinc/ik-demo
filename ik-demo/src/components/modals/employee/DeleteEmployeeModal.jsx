import React, {useState} from 'react';
import {Button, Modal, ModalHeader, ModalFooter} from 'reactstrap'
import axios from "axios";
import {useAuth} from "../../auth/AuthContext";

function DeleteEmployeeModal(props) {

    const [isOpen, setIsOpen] = useState(false);

    const {user} = useAuth();

    const toggle = (e) => {
        setIsOpen(!isOpen)
        if (e.target.name==='delEmpSuccess') {
            axios.delete("http://localhost:8080/api/v1/app/employees/" + props.workerNatId,
                {
                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                })
                .then(r => {
                    axios.get("http://localhost:8080/api/v1/app/employees",
                        {
                            headers: {
                                'Authorization': "Bearer " + user.token
                            }
                        })
                        .then(res => {
                            props.setWorkers(res.data)
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
                <ModalHeader>Delete This Employee?</ModalHeader>
                <ModalFooter>
                    <Button color="danger" onClick={toggle} name="delEmpSuccess">Delete Employee</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default DeleteEmployeeModal;
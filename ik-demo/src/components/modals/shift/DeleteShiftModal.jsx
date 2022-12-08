import React, {useState} from 'react';
import {Button, Modal, ModalHeader, ModalFooter} from 'reactstrap'
import {useAuth} from "../../auth/AuthContext";
import axios from "axios";

function DeleteShiftModal(props) {

    const [isOpen, setIsOpen] = useState(false);

    const {user} = useAuth();

    const toggle = (e) => {
        setIsOpen(!isOpen)
        if (e.target.name==='delShiftSuccess') {
            console.log(props.shiftId)
            axios.delete("http://localhost:8080/api/v1/app/shifts/" + props.shiftId,
                {
                    headers: {
                        'Authorization': "Bearer " + user.token
                    }
                })
                .then(r => {
                    // props.setDayoffs([...props.dayoffs, r.data])
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
                <ModalHeader>Delete This Shift?</ModalHeader>
                <ModalFooter>
                    <Button color="danger" onClick={toggle} name="delShiftSuccess">Delete Shift</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}

export default DeleteShiftModal;
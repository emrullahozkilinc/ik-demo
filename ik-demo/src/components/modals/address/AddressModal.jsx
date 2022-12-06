import React, {useState} from 'react';
import {Button, Modal, ModalHeader, ModalBody, ModalFooter, Table} from 'reactstrap'


function AddressModal(props) {

    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => {setIsOpen(!isOpen)}

    return (
    <div>
        <button type="button" onClick={toggle} className="btn btn-link">Contact Details</button>

        <Modal isOpen={isOpen} toggle={toggle} centered={true} >
            <ModalHeader>Contact Details</ModalHeader>


            <ModalBody>
                <Table borderless style={{verticalAlign:"middle"}}>
                    <tbody>
                    <tr>
                        <th scope="row">Address</th>
                        <td>{props.usercontacts.address}</td>
                    </tr>
                    <tr>
                        <th scope="row" >City</th>
                        <td>{props.usercontacts.city}</td>
                    </tr>
                    <tr>
                        <th scope="row">Country</th>
                        <td>{props.usercontacts.country}</td>
                    </tr>
                    <tr>
                        <th scope="row">Postal Code</th>
                        <td>{props.usercontacts.postalCode}</td>
                    </tr>
                    </tbody>
                </Table>

            </ModalBody>

            <ModalFooter>
                <Button color="secondary" onClick={toggle}>Cancel</Button>
            </ModalFooter>
        </Modal>

        </div>
    );
}

export default AddressModal;
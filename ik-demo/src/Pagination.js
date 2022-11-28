import {useState} from "react";

import React from 'react';

function MyPagination(props) {

    const pages = [];

    for (let i = 0; i < props.maxPage; i++) {
        pages.push(i);
    }

    const [buttonState, setButtonState] = useState(1);

    const nextPage = () => {
        setButtonState((prevState) => prevState+1);
        props.setPage((prev) => prev+1);
    }

    const prevPage = () => {
        setButtonState((prevState) => prevState-1);
        props.setPage((prev) => prev-1);
    }


    return (
        <nav>
            <ul className="pagination justify-content-end">
                {buttonState > 1 ?
                    <li className="page-item">
                        <a className="page-link" href="#!" onClick={prevPage}>Previous</a>
                    </li>
                    :
                    <li className="page-item disabled">
                        <a className="page-link" href="#!">Previous</a>
                    </li>
                }

                {buttonState < props.maxPage ?
                    <li className="page-item">
                        <a className="page-link" href="#!" onClick={nextPage}>Next</a>
                    </li>
                    :
                    <li className="page-item disabled">
                        <a className="page-link" href="#!">Next</a>
                    </li>
                }
            </ul>
        </nav>
    );
}

export default MyPagination;
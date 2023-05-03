import React, { useState } from "react";
import { Button, Col, Container, Row, Table } from "react-bootstrap";
import StudentsModal from "../Forms/StudentsModal";
import { StudentType } from "../../types";

const Students = () => {
    const [showModal, setShowModal] = useState(false);

    const saveStudent = (student: StudentType) => {
        console.log("Saving Student:", student);
        return true;
    };

    return (
        <Container className="mt-4">
            <Row className="justify-content-between">
                <Col md={10}>
                    <h2>Students</h2>
                </Col>
                <Col>
                    <Button
                        variant="outline-primary"
                        onClick={() => setShowModal(true)}
                    >
                        Enroll
                    </Button>
                </Col>
            </Row>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>B#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Level</th>
                        <th>GPA</th>
                        <th>Email</th>
                        <th>BDate</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>B00980339</td>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>Grad</td>
                        <td>3.5</td>
                        <td>example@gmail.com</td>
                        <td>09/09/1990</td>
                        <td>
                            <Button variant="danger">Remove</Button>
                        </td>
                    </tr>
                </tbody>
            </Table>
            <StudentsModal
                show={showModal}
                close={() => setShowModal(false)}
                postStudent={saveStudent}
            />
        </Container>
    );
};

export default Students;

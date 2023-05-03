import React, { useEffect, useState } from "react";
import { Button, Col, Container, Row, Table } from "react-bootstrap";
import StudentsModal from "../Forms/StudentsModal";
import { StudentType } from "../../types";
import { getTable } from "../../api";

const Students = () => {
    const [showModal, setShowModal] = useState(false);
    const [students, setStudents] = useState<StudentType[]>([]);

    const getData = () => {
        getTable("STUDENT")
            .then((data) => setStudents(data))
            .catch((e) => alert(e));
    };

    const saveStudent = (student: StudentType) => {
        console.log("Saving Student:", student);
        return true;
    };

    useEffect(getData, []);

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
                    {students.map((st) => (
                        <tr key={st.bnumber}>
                            <td>{st.bnumber}</td>
                            <td>{st.firstName}</td>
                            <td>{st.lastName}</td>
                            <td>{st.stLevel}</td>
                            <td>{st.gpa}</td>
                            <td>{st.email}</td>
                            <td>{st.birthDate}</td>
                            <td>
                                <Button variant="danger">Remove</Button>
                            </td>
                        </tr>
                    ))}
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

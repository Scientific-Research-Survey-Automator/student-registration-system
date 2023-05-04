import React, { useEffect, useState } from "react";
import { Button, Col, Container, Row, Table } from "react-bootstrap";
import StudentsModal from "../Forms/StudentsModal";
import { StudentType } from "../../types";
import { deleteEntity, getTable, postEntity } from "../../api";

const Students = () => {
    const [showModal, setShowModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [students, setStudents] = useState<StudentType[]>([]);

    const getData = () => {
        setLoading(true);
        getTable("STUDENTS")
            .then((data) => setStudents(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    const saveStudent = (student: StudentType) => {
        setLoading(true);
        postEntity("STUDENTS", student)
            .then((data) => setStudents([...students, data]))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    const removeStudent = (id: string) => {
        setLoading(true);
        deleteEntity("STUDENTS", id)
            .then((data) => {
                const updatedStudents = students.filter(
                    (st) => st.bnumber !== id
                );
                setStudents(updatedStudents);
            })
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    if (loading) return <h4>Loading</h4>;

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
                        ADD
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
                    {students &&
                        students.map((st, i) => (
                            <tr key={i}>
                                <td>{st.bnumber}</td>
                                <td>{st.firstName}</td>
                                <td>{st.lastName}</td>
                                <td>{st.stLevel}</td>
                                <td>{st.gpa}</td>
                                <td>{st.email}</td>
                                <td>{st.birthDate}</td>
                                <td>
                                    <Button
                                        variant="danger"
                                        onClick={() =>
                                            removeStudent(st.bnumber)
                                        }
                                    >
                                        Remove
                                    </Button>
                                </td>
                            </tr>
                        ))}
                </tbody>
            </Table>
            <Row className="justify-content-center">
                <Col md={2}>
                    <Button variant="success" onClick={getData}>
                        Load Table
                    </Button>
                </Col>
            </Row>
            <StudentsModal
                show={showModal}
                close={() => setShowModal(false)}
                postStudent={saveStudent}
            />
        </Container>
    );
};

export default Students;

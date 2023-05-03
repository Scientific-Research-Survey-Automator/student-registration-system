import React, { ChangeEvent, useState } from "react";
import { StudentType } from "../../types";
import { Button, Col, Form, Modal, Row } from "react-bootstrap";

const dummyStudent: StudentType = {
    bnumber: "B00980339",
    firstName: "ABC",
    lastName: "XYZ",
    gpa: 3.5,
    stLevel: "freshman",
    email: "test@example.com",
    birthDate: "2023-05-09",
};

interface StudentsModalProps {
    show: boolean;
    close: () => void;
    postStudent: (student: StudentType) => void;
}

const StudentsModal = ({ show, close, postStudent }: StudentsModalProps) => {
    const [student, setStudent] = useState<StudentType>(dummyStudent);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setStudent({
            ...student,
            [e.target.name]: e.target.value,
        });
    };

    const handleSelect = (e: any) => {
        setStudent({
            ...student,
            [e.target.name]: e.target.value,
        });
    };

    const handleSave = () => {
        postStudent(student);
        close();
    };

    return (
        <Modal size="lg" centered show={show} onHide={close}>
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Student Form
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            B#
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="text"
                                value={student.bnumber}
                                name="bnumber"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            First Name
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="text"
                                value={student.firstName}
                                name="firstName"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            Last Name
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="text"
                                value={student.lastName}
                                name="lastName"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            GPA
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="number"
                                value={student.gpa}
                                name="gpa"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            Email
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="email"
                                value={student.email}
                                name="email"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            Level
                        </Form.Label>
                        <Col sm="10">
                            <Form.Select name="stLevel" onChange={handleSelect}>
                                <option value="freshmen">Freshmen</option>
                                <option value="sophomore">Sophomore</option>
                                <option value="junior">Junior</option>
                                <option value="senior">Senior</option>
                                <option value="master">Master</option>
                                <option value="phd">PhD</option>
                            </Form.Select>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            Birth Date
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="date"
                                value={student.birthDate}
                                name="birthDate"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={close}>
                    Close
                </Button>
                <Button variant="success" onClick={handleSave}>
                    Save
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default StudentsModal;

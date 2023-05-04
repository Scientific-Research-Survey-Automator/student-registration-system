import React, { ChangeEvent, useState } from "react";
import { CourseType, StudentType } from "../../types";
import { Button, Col, Form, Modal, Row } from "react-bootstrap";

const dummyCourse: CourseType = {
    deptCode: "IS",
    courseNo: 501,
    title: "Data mining",
};

interface CoursesModalProps {
    show: boolean;
    close: () => void;
    postCourse: (course: CourseType) => void;
}

const CoursesModal = ({ show, close, postCourse }: CoursesModalProps) => {
    const [course, setCourse] = useState<CourseType>(dummyCourse);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setCourse({
            ...course,
            [e.target.name]: e.target.value,
        });
    };

    const handleSave = () => {
        postCourse(course);
        close();
    };

    return (
        <Modal size="lg" centered show={show} onHide={close}>
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Courses Form
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            Dept Code
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="text"
                                value={course.deptCode}
                                name="deptCode"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            Course#
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="number"
                                value={course.courseNo}
                                name="courseNo"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            Title
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="text"
                                value={course.title}
                                name="title"
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

export default CoursesModal;

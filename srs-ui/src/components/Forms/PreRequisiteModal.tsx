import React, { ChangeEvent, useState } from "react";
import { CourseType, PreReqType, StudentType } from "../../types";
import { Button, Col, Form, Modal, Row } from "react-bootstrap";

const dummyPreReq: PreReqType = {
    deptCode: "IS",
    courseNo: 501,
    preDeptCode: "IS",
    preCourseNo: 502,
};

interface PreRequisiteModalProps {
    show: boolean;
    close: () => void;
    postPreReq: (preReq: PreReqType) => void;
}

const PreRequisiteModal = ({
    show,
    close,
    postPreReq,
}: PreRequisiteModalProps) => {
    const [preReq, setPreReq] = useState<PreReqType>(dummyPreReq);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setPreReq({
            ...preReq,
            [e.target.name]: e.target.value,
        });
    };

    const handleSave = () => {
        postPreReq(preReq);
        close();
    };

    return (
        <Modal size="lg" centered show={show} onHide={close}>
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Course PreRequisites FOrm
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
                                value={preReq.deptCode}
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
                                value={preReq.courseNo}
                                name="courseNo"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            PreRequisite Dept Code
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="text"
                                value={preReq.preDeptCode}
                                name="preDeptCode"
                                onChange={handleChange}
                            />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3">
                        <Form.Label column sm="2">
                            PreRequisite Course#
                        </Form.Label>
                        <Col sm="10">
                            <Form.Control
                                type="number"
                                value={preReq.preCourseNo}
                                name="preCourseNo"
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

export default PreRequisiteModal;

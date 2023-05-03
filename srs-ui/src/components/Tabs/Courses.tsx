import { Button, Col, Container, Row, Table } from "react-bootstrap";
import CoursesModal from "../Forms/CoursesModal";
import { useState } from "react";
import { CourseType } from "../../types";

const Courses = () => {
    const [showModal, setShowModal] = useState(false);

    const saveCourse = (course: CourseType) => {
        console.log("Saving Course:", course);
        return true;
    };
    return (
        <Container className="mt-4">
            <Row className="justify-content-between">
                <Col md={10}>
                    <h2>Courses</h2>
                </Col>
                <Col>
                    <Button
                        variant="outline-primary"
                        onClick={() => setShowModal(true)}
                    >
                        Add
                    </Button>
                </Col>
            </Row>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Course#</th>
                        <th>Title</th>
                        <th>Credits</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Applied Data mining</td>
                        <td>3</td>
                    </tr>
                </tbody>
            </Table>
            <CoursesModal
                show={showModal}
                close={() => setShowModal(false)}
                postCourse={saveCourse}
            />
        </Container>
    );
};

export default Courses;

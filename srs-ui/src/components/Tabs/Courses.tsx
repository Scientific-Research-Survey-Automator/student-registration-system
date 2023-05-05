import { Button, Col, Container, Row, Table } from "react-bootstrap";
import CoursesModal from "../Forms/CoursesModal";
import { useEffect, useState } from "react";
import { CourseType } from "../../types";
import { deleteEntity, getTable, postEntity } from "../../api";

const Courses = () => {
    const [showModal, setShowModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [courses, setCourses] = useState<CourseType[]>([]);

    const getData = () => {
        setLoading(true);
        getTable("COURSES")
            .then((data) => setCourses(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    if (loading) return <h4>Loading</h4>;

    return (
        <Container className="mt-4">
            <Row className="justify-content-between">
                <Col md={10}>
                    <h2>Courses</h2>
                </Col>
            </Row>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Course#</th>
                        <th>Dept Code</th>
                        <th>Title</th>
                    </tr>
                </thead>
                <tbody>
                    {courses &&
                        courses.map((cr, i) => (
                            <tr key={i}>
                                <td>{cr.courseNo}</td>
                                <td>{cr.deptCode}</td>
                                <td>{cr.title}</td>
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
        </Container>
    );
};

export default Courses;

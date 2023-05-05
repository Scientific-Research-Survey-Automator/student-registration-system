import { useState, useEffect } from "react";
import { Button, Col, Container, Row, Table } from "react-bootstrap";
import { CourseCredit } from "../../types";
import { getTable, postEntity } from "../../api";

const CourseCredits = () => {
    const [showModal, setShowModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [courseCreds, setCourseCred] = useState<CourseCredit[]>([]);

    const getData = () => {
        setLoading(true);
        getTable("COURSE_CREDIT")
            .then((data) => setCourseCred(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    const saveCourseCredit = (courseCred: CourseCredit) => {
        setLoading(true);
        postEntity("COURSE_CREDIT", courseCred)
            .then((data) => setCourseCred([...courseCreds, data]))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    if (loading) return <h4>Loading</h4>;
    return (
        <Container className="mt-4">
            <Row className="justify-content-between">
                <Col md={10}>
                    <h2>CourseCredits</h2>
                </Col>
            </Row>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Course#</th>
                        <th>Credits</th>
                    </tr>
                </thead>
                <tbody>
                    {courseCreds.map((cc, i) => (
                        <tr key={i}>
                            <td>{cc.courseNo}</td>
                            <td>{cc.credits}</td>
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

export default CourseCredits;

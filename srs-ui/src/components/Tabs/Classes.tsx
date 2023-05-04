import { useState, useEffect } from "react";
import { Button, Col, Container, Row, Table } from "react-bootstrap";
import { Class, CourseCredit } from "../../types";
import { getTable, postEntity } from "../../api";

const Classes = () => {
    const [showModal, setShowModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [classes, setClasses] = useState<Class[]>([]);

    const getData = () => {
        setLoading(true);
        getTable("CLASSES")
            .then((data) => setClasses(data))
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
                <Col>
                    <Button variant="outline-primary">Add</Button>
                </Col>
            </Row>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>classId</th>
                        <th>room</th>
                        <th>section</th>
                        <th>semester</th>
                        <th>size</th>
                        <th>year</th>
                        <th>limit</th>
                    </tr>
                </thead>
                <tbody>
                    {classes.map((cl, i) => (
                        <tr key={i}>
                            <td>{cl.classId}</td>
                            <td>{cl.room}</td>
                            <td>{cl.section}</td>
                            <td>{cl.semester}</td>
                            <td>{cl.size}</td>
                            <td>{cl.year}</td>
                            <td>{cl.limit}</td>
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

export default Classes;

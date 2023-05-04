import {
    Button,
    Col,
    Container,
    Form,
    InputGroup,
    Row,
    Table,
} from "react-bootstrap";
import { useState } from "react";
import { Enrollment } from "../../types";
import { getTable } from "../../api";

const Enrollments = () => {
    const [showModal, setShowModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [enrollments, setEnrollments] = useState<Enrollment[]>([]);

    const getData = () => {
        setLoading(true);
        getTable("G_ENROLLMENTS")
            .then((data) => setEnrollments(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };
    return (
        <Container>
            <h2>Enrollments</h2>
            {/* <Row>
                <Col md={3}>
                    <Button variant="primary">Fetch All</Button>
                </Col>
                <Col md={4}>
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">
                            Class Id
                        </InputGroup.Text>
                        <Form.Control placeholder="101" type="number" min={1} />
                        <Button variant="outline-secondary">Fetch</Button>
                    </InputGroup>
                </Col>
                <Col md={{ span: 3, offset: 2 }}>
                    <Button variant="outline-primary">ADD</Button>
                </Col>
            </Row> */}
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>G_B#</th>
                        <th>Class Id</th>
                        <th>Score</th>
                        {/* <th>Action</th> */}
                    </tr>
                </thead>
                <tbody>
                    {enrollments.map((en, i) => (
                        <tr key={i}>
                            <td>{en.bnumber}</td>
                            <td>{en.classId}</td>
                            <td>{en.score}</td>
                            {/* <td>
                                <Button variant="danger">Delete</Button>
                            </td> */}
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

export default Enrollments;

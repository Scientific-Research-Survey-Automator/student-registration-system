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
import { Enrollment, Grade } from "../../types";
import { getTable } from "../../api";

const ScoreGrade = () => {
    const [showModal, setShowModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [scoreGrades, setScoreGrades] = useState<Grade[]>([]);

    const getData = () => {
        setLoading(true);
        getTable("SCORE_GRADE")
            .then((data) => setScoreGrades(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };
    return (
        <Container>
            <h2>SCORE GRADE</h2>
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
                        <th>Score</th>
                        <th>Grade</th>
                        {/* <th>Action</th> */}
                    </tr>
                </thead>
                <tbody>
                    {scoreGrades.map((sg, i) => (
                        <tr key={i}>
                            <td>{sg.score}</td>
                            <td>{sg.lgrade}</td>
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

export default ScoreGrade;

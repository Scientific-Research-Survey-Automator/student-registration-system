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
import { Enrollment, StudentType } from "../../types";
import { getTable, getClassStudents } from "../../api";

const Enrollments = () => {
    const [classId, setClassId] = useState("");
    const [enrollments, setEnrollments] = useState<Enrollment[]>([]);
    const [classStudents, setClassStudents] = useState<StudentType[]>([]);

    const getData = () => {
        getTable("G_ENROLLMENTS")
            .then((data) => setEnrollments(data))
            .catch((e) => alert(e));
    };

    const fetchClassStudents = () => {
        getClassStudents(classId)
            .then((data) => setClassStudents(data))
            .catch((e) => alert(e));
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
            <Container>
                <h2>Students In a Class</h2>
                <Row>
                    <Col md={4}>
                        <InputGroup className="mb-3">
                            <InputGroup.Text id="basic-addon1">
                                Class Id
                            </InputGroup.Text>
                            <Form.Control
                                placeholder="101"
                                type="text"
                                onChange={(e) => setClassId(e.target.value)}
                            />
                            <Button
                                variant="outline-secondary"
                                onClick={fetchClassStudents}
                            >
                                Fetch
                            </Button>
                        </InputGroup>
                    </Col>
                </Row>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>G_B#</th>
                            <th>first name</th>
                            <th>last name</th>
                        </tr>
                    </thead>
                    <tbody>
                        {classStudents.map((clst, i) => (
                            <tr key={i}>
                                <td>{clst.bnumber}</td>
                                <td>{clst.firstName}</td>
                                <td>{clst.lastName}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </Container>
        </Container>
    );
};

export default Enrollments;

import {
    Button,
    Col,
    Container,
    Form,
    InputGroup,
    Row,
    Table,
} from "react-bootstrap";

const Enrollments = () => {
    return (
        <Container>
            <h2>Enrollments</h2>
            <Row>
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
            </Row>
            <Container>
                <Table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>G_B#</th>
                            <th>Class Id</th>
                            <th>Score</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>B0098039</td>
                            <td>101</td>
                            <td>100</td>
                            <td>
                                <Button variant="danger">Delete</Button>
                            </td>
                        </tr>
                    </tbody>
                </Table>
            </Container>
        </Container>
    );
};

export default Enrollments;

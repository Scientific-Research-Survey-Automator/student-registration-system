import { Button, Col, Container, Row, Table } from "react-bootstrap";

const CourseCredits = () => {
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
                        <th>Course#</th>
                        <th>Credits</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>3</td>
                    </tr>
                </tbody>
            </Table>
        </Container>
    );
};

export default CourseCredits;

import { useState } from "react";
import { Log } from "../../types";
import { getTable } from "../../api";
import { Button, Col, Container, Row, Table } from "react-bootstrap";

const LogTable = () => {
    const [loading, setLoading] = useState(false);
    const [logs, setLogs] = useState<Log[]>([]);

    const getData = () => {
        setLoading(true);
        getTable("LOGS")
            .then((data) => setLogs(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    if (loading) return <h4>Loading</h4>;

    return (
        <Container className="mt-4">
            <Row className="justify-content-between">
                <Col md={10}>
                    <h2>Logs</h2>
                </Col>
            </Row>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>User</th>
                        <th>Table</th>
                        <th>Tuple</th>
                        <th>Operation</th>
                        <th>Timestamp</th>
                    </tr>
                </thead>
                <tbody>
                    {logs.map((lg, i) => (
                        <tr key={i}>
                            <td>{lg["LOG#"]}</td>
                            <td>{lg.USER_NAME}</td>
                            <td>{lg.TABLE_NAME}</td>
                            <td>{lg.TUPLE_KEYVALUE}</td>
                            <td>{lg.OPERATION}</td>
                            <td>{lg.OP_TIME}</td>
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

export default LogTable;

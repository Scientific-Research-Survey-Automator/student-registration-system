import {
    Button,
    Col,
    Container,
    Form,
    InputGroup,
    Row,
    Table,
} from "react-bootstrap";
import PreRequisiteModal from "../Forms/PreRequisiteModal";
import { useState, useEffect } from "react";
import { PreReqType } from "../../types";
import { getPreReq, getTable } from "../../api";

const PreRequisites = () => {
    const [showModal, setShowModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [deptCode, setDeptCode] = useState("");
    const [courseNo, setCourseNo] = useState("");
    const [prereq, setPrereq] = useState<PreReqType[]>([]);
    const [res, setRes] = useState([]);

    const getData = () => {
        setLoading(true);
        getTable("PREREQUISITES")
            .then((data) => setPrereq(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    const fetchPreReq = () => {
        if (courseNo === "" || deptCode === "") {
            alert("Invalid Input");
            return;
        }
        getPreReq(courseNo, deptCode)
            .then((data) => setRes(data))
            .catch((e) => alert(e));
    };

    if (loading) return <h4>Loading</h4>;

    return (
        <Container>
            <Row className="justify-content-between">
                <Col md={3}>
                    <h2>PreRequisites</h2>
                </Col>
            </Row>
            <Table>
                <thead>
                    <tr>
                        <th>Dept Code</th>
                        <th>Course#</th>
                        <th>PR Dept Code</th>
                        <th>PR Course#</th>
                    </tr>
                </thead>
                <tbody>
                    {prereq.map((pr, i) => (
                        <tr key={i}>
                            <td>{pr.deptCode}</td>
                            <td>{pr.courseNo}</td>
                            <td>{pr.preDeptCode}</td>
                            <td>{pr.preCourseNo}</td>
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
            <Row className="justify-content-between">
                <Col>
                    <h5>Prerequisites Courses (indirect and direct)</h5>
                </Col>
                <Col md={4}>
                    <InputGroup className="mb-3">
                        <Form.Control
                            type="text"
                            placeholder="Dept Code"
                            value={deptCode}
                            onChange={(e) => setDeptCode(e.target.value)}
                        />
                        <Form.Control
                            type="text"
                            placeholder="Course#"
                            value={courseNo}
                            onChange={(e) => setCourseNo(e.target.value)}
                        />
                        <Button variant="secondary" onClick={fetchPreReq}>
                            Fetch
                        </Button>
                    </InputGroup>
                </Col>
            </Row>
            <Table>
                <thead>
                    <tr>
                        <th>Courses</th>
                    </tr>
                </thead>
                <tbody>
                    {res.map((r, i) => (
                        <tr key={i}>
                            <td>{r}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </Container>
    );
};

export default PreRequisites;

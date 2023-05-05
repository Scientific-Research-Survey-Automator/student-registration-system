import {
    Button,
    Col,
    Container,
    Dropdown,
    Form,
    InputGroup,
    Row,
    SplitButton,
    Table,
} from "react-bootstrap";
import { useState } from "react";
import { Enrollment, StudentType } from "../../types";
import {
    getTable,
    getClassStudents,
    postEnrollment,
    deleteEntity,
    deEnroll,
} from "../../api";

const Enrollments = () => {
    const [loading, setLoading] = useState(false);
    const [action, setAction] = useState("Add");
    const [studentClassId, setStudentClassId] = useState("");
    const [classId, setClassId] = useState("");
    const [bnumber, setBnumber] = useState("");
    const [enrollments, setEnrollments] = useState<Enrollment[]>([]);
    const [classStudents, setClassStudents] = useState<StudentType[]>([]);

    const getData = () => {
        setLoading(true);
        getTable("G_ENROLLMENTS")
            .then((data) => setEnrollments(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };
    const fetchClassStudents = () => {
        setLoading(true);
        getClassStudents(studentClassId)
            .then((data) => setClassStudents(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    const doAction = () => {
        if (bnumber === "" || classId === "") {
            alert("Invalid Input");
            return;
        }
        if (action === "Add") enrollStudent();
        if (action === "Delete") removeEnrollment(bnumber, classId);
    };

    const enrollStudent = () => {
        setLoading(true);
        postEnrollment(bnumber, classId)
            .then((data) =>
                setEnrollments([...enrollments, { bnumber, classId, score: 0 }])
            )
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    const removeEnrollment = (bnum: string, clId: string) => {
        setLoading(true);
        deEnroll(bnum, clId)
            .then((data) => {
                const updatedStudents = enrollments.filter(
                    (en) => en.bnumber !== bnum && en.classId !== clId
                );
                setEnrollments(updatedStudents);
            })
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    if (loading) return <h4>Loading...</h4>;

    return (
        <Container>
            <Row className="justify-content-between">
                <Col md={3}>
                    <h2>Enrollments</h2>
                </Col>
                <Col md={4}>
                    <InputGroup className="mb-3">
                        <Form.Control
                            placeholder="ClassId"
                            type="text"
                            value={classId}
                            onChange={(e) => setClassId(e.target.value)}
                        />
                        <Form.Control
                            placeholder="bnumber"
                            type="text"
                            value={bnumber}
                            onChange={(e) => setBnumber(e.target.value)}
                        />
                        <SplitButton
                            variant="secondary"
                            title={action}
                            onClick={doAction}
                        >
                            <Dropdown.Item
                                href="#"
                                onClick={() => setAction("Add")}
                            >
                                ADD
                            </Dropdown.Item>
                            <Dropdown.Item
                                href="#"
                                onClick={() => setAction("Delete")}
                            >
                                DELETE
                            </Dropdown.Item>
                        </SplitButton>
                    </InputGroup>
                </Col>
            </Row>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>G_B#</th>
                        <th>Class Id</th>
                        <th>Score</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {enrollments.map((en, i) => (
                        <tr key={i}>
                            <td>{en.bnumber}</td>
                            <td>{en.classId}</td>
                            <td>{en.score || "null"}</td>
                            <td>
                                <Button
                                    variant="danger"
                                    onClick={() =>
                                        removeEnrollment(en.bnumber, en.classId)
                                    }
                                >
                                    Delete
                                </Button>
                            </td>
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
                                placeholder="c0001"
                                type="text"
                                value={studentClassId}
                                onChange={(e) =>
                                    setStudentClassId(e.target.value)
                                }
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

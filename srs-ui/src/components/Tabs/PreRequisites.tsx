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
import { getTable } from "../../api";

const PreRequisites = () => {
    const [showModal, setShowModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [prereq, setPrereq] = useState<PreReqType[]>([]);

    const getData = () => {
        setLoading(true);
        getTable("PREREQUISITES")
            .then((data) => setPrereq(data))
            .catch((e) => alert(e))
            .finally(() => setLoading(false));
    };

    const savePreRequisite = (preReq: PreReqType) => {
        console.log("Saving PreRequisite:", preReq);
        return true;
    };

    if (loading) return <h4>Loading</h4>;

    return (
        <Container>
            <Row className="justify-content-between">
                <Col md={10}>
                    <h2>PreRequisites</h2>
                </Col>
                <Col>
                    <Button
                        variant="outline-primary"
                        onClick={() => setShowModal(true)}
                    >
                        ADD
                    </Button>
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
                    {prereq &&
                        prereq.map((pr, i) => (
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

            <PreRequisiteModal
                show={showModal}
                close={() => setShowModal(false)}
                postPreReq={savePreRequisite}
            />
        </Container>
    );
};

export default PreRequisites;

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
import { useState } from "react";
import { PreReqType } from "../../types";

const PreRequisites = () => {
    const [showModal, setShowModal] = useState(false);

    const savePreRequisite = (preReq: PreReqType) => {
        console.log("Saving PreRequisite:", preReq);
        return true;
    };

    return (
        <Container>
            <h2>PreRequisites</h2>
            <Row>
                <Col md={3}>
                    <Button variant="primary">Fetch All</Button>
                </Col>
                <Col md={{ span: 3, offset: 6 }}>
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
                        <th>#</th>
                        <th>Dept Code</th>
                        <th>Course#</th>
                        <th>PR Dept Code</th>
                        <th>PR Course#</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>IS</td>
                        <td>501</td>
                        <td>IS</td>
                        <td>502</td>
                    </tr>
                </tbody>
            </Table>

            <PreRequisiteModal
                show={showModal}
                close={() => setShowModal(false)}
                postPreReq={savePreRequisite}
            />
        </Container>
    );
};

export default PreRequisites;

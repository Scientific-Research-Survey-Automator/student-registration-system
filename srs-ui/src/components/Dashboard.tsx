import { Container, Tab, Tabs } from "react-bootstrap";
import StudentTable from "./Tabs/Students";
import CoursesTable from "./Tabs/Courses";
import Enrollments from "./Tabs/Enrollments";
import PreRequisites from "./Tabs/PreRequisites";
import CourseCredits from "./Tabs/CourseCredits";

const Dashboard = () => {
    return (
        <Container className="mt-4">
            <Tabs defaultActiveKey="students" className="mb-3">
                <Tab eventKey="students" title="Students">
                    <StudentTable />
                </Tab>
                <Tab eventKey="courses" title="Courses">
                    <CoursesTable />
                </Tab>
                <Tab eventKey="prereq" title="Prerequisites">
                    <PreRequisites />
                </Tab>
                <Tab eventKey="coursecredit" title="CourseCredit">
                    <CourseCredits />
                </Tab>
                <Tab eventKey="class" title="Classes"></Tab>
                <Tab eventKey="enrollment" title="Enrollments">
                    <Enrollments />
                </Tab>
                <Tab eventKey="grade" title="Grades"></Tab>
            </Tabs>
        </Container>
    );
};

export default Dashboard;

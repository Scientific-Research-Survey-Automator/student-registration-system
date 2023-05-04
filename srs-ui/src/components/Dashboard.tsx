import { useState } from "react";
import { Container, Tab, Tabs } from "react-bootstrap";
import StudentTable from "./Tabs/Students";
import CoursesTable from "./Tabs/Courses";
import Enrollments from "./Tabs/Enrollments";
import PreRequisites from "./Tabs/PreRequisites";
import CourseCredits from "./Tabs/CourseCredits";
import Classes from "./Tabs/Classes";
import ScoreGrade from "./Tabs/ScoreGrade";
import LogTable from "./Tabs/LogTable";

const Dashboard = () => {
    return (
        <Container className="mt-4">
            <Tabs defaultActiveKey="STUDENTS" className="mb-3">
                <Tab eventKey="STUDENTS" title="Students">
                    <StudentTable />
                </Tab>
                <Tab eventKey="COURSES" title="Courses">
                    <CoursesTable />
                </Tab>
                <Tab eventKey="PREREQUISITES" title="Prerequisites">
                    <PreRequisites />
                </Tab>
                <Tab eventKey="COURSE_CREDIT" title="CourseCredit">
                    <CourseCredits />
                </Tab>
                <Tab eventKey="CLASSES" title="Classes">
                    <Classes />
                </Tab>
                <Tab eventKey="G_ENROLLMENT" title="Enrollments">
                    <Enrollments />
                </Tab>
                <Tab eventKey="SCORE_GRADE" title="Grades">
                    <ScoreGrade />
                </Tab>
                <Tab eventKey="LOGS" title="Logs">
                    <LogTable />
                </Tab>
            </Tabs>
        </Container>
    );
};

export default Dashboard;

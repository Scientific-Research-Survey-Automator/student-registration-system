import React from "react";
import { Container, Navbar } from "react-bootstrap";

const AppNavbar = () => {
    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                <Navbar.Brand href="#home">
                    Student Enrollment System
                </Navbar.Brand>
            </Container>
        </Navbar>
    );
};

export default AppNavbar;

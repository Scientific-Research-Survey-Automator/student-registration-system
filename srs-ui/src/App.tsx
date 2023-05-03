import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import AppNavbar from "./components/Navbar";
import Dashboard from "./components/Dashboard";

function App() {
    return (
        <div className="App">
            <AppNavbar />
            <Dashboard />
            {/* <StudentTable />
            <CoursesTable /> */}
        </div>
    );
}

export default App;

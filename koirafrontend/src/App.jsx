import { CssBaseline } from "@mui/material";
import { ThemeProvider } from '@mui/material/styles';
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-alpine.css";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import theme from "./Theme";
import NavigationBar from "./components/NavigationBar";
import Tuotelista from "./components/Tuotelista";

function App() {
  return (
    <>
      <ThemeProvider theme={theme}>
      <CssBaseline /> {/* Normalize the styles */}
      <Router>
        <NavigationBar />
        <Routes>
          <Route path="/tuotteet" element={<Tuotelista />} />
        </Routes>
      </Router>
      </ThemeProvider>
    </>
  );
}

export default App;

import './App.css'
import Carlist from './Carlist.jsx'
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from "@mui/material/Container";
function App() {
  return (
    <>
<Container maxWidth="xl">
<AppBar >
  <Toolbar>
    <Typography variant="h6">Car Shop</Typography>
  </Toolbar>
</AppBar>
<Carlist />
</Container>
</>
  )
}

export default App;

import { useState } from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogTitle from '@mui/material/DialogTitle';
import CarDialog from './CarDialog';

export default function AddCar({ fetchCars }) {
  const [car, setCar] = useState({
    brand: '',
    model: '',
    fuel: '',
    color: '',
    year: '',
    price: ''
  });
  const [open, setOpen] = useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    setCar({...car, [e.target.name]: e.target.value});
  }

  const saveCar = () => {
    fetch('https://carrestapi.herokuapp.com/cars', {
      method: 'POST',
      headers: { 'Content-type':'application/json' },
      body: JSON.stringify(car) 
    })
    .then(response => {
      if (!response.ok)
        throw new Error("Error when adding car: "  + response.statusText);

      fetchCars();
    })
    .catch(err => console.error(err));

    handleClose();
  }

  return (
    <div>
      <Button variant="outlined" onClick={handleClickOpen}>
        Add Car
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>New Car</DialogTitle>
        <CarDialog car={car} handleChange={handleChange} />
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={saveCar}>Save</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
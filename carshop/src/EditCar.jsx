import { useState } from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogTitle from '@mui/material/DialogTitle';
import CarDialog from './CarDialog';

export default function EditCar({ fetchCars, data }) {
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
    setCar({
      brand: data.brand,
      model: data.model,
      color: data.color,
      fuel: data.fuel,
      price: data.price,
      year: data.year
    });
  };

  const handleClose = () => {
    setOpen(false);
  };

  const saveCar = () => {
    fetch(data._links.car.href, {
      method: 'PUT',
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

  const handleChange = (e) => {
    setCar({...car, [e.target.name]: e.target.value});
  }

  return (
    <div>
      <Button size="small" onClick={handleClickOpen}>
        Edit
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Edit Car</DialogTitle>
        <CarDialog car={car} handleChange={handleChange} />
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={saveCar}>Save</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
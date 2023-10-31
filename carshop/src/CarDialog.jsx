import DialogContent from '@mui/material/DialogContent';
import TextField from '@mui/material/TextField';

function CarDialog({ car, handleChange }) {
  return(
    <DialogContent>
      <TextField
        margin="dense"
        label="Brand"
        name="brand"
        fullWidth
        variant="standard"
        value={car.brand}
        onChange={handleChange}
      />
      <TextField
        margin="dense"
        label="Model"
        name="model"
        fullWidth
        variant="standard"
        value={car.model}
        onChange={handleChange}
      />
      <TextField
        margin="dense"
        label="Color"
        name="color"
        fullWidth
        variant="standard"
        value={car.color}
        onChange={handleChange}
      />
      <TextField
        margin="dense"
        label="Fuel"
        name="fuel"
        fullWidth
        variant="standard"
        value={car.fuel}
        onChange={handleChange}
      />
      <TextField
        margin="dense"
        label="Year"
        name="year"
        fullWidth
        variant="standard"
        value={car.year}
        onChange={handleChange}
      />
      <TextField
        margin="dense"
        label="Price"
        name="price"
        fullWidth
        variant="standard"
        value={car.price}
        onChange={handleChange}
      />
    </DialogContent>
  );
}

export default CarDialog;
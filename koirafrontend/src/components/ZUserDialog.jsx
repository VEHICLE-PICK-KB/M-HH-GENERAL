import DialogContent from '@mui/material/DialogContent';
import TextField from '@mui/material/TextField';

function ZUserDialog({ kayttajat, handleChange }) {
  return(
    <DialogContent>
      <TextField
        margin="dense"
        label="Username"
        name="username"
        fullWidth
        variant="standard"
        value={kayttajat.username}
        onChange={handleChange}
      />
      <TextField
        margin="dense"
        label="Password"
        name="passwordHash"
        fullWidth
        variant="standard"
        value={kayttajat.passwordHash}
        onChange={handleChange}
      />
      <TextField
        margin="dense"
        label="Etunimi"
        name="etunimi"
        fullWidth
        variant="standard"
        value={kayttajat.etunimi}
        onChange={handleChange}
      />
      <TextField
        margin="dense"
        label="Sukunimi"
        name="sukunimi"
        fullWidth
        variant="standard"
        value={kayttajat.sukunimi}
        onChange={handleChange}
      />
      <TextField
        margin="dense"
        label="Sähköposti"
        name="sahkoposti"
        fullWidth
        variant="standard"
        value={kayttajat.sahkoposti}
        onChange={handleChange}
      />
    </DialogContent>
  );
}

export default ZUserDialog;
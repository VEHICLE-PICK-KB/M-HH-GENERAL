import { useState } from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogTitle from "@mui/material/DialogTitle";

export default function PoistaKayttaja({ fetchTuotteet }) {
  const [open, setOpen] = useState(false);
  const [username, setUsername] = useState("");

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    setUsername(e.target.value);
  };

  const fetchDeleteKayttaja = () => {
    fetch(`https://softala.haaga-helia.fi:8075/api/kayttajat/${username}`, {
      method: "DELETE",
      credentials: "include",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Something went wrong: " + response.statusText);
        }
        fetchTuotteet();
      })
      .catch((err) => console.error(err));

    handleClose();
  };

  return (
    <div>
      <div style={{ display: "flex", justifyContent: "flex-end" }}>
        <Button variant="contained" color="error" onClick={handleClickOpen}>
          Poista käyttäjä
        </Button>
      </div>
      <Dialog open={open} onClose={handleClose} fullWidth maxWidth="xs">
  <DialogTitle style={{ background: "#4CAF50", color: "white" }}>
    Poista käyttäjä
  </DialogTitle>
  <div style={{ padding: "16px", background: "#f1f1f1" }}>
    <label style={{ display: "block", marginBottom: "8px", color: "#333" }}>
      Käyttäjänimi:
    </label>
    <input
      type="text"
      value={username}
      onChange={handleChange}
      style={{
        width: "100%",
        padding: "8px",
        borderRadius: "4px",
        border: "1px solid #ccc",
      }}
    />
  </div>
  <DialogActions style={{ background: "#f1f1f1" }}>
    <Button onClick={handleClose} style={{ color: "#333" }}>
      Peruuta
    </Button>
    <Button onClick={fetchDeleteKayttaja} style={{ color: "#fff", background: "#4CAF50" }}>
      Poista käyttäjä
    </Button>
  </DialogActions>
</Dialog>
    </div>
  );
}
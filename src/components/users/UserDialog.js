"use client";

import { createUserService } from "@/services/UsersService";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TextField,
} from "@mui/material";
import { useState } from "react";

function UserDialog({ open, handleClose, onUserCreate }) {
  const [name, setName] = useState("");
  const [lastname, setLastname] = useState("");
  const [username, setUsername] = useState("");

  const resetStates = () => {
    setName("");
    setLastname("");
    setUsername("");
  };

  const handleSave = async () => {
    let response = await createUserService({ name, lastname, username });

    if (response.ok) {
      resetStates();
      onUserCreate();
      handleClose();
    } else {
      alert("Error al crear usuario");
    }
  };

  const handleCancel = () => {
    resetStates();
    handleClose();
  }

  return (
    <>
      <Dialog open={open}>
        <DialogTitle>Crear usuario</DialogTitle>
        <DialogContent>
          <TextField
            label="Nombre"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <TextField
            label="Apellido"
            value={lastname}
            onChange={(e) => setLastname(e.target.value)}
          />
          <TextField
            label="Usuario"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </DialogContent>
        <DialogActions>
          <Button variant="contained" color="error" onClick={handleCancel}>
            Cancelar
          </Button>
          <Button variant="contained" onClick={handleSave}>
            Guardar
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}

export default UserDialog;

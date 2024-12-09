"use client";

import { deleteUserService, updateUserService } from "@/services/UsersService";
import {
  Button,
  Card,
  CardActions,
  CardContent,
  TextField,
} from "@mui/material";
import { useEffect, useState } from "react";

function UserCard({ user, onUserUpdate }) {
  const [firstName, setFirstName] = useState();
  const [lastName, setLastName] = useState();
  const [editing, setEditing] = useState(false);

  const handleEdit = () => {
    setEditing(true);
  };

  const handleSave = async () => {
    let response = await updateUserService({
      id: user.id,
      name: firstName,
      lastname: lastName,
      username: user.username,
    });

    if (response.ok) {
      onUserUpdate();
      setEditing(false);
    } else {
      alert("Error");
    }
  };

  const handleCancel = () => {
    setEditing(false);
  };

  const handleDelete = async() => {
    let response = await deleteUserService(user.id);

    if(response.ok) {
      onUserUpdate();
    } else {
      alert('Error al eliminar usuario')
    }

  };

  useEffect(() => {
    setFirstName(user.name);
    setLastName(user.lastname);
  }, [user]);

  return (
    <>
      <Card>
        <CardContent>
          <TextField
            disabled={!editing}
            label="Nombre "
            value={firstName || ""}
            onChange={(e) => setFirstName(e.target.value)}
          />
          <TextField
            disabled={!editing}
            label="Apellido "
            value={lastName || ""}
            onChange={(e) => setLastName(e.target.value)}
          />
        </CardContent>
        <CardActions>
          <Button
            variant="contained"
            color={editing ? "success" : "info"}
            onClick={editing ? handleSave : handleEdit}
          >
            {editing ? "Guardar" : "Editar"}
          </Button>
          <Button
            variant="contained"
            color={editing ? "info" : "error"}
            onClick={editing ? handleCancel : handleDelete}
          >
            {editing ? "Cancelar" : "Borrar"}
          </Button>
        </CardActions>
      </Card>
    </>
  );
}

export default UserCard;

'use client'

import UserCard from "@/components/users/UserCard";
import UserDialog from "@/components/users/UserDialog";
import UsersTable from "@/components/users/UsersTable";
import { getUsersService } from "@/services/UsersService";
import { Button } from "@mui/material";
import { useEffect, useState } from "react";

function PageUsers() {
    const [editedUser, setEditedUser] = useState(null)
    const [rows, setRows] = useState([])
    const [dialogOpen, setDialogOpen] = useState(false)

    const getUsers = async () => {
      let data = await getUsersService()
      setEditedUser(null);
      setRows(data)
    }

    const handleEdit = (user) => {
        setEditedUser(user)
    }

    useEffect(() => {
        console.log('Usuario desde page')
        console.log(editedUser)
    }, [editedUser])

    useEffect(() => {
        getUsers()
    }, [])

    return (
        <>
        <div style={{
            display: 'flex',
            flexDirection:'column',
            alignItems: 'center',

        }}>
            <h1>Tabla usuarios</h1>
            <Button variant="contained" onClick={() => setDialogOpen(true)}>Crear usuario</Button>
            <UsersTable handleEdit={handleEdit} data={rows} ></UsersTable>

            {editedUser && <UserCard user={editedUser} onUserUpdate={getUsers} />}

            <UserDialog open={dialogOpen} handleClose={() => setDialogOpen(false)} onUserCreate={getUsers}/>

        </div>
        </>
    )
}

export default PageUsers;
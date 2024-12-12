"use client";

import { Box } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";

function UsersTable({ handleEdit, data }) {
  const columns = [
    { field: "id", headerName: "ID", width: 90 },
    {
      field: "nombre",
      headerName: "Nombre",
      width: 150,
    }
  ];
  
  const rows = data.map(user => ({
    id: user.id,        
    nombre: user.nombre   
  }));

  return (
    <Box padding={1} sx={{height:400 , width:590}}>
      <DataGrid
        rows={rows}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: {
              pageSize: 5,
            },
          },
        }}
        pageSizeOptions={[5]}
        rowSelection
        onRowClick={(data) => {
            handleEdit(data.row)
        }}
      />
    </Box>
  );
}

export default UsersTable;

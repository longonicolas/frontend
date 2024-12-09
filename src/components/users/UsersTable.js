"use client";

import { Box } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";

function UsersTable({ handleEdit, data }) {
  const columns = [
    { field: "id", headeName: "ID", width: 90 },
    {
      field: "name",
      headerName: "Nombre",
      width: 150,
    },
    {
      field: "lastname",
      headerName: "Apellido",
      width: 150,
    },
    {
      field: "username",
      headerName: "Usuario",
      width: 160,
      // valueGetter: (value, row) => `${row.firstName || ""}.${row.lastName}`.toLowerCase(),
    },
  ];

  return (
    <Box padding={1} sx={{height:400 , width:590}}>
      <DataGrid
        rows={data}
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

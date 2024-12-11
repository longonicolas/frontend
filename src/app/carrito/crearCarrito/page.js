'use client';

import React, { useState } from 'react';
import { Button, TextField, Typography, Box, CircularProgress } from '@mui/material';
import { crearCarrito } from '@/services/CarritoService'; // Función para el POST
import { useRouter } from 'next/navigation'; // Importa useRouter para redirección

function CrearCarritoPage() {
    const [clienteId, setClienteId] = useState('');
    const [nombre, setNombre] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [carrito, setCarrito] = useState(null);

    const router = useRouter(); // Instancia de useRouter

    const handleCreate = async () => {
        setLoading(true);
        setError('');
        setCarrito(null);

        try {
            const carritoNuevoInputDTO = {
                clienteId: parseInt(clienteId, 10), // Asegúrate de que sea un número
                nombre,
            };

            const data = await crearCarrito(carritoNuevoInputDTO);
            setCarrito(data); // Guardamos el carrito creado

            // Redirige automáticamente a la página de agregar productos
            router.push(`/carrito/${data.id}/agregarProducto`);
        } catch (err) {
            setError(err.response?.data?.message || 'Ocurrió un error');
        } finally {
            setLoading(false);
        }
    };

    return (
        <Box
            display="flex"
            flexDirection="column"
            alignItems="center"
            justifyContent="center"
            sx={{ padding: 4, gap: 2 }}
        >
            <Typography variant="h4">Crear Nuevo Carrito</Typography>
            <TextField
                label="ID del Cliente"
                variant="outlined"
                value={clienteId}
                onChange={(e) => setClienteId(e.target.value)}
                sx={{ width: '300px' }}
            />
            <TextField
                label="Nombre del Cliente"
                variant="outlined"
                value={nombre}
                onChange={(e) => setNombre(e.target.value)}
                sx={{ width: '300px' }}
            />
            <Button
                variant="contained"
                color="primary"
                onClick={handleCreate}
                disabled={loading || !clienteId || !nombre}
            >
                Crear Carrito
            </Button>

            {loading && <CircularProgress />}
            {error && <Typography color="error">{error}</Typography>}
            {carrito && (
                <Box
                    mt={4}
                    sx={{
                        border: '1px solid #ccc',
                        borderRadius: '8px',
                        padding: 2,
                        width: '300px',
                        backgroundColor: '#f9f9f9',
                    }}
                >
                    <Typography variant="h6">Carrito Creado</Typography>
                    <Typography><strong>ID:</strong> {carrito.id}</Typography>
                    <Typography><strong>Activo:</strong> {carrito.activo ? 'Sí' : 'No'}</Typography>
                    <Typography><strong>Fecha de Expiración:</strong> {carrito.fechaExpiracion}</Typography>
                    <Typography><strong>Cliente:</strong> {carrito.cliente.nombre} (ID: {carrito.cliente.id})</Typography>
                    <Typography><strong>Productos:</strong> {carrito.productos.length > 0 ? '' : 'Ninguno'}</Typography>
                </Box>
            )}
        </Box>
    );
}

export default CrearCarritoPage;

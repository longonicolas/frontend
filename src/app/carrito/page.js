'use client'

import React, { useState } from 'react';
import { Button, TextField, Typography, Box, CircularProgress } from '@mui/material';
import Link from 'next/link';
import { buscarCarrito } from '@/services/CarritoService';

function SearchCarritoPage() {
    const [carritoId, setCarritoId] = useState('');
    const [carrito, setCarrito] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const handleSearch = async () => {
        setLoading(true);
        setError('');
        setCarrito(null);

        try {
            const data = await buscarCarrito(carritoId);
            setCarrito(data);
        } catch (err) {
            setError(err.message);
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
            <Typography variant="h4">Buscar Carrito</Typography>
            <TextField
                label="ID del Carrito"
                variant="outlined"
                value={carritoId}
                onChange={(e) => setCarritoId(e.target.value)}
                sx={{ width: '300px' }}
            />
            <Button
                variant="contained"
                color="primary"
                onClick={handleSearch}
                disabled={loading || !carritoId}
            >
                Buscar
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
        <Typography variant="h6">Carrito Encontrado</Typography>
        <Typography><strong>ID:</strong> {carrito.id}</Typography>
        <Typography><strong>Activo:</strong> {carrito.activo ? 'Sí' : 'No'}</Typography>
        <Typography><strong>Fecha de Expiración:</strong> {carrito.fechaExpiracion}</Typography>
        <Typography><strong>Cliente:</strong> {carrito.cliente?.nombre || 'Desconocido'}</Typography>
        <Typography><strong>Productos:</strong></Typography>
        {carrito.productos.length > 0 ? (
            <ul>
                {carrito.productos.map((producto, index) => (
                    <li key={index}>
                        {producto.nombre} - {producto.cantidad} x ${producto.precio}
                    </li>
                ))}
            </ul>
        ) : (
            <Typography>No hay productos en este carrito.</Typography>
        )}
                </Box>
            )}

            <Button
                variant="outlined"
                color="secondary"
                sx={{ mt: 2 }}
            >
                <Link href="/carrito/crearCarrito" style={{ textDecoration: 'none', color: 'inherit' }}>
                    Crear Carrito
                </Link>
            </Button>
        </Box>
    );

    
}



export default SearchCarritoPage;

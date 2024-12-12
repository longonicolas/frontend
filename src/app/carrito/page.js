'use client';

import React, { useState, useEffect } from 'react';
import {
    Button,
    TextField,
    Typography,
    Box,
    CircularProgress,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    MenuItem,
    Select,
    InputLabel,
    FormControl,
    IconButton,
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import { buscarCarrito, crearCarrito, agregarProductoAlCarrito, finalizarCompra, modificarCantidad } from '@/services/CarritoService';

function SearchCarritoPage() {
    const [carritoId, setCarritoId] = useState('');
    const [carrito, setCarrito] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [dialogAgregarProducto, setDialogAgregarProducto] = useState(false);
    const [productoId, setProductoId] = useState('');
    const [cantidad, setCantidad] = useState('');
    const [dialogCrearCarrito, setDialogCrearCarrito] = useState(false);
    const [clienteId, setClienteId] = useState('');
    const [clienteNombre, setClienteNombre] = useState('');
    const [carritoCreado, setCarritoCreado] = useState(null);
    const [dialogFinalizarCompra, setDialogFinalizarCompra] = useState(false);
    const [medioDePago, setMedioDePago] = useState('');
    const [dialogEditarCantidad, setDialogEditarCantidad] = useState(false); 
    const [productoSeleccionado, setProductoSeleccionado] = useState(null); 
    const [nuevaCantidad, setNuevaCantidad] = useState(''); 

    const fetchCarrito = async (carritoId) => {
        try {
            setLoading(true);
            const data = await buscarCarrito(carritoId);  // Suponiendo que `buscarCarrito` devuelve la respuesta correctamente
            setCarrito(data);
            setError('');
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        if (carritoId) {
            fetchCarrito(carritoId);
        }
    }, [carritoId]);

    const handleSearch = () => {
        setError('');
        if (carritoId) {
            fetchCarrito(carritoId);
        }
    };

    const handleAgregarProducto = async () => {
        if (!productoId || !cantidad) return;

        try {
            await agregarProductoAlCarrito(carritoId, {
                productoId,
                cantidad: parseInt(cantidad, 10),
            });
            alert('Producto agregado al carrito.');
            setDialogAgregarProducto(false);
            setProductoId('');
            setCantidad('');
            fetchCarrito(carritoId);  // Refresca el carrito
        } catch (err) {
            alert('Error al agregar producto: ' + err.message);
        }
    };

    const handleCrearCarrito = async () => {
        if (!clienteId || !clienteNombre) return;

        try {
            const carritoNuevoInputDTO = {
                clienteId: parseInt(clienteId, 10), // Asegúrate de que sea un número
                nombre: clienteNombre,
            };

            const data = await crearCarrito(carritoNuevoInputDTO);
            setCarrito(data); // Guardamos el carrito creado

        } catch (err) {
            alert('Error al crear carrito: ' + err.message);
        }
    };

    const handleFinalizarCompra = async () => {
        if (!medioDePago) return;

        try {
            await finalizarCompra(carritoId, medioDePago);
            alert('Compra finalizada exitosamente.');
            setDialogFinalizarCompra(false);
            setMedioDePago('');
        } catch (err) {
            alert('Error al finalizar la compra: ' + err.message);
        }
    };

    const handleEditarCantidad = (producto) => {
        setProductoSeleccionado(producto);
        setNuevaCantidad(producto.cantidad);
        setDialogEditarCantidad(true);
    };

    const handleGuardarCantidad = async () => {
        if (!nuevaCantidad || !productoSeleccionado) return;

        try {
            await modificarCantidad(carritoId, productoSeleccionado.id, parseInt(nuevaCantidad, 10));
            alert('Cantidad actualizada correctamente.');
            setDialogEditarCantidad(false);
            setNuevaCantidad('');
            setProductoSeleccionado(null);
            fetchCarrito(carritoId);  // Refresca el carrito después de modificar
        } catch (err) {
            alert('Error al actualizar cantidad: ' + err.message);
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
                            {carrito.productos.map((producto) => (
                                <li key={producto.id} style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
                                    {producto.producto.nombre} - {producto.cantidad} x ${producto.producto.precio}
                                    
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <Typography>No hay productos en este carrito.</Typography>
                    )}
                    <Box display="flex" gap={1} mt={2}>
                        <Button
                            variant="outlined"
                            color="primary"
                            onClick={() => setDialogAgregarProducto(true)}
                        >
                            Agregar Producto
                        </Button>
                        <Button
                            variant="contained"
                            color="secondary"
                            onClick={() => setDialogFinalizarCompra(true)}
                        >
                            Finalizar Compra
                        </Button>
                    </Box>
                </Box>
            )}

            {carritoCreado && (
                <Typography sx={{ mt: 2 }}>
                    <strong>Carrito Creado:</strong> ID {carritoCreado.id}
                </Typography>
            )}

            <Button
                variant="outlined"
                color="secondary"
                sx={{ mt: 2 }}
                onClick={() => setDialogCrearCarrito(true)}
            >
                Crear Carrito
            </Button>

            {/* Diálogo para agregar productos */}
            <Dialog open={dialogAgregarProducto} onClose={() => setDialogAgregarProducto(false)}>
                <DialogTitle>Agregar Producto al Carrito</DialogTitle>
                <DialogContent>
                    <TextField
                        label="ID del Producto"
                        fullWidth
                        value={productoId}
                        onChange={(e) => setProductoId(e.target.value)}
                        margin="dense"
                    />
                    <TextField
                        label="Cantidad"
                        type="number"
                        fullWidth
                        value={cantidad}
                        onChange={(e) => setCantidad(e.target.value)}
                        margin="dense"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setDialogAgregarProducto(false)} color="secondary">
                        Cancelar
                    </Button>
                    <Button onClick={handleAgregarProducto} color="primary">
                        Agregar
                    </Button>
                </DialogActions>
            </Dialog>

            {/* Diálogo para crear carrito */}
            <Dialog open={dialogCrearCarrito} onClose={() => setDialogCrearCarrito(false)}>
                <DialogTitle>Crear Carrito</DialogTitle>
                <DialogContent>
                    <TextField
                        label="ID del Cliente"
                        fullWidth
                        value={clienteId}
                        onChange={(e) => setClienteId(e.target.value)}
                        margin="dense"
                    />
                    <TextField
                        label="Nombre del Cliente"
                        fullWidth
                        value={clienteNombre}
                        onChange={(e) => setClienteNombre(e.target.value)}
                        margin="dense"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setDialogCrearCarrito(false)} color="secondary">
                        Cancelar
                    </Button>
                    <Button onClick={handleCrearCarrito} color="primary">
                        Crear
                    </Button>
                </DialogActions>
            </Dialog>

            {/* Diálogo para finalizar compra */}
            <Dialog open={dialogFinalizarCompra} onClose={() => setDialogFinalizarCompra(false)}>
                <DialogTitle>Finalizar Compra</DialogTitle>
                <DialogContent>
                    <FormControl fullWidth margin="dense">
                        <InputLabel id="medio-pago-label">Medio de Pago</InputLabel>
                        <Select
                            labelId="medio-pago-label"
                            value={medioDePago}
                            onChange={(e) => setMedioDePago(e.target.value)}
                        >
                            <MenuItem value="EFECTIVO">Efectivo</MenuItem>
                            <MenuItem value="DEBITO">Débito</MenuItem>
                            <MenuItem value="CREDITO">Crédito</MenuItem>
                        </Select>
                    </FormControl>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setDialogFinalizarCompra(false)} color="secondary">
                        Cancelar
                    </Button>
                    <Button onClick={handleFinalizarCompra} color="primary">
                        Finalizar
                    </Button>
                </DialogActions>
            </Dialog>

            {/* Diálogo para editar cantidad */}
            <Dialog open={dialogEditarCantidad} onClose={() => setDialogEditarCantidad(false)}>
                <DialogTitle>Editar Cantidad del Producto</DialogTitle>
                <DialogContent>
                    <TextField
                        label="Nueva Cantidad"
                        type="number"
                        fullWidth
                        value={nuevaCantidad}
                        onChange={(e) => setNuevaCantidad(e.target.value)}
                        margin="dense"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setDialogEditarCantidad(false)} color="secondary">
                        Cancelar
                    </Button>
                    <Button onClick={handleGuardarCantidad} color="primary">
                        Guardar
                    </Button>
                </DialogActions>
            </Dialog>
        </Box>
    );
}

export default SearchCarritoPage;

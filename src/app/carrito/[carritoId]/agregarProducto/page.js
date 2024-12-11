'use client';

import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { agregarProductoAlCarrito } from '@/services/CarritoService';

export default function AgregarProducto({ params: { carritoId } }) {
    const router = useRouter();
    const [productoId, setProductoId] = useState('');
    const [cantidad, setCantidad] = useState('');
    const [error, setError] = useState('');

    const handleAgregarProducto = async (e) => {
        e.preventDefault();
        setError('');

        if (productoId <= 0 || cantidad <= 0) {
            setError('El ID del producto y la cantidad deben ser mayores a cero.');
            return;
        }

        try {
            const productoData = {
                productoId: parseInt(productoId, 10),
                cantidad: parseInt(cantidad, 10),
            };

            await agregarProductoAlCarrito(carritoId, productoData);
            alert('Producto agregado exitosamente.');
            router.push(`/carrito/${carritoId}`);
        } catch (err) {
            setError(err.message || 'Ocurrió un error inesperado.');
        }
    };

    return (
        <div>
            <h1>Agregar Producto al Carrito {carritoId}</h1>
            <form onSubmit={handleAgregarProducto}>
                <div>
                    <label htmlFor="productoId">Producto ID:</label>
                    <input
                        type="number"
                        id="productoId"
                        value={productoId}
                        onChange={(e) => setProductoId(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="cantidad">Cantidad:</label>
                    <input
                        type="number"
                        id="cantidad"
                        value={cantidad}
                        onChange={(e) => setCantidad(e.target.value)}
                        required
                    />
                </div>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <button type="submit">Agregar Producto</button>
            </form>
        </div>
    );
}

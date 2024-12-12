'use client';

import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { buscarCarrito } from '@/services/CarritoService';

export default function Carrito({ params: { carritoId } }) {
    const [carrito, setCarrito] = useState(null);
    const [error, setError] = useState('');
    const router = useRouter();

    useEffect(() => {
        const fetchCarrito = async () => {
            try {
                const data = await buscarCarrito(carritoId);
                setCarrito(data);
            } catch (err) {
                setError('No se pudo cargar el carrito.');
            }
        };

        fetchCarrito();
    }, [carritoId]);

    if (error) {
        return <p style={{ color: 'red' }}>{error}</p>;
    }

    if (!carrito) {
        return <p>Cargando carrito...</p>;
    }

    return (
        <div>
            <h1>Carrito {carritoId}</h1>
            <ul>
                {carrito.productos.map((producto, index) => (
                    <li key={index}>
                        {producto.nombre} - {producto.cantidad} x ${producto.precio}
                    </li>
                ))}
            </ul>
            <button onClick={() => router.push('/carrito')}>Volver a Carritos</button>
        </div>
    );
}

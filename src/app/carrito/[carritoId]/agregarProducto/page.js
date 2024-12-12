'use client';

import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { agregarProductoAlCarrito } from '@/services/CarritoService';

export default function AgregarProducto({ params: { carritoId } }) {
    const router = useRouter();
    const [productoId, setProductoId] = useState('');
    const [cantidad, setCantidad] = useState('');
    const [error, setError] = useState('');
    const [hoveredButton, setHoveredButton] = useState(null);

    // Estilos comunes para los botones
    const buttonStyle = {
        margin: "10px",
        padding: "15px 30px",
        fontSize: "16px",
        borderRadius: "5px",
        backgroundColor: "#007BFF",
        color: "white",
        border: "none",
        cursor: "pointer",
        transition: "transform 0.3s ease, background-color 0.3s ease",
    };

    // Estilos adicionales para el botón en hover
    const hoverStyle = {
        transform: "scale(1.1)",
        backgroundColor: "#0056b3",
    };

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
            //router.push(`/carrito/${carritoId}/agregarProducto/page.js`);
        } catch (err) {
            setError(err.message || 'Ocurrió un error inesperado.');
        }
    };

    return (
        <div
            style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                height: "100vh",
                padding: "20px",
                fontFamily: "Arial",
            }}
        >
            <h1>Agregar Producto al Carrito {carritoId}</h1>
            <form
                onSubmit={handleAgregarProducto}
                style={{ display: "flex", flexDirection: "column", alignItems: "center" }}
            >
                <div style={{ marginBottom: "15px" }}>
                    <label htmlFor="productoId" style={{ display: "block", marginBottom: "5px" }}>Producto ID:</label>
                    <input
                        type="number"
                        id="productoId"
                        value={productoId}
                        onChange={(e) => setProductoId(e.target.value)}
                        required
                        style={{
                            padding: "10px",
                            fontSize: "16px",
                            borderRadius: "5px",
                            border: "1px solid #ccc",
                        }}
                    />
                </div>
                <div style={{ marginBottom: "15px" }}>
                    <label htmlFor="cantidad" style={{ display: "block", marginBottom: "5px" }}>Cantidad:</label>
                    <input
                        type="number"
                        id="cantidad"
                        value={cantidad}
                        onChange={(e) => setCantidad(e.target.value)}
                        required
                        style={{
                            padding: "10px",
                            fontSize: "16px",
                            borderRadius: "5px",
                            border: "1px solid #ccc",
                        }}
                    />
                </div>
                {error && <p style={{ color: 'red', marginBottom: "15px" }}>{error}</p>}
                <button
                    type="submit"
                    onMouseEnter={() => setHoveredButton("submit")}
                    onMouseLeave={() => setHoveredButton(null)}
                    style={{
                        ...buttonStyle,
                        ...(hoveredButton === "submit" ? hoverStyle : {}),
                    }}
                >
                    Agregar Producto
                </button>
            </form>
            <button
                onClick={() => router.push(`/carrito/${carritoId}`)}
                onMouseEnter={() => setHoveredButton("back")}
                onMouseLeave={() => setHoveredButton(null)}
                style={{
                    ...buttonStyle,
                    ...(hoveredButton === "back" ? hoverStyle : {}),
                }}
            >
                Volver al Carrito
            </button>
        </div>
    );
}

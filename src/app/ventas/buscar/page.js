"use client";

import React, { useState } from "react";
import { buscarVentaService } from "@/services/VentasService";

const BuscarVenta = () => {
  const [ventaId, setVentaId] = useState("");
  const [venta, setVenta] = useState(null);
  const [error, setError] = useState("");

  const handleBuscarVenta = async () => {
    setError(""); // Limpiar cualquier mensaje de error previo
    setVenta(null); // Limpiar cualquier detalle previo

    if (!ventaId) {
      setError("Ingresa un ID de venta válido.");
      return;
    }

    try {
      const data = await buscarVentaService(ventaId);
      if (!data || Object.keys(data).length === 0) {
        setError("EL ID NO CORRESPONDE A UNA VENTA REGISTRADA");
      } else {
        setVenta(data);
      }
    } catch (error) {
      console.error("Error al buscar la venta:", error);
      setError("EL ID NO CORRESPONDE A UNA VENTA REGISTRADA");
    }
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        fontFamily: "Arial",
        padding: "20px",
      }}
    >
      <h1>Buscar Venta por ID</h1>
      <input
        type="text"
        placeholder="ID de Venta"
        value={ventaId}
        onChange={(e) => setVentaId(e.target.value)}
        style={{
          width: "300px",
          height: "40px",
          fontSize: "16px",
          padding: "5px 10px",
          margin: "10px 0",
          borderRadius: "5px",
          border: "1px solid #ccc",
        }}
      />
      <button
        onClick={handleBuscarVenta}
        style={{
          padding: "10px 20px",
          fontSize: "16px",
          borderRadius: "5px",
          backgroundColor: "#007BFF",
          color: "white",
          border: "none",
          cursor: "pointer",
        }}
      >
        Buscar
      </button>
      {error && (
        <div
          style={{
            marginTop: "20px",
            color: "white",
            backgroundColor: "red",
            padding: "10px",
            borderRadius: "5px",
            fontWeight: "bold",
          }}
        >
          {error}
        </div>
      )}
      {venta && (
        <div style={{ marginTop: "20px", textAlign: "left", width: "90%" }}>
          <h2>Detalle de Venta</h2>
          <p>
            <strong>Cliente:</strong> {venta.cliente}
          </p>
          <p>
            <strong>Vendedor:</strong> {venta.vendedor}
          </p>
          <p>
            <strong>Fecha Generación:</strong> {venta.fechaGeneracion}
          </p>
          <p>
            <strong>Importe:</strong> ${venta.importe.toFixed(2)}
          </p>
          <h3>Productos</h3>
          {venta.productos && venta.productos.length > 0 ? (
            <table
              style={{
                borderCollapse: "collapse",
                width: "100%",
                marginTop: "10px",
              }}
            >
              <thead>
                <tr>
                  <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                    Nombre del Producto
                  </th>
                  <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                    Cantidad
                  </th>
                  <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                    Precio Unitario
                  </th>
                </tr>
              </thead>
              <tbody>
                {venta.productos.map((producto) => (
                  <tr key={producto.id}>
                    <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                      {producto.producto.nombre}
                    </td>
                    <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                      {producto.cantidad}
                    </td>
                    <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                      ${producto.precioUnitario.toFixed(2)}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p>No hay productos asociados a esta venta.</p>
          )}
        </div>
      )}
    </div>
  );
};

export default BuscarVenta;

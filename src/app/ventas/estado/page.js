"use client";

import React, { useState } from "react";
import { buscarEstadoVentaService } from "@/services/VentasService";

const BuscarEstadoVenta = () => {
  const [estadoId, setEstadoId] = useState(""); // Solo ID
  const [estadoVenta, setEstadoVenta] = useState(null);
  const [error, setError] = useState(""); // Para manejar errores o mensajes

  const handleBuscarEstadoVenta = async () => {
    setEstadoVenta(null); // Limpiar resultados previos
    setError(""); // Limpiar errores previos

    if (!estadoId) {
      setError("Por favor, ingresa un ID válido.");
      return;
    }

    try {
      const data = await buscarEstadoVentaService(estadoId);
      if (!data || !data.estadoVenta) {
        setError("No se encontró información para el ID proporcionado.");
      } else {
        setEstadoVenta(data.estadoVenta);
      }
    } catch (error) {
      console.error("Error al buscar el estado de venta:", error);
      setError("Hubo un problema al buscar el estado de la venta.");
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
      <h1>Buscar Estado de Venta</h1>
      <input
        type="text"
        placeholder="ID de Venta"
        value={estadoId}
        onChange={(e) => setEstadoId(e.target.value)}
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
        onClick={handleBuscarEstadoVenta}
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

      {/* Mensaje de Error */}
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

      {/* Estado de Venta */}
      {estadoVenta && (
        <div
          style={{
            marginTop: "20px",
            color: estadoVenta === "PAGADO" || estadoVenta === "ENTREGADO" ? "white" : "white",
            backgroundColor:
              estadoVenta === "PAGADO" || estadoVenta === "ENTREGADO" ? "green" : "red",
            padding: "10px",
            borderRadius: "5px",
            fontWeight: "bold",
          }}
        >
          {estadoVenta}
        </div>
      )}
    </div>
  );
};

export default BuscarEstadoVenta;

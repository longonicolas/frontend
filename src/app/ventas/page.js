"use client";

import React, { useState, useEffect } from "react";
import {
  getVentasService,
  buscarVentaService,
  buscarEstadoVentaService,
} from "@/services/VentasService"; // Ajusta el path según tu estructura

const VentasView = () => {
  const [ventas, setVentas] = useState([]);
  const [venta, setVenta] = useState(null);
  const [estadoVenta, setEstadoVenta] = useState(null);

  const [ventaId, setVentaId] = useState("");
  const [estado, setEstado] = useState("");
  const [estadoId, setEstadoId] = useState("");

  // Cargar todas las ventas al montar el componente
  useEffect(() => {
    const fetchVentas = async () => {
      const data = await getVentasService();
      setVentas(data);
    };
    fetchVentas();
  }, []);

  // Buscar una venta por ID
  const handleBuscarVenta = async () => {
    if (!ventaId) return alert("Ingresa un ID de venta válido.");
    const data = await buscarVentaService(ventaId);
    setVenta(data);
  };

  // Buscar estado de venta por estado e ID
  const handleBuscarEstadoVenta = async () => {
    if (!estado || !estadoId)
      return alert("Debes ingresar el estado y el ID.");
    const data = await buscarEstadoVentaService(estado, estadoId);
    setEstadoVenta(data);
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>
      <h1>Gestión de Ventas</h1>

      {/* Listar todas las ventas */}
      <div>
        <h2>Lista de Ventas</h2>
        {ventas.length > 0 ? (
          <ul>
            {ventas.map((venta) => (
              <li key={venta.id}>
                {venta.id} - {venta.descripcion}
              </li>
            ))}
          </ul>
        ) : (
          <p>Cargando ventas...</p>
        )}
      </div>

      {/* Buscar una venta por ID */}
      <div>
        <h2>Buscar Venta</h2>
        <input
          type="text"
          placeholder="ID de Venta"
          value={ventaId}
          onChange={(e) => setVentaId(e.target.value)}
        />
        <button onClick={handleBuscarVenta}>Buscar</button>
        {venta && (
          <div>
            <h3>Detalle de Venta</h3>
            <p>ID: {venta.id}</p>
            <p>Descripción: {venta.descripcion}</p>
          </div>
        )}
      </div>

      {/* Buscar estado de venta */}
      <div>
        <h2>Buscar Estado de Venta</h2>
        <input
          type="text"
          placeholder="Estado"
          value={estado}
          onChange={(e) => setEstado(e.target.value)}
        />
        <input
          type="text"
          placeholder="ID de Venta"
          value={estadoId}
          onChange={(e) => setEstadoId(e.target.value)}
        />
        <button onClick={handleBuscarEstadoVenta}>Buscar</button>
        {estadoVenta && (
          <div>
            <h3>Resultado de la Búsqueda</h3>
            <p>Estado: {estadoVenta.estado}</p>
            <p>Detalles: {estadoVenta.detalles}</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default VentasView;

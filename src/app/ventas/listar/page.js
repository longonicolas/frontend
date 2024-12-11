"use client";

import React, { useEffect, useState } from "react";
import { getVentasService } from "@/services/VentasService";

const ListarVentas = () => {
  const [ventas, setVentas] = useState([]);

  useEffect(() => {
    const fetchVentas = async () => {
      const data = await getVentasService();
      setVentas(data);
    };
    fetchVentas();
  }, []);

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>
      <h1>Lista de Ventas</h1>
      {ventas.length > 0 ? (
        <table style={{ borderCollapse: "collapse", width: "100%" }}>
          <thead>
            <tr>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>ID</th>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>Cliente</th>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>Vendedor</th>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>Fecha Generación</th>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>Fecha Entrega</th>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>Importe</th>
            </tr>
          </thead>
          <tbody>
            {ventas.map((venta) => (
              <tr key={venta.id}>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{venta.id}</td>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{venta.cliente}</td>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{venta.vendedor}</td>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{venta.fechaGeneracion}</td>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{venta.fechaEntrega}</td>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{venta.importe}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Cargando ventas...</p>
      )}
    </div>
  );
};

export default ListarVentas;
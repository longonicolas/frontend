"use client";

import { useRouter } from "next/navigation";
import { useState } from "react";

const VentasPage = () => {
  const router = useRouter();
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
    transition: "transform 0.3s ease, background-color 0.3s ease", // Animación de transición
  };

  // Estilos adicionales para el botón en hover
  const hoverStyle = {
    transform: "scale(1.1)",  // Aumenta el tamaño al pasar el mouse
    backgroundColor: "#0056b3",  // Cambia el color del botón
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
      <h1>Gestión de Ventas</h1>
      <p>Selecciona una opción:</p>
      <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
        <button
          onClick={() => router.push("/ventas/listar")}
          onMouseEnter={() => setHoveredButton("listar")}
          onMouseLeave={() => setHoveredButton(null)}
          style={{
            ...buttonStyle,
            ...(hoveredButton === "listar" ? hoverStyle : {}),
          }}
        >
          Listar Ventas
        </button>
        <button
          onClick={() => router.push("/ventas/buscar")}
          onMouseEnter={() => setHoveredButton("buscar")}
          onMouseLeave={() => setHoveredButton(null)}
          style={{
            ...buttonStyle,
            ...(hoveredButton === "buscar" ? hoverStyle : {}),
          }}
        >
          Buscar Venta por ID
        </button>
        <button
          onClick={() => router.push("/ventas/estado")}
          onMouseEnter={() => setHoveredButton("estado")}
          onMouseLeave={() => setHoveredButton(null)}
          style={{
            ...buttonStyle,
            ...(hoveredButton === "estado" ? hoverStyle : {}),
          }}
        >
          Buscar Estado de Venta
        </button>
      </div>
    </div>
  );
};

export default VentasPage;

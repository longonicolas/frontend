//const API_URL = process.env.NEXT_PUBLIC_API_URL;
const API_URL = "http://localhost:8080";

/**

 * @param {number} carritoId - ID del carrito a buscar.
 * @returns {Promise<Object>} - Datos del carrito o lanza un error si falla.
 */
export async function buscarCarrito(carritoId) {
    const token = localStorage.getItem("jwtToken");
    const response = await fetch(`${API_URL}/carritos/${carritoId}`,
        {headers: {
            "Authorization": `Bearer ${token}`,
          }}
    );
    if (!response.ok) {
        throw new Error(`Error fetching carrito: ${response.status} ${response.statusText}`);
    }
    return await response.json();
}


export async function crearCarrito(carritoNuevoInputDTO) {
    const token = localStorage.getItem("jwtToken");
    const response = await fetch(`${API_URL}/carritos`, {

        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`,
              
        },
        body: JSON.stringify(carritoNuevoInputDTO),
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(`Error al crear el carrito: ${errorMessage}`);
    }

    return response.json();
}

export const agregarProductoAlCarrito = async (carritoId, productoData) => {
    const token = localStorage.getItem("jwtToken");
    try {
        const response = await fetch(`${API_URL}/carritos/${carritoId}/productos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`,
            },
            body: JSON.stringify(productoData),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Error al agregar producto al carrito.');
        }

        return response;
    } catch (error) {
        console.error('Error en agregarProductoAlCarrito:', error.message);
        throw error;
    }
};

export const finalizarCompra = async (carritoId, medioDePago) => {
    const token = localStorage.getItem("jwtToken");
    try {
        const response = await fetch(`${API_URL}/carritos/${carritoId}/finalizacion`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`,
            },
            body: JSON.stringify({ medioDePago }),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Error al finalizar compra.');
        }

        return response;
    } catch (error) {
        console.error('Error en finalizarCompra:', error.message);
        throw error;
    }
}



export const modificarCantidad = async (carritoId, carritoProductoId, cantidad) => {
    const token = localStorage.getItem("jwtToken"); // Asegúrate de que el token esté disponible en localStorage
    if (!token) {
        throw new Error("Token no disponible. Por favor, inicia sesión.");
    }

    try {
        const response = await fetch(`${API_URL}/carritos/${carritoId}/productos`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`,
            },
            body: JSON.stringify({ itemId: carritoProductoId, nuevaCantidad: cantidad }) // Revisa que los nombres coincidan con lo que espera el backend
        });

        if (!response.ok) {
            // Si la respuesta no es correcta, lanzamos un error
            const errorData = await response.json();
            throw new Error(errorData.message || 'Error al modificar cantidad del producto.');
        }

        // Si la respuesta es exitosa, retorna la respuesta
        return response.json(); // Retornar la respuesta como JSON si es necesario
    } catch (error) {
        console.error('Error en modificarCantidad:', error.message);
        throw error; // Lanzamos el error para que pueda ser capturado donde se llama a esta función
    }
};
//export const eliminarProductoDelCarrito = async (carritoId, productoId) => {

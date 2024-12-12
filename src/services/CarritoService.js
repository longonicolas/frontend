const API_URL = process.env.NEXT_PUBLIC_API_URL;

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

const API_URL = "http://localhost:8080";

/**

 * @param {number} carritoId - ID del carrito a buscar.
 * @returns {Promise<Object>} - Datos del carrito o lanza un error si falla.
 */
export async function buscarCarrito(carritoId) {
    const response = await fetch(`${API_URL}/carritos/${carritoId}`);
    if (!response.ok) {
        throw new Error(`Error fetching carrito: ${response.status} ${response.statusText}`);
    }
    return await response.json();
}


export async function crearCarrito(carritoNuevoInputDTO) {
    const response = await fetch(`${API_URL}/carritos`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(carritoNuevoInputDTO),
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(`Error al crear el carrito: ${errorMessage}`);
    }

    return response.json();
}

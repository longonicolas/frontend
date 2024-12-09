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

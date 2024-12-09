const API_URL = "http://localhost:8080";

export async function getVentasService() {
    let data = await fetch(`${API_URL}/ventas`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        return data;
      });
    return data;
}


export async function buscarVentaService(id) {
  let data = await fetch(`${API_URL}/ventas/${id}`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      return data;
    });
  return data;
}

export async function buscarEstadoVentaService(estado, id) {
  let data = await fetch(`${API_URL}/ventas/${estado}/${id}`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      return data;
    });
  return data;  

}

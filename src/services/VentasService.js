//const API_URL = process.env.NEXT_PUBLIC_API_URL;
const API_URL = "http://localhost:8080";

export async function getVentasService() {
  const token = localStorage.getItem("jwtToken");
    let data = await fetch(`${API_URL}/ventas`,
      {headers: {
        "Authorization": `Bearer ${token}`
      }}
    )
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        return data;
      });
    return data;
}


export async function buscarVentaService(id) {
  const token = localStorage.getItem("jwtToken");
  let data = await fetch(`${API_URL}/ventas/${id}`,
    {headers: {
      "Authorization": `Bearer ${token}`
    }}
  )
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      return data;
    });
  return data;
}

export async function buscarEstadoVentaService(id) {
  const token = localStorage.getItem("jwtToken");

  let data = await fetch(`${API_URL}/ventas/${id}/estados`,
    {headers: {
      "Authorization": `Bearer ${token}`
    }}
  )
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      return data;
    });
  return data;  

}

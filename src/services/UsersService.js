const API_URL = process.env.NEXT_PUBLIC_API_URL;



export async function getUsersService() {
  let data = await fetch(`${API_URL}/users`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      return data;
    });
  return data;
}

/*
export async function updateUserService(user) {
  let response = await fetch(`${API_URL}/users/${user.id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  }).then((response) => response);

  return response;
}


export async function deleteUserService(id) {
  let response = await fetch(`${API_URL}/users/${id}`, {
    method: "DELETE",
  }).then((response) => response);

  return response;
}*/

export async function createUserService(user) {
  let response = await fetch(`${API_URL}/signup`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });
  return response;
}

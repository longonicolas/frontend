const API_URL = "http://localhost:8080";

export async function getUsersService() {
  const token = localStorage.getItem("jwtToken");
  let data = await fetch(`${API_URL}/users`, {
    headers: {
      "Authorization": `Bearer ${token}`,
    }
  })
    .then((response) => response.json())
    .then((data) => {
      return data;
    });
  return data;
}

export async function loginUserService(user) {
  try {
    const response = await fetch(`${API_URL}/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(user)
    }).then((response) => response);

    if (response.status === 200) {
      const data = await response.json();
      if (data.token) {
        localStorage.setItem("jwtToken", data.token);
      }
      return { success: true, data };
    } else if (response.status === 403) {
      return { success: false, error: "Invalid credentials" };
    } else {
      return { success: false, error: "An unexpected error occurred" };
    }
  } 
  catch (error) {
    return { success: false, error: "Network error: " + error.message };
  }
}

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
}

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

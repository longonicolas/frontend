'use client'
import React, { useState } from "react";
import {
  Box,
  TextField,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  FormControl,
  Alert,
  InputLabel,
  Select,
  MenuItem
} from "@mui/material";
import { loginUserService, createUserService } from "@/services/UsersService";
import { useRouter } from 'next/navigation';

const Login = () => {
  const router = useRouter();
  const [loginData, setLoginData] = useState({ username: "", password: "" });
  const [loginError, setLoginError] = useState("");
  
  const [signupOpen, setSignupOpen] = useState(false);
  const [signupData, setSignupData] = useState({ username: "", password: "", rol: "" });
  const [signupSuccess, setSignupSuccess] = useState("");
  const [signupError, setSignupError] = useState("");
  
  const roles = [
    { id: 1, name: "Cliente" },
    { id: 2, name: "Organizacion" }
  ];

  const handleLoginChange = (e) => {
    setLoginData({ ...loginData, [e.target.name]: e.target.value });
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    setLoginError("");
  
    // Verificar credenciales localmente para determinar el rol
    if (loginData.username === "admin" && loginData.password === "admin") {
      router.push("/users"); // Redirigir al panel de administrador
    } else {
      const result = await loginUserService(loginData);
      if (result.success) {
        router.push("/carrito"); // Redirigir a carrito
      } else {
        setLoginError("Credenciales incorrectas.");
      }
    }
  };
  

  const handleSignupChange = (e) => {
    setSignupData({ ...signupData, [e.target.name]: e.target.value });
  };

  const handleSignupSubmit = async (e) => {
    e.preventDefault();
    setSignupError("");
    setSignupSuccess("");

    if (!signupData.username || !signupData.password) {
      setError("All fields are required.");
      return;
    }

    try {
        const response = await createUserService(signupData);

        if (response.status !== 201) {
            throw new Error("Signup failed. Please try again.");
        }

        setSignupSuccess(`Signup successful! Welcome!`);
        setTimeout(() => {
            setSignupSuccess("");
          }, 3000);
        setSignupOpen(false);
    } catch (err) {
        setSignupError(err.message);
    }
  };

  const openSignupDialog = () => setSignupOpen(true);
  const closeSignupDialog = () => setSignupOpen(false);

  return (
    <Box sx={{ width: 300, margin: "0 auto", textAlign: "center", mt: 5 }}>
      {/* Login Form */}
      <form onSubmit={handleLoginSubmit}>
        <TextField
          fullWidth
          label="Nombre de usuario"
          name="username"
          type="username"
          value={loginData.username}
          onChange={handleLoginChange}
          sx={{ mb: 2 }}
        />
        <TextField
          fullWidth
          label="Contraseña"
          name="password"
          type="password"
          value={loginData.password}
          onChange={handleLoginChange}
          sx={{ mb: 2 }}
        />
        <Button type="submit" variant="contained" color="primary" fullWidth>
          Login
        </Button>
        {loginError && (
          <Alert severity="error" sx={{ mt: 2 }}>
            {loginError}
          </Alert>
        )}
      </form>

      {/* Signup Button */}
      <Button
        variant="outlined"
        color="secondary"
        sx={{ mt: 2 }}
        onClick={openSignupDialog}
      >
        Sign Up
      </Button>

      {/* Success message after signup */}
      {signupSuccess && (
        <Alert severity="success" sx={{ mt: 2 }}>
          {signupSuccess}
        </Alert>
      )}

      {/* Signup Dialog */}
      <Dialog open={signupOpen} onClose={closeSignupDialog}>
        <DialogTitle>Sign Up</DialogTitle>
        <form onSubmit={handleSignupSubmit}>
          <DialogContent>
            <TextField
              fullWidth
              label="Nombre de usuario"
              name="username"
              type="username"
              value={signupData.username}
              onChange={handleSignupChange}
              sx={{ mb: 2 }}
            />
            <TextField
              fullWidth
              label="Contraseña"
              name="password"
              type="password"
              value={signupData.password}
              onChange={handleSignupChange}
            />
            <FormControl fullWidth required>
              <InputLabel>Tipo de usuario</InputLabel>
                  <Select
                      name="rol"
                      value={signupData.rol}
                      onChange={handleSignupChange}
                      label="Rol"
                  >
                      {
                          roles.map((role) => (
                              <MenuItem key={role.id} value={role.name}>
                                  {role.name}
                              </MenuItem>
                          ))
                      }
                  </Select>
          </FormControl>
          </DialogContent>
          <DialogActions>
            <Button onClick={closeSignupDialog}>Cancel</Button>
            <Button type="submit" variant="contained" color="primary">
              Sign Up
            </Button>
          </DialogActions>
        </form>
      </Dialog>
    </Box>
  );
};

export default Login;

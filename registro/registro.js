const API_BASE = "http://localhost:8080";

document.getElementById("registroForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const usuario = {
    nombre: document.getElementById("nombre").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value,
    rol: document.getElementById("rol").value
  };

  try {
    const res = await fetch(`${API_BASE}/api/usuarios/registro`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(usuario)
    });

    if (res.ok) {
      document.getElementById("mensajeRegistro").textContent = "Registro exitoso. Ya podés iniciar sesión.";
      e.target.reset();
    } else if (res.status === 409) {
      document.getElementById("mensajeRegistro").textContent = "Ese correo ya está registrado.";
    } else {
      document.getElementById("mensajeRegistro").textContent = "Hubo un error al registrarse.";
    }
  } catch (error) {
    console.error("Error de red:", error);
    document.getElementById("mensajeRegistro").textContent = "Error de conexión con el servidor.";
  }
});

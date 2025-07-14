const API_BASE = "http://localhost:8080";

document.getElementById("loginForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  try {
    const res = await fetch(`${API_BASE}/api/usuarios/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ email, password })
    });

    if (res.ok) {
      const usuario = await res.json();
      document.getElementById("mensajeLogin").textContent = `Bienvenido, ${usuario.nombre}`;
      // Acá podrías guardar los datos en localStorage o redireccionar
      // localStorage.setItem("usuario", JSON.stringify(usuario));
      // window.location.href = "dashboard.html";
    } else {
      document.getElementById("mensajeLogin").textContent = "Correo o contraseña incorrectos.";
    }
  } catch (error) {
    console.error("Error de red:", error);
    document.getElementById("mensajeLogin").textContent = "Hubo un problema con el servidor.";
  }
});

const API_BASE = "http://localhost:8080"; // Cambiar si es necesario

document.addEventListener("DOMContentLoaded", () => {
  document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const loginData = {
      email: document.getElementById("email").value,
      password: document.getElementById("password").value
    };

    try {
      const res = await fetch(`${API_BASE}/api/usuarios/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(loginData)
      });

      if (res.ok) {
        const usuario = await res.json();

        Swal.fire({
          icon: "success",
          title: "¡Bienvenido!",
          text: `Hola ${usuario.nombre}`,
          confirmButtonText: "Ir a Turnos"
        }).then(() => {
          window.location.href = "turnos.html"; // Asegúrate que exista o se cree después
        });

        // Guardar el usuario en localStorage si querés mantener sesión:
        // localStorage.setItem("usuario", JSON.stringify(usuario));
      } else {
        Swal.fire("Error", "Email o contraseña incorrectos", "error");
      }
    } catch (error) {
      console.error("Error al iniciar sesión:", error);
      Swal.fire("Error", "Problema de red o servidor", "error");
    }
  });
});

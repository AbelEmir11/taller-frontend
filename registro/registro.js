const API_BASE = "http://localhost:8080"; // Cambia si es necesario

document.addEventListener("DOMContentLoaded", () => {
  document.getElementById("registroForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const nuevoUsuario = {
      nombre: document.getElementById("nombre").value,
      email: document.getElementById("email").value,
      password: document.getElementById("password").value
    };

    try {
      const res = await fetch(`${API_BASE}/api/usuarios/registro`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoUsuario)
      });

      if (res.status === 201) {
        Swal.fire({
          icon: "success",
          title: "¡Registro exitoso!",
          text: "Ya podés iniciar sesión.",
          confirmButtonText: "Ir al login"
        }).then(() => {
          window.location.href = "login.html"; // Asegurate de que exista login.html
        });
      } else if (res.status === 409) {
        Swal.fire("Error", "El email ya está en uso", "error");
      } else {
        Swal.fire("Error", "No se pudo registrar", "error");
      }
    } catch (err) {
      console.error("Error en el registro:", err);
      Swal.fire("Error", "Error de red o servidor", "error");
    }
  });
});

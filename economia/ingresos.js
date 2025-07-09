const API_BASE = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", () => {
  cargarIngresos();

  document.getElementById("ingresoForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const ingreso = {
      concepto: document.getElementById("concepto").value,
      monto: parseFloat(document.getElementById("monto").value),
      fecha: document.getElementById("fecha").value
    };

    try {
      const res = await fetch(`${API_BASE}/api/ingresos`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(ingreso)
      });
if (res.ok) {
  cargarIngresos();
  e.target.reset();

  Swal.fire({
    icon: 'success',
    title: '¡Ingreso agregado!',
    showConfirmButton: false,
    timer: 1500
  });
} else {
  Swal.fire({
    icon: 'error',
    title: 'Error al agregar el ingreso',
    text: 'Inténtalo nuevamente.'
  });
}

    } catch (error) {
      Swal.fire({
    icon: 'error',
    title: 'No se pudo conectar al servidor',
    text: error.message
  });
    }
  });
});

async function cargarIngresos() {
  try {
    const res = await fetch(`${API_BASE}/api/ingresos`);
    const ingresos = await res.json();

    const tbody = document.querySelector("#tablaIngresos tbody");
    tbody.innerHTML = "";

    let total = 0;

    ingresos.forEach(ing => {
      total += ing.monto;

      const fila = `
        <tr>
          <td>${ing.concepto}</td>
          <td>$${ing.monto.toFixed(2)}</td>
          <td>${ing.fecha}</td>
        </tr>
      `;
      tbody.innerHTML += fila;
    });

    document.getElementById("totalIngresos").textContent = total.toFixed(2);
  } catch (error) {
    console.error("Error al cargar ingresos:", error);
    mostrarMensaje("❌ No se pudieron cargar los ingresos", "red");
  }
}

function mostrarMensaje(texto, color) {
  const div = document.getElementById("mensaje");
  div.textContent = texto;
  div.style.color = color;

  setTimeout(() => {
    div.textContent = "";
  }, 4000); // se borra a los 4 segundos
}

const API_BASE = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", () => {
  cargarEgresos();

  document.getElementById("egresoForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const egreso = {
      concepto: document.getElementById("concepto").value,
      monto: parseFloat(document.getElementById("monto").value),
      fecha: document.getElementById("fecha").value
    };

    const res = await fetch(`${API_BASE}/api/egresos`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(egreso)
    });

    if (res.ok) {
      cargarEgresos();
      e.target.reset();
      Swal.fire("¡Éxito!", "Egreso agregado correctamente.", "success");
    } else {
      Swal.fire("Error", "No se pudo agregar el egreso.", "error");
    }
  });
});

async function cargarEgresos() {
  try {
    const res = await fetch(`${API_BASE}/api/egresos`);
    const egresos = await res.json();

    const tbody = document.querySelector("#tablaEgresos tbody");
    const total = egresos.reduce((acc, e) => acc + e.monto, 0);
    tbody.innerHTML = "";

    egresos.forEach(eg => {
      const fila = `
        <tr>
          <td>${eg.concepto}</td>
          <td>$${eg.monto.toFixed(2)}</td>
          <td>${eg.fecha}</td>
        </tr>
      `;
      tbody.innerHTML += fila;
    });

    document.getElementById("totalEgresos").textContent = total.toFixed(2);
  } catch (error) {
    console.error("Error al cargar los egresos:", error);
    Swal.fire("Error", "No se pudieron cargar los egresos.", "error");
  }
}

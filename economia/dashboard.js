const API_BASE = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", () => {
  cargarResumenEconomico();
});

async function cargarResumenEconomico() {
  try {
    const res = await fetch(`${API_BASE}/api/economia`);
    if (!res.ok) throw new Error("Error al obtener el resumen");

    const datos = await res.json();

    // Actualizar los elementos del DOM
    document.getElementById("totalIngresos").textContent = datos.totalIngresos.toFixed(2);
    document.getElementById("totalEgresos").textContent = datos.totalEgresos.toFixed(2);
    document.getElementById("balance").textContent = datos.balance.toFixed(2);

    // Progreso hacia la meta
    const porcentaje = Math.min(datos.porcentajeAlcanzado, 100);
    document.getElementById("barraMeta").style.width = `${porcentaje}%`;
    document.getElementById("porcentajeMeta").textContent = `${porcentaje.toFixed(1)}%`;

    // Crear gráfico
    crearGrafico(datos.totalIngresos, datos.totalEgresos);

  } catch (error) {
    console.error("Error cargando el resumen:", error);
  }
}

function crearGrafico(ingresos, egresos) {
  const ctx = document.getElementById('graficoEconomico').getContext('2d');

  new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: ['Ingresos', 'Egresos'],
      datasets: [{
        label: 'Distribución Económica',
        data: [ingresos, egresos],
        backgroundColor: ['#28a745', '#dc3545'],
        borderColor: ['#1e7e34', '#bd2130'],
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { position: 'bottom' }
      }
    }
  });
}

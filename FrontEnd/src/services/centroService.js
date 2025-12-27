const API_URL = "http://localhost:8080/centros";

export async function listarCentros() {
  const response = await fetch(API_URL);
  if (!response.ok) {
    throw new Error("Error al obtener centros");
  }
  return response.json();
}

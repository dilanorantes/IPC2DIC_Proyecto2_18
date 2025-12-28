import { useState } from "react";

function Ruta() {
  const [rutas, setRutas] = useState([]);
  const [mostrar, setMostrar] = useState(false);

  const listarRutas = async () => {
    try {
      const response = await fetch("http://localhost:8080/rutas");
      const data = await response.json();
      setRutas(data);
      setMostrar(true);
    } catch (error) {
      console.error("Error al listar rutas:", error);
    }
  };

  return (
    <div className="container">
      <h2>Rutas</h2>

      <button onClick={listarRutas}>
        Listar Rutas
      </button>

      {mostrar && (
        rutas.length === 0 ? (
          <p>No hay rutas registradas</p>
        ) : (
          <div style={{ marginTop: "20px" }}>
            {rutas.map((ruta, index) => (
              <div
                key={index}
                style={{
                  padding: "10px",
                  borderBottom: "1px solid #ddd",
                  fontSize: "0.95em"
                }}
              >
                <strong>ID:</strong> {ruta.ide}<br />
                <strong>Origen:</strong> {ruta.origen}<br />
                <strong>Destino:</strong> {ruta.destino}<br />
                <strong>Distancia:</strong> {ruta.distancia}
              </div>
            ))}
          </div>
        )
      )}
    </div>
  );
}

export default Ruta;

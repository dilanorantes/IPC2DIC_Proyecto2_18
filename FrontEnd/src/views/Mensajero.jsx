import { useState } from "react";

function Mensajero() {
  const [mensajeros, setMensajeros] = useState([]);
  const [mostrar, setMostrar] = useState(false);

  const listarMensajeros = async () => {
    try {
      const response = await fetch("http://localhost:8080/mensajeros");
      const data = await response.json();
      setMensajeros(data);
      setMostrar(true);
    } catch (error) {
      console.error("Error al listar mensajeros:", error);
    }
  };

  return (
    <div className="container">
      <h2>Mensajeros</h2>

      <button onClick={listarMensajeros}>
        Listar Mensajeros
      </button>

      {mostrar && (
        mensajeros.length === 0 ? (
          <p>No hay mensajeros registrados</p>
        ) : (
          <div style={{ marginTop: "20px" }}>
            {mensajeros.map((mensajero, index) => (
              <div
                key={index}
                style={{
                  padding: "10px",
                  borderBottom: "1px solid #ddd",
                  fontSize: "0.95em"
                }}
              >
                <strong>ID:</strong> {mensajero.ide}<br />
                <strong>Nombre:</strong> {mensajero.nombre}<br />
                <strong>Capacidad:</strong> {mensajero.capacidad_envio}<br />
                <strong>Centro asignado:</strong> {mensajero.centro_asig}<br />
                <strong>Estado:</strong> {mensajero.estado}
              </div>
            ))}
          </div>
        )
      )}
    </div>
  );
}

export default Mensajero;

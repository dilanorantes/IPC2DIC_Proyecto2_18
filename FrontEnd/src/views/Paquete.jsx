import { useState } from "react";

function Paquete() {

  const [paquetes, setPaquetes] = useState([]);
  const [mostrar, setMostrar] = useState(false);

  const listarPaquetes = async () => {
    try {
      const response = await fetch("http://localhost:8080/paquetes");
      const data = await response.json();
      setPaquetes(data);
      setMostrar(true);
    } catch (error) {
      console.error("Error al listar paquetes:", error);
    }
  };

  return (
    <div className="container">
      <h2>Paquetes</h2>

      <button onClick={listarPaquetes}>
        Listar Paquetes
      </button>

      {mostrar && (
        paquetes.length === 0 ? (
          <p>No hay paquetes registrados</p>
        ) : (
          <div style={{ marginTop: "20px" }}>
            {paquetes.map((paquete, index) => (
              <div
                key={index}
                style={{
                  padding: "10px",
                  borderBottom: "1px solid #ddd",
                  fontSize: "0.95em"
                }}
              >
                <strong>ID:</strong> {paquete.ide}<br />
                <strong>Cliente:</strong> {paquete.cliente}<br />
                <strong>Peso:</strong> {paquete.peso}<br />
                <strong>Destino:</strong> {paquete.destino}<br />
                <strong>Centro Actual:</strong> {paquete.centro_actual}<br />
                <strong>Estado:</strong> {paquete.estado}
              </div>
            ))}
          </div>
        )
      )}
    </div>
  );
}

export default Paquete;

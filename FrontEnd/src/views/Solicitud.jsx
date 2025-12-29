import { useState } from "react";

export default function Solicitud() {
//constgantes principales de solicitudes 
  const [solicitudes, setSolicitudes] = useState([]);
  const [procesada, setProcesada] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  // constante de listar solicitudes 
  const listarSolicitudes = async () => {
    try {
      setLoading(true);
      setError(null);
      setProcesada(null);

      const res = await fetch("http://localhost:8080/solicitudes");
      const data = await res.json();

      setSolicitudes(data);
    } catch {
      setError("No se pudieron cargar las solicitudes");
    } finally {
      setLoading(false);
    }
  };

  
  // constante de procesar solicitud
  const procesarSolicitud = async () => {
    try {
      setLoading(true);
      setError(null);
      setProcesada(null);

      const res = await fetch(
        "http://localhost:8080/solicitudes/procesar",
        { method: "POST" }
      );

      if (!res.ok) {
        const msg = await res.text();
        throw new Error(msg);
      }

      const data = await res.json();
      setProcesada(data);

      listarSolicitudes();
    } catch (e) {
      setError(e.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <h2>Gestión de Solicitudes</h2>

      <div>
        <button onClick={listarSolicitudes}>
          Listar Solicitudes
        </button>

        <button onClick={procesarSolicitud} style={{ marginLeft: "10px" }}>
          Procesar Solicitud
        </button>
      </div>

      {loading && <p>Cargando...</p>}

      {error && (
        <div style={{ marginTop: "15px" }}>
          <strong>{error}</strong>
        </div>
      )}

      {/* Proceso de solicitud procesada */}
      {procesada && (
        <div style={{ marginTop: "20px" }}>
          <h3>Solicitud Procesada</h3>
          <p><strong>ID:</strong> {procesada.ide}</p>
          <p><strong>Tipo:</strong> {procesada.tipo}</p>
          <p><strong>Paquete:</strong> {procesada.paquete_a_enviar}</p>
          <p><strong>Prioridad:</strong> {procesada.prioridad}</p>
          <p><strong>Estado:</strong> {procesada.estado_solicitud}</p>
          <hr />
        </div>
      )}

      {/*Lista de solicitudes */}
      <h3>Cola de Solicitudes</h3>

      {solicitudes.length === 0 ? (
        <p>No hay solicitudes registradas</p>
      ) : (
        solicitudes.map((s, i) => (
          <div
            key={i}
            style={{
              padding: "10px",
              borderBottom: "1px solid #ddd"
            }}
          >
            <strong>ID:</strong> {s.ide}<br />
            <strong>Tipo:</strong> {s.tipo}<br />
            <strong>Paquete:</strong> {s.paquete_a_enviar}<br />
            <strong>Prioridad:</strong> {s.prioridad}<br />
            <strong>Estado:</strong> {s.estado_solicitud}
          </div>
        ))
      )}
    </div>
  );
}

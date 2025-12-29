import { useState } from "react";
// constantes princiapales de solicitud 
export default function Solicitud() {
  const [solicitudes, setSolicitudes] = useState([]);
  const [procesada, setProcesada] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  // cosntane elinar por id 
  const [idEliminar, setIdEliminar] = useState("");

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
//constante de procesar solicitud 
  const procesarSolicitud = async () => {
    try {
      setLoading(true);
      setError(null);
      setProcesada(null);

      const res = await fetch("http://localhost:8080/solicitudes/procesar", {
        method: "POST",
      });

      if (!res.ok) throw new Error(await res.text());

      const data = await res.json();
      setProcesada(data);
      listarSolicitudes();
    } catch (e) {
      setError(e.message);
    } finally {
      setLoading(false);
    }
  };

  // constante eliminar solicitud por id
  const eliminarSolicitudPorId = async () => {
    try {
      setLoading(true);
      setError(null);

      if (!idEliminar.trim()) {
        setError("Ingrese el ID de la solicitud");
        return;
      }

      const res = await fetch(
        `http://localhost:8080/solicitudes/${idEliminar}`,
        { method: "DELETE" }
      );

      if (!res.ok) throw new Error(await res.text());

      setIdEliminar("");
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

      <button onClick={listarSolicitudes}>Listar Solicitudes</button>
      <button onClick={procesarSolicitud} style={{ marginLeft: "10px" }}>
        Procesar Solicitud
      </button>

      <br /><br />

      {/*eliminar por id la solicitud estructura */}
      <h3>Eliminar Solicitud por ID</h3>
      <input
        placeholder="ID de la solicitud"
        value={idEliminar}
        onChange={(e) => setIdEliminar(e.target.value)}
      />
      <button onClick={eliminarSolicitudPorId}>Eliminar</button>

      {loading && <p>Cargando...</p>}
      {error && <p>{error}</p>}

      {procesada && (
        <>
          <h3>Solicitud Procesada</h3>
          <p><strong>ID:</strong> {procesada.ide}</p>
          <p><strong>Tipo:</strong> {procesada.tipo}</p>
          <p><strong>Paquete:</strong> {procesada.paquete_a_enviar}</p>
          <p><strong>Prioridad:</strong> {procesada.prioridad}</p>
          <p><strong>Estado:</strong> {procesada.estado_solicitud}</p>
        </>
      )}

      <h3>Cola de Solicitudes</h3>
      {solicitudes.map((s) => (
        <div key={s.ide} style={{ borderBottom: "1px solid #ccc" }}>
          <strong>ID:</strong> {s.ide}<br />
          <strong>Tipo:</strong> {s.tipo}<br />
          <strong>Paquete:</strong> {s.paquete_a_enviar}<br />
          <strong>Prioridad:</strong> {s.prioridad}<br />
          <strong>Estado:</strong> {s.estado_solicitud}
        </div>
      ))}
    </div>
  );
}

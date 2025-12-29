import { useState } from "react";

function Mensajero() {

  //constantes principales de mensajeros
  const [mensajeros, setMensajeros] = useState([]);
  const [mostrarLista, setMostrarLista] = useState(false);

  // constante de metodo buscar id 
  const [idBuscar, setIdBuscar] = useState("");
  const [mensajeroSeleccionado, setMensajeroSeleccionado] = useState(null);

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // constante de listar 
  const listarMensajeros = async () => {
    try {
      setLoading(true);
      setError(null);

      const response = await fetch("http://localhost:8080/mensajeros");
      const data = await response.json();

      setMensajeros(data);
      setMostrarLista(true);
      setMensajeroSeleccionado(null);
    } catch (error) {
      setError("Error al listar mensajeros");
    } finally {
      setLoading(false);
    }
  };

  // constante de obtener mensaje 
  const obtenerMensajeroPorId = async () => {
    if (!idBuscar) return;

    try {
      setLoading(true);
      setError(null);

      const response = await fetch(`http://localhost:8080/mensajeros/${idBuscar}`);

      if (!response.ok) throw new Error();

      const data = await response.json();
      setMensajeroSeleccionado(data);
      setMostrarLista(false);
    } catch (error) {
      setMensajeroSeleccionado(null);
      setError("Mensajero no encontrado");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <h2>Gestión de Mensajeros</h2>

      {/* buscar mensajero por id */}
      <div>
        <input
          type="text"
          placeholder="ID del mensajero"
          value={idBuscar}
          onChange={(e) => setIdBuscar(e.target.value)}
        />
        <button onClick={obtenerMensajeroPorId}>
          Obtener Mensajero
        </button>
      </div>

      <br />

      {/* listar mensajeros */}
      <button onClick={listarMensajeros}>
        Listar Mensajeros
      </button>

      {loading && <p>Cargando...</p>}
      {error && <p>{error}</p>}

      {/* atributos de mensajeros*/}
      {mensajeroSeleccionado && (
        <div>
          <h3>Detalle del Mensajero</h3>
          <p><strong>ID:</strong> {mensajeroSeleccionado.ide}</p>
          <p><strong>Nombre:</strong> {mensajeroSeleccionado.nombre}</p>
          <p><strong>Capacidad:</strong> {mensajeroSeleccionado.capacidad_envio}</p>
          <p><strong>Centro asignado:</strong> {mensajeroSeleccionado.centro_asig}</p>
          <p><strong>Estado:</strong> {mensajeroSeleccionado.estado}</p>

          <button onClick={() => setMensajeroSeleccionado(null)}>
            Volver
          </button>
        </div>
      )}

      {/* estrucutra de lista de mensjaeros*/}
      {mostrarLista && (
        <>
          <h3>Mensajeros Registrados</h3>

          {mensajeros.length === 0 ? (
            <p>No hay mensajeros registrados</p>
          ) : (
            mensajeros.map((m, index) => (
              <div key={index}>
                <strong>{m.ide}</strong> – {m.nombre} –   
                <strong> Capacidad:</strong> {m.capacidad_envio} – 
                <strong> Estado:</strong> {m.estado}
                <hr />
              </div>
            ))
          )}
        </>
      )}
    </div>
  );
}

export default Mensajero;

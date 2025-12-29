import { useState } from "react";

export default function Paquete() {

  //constantes principales de paquetes
  const [paquetes, setPaquetes] = useState([]);
  const [mostrarLista, setMostrarLista] = useState(false);

  // constante de metodo buscar id 
  const [idBuscar, setIdBuscar] = useState("");
  const [paqueteSeleccionado, setPaqueteSeleccionado] = useState(null);

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // constante de listar 
  const listarPaquetes = async () => {
    try {
      setLoading(true);
      setError(null);

      const response = await fetch("http://localhost:8080/paquetes");
      const data = await response.json();

      setPaquetes(data);
      setMostrarLista(true);
      setPaqueteSeleccionado(null);
    } catch (err) {
      setError("No se pudieron cargar los paquetes");
    } finally {
      setLoading(false);
    }
  };

  // constante de obtener paquete por id 
  const obtenerPaquetePorId = async () => {
    if (!idBuscar) return;

    try {
      setLoading(true);
      setError(null);

      const response = await fetch(`http://localhost:8080/paquetes/${idBuscar}`);

      if (!response.ok) throw new Error();

      const data = await response.json();
      setPaqueteSeleccionado(data);
      setMostrarLista(false);
    } catch (err) {
      setPaqueteSeleccionado(null);
      setError("Paquete no encontrado");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <h2>Gestión de Paquetes</h2>

      {/* buscar paquete por id */}
      <div>
        <input
          type="text"
          placeholder="ID del paquete"
          value={idBuscar}
          onChange={(e) => setIdBuscar(e.target.value)}
        />
        <button onClick={obtenerPaquetePorId}>
          Obtener Paquete
        </button>
      </div>

      <br />

      {/* listar paquetes */}
      <button onClick={listarPaquetes}>
        Listar Paquetes
      </button>

      {loading && <p>Cargando...</p>}
      {error && <p>{error}</p>}

      {/* atributos de paquetes  */}
      {paqueteSeleccionado && (
        <div>
          <h3>Detalle del Paquete</h3>
          <p><strong>ID:</strong> {paqueteSeleccionado.ide}</p>
          <p><strong>Cliente:</strong> {paqueteSeleccionado.cliente}</p>
          <p><strong>Peso:</strong> {paqueteSeleccionado.peso}</p>
          <p><strong>Centro en el que se encuentra:</strong> {paqueteSeleccionado.centro_actual}</p>
          <p><strong>Estado del paquete:</strong> {paqueteSeleccionado.estado}</p>
          <p><strong>Centro al que hay que mandar el paquete:</strong> {paqueteSeleccionado.destino}</p>

          <button onClick={() => setPaqueteSeleccionado(null)}>
            Volver
          </button>
        </div>
      )}

      {/* estructura de lista de paquetes */}
      {mostrarLista && (
        <>
          <h3>Paquetes Registrados</h3>

          {paquetes.length === 0 ? (
            <p>No hay paquetes registrados</p>
          ) : (
            paquetes.map((p, index) => (
              <div key={index}>
                <strong>{p.ide}</strong> – {p.cliente} – 
                <strong> Peso:</strong> {p.peso} – 
                <strong> Estado:</strong> {p.estado}
                <hr />
              </div>
            ))
          )}
        </>
      )}
    </div>
  );
}

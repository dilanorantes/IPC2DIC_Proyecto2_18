import { useState } from "react";
import { listarCentros } from "../services/centroService";

function Centro() {
  const [centros, setCentros] = useState([]);

  const cargarCentros = async () => {
    const data = await listarCentros();
    setCentros(data);
  };

  return (
    <div>
      <h2>Centros</h2>

      <button onClick={cargarCentros}>
        Centros Listar
      </button>

      <br /><br />

      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Ciudad</th>
            <th>Capacidad</th>
          </tr>
        </thead>
        <tbody>
          {centros.map((centro) => (
            <tr key={centro.idcentro}>
              <td>{centro.idcentro}</td>
              <td>{centro.nombre}</td>
              <td>{centro.ciudad}</td>
              <td>{centro.capacidad_total}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Centro;

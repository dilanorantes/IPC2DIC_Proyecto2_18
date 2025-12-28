import { useState } from 'react'

export default function Centro() {

//constantes necesarios para hacer funcionar la pagina 
  const [centros, setCentros] = useState([])
  const [mostrarLista, setMostrarLista] = useState(false)

  const [centroSeleccionado, setCentroSeleccionado] = useState(null)
  const [idBuscar, setIdBuscar] = useState('')

  const [paquetes, setPaquetes] = useState({})
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  //se llama el metodo de listar los centros del backend
  const listarCentros = async () => {
    try {
      setLoading(true)
      setError(null)

      const response = await fetch('http://localhost:8080/centros')
      const data = await response.json()

      setCentros(data)
      setMostrarLista(true)
    } catch (err) {
      setError('No se pudieron cargar los centros')
    } finally {
      setLoading(false)
    }
  }

  //se llama el metodo de obtener centro por ID
  const obtenerCentroPorId = async () => {
    if (!idBuscar) return

    try {
      setLoading(true)
      setError(null)

      const response = await fetch(`http://localhost:8080/centros/${idBuscar}`)

      if (!response.ok) throw new Error()

      const data = await response.json()
      setCentroSeleccionado(data)
    } catch (err) {
      setCentroSeleccionado(null)
      setError('Centro no encontrado')
    } finally {
      setLoading(false)
    }
  }

  // se llama el metodo de obtener paquetes del backend segun el ID del centro
  const obtenerPaquetes = async (idCentro) => {
    try {
      const response = await fetch(`http://localhost:8080/centros/${idCentro}/paquetes`)
      const data = await response.json()

      setPaquetes(prev => ({
        ...prev,
        [idCentro]: data
      }))
    } catch (err) {
      console.error('Error al cargar paquetes')
    }
  }

  return (
    <div className="container">
      <h2>Gestión de Centros</h2>

      {/*Obtiene los centros por el ID que ingresa el usuario*/}
      <div>
        <input
          type="text"
          placeholder="ID del centro"
          value={idBuscar}
          onChange={(e) => setIdBuscar(e.target.value)}
        />
        <button onClick={obtenerCentroPorId}>
          Obtener Centro
        </button>
      </div>

      <br />

      {/*boton para listar los centros */}
      <button onClick={listarCentros}>
        Listar Centros
      </button>

      {loading && <p>Cargando...</p>}
      {error && <p>{error}</p>}

      {/*atributos del centro segun sus variables  */}
      {centroSeleccionado && (
        <div>
          <h3>Detalle del Centro</h3>
          <p>ID: {centroSeleccionado.idcentro}</p>
          <p>Nombre: {centroSeleccionado.nombre}</p>
          <p>Ciudad: {centroSeleccionado.ciudad}</p>
          <p>Capacidad: {centroSeleccionado.capacidad_total}</p>

          <button onClick={() => setCentroSeleccionado(null)}>
            Volver
          </button>
        </div>
      )}

      {/*Lista de todos los centros con la opcion de ver sus paquetes*/}
      {mostrarLista && !centroSeleccionado && (
        <>
          <h3>Centros Registrados</h3>

          {centros.map((centro) => (
            <div key={centro.idcentro}>
              <strong>{centro.idcentro}</strong> – {centro.nombre} ({centro.ciudad}) ({centro.capacidad_total})

                {/*boton para obtener los paqeutes de cada centro*/}
              <div>
                <button onClick={() => obtenerPaquetes(centro.idcentro)}>
                  Ver Paquetes
                </button>
              </div>

              {/* muestra los ids de cada paquete en cada centro  */}
              {paquetes[centro.idcentro] && (
                <div>
                  <p><strong>Paquetes:</strong></p>
                  {paquetes[centro.idcentro].length === 0 ? (
                    <p>No hay paquetes</p>
                  ) : (
                    paquetes[centro.idcentro].map((p, i) => (
                      <p key={i}>
                        <strong>• {p.ide}</strong>
                      </p>
                    ))
                  )}
                </div>
              )}

              <hr />
            </div>
          ))}
        </>
      )}
    </div>
  )
}

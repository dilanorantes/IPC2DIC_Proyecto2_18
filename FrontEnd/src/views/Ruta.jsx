import { useState } from 'react'

export default function Ruta() {
  // Constantes principales
  const [rutas, setRutas] = useState([])
  const [mostrarLista, setMostrarLista] = useState(false)

  // constane para editar ruta
  const [rutaEditar, setRutaEditar] = useState(null)
  
  // constantes de atributos de ruta 
  const [ide, setIde] = useState('')
  const [origen, setOrigen] = useState('')
  const [destino, setDestino] = useState('')
  const [distancia, setDistancia] = useState('')

  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  // listar las rutas 
  const listarRutas = async () => {
    try {
      setLoading(true)
      setError(null)

      const response = await fetch('http://localhost:8080/rutas')
      const data = await response.json()

      setRutas(data)
      setMostrarLista(true)
    } catch (err) {
      setError('No se pudieron cargar las rutas')
    } finally {
      setLoading(false)
    }
  }

  // crear rutas
  const crearRuta = async () => {
    try {
      setLoading(true)
      setError(null)

      // validaciones implemente porque no funcionaba el boton muy bien el boton 
      if (!ide.trim() || !origen.trim() || !destino.trim() || !distancia) {
        setError('Todos los campos son obligatorios')
        return
      }

      const distanciaNum = parseFloat(distancia)
      if (isNaN(distanciaNum) || distanciaNum <= 0) {
        setError('La distancia debe ser mayor a 0')
        return
      }

      const response = await fetch('http://localhost:8080/rutas', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          ide: ide.trim(),
          origen: origen.trim(),
          destino: destino.trim(),
          distancia: distanciaNum
        })
      })

      if (!response.ok) {
        let errorMessage
        try {
          errorMessage = await response.text()
        } catch {
          errorMessage = `Error ${response.status}: ${response.statusText}`
        }
        throw new Error(errorMessage)
      }

      // Limpiar formulario despues de agregar una ruta 
      setIde('')
      setOrigen('')
      setDestino('')
      setDistancia('')
      
      // Actualiza la lista despues de agregar una ruta 
      if (mostrarLista) {
        listarRutas()
      }

    } catch (err) {
      setError(err.message || 'Error al crear ruta')
    } finally {
      setLoading(false)
    }
  }

  // se prepara la edicion de los atributos de las rutas
  const prepararEdicion = (ruta) => {
    setRutaEditar(ruta.ide)
    setIde(ruta.ide)
    setOrigen(ruta.origen)
    setDestino(ruta.destino)
    setDistancia(ruta.distancia.toString())
  }

  // constnate para editar las rutas
  const editarRuta = async () => {
    try {
      setLoading(true)
      setError(null)

      const response = await fetch(`http://localhost:8080/rutas/${rutaEditar}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          ide: ide,
          origen: origen,
          destino: destino,
          distancia: parseFloat(distancia) || 0
        })
      })

      if (!response.ok) {
        const errorText = await response.text()
        throw new Error(errorText)
      }

      const rutaEditada = await response.json()
      
      setRutaEditar(null)
      setIde('')
      setOrigen('')
      setDestino('')
      setDistancia('')
      
      if (mostrarLista) {
        listarRutas()
      }

    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container">
      <h2>Gestión de Rutas</h2>

      {/*estructura de formulario para la editar y crear rutas*/}
      <div>
        <h3>{rutaEditar ? 'Editar Ruta' : 'Crear Nueva Ruta'}</h3>

        <input
          type="text"
          placeholder="ID"
          value={ide}
          onChange={(e) => setIde(e.target.value)}
          disabled={!!rutaEditar}
        />

        <input
          type="text"
          placeholder="Origen"
          value={origen}
          onChange={(e) => setOrigen(e.target.value)}
        />

        <input
          type="text"
          placeholder="Destino"
          value={destino}
          onChange={(e) => setDestino(e.target.value)}
        />

        <input
          type="number"
          placeholder="Distancia"
          value={distancia}
          onChange={(e) => setDistancia(e.target.value)}
          min="0.1"
          step="0.1"
        />

        <br /><br />

        <button onClick={rutaEditar ? editarRuta : crearRuta}>
          {rutaEditar ? 'Guardar Cambios' : 'Crear Ruta'}
        </button>

        {rutaEditar && (
          <button onClick={() => {
            setRutaEditar(null)
            setIde('')
            setOrigen('')
            setDestino('')
            setDistancia('')
            setError(null)
          }}>
            Cancelar
          </button>
        )}

        <br /><br />
      </div>

      {/* Boton para listar rutas */}
      <button onClick={listarRutas}>
        Listar Rutas
      </button>

      {loading && <p>Cargando...</p>}
      {error && <p>{error}</p>}

      {/* Lista de rutas */}
      {mostrarLista && !rutaEditar && (
        <>
          <h3>Rutas Registradas</h3>

          {rutas.length === 0 ? (
            <p>No hay rutas registradas</p>
          ) : (
            rutas.map((ruta) => (
              <div key={ruta.ide}>
                <strong>{ruta.ide}</strong> – 
                {ruta.origen} ➜ {ruta.destino} – 
                <strong>Distancia:</strong> {ruta.distancia}

                <button onClick={() => prepararEdicion(ruta)}>
                  Editar
                </button>

                <hr />
              </div>
            ))
          )}
        </>
      )}
    </div>
  )
}
import { useState } from 'react'

export default function Ruta() {
  //constantes necesarios para rutas
  const [rutas, setRutas] = useState([])
  const [mostrarLista, setMostrarLista] = useState(false)

  const [rutaEditar, setRutaEditar] = useState(null)

  const [ide, setIde] = useState('')
  const [origen, setOrigen] = useState('')
  const [destino, setDestino] = useState('')
  const [distancia, setDistancia] = useState('')

 //constante de eliminar por id 
  const [idEliminar, setIdEliminar] = useState('')

  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
//constante de listar rutas 
  const listarRutas = async () => {
    try {
      setLoading(true)
      setError(null)

      const response = await fetch('http://localhost:8080/rutas')
      const data = await response.json()

      setRutas(data)
      setMostrarLista(true)
    } catch {
      setError('No se pudieron cargar las rutas')
    } finally {
      setLoading(false)
    }
  }
//constante para crear una ruta 
  const crearRuta = async () => {
    try {
      setLoading(true)
      setError(null)

      if (!ide || !origen || !destino || !distancia) {
        setError('Todos los campos son obligatorios')
        return
      }

      const response = await fetch('http://localhost:8080/rutas', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          ide,
          origen,
          destino,
          distancia: parseFloat(distancia)
        })
      })

      if (!response.ok) throw new Error(await response.text())

      setIde('')
      setOrigen('')
      setDestino('')
      setDistancia('')

      if (mostrarLista) listarRutas()
    } catch (e) {
      setError(e.message)
    } finally {
      setLoading(false)
    }
  }

  const prepararEdicion = (ruta) => {
    setRutaEditar(ruta.ide)
    setIde(ruta.ide)
    setOrigen(ruta.origen)
    setDestino(ruta.destino)
    setDistancia(ruta.distancia)
  }
//constante para editar una ruta 
  const editarRuta = async () => {
    try {
      setLoading(true)
      setError(null)

      const res = await fetch(`http://localhost:8080/rutas/${rutaEditar}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          ide,
          origen,
          destino,
          distancia: parseFloat(distancia)
        })
      })

      if (!res.ok) throw new Error(await res.text())

      setRutaEditar(null)
      setIde('')
      setOrigen('')
      setDestino('')
      setDistancia('')

      listarRutas()
    } catch (e) {
      setError(e.message)
    } finally {
      setLoading(false)
    }
  }

  // constante de eliminar ruta por id
  const eliminarRutaPorId = async () => {
    try {
      setLoading(true)
      setError(null)

      if (!idEliminar.trim()) {
        setError('Ingrese el ID de la ruta a eliminar')
        return
      }

      const res = await fetch(`http://localhost:8080/rutas/${idEliminar}`, {
        method: 'DELETE'
      })

      if (!res.ok) throw new Error(await res.text())

      setIdEliminar('')
      listarRutas()
    } catch (e) {
      setError(e.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container">
      <h2>Gestión de Rutas</h2>

      <h3>{rutaEditar ? 'Editar Ruta' : 'Crear Ruta'}</h3>

      <input placeholder="ID" value={ide} onChange={e => setIde(e.target.value)} disabled={!!rutaEditar} />
      <input placeholder="Origen" value={origen} onChange={e => setOrigen(e.target.value)} />
      <input placeholder="Destino" value={destino} onChange={e => setDestino(e.target.value)} />
      <input type="number" placeholder="Distancia" value={distancia} onChange={e => setDistancia(e.target.value)} />

      <br /><br />

      <button onClick={rutaEditar ? editarRuta : crearRuta}>
        {rutaEditar ? 'Guardar Cambios' : 'Crear Ruta'}
      </button>

      <br /><br />

      {/* Eliminar por id ruta estructura */}
      <h3>Eliminar Ruta por ID</h3>
      <input
        placeholder="ID de la ruta"
        value={idEliminar}
        onChange={e => setIdEliminar(e.target.value)}
      />
      <button onClick={eliminarRutaPorId}>Eliminar</button>

      <br /><br />

      <button onClick={listarRutas}>Listar Rutas</button>

      {loading && <p>Cargando...</p>}
      {error && <p>{error}</p>}

      {mostrarLista && (
        <>
          <h3>Rutas Registradas</h3>
          {rutas.map(r => (
            <div key={r.ide}>
              <strong>{r.ide}</strong> — {r.origen} ➜ {r.destino} — {r.distancia}
              <button onClick={() => prepararEdicion(r)}>Editar</button>
              <hr />
            </div>
          ))}
        </>
      )}
    </div>
  )
}

import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="navbar">
        <div className="nav-links">
            <Link to="/">Cargar Archivos</Link>
            <Link to="/Centro">Centros</Link>
            <Link to="/Ruta">Rutas</Link>
            <Link to="/Mensajero">Mensajeros</Link>
            <Link to="/Paquete">Paquetes</Link>
            <Link to="/Solicitud">Solicitudes</Link>
        </div>
    </nav>

  );
}

export default Navbar;

import { Link } from "react-router-dom";
function Home   () {
  //se crea la funcion de abrir un administrador de archivos
  const abrirExplorador = () => {
    document.getElementById("archivo").click();
  };

  return (
      <div>
        <h1>Inicio</h1>

        <button onClick={abrirExplorador}>
          Cargar archivo XML
        </button> 

        <input 
        id = "archivo"
        type="file"
        hidden/>
        
        <hr />
        
        {/*Botones de navegacion */}
        <button>
            <Link to = "/Centro">Gestion de centros</Link>
        </button>
        <button>
            <Link to = "/Ruta">Gestion de rutas</Link>
        </button>
        <button>
            <Link to = "/Mensajero">Gestion de mensajeros</Link>
        </button>
        <button>
            <Link to = "/Paquete">Gestion de paquetes</Link>
        </button>
        <button>
            <Link to = "/Solicitud">Gestion de solicitudes</Link>
        </button>

      </div>
  );    
}

export default Home;

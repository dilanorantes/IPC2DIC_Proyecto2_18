import { Link } from "react-router-dom";
function Home   () {
  //se crea la funcion de abrir un administrador de archivos
  const abrirExplorador = () => {
    const input = document.createElement('input');
    input.type = 'file';
    input.accept = '.xml';
    input.onchange = async (event) => {
      const file = event.target.files[0];
      if (file) {
        try {
          const archivo = new FormData();
          archivo.append("file", file);
          const response = await fetch('http://localhost:8080/api/importar', {
            method: 'POST',
            body: formData,
          });
          //dependiendo de la respuesta del endpoint toma una decision
          if (response.ok) {
            console.log("el archivo se leyó bien");
          } else {
            console.error("hay un errror al enviarlo");
          }
        } catch (error) {
          console.error("error al subirlo", error);
        }
      } else {
        alert("solo selecciona archivos .xml");
      }
    };
    input.click();
  };

  return (
      <div>
        <h1>Inicio</h1>

        <button onClick={abrirExplorador}>
          Cargar archivo XML
        </button> 

        <input
        id="archivo"
        type="file"
        accept=".xml"
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

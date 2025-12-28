import { Link } from "react-router-dom";
function Home   () {
  //se crea la funcion de abrir un administrador de archivos
  const abrirExplorador = () => {
    const entrada = document.createElement('input');
    entrada.type = 'file';
    //solo aceptamos archivo xml, cuando se abre la ventana para elegir pide .xml
    entrada.accept = '.xml';
    
    entrada.onchange = async (event) => {
      const file = event.target.files[0];
      if (file) {
        try {
          const archivo = new FormData();
          archivo.append("file", file);
          //hago el post al backend
          const response = await fetch('http://localhost:8080/api/importar', {
            method: 'POST',
            body: archivo,
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
    entrada.click();
  };

  return (
      <div>
        <h1>Cargar Archivo</h1>

        <button onClick={abrirExplorador}>
          Cargar archivo XML
        </button> 

        <input
        id="archivo"
        type="file"
        accept=".xml"
        hidden/>

      </div>
  );    
}

export default Home;

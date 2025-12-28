import { useState } from "react";

function Home() {
  //mensaje a mostrar lo puse nulo para que no se muestre desde el principio
  //solo cuando le demos click al boton de cargar archivo y haya error o no
  const [mensajeMostrar, setMensaje] = useState(null);

  const abrirExploradorWindows = () => {
    const entrada = document.createElement("input");
    entrada.type = "file";
    entrada.accept = ".xml";
    entrada.onchange = async (event) => {
      const documento = event.target.files[0];

      //si ya se seleccionó intenta hacer un post al bakcend
      if (documento) {
        try {
          const archivo = new FormData();
          archivo.append("file", documento);
          const respuestaBackend = await fetch("http://localhost:8080/api/importar", {
            method: "POST",
            body: archivo,
          });
          if (respuestaBackend.ok) {
            setMensaje("el archivo está correcto y se leyó bien");
          } else {
            setMensaje("se tiene un error al leer el archvo, no se cargó al sistema");
          }
        } catch (error) {
          setMensaje("error inesperado");
        }
      } else {
        alert("error inesperado num2");
      }
    };
    entrada.click();
  };

  return (
    <div>
      <h1>Pagina de carga con un archivo xml </h1>
      <button onClick={abrirExploradorWindows}>Haz click para cargar tu archivo , tiene que ser xml</button>
      {mensajeMostrar && (
      <span
        style={{
          color: mensajeMostrar.includes("error") ? "red" : "green"
        }}
      >
        {mensajeMostrar}
      </span>
)}
    </div>
  );
}


export default Home;

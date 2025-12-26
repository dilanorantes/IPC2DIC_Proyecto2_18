import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  //se crea la funcion de abrir un administrador de archivos
  const abrirExplorador = () => {
    document.getElementById("archivos").click();
  };

  return (
    <>
      <div>
        <button onClick={abrirExplorador}>
          Cargar archivo XML
          </button> 

        <input 
        id = "archivos"
        type="file"
        hidden/>

      </div>
    </>
  );
}

export default App;

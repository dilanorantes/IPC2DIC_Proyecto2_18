import { Routes, Route } from "react-router-dom";

import Navbar from "./components/NavBar";

import Home from "./views/Home";
import Centro from "./views/Centro";
import Ruta from "./views/Ruta";
import Mensajero from "./views/Mensajero";
import Paquete from "./views/Paquete";
import Solicitud from "./views/Solicitud";


function App() {
  return (
    <>
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Centro" element={<Centro />} />
        <Route path="/Ruta" element={<Ruta />} />
        <Route path="/Mensajero" element={<Mensajero />} />
        <Route path="/Paquete" element={<Paquete />} />
        <Route path="/Solicitud" element={<Solicitud />} />
      </Routes>
    </>
  );
}

export default App;


import {BrowserRouter, Routes, Route} from "react-router-dom"

import Home from "./views/Home"
import Centro from "./views/Centro"
import Ruta from "./views/Ruta"
import Mensajero from "./views/Mensajero"
import Paquete from "./views/Paquete"
import Solicitud from "./views/Paquete"

function App (){
    return(
      <BrowserRouter>
        <Routes>
          <Route path="/" element = {<Home />}/> 
          <Route path="/Centro" element = {<Centro />}/> 
          <Route path="/Ruta" element = {<Ruta />}/> 
          <Route path="/Mensajero" element = {<Mensajero />}/> 
          <Route path="/Paquete" element = {<Paquete />}/> 
          <Route path="/Solicitud" element = {<Solicitud />}/> 
        </Routes>
      </BrowserRouter>
    )
}
export default App  


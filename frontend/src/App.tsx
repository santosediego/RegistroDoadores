import Form from "pages/Form";
import Listing from "pages/Listing";
import Print from "pages/Print";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import 'react-toastify/dist/ReactToastify.css';
import Navbar from "./core/components/Navbar";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Listing />} />
        <Route path="/print" element={<Print />} />
        <Route path="/form/:doadorId" element={<Form />} />
        <Route path="/form/:state/:doadorId" element={<Form />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
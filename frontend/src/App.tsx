import Form from "pages/Form";
import Listing from "pages/Listing";
import Print from "pages/Print";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./core/components/Navbar";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <>
      <ToastContainer />
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Listing />} />
          <Route path="/print" element={<Print />} />
          <Route path="/form/:doadorId" element={<Form />} />
          <Route path="/form/:state/:doadorId" element={<Form />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
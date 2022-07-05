import Navbar from "./core/components/Navbar";
import Form from "pages/Form";
import Listing from "pages/Listing";
import Print from "pages/Print";
import Auth from "pages/Auth";
import { Routes, Route } from "react-router-dom";
import { unstable_HistoryRouter as HistoryRouter } from "react-router-dom";
import { history } from "core/utils/history";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <>
      <ToastContainer />
      <HistoryRouter history={history}>
        <Navbar />
        <Routes>
          <Route path="/auth" element={<Auth />} />
          <Route path="/" element={<Listing />} />
          <Route path="/print" element={<Print />} />
          <Route path="/form/:doadorId" element={<Form />} />
          <Route path="/form/:state/:doadorId" element={<Form />} />
        </Routes>
      </HistoryRouter>
    </>
  );
}

export default App;
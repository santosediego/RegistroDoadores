import Navbar from "./core/components/Navbar";
import Form from "pages/Form";
import Listing from "pages/Listing";
import Print from "pages/Print";
import Auth from "pages/Auth";

import { PrivateRoute } from "core/utils/PrivateRoute";
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
          <Route path="/" element={
            <PrivateRoute>
              <Listing />
            </PrivateRoute>
          } />
          <Route path="/print" element={
            <PrivateRoute>
              <Print />
            </PrivateRoute>
          } />
          <Route path="/form/:doadorId" element={
            <PrivateRoute>
              <Form />
            </PrivateRoute>
          } />
          <Route path="/form/:state/:doadorId" element={
            <PrivateRoute>
              <Form />
            </PrivateRoute>
          } />
          <Route path="*" element={
            <PrivateRoute>
              <Listing />
            </PrivateRoute>
          } />
        </Routes>
      </HistoryRouter>
    </>
  );
}

export default App;
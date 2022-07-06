import { Navigate } from "react-router-dom";
import { isAuthenticated } from "./auth";

export function PrivateRoute({ children }) {

    const auth = isAuthenticated();

    return auth ? children : <Navigate to="/auth" />;
}
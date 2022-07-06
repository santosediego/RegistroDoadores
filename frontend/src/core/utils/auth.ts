import jwtDecode from 'jwt-decode';
import { history } from './history';

export const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? 'vidaporvidas';
export const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET ?? 'vidaporvidas123';

type LoginResponse = {
    access_token: string;
    token_type: string;
    expires_in: number;
    scope: string;
    userName: string;
    userId: number;
}

type AccessToken = {
    exp: number;
    user_name: string;
}

export const saveSessionData = (loginResponse: LoginResponse) => {
    localStorage.setItem('authData', JSON.stringify(loginResponse));
}

// Colhe os dados da seção;*
export const getSessionData = () => {
    const sessionData = localStorage.getItem('authData') || '{}';

    const parsedSessionData = JSON.parse(sessionData);

    return parsedSessionData as LoginResponse;
}

// Extrai o token da seção;*
export const getAccessTokenDecoded = () => {
    const sessionData = getSessionData();

    try {
        const tokenDecoded = jwtDecode(sessionData.access_token);
        return tokenDecoded as AccessToken;
    } catch (error){
        return {} as AccessToken;
    }
}

// Verifica se  chave não está expirada;
export const isTokenValid = () => {
    const { exp } = getAccessTokenDecoded();

    return Date.now() <= exp * 1000; // multiplicado por mil para converter para milisegundos;
}

// Solicita a verificação da existência do token e se ele esta expirado;
export const isAuthenticated = () => {
    const sessionData = getSessionData();

    return sessionData.access_token && isTokenValid();
}

// Faz o logout
export const logout = () =>{
    localStorage.removeItem("authData"); // Limpa a chave, se usado .clear() limpa tudo;
    history.replace('/auth');
}
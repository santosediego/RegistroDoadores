
export const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? 'vidaporvidas';
export const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET ?? 'vidaporvidas123';

type LoginResponse = {
    access_token: string;
    token_type: string;
    expires_in: number;
    scope: string;
    userFirstName: string;
    userId: number;
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
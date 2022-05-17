import axios, { Method } from 'axios';

type RequestParams = {
    method?: Method;
    url: string;
    data?: object | string;
    params?: object;
}

type CepParams = {
    cep: string;
}

const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

export const makeRequest = ({ method = 'GET', url, data, params }: RequestParams) => {
    return axios({
        method,
        url: `${BASE_URL}${url}`,
        data,
        params
    })
}

export const cepRequest = ({ cep }: CepParams ) => {
    return axios({
        url: `https://viacep.com.br/ws/${cep}/json/`
    })
}
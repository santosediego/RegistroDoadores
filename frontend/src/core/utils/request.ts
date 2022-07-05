import axios, { AxiosRequestConfig } from 'axios';
import { CLIENT_ID, CLIENT_SECRET, getSessionData } from './auth';
import { history } from './history';

type CepParams = {
    cep: string;
}

type LoginData = {
    username: string;
    password: string;
}

const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

export const cepRequest = ({ cep }: CepParams) => {
    return axios({
        url: `https://viacep.com.br/ws/${cep}/json/`
    })
}

export const makeRequest = (params: AxiosRequestConfig) => {
    return axios({
        ...params,
        baseURL: BASE_URL
    })
}

export const makePrivateRequest = (params: AxiosRequestConfig) => {
    const sessionData = getSessionData();

    const headers = {
        'Authorization': `Bearer ${sessionData.access_token}`
    }

    return makeRequest({ ...params, headers })
}

export const makeLogin = (loginData: LoginData) => {
    const token = `${CLIENT_ID}:${CLIENT_SECRET}`;

    const headers = {
        Authorization: `Basic ${window.btoa(token)}`,
        'Content-Type': 'application/x-www-form-urlencoded'
    }

    const payload = `username=${loginData.username}&password=${loginData.password}&grant_type=password`

    return makeRequest({ url: '/oauth/token', data: payload, method: 'POST', headers });
}

// Add a request interceptor
axios.interceptors.request.use(function (config) {
    // Do something before request is sent
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
}, function (error) {
    if (error.response.status === 401 || error.response.status === 403) {
        history.replace('/auth');
    }
    return Promise.reject(error);
});
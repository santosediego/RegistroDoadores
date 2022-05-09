export type DoadorResponse = {
    content: Doador[];
    totalPages: number;
}

export type Doador = {
    id: number;
    nome: string;
    cpf: string;
    rg: string;
    dataNascimento: string
    genero: string;
    estadoCivil: string;
    grupoSanguineo: string;
    celular: string;
    peso: string;
    logradouro: string;
    numero: string;
    complemento: string;
    bairro: string;
    cep: string;
    localidade: string;
    estado: string;
    dataCadastro: string;
    dataAlteracao: string;
}
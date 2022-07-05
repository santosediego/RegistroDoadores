import { ReactComponent as Delete } from 'core/assets/img/delete.svg';
import BaseForm from "core/components/BaseForm";
import { Doador } from "core/types/Doador";
import { makePrivateRequest } from "core/utils/request";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import Select from 'react-select';
import DoadoresPDF from 'core/report/doadoresReport';
import { messageSuccess } from 'core/utils/toastMessages';

type FormState = {
    doadores: Doador[];
}

function Print() {

    const { handleSubmit } = useForm<FormState>();

    const [selectedDate, setSelectedDate] = useState('')
    const [doadores, setDoadores] = useState<Doador[]>([]);
    const [selectedDoadores, setSelectedDoadores] = useState<Doador[]>([]);
    const [listDoadores, setListDoadores] = useState<Doador[]>([]);
    const [isLoadingDoadores, setIsLoadingDoadores] = useState(false);

    useEffect(() => {
        setIsLoadingDoadores(true);
        makePrivateRequest({ url: '/doadores/export' })
            .then(response => setDoadores(response.data)
            )
            .finally(() => setIsLoadingDoadores(false));

    }, []);

    const handleAddDoador = () => {
        let newList = [...listDoadores];

        selectedDoadores.map(doador => (
            newList.push({
                id: doador.id,
                nome: doador.nome,
                cpf: doador.cpf,
                rg: doador.rg,
                dataNascimento: doador.dataNascimento,
                genero: doador.genero,
                estadoCivil: doador.estadoCivil,
                peso: doador.peso,
                celular: doador.celular,
                grupoSanguineo: doador.grupoSanguineo,
                cep: doador.cep,
                logradouro: doador.logradouro,
                numero: doador.numero,
                bairro: doador.bairro,
                localidade: doador.localidade,
                estado: doador.estado,
                complemento: doador.complemento,
                dataAlteracao: doador.dataAlteracao,
                dataCadastro: doador.dataCadastro
            })
        ))

        setListDoadores(newList);
        setSelectedDoadores([]);
    }

    const handleRemoveList = (index: number) => {
        let newList = [...listDoadores];

        newList.splice(index, 1);

        setListDoadores(newList);
    }

    const onSubmit = () => {
        DoadoresPDF(listDoadores, selectedDate);
        messageSuccess('Listagem gerada com sucesso!')
    }

    return (
        <form
            onSubmit={handleSubmit(onSubmit)}
            className="container"
        >
            <BaseForm title="Lista de doadores">
                <div className="row g-3 mb-5">
                    <div className="col-md-2">
                        <input
                            name='date'
                            type="date"
                            className='form-control'
                            onChange={date => setSelectedDate(date.target.value)}
                            required
                        />
                    </div>
                    <div className="col-md-8">
                        <Select
                            name="doadores"
                            value={selectedDoadores}
                            options={doadores}
                            getOptionLabel={(option: Doador) => option.nome + " - " + option.cpf}
                            getOptionValue={(option: Doador) => String(option.id)}
                            isLoading={isLoadingDoadores}
                            placeholder={"Selecione os doadores"}
                            noOptionsMessage={() => "Sem opções"}
                            closeMenuOnSelect={false}
                            isMulti
                            onChange={value => setSelectedDoadores(value as Doador[])}
                        />
                    </div>

                    <div className="col-md-2">
                        <button
                            type='button'
                            className='form-control'
                            onClick={handleAddDoador}
                        >
                            Adicionar
                        </button>
                    </div>
                </div>

                <div className="row g-3">
                    <table className="table table-responsive table-sm">
                        <thead>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col">Nome</th>
                                <th scope="col">Documentação</th>
                                <th scope="col">Endereço</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            {listDoadores?.map((doador, i) => (

                                <tr>
                                    <td>{i + 1}</td>
                                    <td>{doador.nome}</td>
                                    <td>
                                        <tr>CPF: {doador.cpf}</tr>
                                        <tr>RG: {doador.rg}</tr>
                                        <tr>Cel: {doador.celular}</tr>
                                    </td>
                                    <td>
                                        <tr>End: {doador.logradouro} {doador.numero}</tr>
                                        <tr>Bairro: {doador.bairro}, {doador.localidade}/{doador.estado}</tr>
                                        <tr>{doador.complemento}</tr>
                                    </td>
                                    <td>
                                        <button
                                            type='button'
                                            className='btn outiline:none'
                                            onClick={() => handleRemoveList(i)}
                                        >
                                            <Delete />
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </BaseForm>
        </form>
    );
}

export default Print;
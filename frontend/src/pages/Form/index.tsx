import { cepRequest, makePrivateRequest } from "core/utils/request";
import { messageError, messageSuccess, messageWarning } from "core/utils/toastMessages";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import BaseForm from "../../core/components/BaseForm";
import dayjs from "dayjs";
import { estados, estadosCivis, genero, gruposSanguineo } from "core/utils/selectOptions";
import MaskedInput from "core/utils/MaskedInput";
import ReactDatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

type FormState = {
    id: number;
    nome: string;
    cpf: string;
    rg: string;
    dataNascimento: Date;
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
}

type ParamsType = {
    doadorId: string;
    state: string
}

function Form() {

    const navigate = useNavigate();
    const { doadorId, state } = useParams<ParamsType>();
    const { register, setValue, handleSubmit, control, formState: { errors } } = useForm<FormState>();
    const isView = state === 'view';
    const [isDisabled, setIsDisabled] = useState(false);
    const isEditing = doadorId !== "create"; // o isEditing é diferente de create?
    const formTitle = isView ? 'Visualiar doador' : isEditing ? 'Editar doador' : 'Cadastrar doador';

    useEffect(() => {
        if (isEditing) {
            makePrivateRequest({ url: `/doadores/${doadorId}` })
                .then(response => {
                    setValue('nome', response.data.nome);
                    setValue('cpf', response.data.cpf);
                    setValue('rg', response.data.rg);
                    setValue('dataNascimento', dayjs(response.data.dataNascimento).toDate());
                    setValue('genero', response.data.genero);
                    setValue('estadoCivil', response.data.estadoCivil);
                    setValue('grupoSanguineo', response.data.grupoSanguineo);
                    setValue('celular', response.data.celular);
                    setValue('peso', response.data.peso);
                    setValue('logradouro', response.data.logradouro);
                    setValue('numero', response.data.numero);
                    setValue('complemento', response.data.complemento);
                    setValue('bairro', response.data.bairro);
                    setValue('cep', response.data.cep);
                    setValue('localidade', response.data.localidade);
                    setValue('estado', response.data.estado);
                })
        }
    }, [doadorId, isEditing, setValue]);

    const onSubmit = (data: FormState) => {

        setIsDisabled(true);

        makePrivateRequest({
            url: isEditing ? `/doadores/${doadorId}` : '/doadores',
            method: isEditing ? 'PUT' : 'POST',
            data
        })
            .then((response) => {
                messageSuccess('Doador salvo com sucesso!');
                navigate(`/`);
            })
            .catch((Error) => {
                setIsDisabled(false);
                messageError(Error.response.data.message);
            })
    }

    const askCEP = (cep: string) => {
        cepRequest({ cep })
            .then(response => {
                setValue('logradouro', response.data.logradouro);
                setValue('complemento', response.data.complemento);
                setValue('bairro', response.data.bairro);
                setValue('cep', response.data.cep);
                setValue('localidade', response.data.localidade);
                setValue('estado', response.data.uf);
            })
            .catch(() => {
                messageWarning('CEP não encontrado!');
            })
    };

    return (
        <form onSubmit={handleSubmit(onSubmit)} className="container">
            <BaseForm title={formTitle} isView={isView} isDisabled={isDisabled}>
                <div className="row g-3">
                    <div className="col-12">
                        <input
                            {...register("nome", {
                                required: { value: true, message: "Nome é obrigatório." },
                                minLength: { value: 2, message: "Nome é obrigatório, mínimo de 2 caracteres." },
                            })}
                            type="text"
                            className="form-control"
                            name="nome"
                            placeholder="Nome"
                            disabled={isView}
                            maxLength={120}
                        />
                        {errors.nome && <div className="invalid-feedback d-block">
                            {errors.nome.message}
                        </div>}
                    </div>
                    <div className="col-md-3">
                        <Controller
                            name="cpf"
                            control={control}
                            rules={{
                                required: { value: true, message: "CPF é obrigatório" },
                                minLength: { value: 14, message: "CPF é obrigatório, mínimo de 14 caracteres." },
                            }}
                            render={({ field: { onChange, name, value } }) => {
                                return (
                                    <MaskedInput
                                        mask={"999.999.999-99"}
                                        name={name}
                                        value={value}
                                        onChange={onChange}
                                        disabled={isView}
                                        className="form-control"
                                        placeholder={"CPF"}
                                    />
                                )
                            }}
                        />
                        {errors.cpf && <div className="invalid-feedback d-block">
                            {errors.cpf.message}
                        </div>}
                    </div>
                    <div className="col-md-3">
                        <Controller
                            name="rg"
                            control={control}
                            render={({ field: { onChange, name, value } }) => {
                                return (
                                    <MaskedInput
                                        mask={"99-999.999"}
                                        name={name}
                                        value={value}
                                        onChange={onChange}
                                        disabled={isView}
                                        className="form-control"
                                        placeholder={"RG"}
                                    />
                                )
                            }}
                        />
                    </div>
                    <div className="col-md-3">
                        <Controller
                            control={control}
                            name="dataNascimento"
                            rules={{
                                required: { value: true, message: "Data de nascimento é obrigatório" },
                            }}
                            render={({ field: { onChange, value, ref } }) => (
                                <ReactDatePicker
                                    onChange={onChange}
                                    className="form-control"
                                    name="dataNascimento"
                                    placeholderText="Data de Nascimento"
                                    disabled={isView}
                                    selected={value}
                                    dateFormat="dd/MM/yyyy"
                                    maxDate={new Date()}
                                    showYearDropdown
                                    yearDropdownItemNumber={50}
                                    scrollableYearDropdown
                                    autoComplete="off"
                                />
                            )}
                        />
                        {errors.dataNascimento && <div className="invalid-feedback d-block">
                            {errors.dataNascimento.message}
                        </div>}
                    </div>
                    <div className="col-md-3">
                        <input
                            {...register("peso")}
                            type="number"
                            className="form-control"
                            name="peso"
                            placeholder="Peso"
                            step={"0.01"}
                            min={0}
                            disabled={isView}
                            max={300}
                        />
                    </div>
                    <div className="col-md-3">
                        <select
                            {...register("genero")}
                            className="form-select"
                            name="genero"
                            disabled={isView}
                        >
                            {genero.map((genero) => (
                                <option value={genero.value} >{genero.nome}</option>
                            ))}

                        </select>
                    </div>
                    <div className="col-md-3">
                        <select
                            {...register("estadoCivil")}
                            className="form-select"
                            name="estadoCivil"
                            disabled={isView}
                        >
                            {estadosCivis.map((estadoCivil) => (
                                <option value={estadoCivil.value} >{estadoCivil.nome}</option>
                            ))}
                        </select>
                    </div>
                    <div className="col-md-3">

                        <select
                            {...register("grupoSanguineo")}
                            className="form-select"
                            name="grupoSanguineo"
                            disabled={isView}
                        >
                            {gruposSanguineo.map((grupoSanguineo) => (
                                <option value={grupoSanguineo.value} >{grupoSanguineo.nome}</option>
                            ))}
                        </select>
                    </div>
                    <div className="col-md-3">
                        <Controller
                            name="celular"
                            control={control}
                            render={({ field: { onChange, name, value } }) => {
                                return (
                                    <MaskedInput
                                        mask={"(99) 9 9999-9999"}
                                        name={name}
                                        value={value}
                                        onChange={onChange}
                                        disabled={isView}
                                        className="form-control"
                                        placeholder={"Celular"}
                                    />
                                )
                            }}
                        />
                    </div>
                </div>
                <div className="row g-3">
                    <div className="col-md-4 mt-5">
                        <input
                            {...register("cep")}
                            type="text"
                            onBlur={(e) => askCEP(e.target.value)}
                            className="form-control"
                            name="cep"
                            placeholder="CEP"
                            disabled={isView}
                            maxLength={9}
                        />
                    </div>
                    <div className="col-md-9">
                        <input
                            {...register("logradouro")}
                            type="text"
                            className="form-control"
                            name="logradouro"
                            placeholder="Endereço"
                            disabled={isView}
                            maxLength={120}
                        />
                    </div>
                    <div className="col-md-3">
                        <input
                            {...register("numero")}
                            type="text"
                            className="form-control"
                            name="numero"
                            placeholder="Número"
                            disabled={isView}
                            maxLength={15}
                        />
                    </div>
                    <div className="col-md-6">
                        <input
                            {...register("bairro")}
                            type="text"
                            className="form-control"
                            name="bairro"
                            placeholder="Bairro"
                            disabled={isView}
                            maxLength={120}
                        />
                    </div>
                    <div className="col-md-6">
                        <input
                            {...register("localidade")}
                            type="text"
                            className="form-control"
                            name="localidade"
                            placeholder="Cidade"
                            disabled={isView}
                            maxLength={120}
                        />
                    </div>
                    <div className="col-md-6">
                        <select
                            {...register("estado")}
                            className="form-select"
                            name="estado"
                            disabled={isView}
                        >
                            {estados.map((estado) => (
                                <option value={estado.value} >{estado.nome}</option>
                            ))}
                        </select>
                    </div>
                    <div className="col-md-6">
                        <input
                            {...register("complemento")}
                            type="text"
                            className="form-control"
                            name="complemento"
                            placeholder="Complemento"
                            disabled={isView}
                            maxLength={120}
                        />
                    </div>
                </div>
            </BaseForm>
        </form>
    );
} export default Form;
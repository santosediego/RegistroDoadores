import { cepRequest, makeRequest } from "core/utils/request";
import { messageError, messageSuccess, messageWarning } from "core/utils/toastMessages";
import dayjs from "dayjs";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import BaseForm from "./components/BaseForm";

type FormState = {
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
    pais: string;
}

type ParamsType = {
    doadorId: string;
    state: string
}

function Form() {

    const navigate = useNavigate();
    const { doadorId, state } = useParams<ParamsType>();
    const { register, setValue, handleSubmit } = useForm<FormState>();
    const isView = state === 'view';
    const isEditing = doadorId !== "create"; // o isEditing é diferente de create?
    const formTitle = isView ? 'Visualiar doador' : isEditing ? 'Editar doador' : 'Cadastrar doador';

    useEffect(() => {
        if (isEditing) {
            makeRequest({url:`/doadores/${doadorId}`})
                .then(response => {
                    setValue('nome', response.data.nome);
                    setValue('cpf', response.data.cpf);
                    setValue('rg', response.data.rg);
                    setValue('dataNascimento', (dayjs(response.data.dataNascimento).format('YYYY-MM-DD')));
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

        data.dataNascimento = dayjs(data.dataNascimento).toISOString();

        makeRequest({
            url: isEditing ? `/doadores/${doadorId}` : '/doadores',
            method: isEditing ? 'PUT' : 'POST',
            data
        })
            .then((response) => {
                console.log(response);
                messageSuccess('Doador salvo com sucesso!');
                navigate(`/`);
            })
            .catch(() => {
                messageError('Erro ao salvar doador!')
            })
    }

    const askCEP = (cep: string) => {
        cepRequest({cep})
            .then(response => {
                setValue('logradouro', response.data.logradouro);
                setValue('complemento', response.data.complemento);
                setValue('bairro', response.data.bairro);
                setValue('cep', response.data.cep);
                setValue('localidade', response.data.localidade);
                setValue('estado', response.data.uf);
                setValue('pais', 'Brasil')
            })
            .catch(() => {
                messageWarning('CEP não encontrado!');
            })
    };

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <BaseForm title={formTitle} isView={isView}>
                <div className="row g-3">
                    <div className="col-12">
                        <input
                            {...register("nome")}
                            type="text"
                            className="form-control"
                            name="nome"
                            placeholder="Nome"
                            disabled={isView}
                        />
                    </div>
                    <div className="col-md-4">
                        <input
                            {...register("cpf")}
                            type="text"
                            className="form-control"
                            name="cpf"
                            placeholder="CPF"
                            disabled={isView}
                        />
                    </div>
                    <div className="col-md-4">
                        <input
                            {...register("rg")}
                            type="text"
                            className="form-control"
                            name="rg"
                            placeholder="RG"
                            disabled={isView}
                        />
                    </div>
                    <div className="col-md-4">
                        <input
                            {...register("dataNascimento")}
                            type="date"
                            className="form-control"
                            name="dataNascimento"
                            placeholder="Data Nascimento"
                            disabled={isView}
                        />
                    </div>
                    <div className="col-md-4">
                        <select
                            {...register("genero")}
                            className="form-select"
                            name="genero"
                            disabled={isView}
                        >
                            <option value={"M"} >Masculino</option>
                            <option value={"F"} >Feminino</option>
                        </select>
                    </div>
                    <div className="col-md-4">
                        <select
                            {...register("estadoCivil")}
                            className="form-select"
                            name="estadoCivil"
                            disabled={isView}
                        >
                            <option value={"SOL"} >Solteiro(a)</option>
                            <option value={"CAS"} >Casado(a)</option>
                            <option value={"SEP"} >Separado(a)</option>
                            <option value={"DIV"} >Divorciado(a)</option>
                            <option value={"VIU"} >Viúvo(a)</option>
                        </select>
                    </div>
                    <div className="col-md-4">
                        <input
                            {...register("peso")}
                            type="number"
                            className="form-control"
                            name="peso"
                            placeholder="Peso"
                            step={"0.01"}
                            min={0}
                            disabled={isView}
                        />
                    </div>
                    <div className="col-md-6">
                        <input
                            {...register("celular")}
                            type="text"
                            className="form-control"
                            name="celular"
                            placeholder="Celular"
                            disabled={isView}
                        />
                    </div>
                    <div className="col-md-6">
                        <select
                            {...register("grupoSanguineo")}
                            className="form-select"
                            name="grupoSanguineo"
                            disabled={isView}
                        >
                            <option value={"A+"} >A+</option>
                            <option value={"B+"} >B+</option>
                            <option value={"AB+"} >AB+</option>
                            <option value={"O+"} >O+</option>
                            <option value={"A-"} >A-</option>
                            <option value={"B-"} >B-</option>
                            <option value={"AB-"} >AB-</option>
                            <option value={"O-"} >O-</option>
                            <option value={"FO-"} >Falso O-</option>
                            <option value={"SD"} >Sangue dourado</option>
                        </select>
                    </div>
                </div>
                <div className="row g-3">
                    <div className="col-md-6 mt-5">
                        <input
                            {...register("cep")}
                            type="text"
                            onBlur={(e) => askCEP(e.target.value)}
                            className="form-control"
                            name="cep"
                            placeholder="CEP"
                            disabled={isView}
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
                        />
                    </div>
                    <div className="col-md-6">
                        <select
                            {...register("estado")}
                            className="form-select"
                            name="estado"
                            disabled={isView}
                        >
                            <option value={"AC"}>Acre (AC)</option>
                            <option value={"AL"}>Alagoas (AL)</option>
                            <option value={"AP"}>Amapá (AP)</option>
                            <option value={"AM"}>Amazonas (AM)</option>
                            <option value={"BA"}>Bahia (BA)</option>
                            <option value={"CE"}>Ceará (CE)</option>
                            <option value={"DF"}>Distrito Federal (DF)</option>
                            <option value={"ES"}>Espírito Santo (ES)</option>
                            <option value={"GO"}>Goiás (GO)</option>
                            <option value={"MA"}>Maranhão (MA)</option>
                            <option value={"MT"}>Mato Grosso (MT)</option>
                            <option value={"MS"}>Mato Grosso do Sul (MS)</option>
                            <option value={"MG"}>Minas Gerais (MG)</option>
                            <option value={"PA"}>Pará (PA)</option>
                            <option value={"PB"}>Paraíba (PB)</option>
                            <option value={"PR"}>Paraná (PR)</option>
                            <option value={"PE"}>Pernambuco (PE)</option>
                            <option value={"PI"}>Piauí (PI)</option>
                            <option value={"RJ"}>Rio de Janeiro (RJ)</option>
                            <option value={"RN"}>Rio Grande do Norte (RN)</option>
                            <option value={"RS"}>Rio Grande do Sul (RS)</option>
                            <option value={"RO"}>Rondônia (RO)</option>
                            <option value={"RR"}>Roraima (RR)</option>
                            <option value={"SC"}>Santa Catarina (SC)</option>
                            <option value={"SP"}>São Paulo (SP)</option>
                            <option value={"SE"}>Sergipe (SE)</option>
                            <option value={"TO"}>Tocantins (TO)</option>
                        </select>
                    </div>
                    <div className="col-md-6">
                        <input
                            {...register("pais")}
                            type="text"
                            className="form-control"
                            name="pais"
                            placeholder="País"
                            disabled={isView}
                        />
                    </div>
                    <div className="col-md-12">
                        <input
                            {...register("complemento")}
                            type="text"
                            className="form-control"
                            name="complemento"
                            placeholder="Complemento"
                            disabled={isView}
                        />
                    </div>
                </div>
            </BaseForm>
        </form>
    );
} export default Form;
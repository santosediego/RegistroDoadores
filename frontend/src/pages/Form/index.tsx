import BaseForm from "./components/BaseForm";

function Form() {
    return (
        <form>
            <BaseForm title="Formulário">
                <div className="row g-3">
                    <div className="col-12">
                        <input type="text" className="form-control" name="nome" placeholder="Nome" />
                    </div>
                    <div className="col-md-6">
                        <input type="text" className="form-control" name="cpf" placeholder="CPF" />
                    </div>
                    <div className="col-md-6">
                        <input type="text" className="form-control" name="rg" placeholder="RG" />
                    </div>
                    <div className="col-md-6">
                        <input type="date" className="form-control" name="dataNascimento" placeholder="Data Nascimento" />
                    </div>
                    <div className="col-md-6">
                        <select className="form-select" name="genero">
                            <option key="M">Masculino</option>
                            <option key="F">Feminino</option>
                        </select>
                    </div>
                    <div className="col-md-6">
                        <select className="form-select" name="estadoCivil">
                            <option key="SOL">Solteiro(a)</option>
                            <option key="CAS">Casado(a)</option>
                            <option key="SEP">Separado(a)</option>
                            <option key="DIV">Divorciado(a)</option>
                            <option key="VIU">Viúvo(a)</option>
                        </select>
                    </div>
                    <div className="col-md-6">
                        <input type="number" className="form-control" name="peso" placeholder="Peso" />
                    </div>
                    <div className="col-md-6">
                        <input type="text" className="form-control" name="celular" placeholder="Celular" />
                    </div>
                    <div className="col-md-6">
                        <select className="form-select" name="grupoSanguineo">
                            <option key="A+">A+</option>
                            <option key="B+">B+</option>
                            <option key="AB+">AB+</option>
                            <option key="O+">O+</option>
                            <option key="A-">A-</option>
                            <option key="B-">B-</option>
                            <option key="AB-">AB-</option>
                            <option key="O-">O-</option>
                            <option key="FO-">Falso O-</option>
                            <option key="SD">Sangue dourado</option>
                        </select>
                    </div>
                </div>
                <div className="row g-3">
                    <div className="col-md-6 mt-5">
                        <input type="text" className="form-control" name="cep" placeholder="CEP" />
                    </div>
                    <div className="col-md-12">
                        <input type="text" className="form-control" name="logradouro" placeholder="Endereço" />
                    </div>
                    <div className="col-md-6">
                        <input type="text" className="form-control" name="bairro" placeholder="Bairro" />
                    </div>
                    <div className="col-md-6">
                        <input type="text" className="form-control" name="cidade" placeholder="Cidade" />
                    </div>
                    <div className="col-md-6">
                        <input type="text" className="form-control" name="estado" placeholder="Estado" />
                    </div>
                    <div className="col-md-6">
                        <input type="text" className="form-control" name="pais" placeholder="País" />
                    </div>
                    <div className="col-md-12">
                        <input type="text" className="form-control" name="complemento" placeholder="Complemento" />
                    </div>
                </div>
            </BaseForm>
        </form>
    );
} export default Form;
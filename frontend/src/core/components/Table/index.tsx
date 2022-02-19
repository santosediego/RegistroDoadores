import { ReactComponent as ToView } from 'core/assets/img/toView.svg';
import { ReactComponent as ToEdit } from 'core/assets/img/toEdit.svg';
import { ReactComponent as Delete } from 'core/assets/img/delete.svg';
import './styles.css';

function Table() {

    const pessoa = {
        id: 1,
        nome: 'Joãozinho Krueger',
        cpf: '495.683.636-88',
        rg: '28.953.807'
    }

    return (
        <div className="container">
            <table className="table table-responsive table-sm">
                <thead>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">CPF</th>
                        <th scope="col">RG</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                    <tr>
                        <td>{pessoa.nome}</td>
                        <td>{pessoa.cpf}</td>
                        <td>{pessoa.rg}</td>
                        <td>
                            <button className="btn vidas-ToView"><ToView /></button>
                            <button className="btn"><ToEdit /></button>
                            <button className="btn"><Delete /></button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default Table;
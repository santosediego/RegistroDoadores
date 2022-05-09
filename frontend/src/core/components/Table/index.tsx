import { ReactComponent as ToView } from 'core/assets/img/toView.svg';
import { ReactComponent as ToEdit } from 'core/assets/img/toEdit.svg';
import { ReactComponent as Delete } from 'core/assets/img/delete.svg';
import { DoadorResponse } from 'core/types/Doador';
import './styles.css';

type Props = {
    doadores?: DoadorResponse;
}

function Table({ doadores }: Props) {

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
                    {doadores?.content.map(doador => (
                        <tr key={doador.id}>
                            <td>{doador.nome}</td>
                            <td>{doador.cpf}</td>
                            <td>{doador.rg}</td>
                            <td>
                                <button className="btn vidas-table-ToView" title='Visualizar'><ToView /></button>
                                <button className="btn" title='Editar'><ToEdit /></button>
                                <button className="btn" title='Excluir'><Delete /></button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Table;
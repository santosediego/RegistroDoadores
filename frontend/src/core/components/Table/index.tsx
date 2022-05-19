import { ReactComponent as ToView } from 'core/assets/img/toView.svg';
import { ReactComponent as ToEdit } from 'core/assets/img/toEdit.svg';
import { ReactComponent as Delete } from 'core/assets/img/delete.svg';
import { Doador } from 'core/types/Doador';
import { Link } from 'react-router-dom';
import './styles.css';

type Props = {
    doadores?: Doador[];
    onRemove: (doadorId: number) => void;
}

function Table({ doadores, onRemove }: Props) {

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
                    {doadores?.map(doador => (
                        <tr key={doador.id}>
                            <td>{doador.nome}</td>
                            <td>{doador.cpf}</td>
                            <td>{doador.rg}</td>
                            <td>
                                <Link to={`form/view/${doador.id}`} type="button" title='Visualizar' className="btn vidas-table-ToView" >
                                    <ToView />
                                </Link>
                                <Link to={`form/edit/${doador.id}`} type="button" title='Editar' className="btn vidas-table-ToView" >
                                    <ToEdit />
                                </Link>
                                <button
                                    className="btn"
                                    title='Excluir'
                                    onClick={() => onRemove(doador.id)}
                                >
                                    <Delete />
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Table;
import { ReactComponent as ToView } from 'core/assets/img/toView.svg';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { makePrivateRequest } from 'core/utils/request';
import { messageError, messageSuccess } from 'core/utils/toastMessages';
import './styles.css'

export type FilterForm = {
    search?: string;
}

type Props = {
    onSearch: (filter: FilterForm) => void;
    handleUpload: (selectedFile: File) => void;
}

function Search({ onSearch, handleUpload }: Props) {

    const navigate = useNavigate();
    const [search, setSearch] = useState('');

    const handleChangeSearch = (search: string) => {
        setSearch(search);
        onSearch({ search });
    }

    const handleCreate = () => {
        navigate(`/form/edit/create`);
    }

    const handlePrint = () => {
        navigate(`/print`);
    }

    const handleDownload = () => {
        makePrivateRequest({
            url: `/doadores/download`,
            responseType: 'blob'
        }).then(response => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'doadores.csv'); //or any other extension
            document.body.appendChild(link);
            link.click();
            messageSuccess('Arquivo CSV gerado com sucesso!');
        }).catch(() => {
            messageError('Erro ao realizar download!')
        });
    }

    const handleChangeUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
        const selectedFile = event.target.files?.[0];
        if (selectedFile) {
            handleUpload(selectedFile);
        }
    }

    return (
        <div className='container menu-container'>
            <div className="input-search">
                <input
                    value={search}
                    type="search"
                    placeholder='Pesquise por Nome, CPF ou RG'
                    onChange={event => handleChangeSearch(event.target.value)}
                />
                <ToView />
            </div>
            <div className='menu-container-controls'>
                <button className='btn btn-menu' onClick={handleCreate}> Cadastrar</button>
                <button className='btn btn-menu' onClick={handlePrint}>Gerar lista</button>
                <div className="dropdown">
                    <button className="btn btn-menu dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Arquivos
                    </button>
                    <ul className="dropdown-menu">
                        <li><div className="dropdown-item" onClick={handleDownload}>Exportar doadores</div></li>
                        <li>
                            <div className="dropdown-item">
                                <input
                                    type="file"
                                    id='upload'
                                    hidden
                                    accept='text/csv'
                                    onChange={handleChangeUpload}
                                />
                                <label htmlFor="upload">Importar doadores</label>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default Search;
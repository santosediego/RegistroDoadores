import { ReactComponent as ToView } from 'core/assets/img/toView.svg';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './styles.css'

export type FilterForm = {
    search?: string;
}

type Props = {
    onSearch: (filter: FilterForm) => void;
}

function Search({ onSearch }: Props) {

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
            </div>

        </div>
    );
}

export default Search;
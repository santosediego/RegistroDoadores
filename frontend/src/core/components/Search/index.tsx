import { ReactComponent as ToView } from 'core/assets/img/toView.svg';
import { useNavigate } from 'react-router-dom';
import './styles.css'

function Search() {

    const navigate = useNavigate();

    const handleCreate = () => {
        navigate(`/form/edit/create`);
    }

    return (
        <>
            <div className='container'>
                <div className="menu-container">
                    <div className='menu-nav-contant col'>
                        <div className='input-group'>
                            <input type="text" className='form-control' />
                            <button className='btn btn-outline' type='button'>
                                <ToView />
                            </button>
                        </div>
                        <button className='btn btn-menu' onClick={handleCreate}> Cadastrar</button>
                        <div className='col-md-2'>
                            <button className='btn btn-menu'>Gerar lista</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
} export default Search;
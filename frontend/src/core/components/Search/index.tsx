import { ReactComponent as ToView } from 'core/assets/img/toView.svg';
import { Link } from 'react-router-dom';
import './styles.css'

function Search() {

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
                        <Link to="/form/create" className='col'>
                            <button className='btn btn-menu'> Cadastrar</button>
                        </Link>
                        <div className='col-md-2'>
                            <button className='btn btn-menu'>Gerar lista</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
} export default Search;
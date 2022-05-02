import { ReactComponent as ToView } from 'core/assets/img/toView.svg';
import './styles.css'

function Search() {
    return (
        <>
            <div className='container'>
                <div className="menu-container">
                    <div className='menu-nav-contant col'>
                        <div className='input-group'>
                            <input type="text" className='form-control' />
                            <button className='btn btn-outline' type='button'><ToView /></button>
                        </div>
                        <div className='col'>
                            <button className='btn btn-menu'> Cadastrar</button>
                        </div>
                        <div className='col-md-2'>
                            <button className='btn btn-menu'>Gerar lista</button>
                        </div>
                    </div>
                </div>
            </div>


            {/*
        <div className="container search-container" >
            <div className="input-group mb-3 col">
                <input type="text" className="form-control" />
                <button className="btn btn-outline-secondary" type="button"> <ToView /></button>
            </div>
            <div className="col">
                <button className='btn btn-primary ' type='button'> Cadastrar</button>
                <button className='btn btn-primary' type='button'> Gerar lista</button>
            </div>
        </div >
        */}
        </>
    );
} export default Search;
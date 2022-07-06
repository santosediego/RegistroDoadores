import { getSessionData, logout } from 'core/utils/auth';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import './styles.css';

function Navbar() {

    const [currentUser, setCurrentUser] = useState('');
    const location = useLocation();

    useEffect(() => {
        const currentUser = getSessionData();
        setCurrentUser(currentUser.userName);
    }, [location]);

    const handleLogout = (event: React.MouseEvent<HTMLAnchorElement, MouseEvent>) => {
        event.preventDefault(); // Interrompe o comportamento padrao da url;
        logout();
    }

    return (
        <header>
            <nav className='container vidas-nav'>
                <div className='vidas-nav-content'>
                    <Link to="/">
                        <h1>Vida por vidas</h1>
                    </Link>
                </div>
                <div className='vidas-nav-content'>
                    {currentUser && (
                        <a
                            href="#logout"
                            className='nav-link active d-inline'
                            onClick={(event) => {
                                handleLogout(event);
                            }}
                        >
                            {`${currentUser} / Logout`}
                        </a>
                    )}
                </div>
            </nav>
        </header>)
}

export default Navbar;
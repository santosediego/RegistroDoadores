import { ReactComponent as Arrow } from 'core/assets/img/arrow.svg';
import { DoadorResponse } from 'core/types/Doador';

import './styles.css'

type Props = {
    page: DoadorResponse;
    onChange: Function;
}

function Pagination( { page, onChange } : Props) {
    return (
        <div className="vida-pagination-container">
            <div className="vida-pagination-box">
                <button className="vida-pagination-button" disabled={page.first} onClick = {() => onChange(page.number - 1)} >
                    <Arrow />
                </button>
                <p>{`${page.number + 1} de ${page.totalPages}`}</p>
                <button className="vida-pagination-button" disabled={page.last} onClick = {() => onChange(page.number + 1)} >
                    <Arrow className="vida-flip-horizontal" />
                </button>
            </div>
        </div>
    );
};

export default Pagination;
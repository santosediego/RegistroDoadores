import React from 'react';
import { Link } from 'react-router-dom';
import './styles.css';

type Props = {
    title: string;
    children: React.ReactNode;
}

const BaseForm = ({ title, children }: Props) => {

    return (
        <div className="vida-base-form card-base">
            <h1 className="base-form-title">
                {title}
            </h1>
            {children}
            <div className="base-form-actions">
                <Link to={"/"}>
                    <button className="btn btn-outline-danger">
                        CANCELAR
                    </button>
                </Link>
                <button className="btn btn-primary btn-salvar">
                    SALVAR
                </button>
            </div>
        </div>
    );
}

export default BaseForm;
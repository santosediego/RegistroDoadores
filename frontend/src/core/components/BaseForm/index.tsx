import React from 'react';
import { Link } from 'react-router-dom';
import './styles.css';

type Props = {
    title: string;
    children: React.ReactNode;
    isView?: boolean;
    isDisabled?: boolean;
}

const BaseForm = ({ title, children, isView, isDisabled }: Props) => {

    return (
        <div className="vida-base-form card-base">
            <h1 className="base-form-title">
                {title}
            </h1>
            {children}
            <div className="base-form-actions">
                <Link to={"/"} className="btn btn-outline-danger" type='button'>
                    CANCELAR
                </Link>
                {!isView ? (
                    <button className="btn btn-primary btn-salvar" disabled={isDisabled}>
                        SALVAR
                    </button>
                ) : null}
            </div>
        </div>
    );
}

export default BaseForm;
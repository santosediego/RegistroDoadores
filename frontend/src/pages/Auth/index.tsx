import { saveSessionData } from 'core/utils/auth';
import { makeLogin } from 'core/utils/request';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import './styles.css';

type FormState = {
    username: string;
    password: string;
}

function Auth() {

    const { register, handleSubmit, formState: { errors } } = useForm<FormState>();
    const [hasError, setHasError] = useState(false);
    const navigate = useNavigate();

    const onSubmit = (data: FormState) => {
        makeLogin(data)
            .then(response => {
                setHasError(false);
                saveSessionData(response.data);
                navigate('/');
            })
            .catch(() => {
                setHasError(true);
            })
    }

    return (
        <div className="container">
            <main className="auth-container">
                <form className='login-form' onSubmit={handleSubmit(onSubmit)}>
                    {hasError && (
                        <div className="alert alert-danger mt-5">
                            Usuário ou senha inválidos!
                        </div>
                    )}
                    <div>
                        <input
                            {...register("username", {
                                required: { value: true, message: "Campo obrigatório!" }
                            })}
                            type="username"
                            className={`form-control input-base`}
                            placeholder="Nome de usuário"
                            name="username"
                        />
                        {errors.username && (
                            <div className="invalid-feedback d-block">
                                {errors.username.message}
                            </div>
                        )}
                    </div>
                    <div>
                        <input
                            {...register("password", {
                                required: { value: true, message: "Campo obrigatório!" }
                            })}
                            type="password"
                            className={`form-control input-base`}
                            placeholder="Senha"
                            name="password"
                        />
                        {errors.password && (
                            <div className="invalid-feedback d-block">
                                {errors.password.message}
                            </div>
                        )}
                    </div>
                    <div>
                        <button className='btn btn-primary btn-login'>Logar</button>
                    </div>
                </form>
            </main>
        </div>
    );
}

export default Auth;
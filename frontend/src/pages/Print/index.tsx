import BaseForm from "core/components/BaseForm";
import { Doador } from "core/types/Doador";
import { makeRequest } from "core/utils/request";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import Select from 'react-select';

type FormState = {
    doadores: Doador[];
}

function Print() {

    const { handleSubmit } = useForm<FormState>();

    const [codDoadorTeste, setCodDoadorTeste] = useState(null);

    const [doadores, setDoadores] = useState<Doador[]>();
    const [isLoadingDoadores, setIsLoadingDoadores] = useState(false);
    const { control } = useForm<FormState>();

    useEffect(() => {
        setIsLoadingDoadores(true);
        makeRequest({ url: '/doadores' })
            .then(response => setDoadores(response.data.content))
            .finally(() => setIsLoadingDoadores(false));

    }, []);

    return (
        <form onSubmit={handleSubmit((data => console.log(data)))} className="container">
            <BaseForm title="Lista de doadores - TESTE">
                <div className="row g-3 mb-5">
                    <div className="col-md-10">
                        <Controller
                            name="doadores"
                            control={control}
                            render={({ field : { onChange, onBlur, value, ref} }) => {
                                return (
                                    <Select
                                        options={doadores}
                                        getOptionLabel={(option: Doador) => option.nome + " - " + option.cpf}
                                        getOptionValue={(option: Doador) => String(option.id)}
                                        isLoading={isLoadingDoadores}
                                        placeholder={"Selecione os doadores"}
                                        noOptionsMessage={() => "Sem opções"}
                                        closeMenuOnSelect={false}
                                        isMulti
                                        onChange={onChange}
                                        onBlur={onBlur}
                                    />
                                )
                            }}
                        />
                    </div>
                </div>
            </BaseForm>
        </form>
    );
}

export default Print;
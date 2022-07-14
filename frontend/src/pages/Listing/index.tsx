import Pagination from "core/components/Pagination";
import Search, { FilterForm } from "core/components/Search";
import Table from "core/components/Table";
import { DoadorResponse } from "core/types/Doador";
import { makePrivateRequest } from "core/utils/request";
import { useCallback, useEffect, useState } from "react";
import { messageError, messageInfo } from "core/utils/toastMessages";

function Listing() {

    const [pageNumber, setPageNumber] = useState(0);

    const [page, setPage] = useState<DoadorResponse>({
        content: [],
        last: true,
        totalPages: 0,
        totalElements: 0,
        size: 10,
        number: 0,
        first: true,
        numberOfElements: 0,
        empty: true,
    });

    const handlePageChange = (newPageNumber: number) => {
        setPageNumber(newPageNumber);
    }

    const getDoadores = useCallback(( search?: FilterForm) => {

        const params = {
            page: pageNumber,
            size: 10,
            sort: 'nome,asc',
            conditions: search?.search
        }

        makePrivateRequest({ url: `/doadores`, params })
            .then(response => {
                const data = response.data as DoadorResponse;
                if (data.totalPages === 1) setPageNumber(0);
                setPage(data);
            })
            .catch((error) => {
                console.log(error.response)
                if (error.response.status === 401 || error.response.status === 403) {
                    messageError('Favor, fazer o login');
                } else{
                    messageError('Erro de conexão');
                }
            })
    }, [pageNumber])

    useEffect(() => {
        getDoadores();
    }, [getDoadores]);

    const onRemove = (doadorId: number) => {

        const confirm = window.confirm('Deseja mesmo excluir este doador?');

        if (confirm) {
            makePrivateRequest({ url: `/doadores/${doadorId}`, method: 'DELETE' })
                .then(() => {
                    messageInfo('Doador removido com sucesso!');
                    getDoadores();
                })
                .catch(() => {
                    messageError('Erro ao remover doador!');
                })
        }
    }

    return (
        <>
            <Search onSearch={search => getDoadores(search)} />
            <Table doadores={page.content} onRemove={onRemove} />
            <Pagination page={page} onChange={handlePageChange} />
        </>
    );
}

export default Listing;
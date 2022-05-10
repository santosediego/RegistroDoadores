import axios from "axios";
import Pagination from "core/components/Pagination";
import Search from "core/components/Search";
import Table from "core/components/Table";
import { DoadorResponse } from "core/types/Doador";
import { BASE_URL } from "core/utils/request";
import { useEffect, useState } from "react";

function Listing() {

    const [pageNumber, setPageNumber] = useState(0);
    
    const [page, setpage] = useState<DoadorResponse>({
        content: [],
        last: true,
        totalPages: 0,
        totalElements: 0,
        size: 12,
        number: 0,
        first: true,
        numberOfElements: 0,
        empty: true
    });

    const handlePageChange = (newPageNumber : number) => {
        setPageNumber(newPageNumber);
    }

    useEffect(() => {
        axios.get(`${BASE_URL}/doadores?size=10&page=${pageNumber}`)
            .then(response => {
                const data = response.data as DoadorResponse;
                setpage(data);
            });
    }, [pageNumber]);

    return (
        <>
            <Search />
            <Table doadores={page.content} />
            <Pagination page={page} onChange={handlePageChange} />
        </>
    );
}

export default Listing;
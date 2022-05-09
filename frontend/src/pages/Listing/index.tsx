import axios from "axios";
import Search from "core/components/Search";
import Table from "core/components/Table";
import { DoadorResponse } from "core/types/Doador";
import { BASE_URL } from "core/utils/request";
import { useEffect, useState } from "react";

function Listing() {

    const [pageNumber, setPageNumber] = useState(0);
    const [page, setPage] = useState<DoadorResponse>({
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

    useEffect(() => {
        axios.get(`${BASE_URL}/doadores?size=10`)
            .then(response => {
                const data = response.data as DoadorResponse;
                console.log(data);
                setPageNumber(data.number)
                setPage(data);
            });
    }, []);

    return (
        <>
            <p>{pageNumber + 1}</p>
            <Search />
            <Table doadores={page} />
        </>
    );
}

export default Listing;
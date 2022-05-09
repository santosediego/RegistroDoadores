import axios from "axios";
import Search from "core/components/Search";
import Table from "core/components/Table";
import { BASE_URL } from "core/utils/request";

function Listing() {

    axios.get(`${BASE_URL}/doadores`)
        .then(response => {
            console.log(response.data);
        });

    return (
        <>
            <Search />
            <Table />
        </>
    );
}

export default Listing;
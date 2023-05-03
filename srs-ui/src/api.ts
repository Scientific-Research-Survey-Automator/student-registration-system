import axios from "axios";
import { API_URL } from "./constants";
import { getDummyTableData } from "./utility";

export const getTable = async (table: string) => {
    console.log("Fetching table", table);
    const url = API_URL + "/tables/" + table;

    try {
        const resp = await axios.get(url);
        console.log(resp.data);
        return resp.data;
    } catch (e) {
        return getDummyTableData(table);
    }
};

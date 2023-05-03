import axios from "axios";
import { API_URL } from "./constants";
import { getDummyTableData, getPath } from "./utility";

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

export const postEntity = async (table: string, data: any) => {
    console.log("Posting to ", table);
    console.log("With data ", data);
    const entityPath = getPath(table);
    const url = API_URL + entityPath;

    try {
        const resp = await axios.post(url, data);
        console.log(resp.data);
        return resp.data;
    } catch (e) {
        return getDummyTableData(table)[0];
    }
};

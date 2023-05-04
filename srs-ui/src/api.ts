import axios, { AxiosError } from "axios";
import { API_URL } from "./constants";
import { getDummyTableData, getPath } from "./utility";
import { ErrorInfo } from "react";

export const getTable = async (table: string) => {
    console.log("Fetching table", table);
    const url = API_URL + "/tables/" + table;

    const resp = await axios.get(url);
    console.log(resp.data);
    return resp.data;
};

export const getClassStudents = async (cId: string) => {
    console.log("Fetching students for", cId);
    const url = API_URL + "/enrollment/class/" + cId + "/students";

    try {
        const resp = await axios.get(url);
        console.log(resp.data);
        return resp.data;
    } catch (e: any) {
        throw new Error(e.response.data.message);
    }
};

export const getPreReq = async (courseNo: string, deptCode: string) => {
    console.log("Fetching prereq for", courseNo, deptCode);
    const url = API_URL + "/courses/prerequisites/" + deptCode + "/" + courseNo;

    try {
        const resp = await axios.get(url);
        console.log(resp.data);
        return resp.data;
    } catch (e: any) {
        throw new Error(e.response.data.message);
    }
};

export const postEntity = async (table: string, data: any) => {
    console.log("Posting to ", table);
    console.log("With data ", data);
    const entityPath = getPath(table);
    const url = API_URL + entityPath;

    const resp = await axios.post(url, data);
    console.log(resp.data);
    return resp.data;
};

export const deleteEntity = async (table: string, id: any) => {
    console.log("Deleting from ", table);
    console.log("With id ", id);
    const entityPath = getPath(table);
    const url = API_URL + entityPath + id;

    const resp = await axios.delete(url);
    console.log(resp.data);
    return resp.data;
};

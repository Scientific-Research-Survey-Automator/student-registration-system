export const getDummyTableData = (table: string) => {
    if (table === "STUDENT") {
        return [
            {
                bnumber: "string",
                firstName: "string",
                lastName: "string",
                gpa: 0,
                stLevel: "string",
                email: "string",
                birthDate: "string",
            },
        ];
    }
    return [];
};

export const getPath = (table: string) => {
    if (table === "STUDENTS") return "/students/";
};

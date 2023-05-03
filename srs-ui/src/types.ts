export type StudentType = {
    bnumber: string;
    firstName: string;
    lastName: string;
    gpa: number;
    stLevel: string;
    email: string;
    birthDate: string;
};

export type CourseType = {
    deptCode: string;
    "course#": number;
    title: string;
};

export type PreReqType = {
    deptCode: string;
    "course#": number;
    prDeptCode: string;
    "prCourse#": number;
};

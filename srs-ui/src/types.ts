export type StudentType = {
    "b#": string;
    firstName: string;
    lastName: string;
    gpa: number;
    level: "freshman" | "sophomore" | "junior" | "senior" | "master" | "PhD";
    email: string;
    bdate: string;
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

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
    courseNo: number;
    title: string;
};

export type PreReqType = {
    deptCode: string;
    "course#": number;
    prDeptCode: string;
    "prCourse#": number;
};

export type CourseCredit = {
    courseNo: number;
    credits: number;
};

export type Class = {
    classId: string;
    section: number;
    year: string;
    semester: string;
    limit: number;
    size: number;
    room: string;
};

export type Enrollment = {
    bNumber: string;
    classId: string;
    score: number;
};

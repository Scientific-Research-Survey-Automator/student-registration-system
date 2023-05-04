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
    courseNo: number;
    preDeptCode: string;
    preCourseNo: number;
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
    bnumber: string;
    classId: string;
    score: number;
};

export type Grade = {
    score: number;
    lgrade: string;
};

export type Log = {
    "LOG#": number;
    USER_NAME: string;
    OP_TIME: string;
    TABLE_NAME: string;
    OPERATION: string;
    TUPLE_KEYVALUE: string;
};

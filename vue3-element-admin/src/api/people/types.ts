export interface housemaster{
    id?: number;
    name?: string;
    buildName?: string;
    gender?: string
    age?: string;
    phone?: string;
}

export interface peoplePage extends PageQuery {
    name?: string;
}

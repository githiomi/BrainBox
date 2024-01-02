export interface Response<T> {
    message : string;
    resource : T;
    timestamp : number;
}
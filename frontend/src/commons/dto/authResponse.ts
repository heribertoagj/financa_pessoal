import { Permission } from "./permissionDto";

export class AuthResponse {
    _id?: number;
    name?:string;
    token?: string;
    roles?: Permission[];
}
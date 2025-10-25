import { RoleEnum } from "@commons/enums/roleEnum";

export class RegisterRequest {
    code?: string;
    name?: string;
    email?: string;
    username?: string;
    password?: string;
    role?: RoleEnum;
}
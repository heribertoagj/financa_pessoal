import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, map } from "rxjs";
import { CommonService } from "./commonService";
import { AuthResponse } from "@commons/dto/authResponse";
import { AuthRequest } from "@commons/dto/authRequest";
import { CommonResponse } from "@commons/dto/commonResponse";
import { environment } from "@environment";
import { Path } from "@commons/enums/pathEnum";
import { RegisterRequest } from "@commons/dto/registerRequest";


@Injectable({
    providedIn: 'root'
})
export class AuthService extends CommonService {

    private currentUserSubject: BehaviorSubject<any>;
    public currentUser: Observable<AuthResponse>;

    constructor(private httpClient: HttpClient) {
        super()

        let userAuth = sessionStorage.getItem(environment.tokenSession);
        if (userAuth) {
            userAuth = JSON.parse(userAuth)
        }

        this.currentUserSubject = new BehaviorSubject(userAuth)
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): AuthResponse {
        return this.currentUserSubject.value;
    }

    isAuthenticated(): boolean {
        return this.currentUserValue != null && this.currentUserValue._id != null
    }

    login(auth: AuthRequest): Observable<any> {
        return this.httpClient.post<any>(environment.url + Path.SIGNIN,
            auth, this.httpOptions)
            .pipe(
                map(result => {
                    let response = result as CommonResponse
                    if (response.code == 200) {
                        let data = result.data as AuthResponse
                        sessionStorage.setItem(environment.tokenSession, JSON.stringify(data))
                        this.currentUserSubject.next(data)
                    }
                    return result
                })
            )
    }

    logout(): void {
        sessionStorage.removeItem(environment.tokenSession)
        this.currentUserSubject.next(null)
    }

    register(register: RegisterRequest): Observable<any> {
        return this.httpClient.post<any>(environment.url + Path.REGISTER, register, this.httpOptions);
    }
}
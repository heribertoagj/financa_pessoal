import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Path } from "@commons/enums/pathEnum";
import { environment } from "@environment";
import { CommonService } from "@service/commonService";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class revenueService extends CommonService{
        constructor(private httpClient: HttpClient) {
            super()
        }
    
        find(): Observable<any> {
            return this.httpClient.get<any>(environment.url + Path.REVENUE_FIND, this.httpOptions)
        }
    
        create(): Observable<any> {
            return this.httpClient.post<any>(environment.url + Path.REVENUE_TYPE_CREATE, this.httpOptions)
        }
}
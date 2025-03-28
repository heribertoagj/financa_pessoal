import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { environment } from '@environment';
import { AuthService } from '@service/authService';

export const httpRequestInterceptor: HttpInterceptorFn = (req, next) => {
    const authService = inject(AuthService)
    const path = req.url.replace(environment.url, '')

    if (environment.listPathNoAuth.indexOf(path) != -1) {
        return next(req);
    }

    if (req.method == "GET"){
        const reqWithUserId = req.clone({
            params: req.params.set('idUser', authService.currentUserValue._id ?? '')
        });

        if (authService.currentUserValue.token) {
            const reqWithAuth = reqWithUserId.clone({
                headers: req.headers.set('authorization', "Bearer " + authService.currentUserValue.token)
            })
            return next(reqWithAuth)
        }    

        return next(reqWithUserId)
    } 

    if (authService.currentUserValue.token) {
        const reqWithAuth = req.clone({
            headers: req.headers.set('authorization', "Bearer " + authService.currentUserValue.token)
        })
        return next(reqWithAuth)
    }

    return next(req);
}
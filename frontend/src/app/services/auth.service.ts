import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  login(credentials: any) {
    console.log('Sending login request to:', `${this.apiUrl}/login`);
    return this.http.post(`${this.apiUrl}/login`, credentials)
      .pipe(
        tap(response => console.log('Login response:', response)),
        catchError(error => {
          console.error('Login error:', error);
          return throwError(error);
        })
      );
  }
}

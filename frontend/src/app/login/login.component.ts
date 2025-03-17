import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { CommonResponse } from '@commons/dto/commonResponse';
import { AuthRequest } from '@commons/dto/authRequest';
import { AuthService } from '@service/authService';
import { Router, RouterModule } from '@angular/router';

import { merge } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import {MatIconModule} from '@angular/material/icon';

import { Utils } from '@commons/utils';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule,  MatIconModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoading: boolean = false
  isSubmitted: boolean = false

  errorMessage = ""
  successMessage = ""

  readonly username = new FormControl<string | null>('', [Validators.required]);
  usernameErrorMessage = signal('');

  readonly password = new FormControl('', [Validators.required]);
  passwordErrorMessage = signal('');

  constructor(
    private router: Router,
    private authService: AuthService) {

      merge(this.username.statusChanges, this.username.valueChanges)
      .pipe(takeUntilDestroyed())
      .subscribe(() => this.updateErrorMessage());
  }

  ngOnInit(): void {   }

  updateErrorMessage() {
    this.usernameErrorMessage.set('');
    this.passwordErrorMessage.set('');
    if (this.username.hasError('required')) {
      this.usernameErrorMessage.set('Login is required');
    }
    if (this.password.hasError('required')) {
      this.passwordErrorMessage.set('password is required');
    }
  }

  onLogin() {
    if (this.username.invalid || this.password.invalid) {
      this.updateErrorMessage()
      return
    }

    let authRequest: AuthRequest = new AuthRequest()
    authRequest.username = this.username.value!
    authRequest.password = btoa(this.password.value!);
    
    this.isLoading = this.isSubmitted = true
    this.authService.login(authRequest).subscribe({
      next: result => {
        let response = result as CommonResponse
        if (response.code == 200) {
          this.router.navigate(['home'])
        }
        else {
          Utils.toShowError(this, 'Usuário ou senha inválido!')
        }

        this.isLoading = false;
      },
      error: err => {
        Utils.toShowError(this, 'Ops! Ocorreu algum imprevisto na validação do usuário')
        this.isLoading = false;
      }
    })
  }
  
  onRegister() {
    this.router.navigate(['register'])
  }
}

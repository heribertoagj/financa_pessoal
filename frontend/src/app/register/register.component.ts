import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule, Validators, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Utils } from '@commons/utils';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field'
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { RegisterRequest } from '@commons/dto/registerRequest';

import { AuthService } from '@service/authService';
import { CommonResponse } from '@commons/dto/commonResponse';
import { R } from '@angular/cdk/overlay.d-BdoMy0hX';
import { RoleEnum } from '@commons/enums/roleEnum';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatFormFieldModule,
    MatLabel, MatInputModule,
    MatIconModule, MatButtonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {

  form!: FormGroup;


  submited: boolean = false
  successMessage: string = ''
  errorMessage: string = ''


  constructor(
    private fb: FormBuilder,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  onRegister(): void {
    this.submited = true
    this.form.markAllAsTouched();

    if (this.form.invalid) {
      Utils.toShowError(this, 'Preencha todos os campos obrigatórios.')
      return;
    }

    let register = new RegisterRequest();
    register.name = this.form.get('name')?.value;
    register.email = this.form.get('email')?.value;
    register.username = this.form.get('login')?.value;
    register.password = this.form.get('password')?.value;
    register.role = RoleEnum.ADMIN;

    this.authService.register(register).subscribe({
      next: (response) => {
        Utils.toShowSuccess(this, 'Registro realizado com sucesso');
      }
      , error: (err) => {
        let errorResponse = err as CommonResponse    
        if (errorResponse.message) Utils.toShowError(this, errorResponse.message );
        else Utils.toShowError(this, 'Erro ao realizar o registro do usuário.');
      }
    });
  }

  nameErrorMessage() {
    if (this.form.get('name')?.hasError('required')) {
      return 'Name is required';
    }
    return '';
  }

  emailErrorMessage() {
    if (this.form.get('email')?.hasError('required')) {
      return 'Email is required';
    }
    return '';
  }

  loginErrorMessage() {
    if (this.form.get('login')?.hasError('required')) {
      return 'Login is required';
    }
    return '';
  }

  passwordErrorMessage() {
    if (this.form.get('password')?.hasError('required')) {
      return 'Password is required';
    }
    return '';
  }

  confirmPasswordErrorMessage() {
    if (this.form.get('confirmPassword')?.hasError('required')) {
      return 'Confirm password is required';
    }
    return '';
  }

  goBack() {
    window.history.back();
  }
}

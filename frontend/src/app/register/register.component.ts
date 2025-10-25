import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule, Validators, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Utils } from '@commons/utils';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field'
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

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


  constructor(private fb: FormBuilder) { }

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

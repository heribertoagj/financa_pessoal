import { CommonModule } from '@angular/common';
import { Component, NgModule, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonResponse } from '@commons/dto/commonResponse';
import { AuthRequest } from '@commons/dto/authRequest';
import { AuthService } from '@service/authService';
import { Router, RouterModule } from '@angular/router';

import { Utils } from '@commons/utils';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoading: boolean = false
  isSubmitted: boolean = false

  username = '';
  usernameError = '';

  password = '';
  passwordError = '';

  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private router: Router, 
    private authService: AuthService) {

  }

  ngOnInit(): void {
    
  }

  onLogin() {
    
    if (!this.validateFields()) return

    let authRequest: AuthRequest = new AuthRequest()
    authRequest.username = this.username
    authRequest.password = this.password

    this.isLoading = this.isSubmitted = true
    this.authService.login(authRequest).subscribe({
      next: result => {

        let response = result as CommonResponse
        if (response.code == 200) {
          this.router.navigate(['home'])
        }
        else{
          alert(JSON.stringify(result))

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

  validateFields(){
    this.usernameError = this.passwordError = ""

    let existsError = false
    if (!this.username) {
      this.usernameError = "Usuário é obrigatório"
      existsError = true
    }

    if (!this.password) {
      this.passwordError = "Senha é obrigatório"
      existsError = true
    }

    if (existsError) Utils.toShowError(this, "Informar campos obrigatórios")

    return !existsError 
  }

  onRegister(){
    this.router.navigate(['register'])
  }


 
}

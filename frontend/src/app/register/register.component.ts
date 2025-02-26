import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Utils } from '@commons/utils';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {


  successMessage: string = ''
  errorMessage: string = ''


  name:String = ""
  nameError:boolean = false

  email:String= ""
  emailError:boolean = false
  
  login:String = ""
  loginError:boolean = false
  
  password:String = ""
  passwordError:boolean = false
  
  confirmPassword:String = ""
  confirmPasswordError:boolean = false

  constructor(){}

  ngOnInit(): void {
    
  }

  onRegister(): void{


    if (!this.validateFields()){
      return
    }




  }

  cleanErrors():void{
    this.nameError = false
    this.emailError = false
    this.loginError = false
    this.passwordError = false
    this.confirmPasswordError = false
  }

  validateFields(): boolean{
    this.cleanErrors()
    
    let existsError = false

    if (!this.name){
      this.nameError = existsError = true
    }

    if (!this.email){
      this.emailError = existsError = true
    }

    if (!this.login){
      this.loginError = existsError = true
    }

    if (!this.password){
      this.passwordError = existsError = true
    }

    if (!this.confirmPassword){
      this.confirmPasswordError = existsError = true
    }

    if (existsError) Utils.toShowError(this, 'Informar campos obrigatórios')
    return !existsError
  }
}

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { AuthService } from '@service/authService';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'financas_pessoais';  
  isAuthenticated?: boolean = false

  constructor(private authService: AuthService){}

  ngOnInit(): void {
    this.subscribeAuthUser()
  }

  subscribeAuthUser() {
    this.authService.currentUser.subscribe(auth => {
      this.isAuthenticated = this.authService.isAuthenticated()
    })
  }
}
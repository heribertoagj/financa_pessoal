import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationStart, Router, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { AuthService } from '@service/authService';
import { A11yModule } from "@angular/cdk/a11y";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HeaderComponent, A11yModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'financas_pessoais';
  isAuthenticated?: boolean = false

  constructor(
    private authService: AuthService,
    private router: Router

  ) { }

  ngOnInit(): void {
    this.subscribeAuthUser()
    this.listenBackButton()
  }

  subscribeAuthUser() {
    this.authService.currentUser.subscribe(auth => {
      this.isAuthenticated = this.authService.isAuthenticated()
    })
  }

  listenBackButton(): void {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (event.url == '/login') {
          this.authService.logout()
        }
      }
    });
  }
}
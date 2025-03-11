import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { Router } from '@angular/router';
import { AuthService } from '@service/authService';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, MatMenuModule, MatIconModule, MatButtonModule, MatDividerModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  isActive:boolean = false;
  name: string;

  constructor(
    private router: Router,
    private authService: AuthService

  ) {
    this.name = ""
  }

  ngOnInit(): void {
      this.name = this.authService.currentUserValue.name || ''
  }

  onLogout() {
    this.router.navigate(['login'])
  }

  onNavigate(){  
    this.router.navigate(['receive/list'])
  }
}
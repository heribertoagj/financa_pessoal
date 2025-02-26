import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { authGuard } from '@interceptors/auth.guard';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent, canActivate: [authGuard] },
    { path: 'register', component: RegisterComponent },
    { path: '', component: LoginComponent },
    { path: '**', component: LoginComponent }
];

import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

import { ListComponent as ListReceiveComponent } from './receive/list/list.component';
import { authGuard } from '@interceptors/auth.guard';
import { RegisterComponent } from './register/register.component';
import { EditReceiveComponent } from './receive/edit/edit.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent, canActivate: [authGuard] },
    { path: 'receive', children: [
        {path: 'list', component: ListReceiveComponent, canActivate: [authGuard]},
        {path: 'edit', component: EditReceiveComponent, canActivate: [authGuard]},
    ]},
    { path: 'register', component: RegisterComponent },
    { path: '', component: LoginComponent },
    { path: '**', component: LoginComponent }
];

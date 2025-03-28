import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

import { ListComponent as ListReceiveComponent } from './revenue/list/list.component';
import { ListComponent as ListExpensesComponent } from './expenses/list/list.component';

import { authGuard } from '@interceptors/auth.guard';
import { RegisterComponent } from './register/register.component';
import { EditReceiveComponent } from './revenue/edit/edit.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent, canActivate: [authGuard] },
    { path: 'register', component: RegisterComponent },
    { path: 'receive', children: [
        {path: 'list', component: ListReceiveComponent, canActivate: [authGuard]},
        {path: 'edit', component: EditReceiveComponent, canActivate: [authGuard]},
    ]},
    { path: 'expense', children: [
        {path: 'list', component: ListExpensesComponent, canActivate: [authGuard]},
        {path: 'edit', component: EditReceiveComponent, canActivate: [authGuard]},
    ]},
    { path: '', component: LoginComponent },
    { path: '**', component: LoginComponent },

];

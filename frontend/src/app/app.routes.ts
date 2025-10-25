import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

import { ListComponent as ListReceitaComponent } from './receita/list/list.component';
import { EditComponent as EditReceitaComponent } from './receita/edit/edit.component';

import { ListComponent as ListExpensesComponent } from './despesa/list/list.component';

import { authGuard } from '@interceptors/auth.guard';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent, canActivate: [authGuard] },
    { path: 'register', component: RegisterComponent },
    { path: 'receita', children: [
        {path: 'list', component: ListReceitaComponent, canActivate: [authGuard]},
        {path: 'edit', component: EditReceitaComponent, canActivate: [authGuard]},
    ]},
    { path: 'despesa', children: [
        {path: 'list', component: ListExpensesComponent, canActivate: [authGuard]},
        //{path: 'edit', component: EditReceiveComponent, canActivate: [authGuard]},
    ]},
    { path: '', component: LoginComponent },
    { path: '**', component: LoginComponent },

];

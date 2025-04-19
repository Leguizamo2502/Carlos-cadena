import { Routes } from '@angular/router';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { ManageBooksComponent } from './views/manage-books/manage-books.component';
import { RegisterLoanComponent } from './views/register-loan/register-loan.component';
import { RegisterUserComponent } from './views/register-user/register-user.component';

export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'register-loan', component: RegisterLoanComponent },
  { path: 'register-user', component: RegisterUserComponent },
  { path: 'manage-books', component: ManageBooksComponent },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
];

import { Routes } from '@angular/router';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { ManageBooksComponent } from './views/manage-books/manage-books.component';
import { RegisterLoanComponent } from './views/register-loan/register-loan.component';
import { RegisterUserComponent } from './views/register-user/register-user.component';

import { FormCategoryComponent } from './components/form-category/form-category.component';
import { FormPublisherComponent } from './components/form-publisher/form-publisher.component';
import { FormAuthorComponent } from './components/form-author/form-author.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';

export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'register-loan', component: RegisterLoanComponent },
  { path: 'register-user', component: RegisterUserComponent },
  { path: 'manage-books/createcategory', component: FormCategoryComponent },
  { path: 'manage-books/createpublisher', component: FormPublisherComponent },
  { path: 'manage-books/createauthor', component: FormAuthorComponent },
  { path: 'manage-books', component: ManageBooksComponent },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'register-user/update/:id', component: UpdateUserComponent },

];

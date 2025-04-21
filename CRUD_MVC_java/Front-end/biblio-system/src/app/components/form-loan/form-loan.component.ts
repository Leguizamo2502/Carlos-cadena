import { user } from './../../models/user.model';
import { BookService } from './../../services/book.service';
import { book } from './../../models/book.model';
import { routes } from './../../app.routes';
import { UserService } from './../../services/user.service';
import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormBuilder } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Router, RouterLink } from '@angular/router';
import { LoanService } from '../../services/loan.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-form-loan',
  
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    RouterLink,
    MatSelectModule,
    FormsModule,
    MatCheckboxModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule
    
  ],
  templateUrl: './form-loan.component.html',
  styleUrl: './form-loan.component.css'
})
export class FormLoanComponent {
  private readonly fb = inject(FormBuilder);

  loanService = inject(LoanService);
  userService = inject(UserService);
  bookService = inject(BookService)
  snacBar = inject(MatSnackBar);

  router = inject(Router);

  users: user[]=[];
  books: book[]=[];

  form = this.fb.group({
    loan_date:[new Date().toISOString()],
    return_date:[null],
    status:['Pendiente'],
    id_user:[''],
    id_book:['']
  })

  constructor(){
    this.bookService.getBook().subscribe((data)=>{
      this.books = data;
    })
    this.userService.getUser().subscribe((data)=>{
      this.users = data;
    })
  }

  guardar(){
    console.log(this.form.value)
  }


}

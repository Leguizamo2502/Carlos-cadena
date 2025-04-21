import { loan, loanCreate } from './../../models/loan.model';
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
import { MatNativeDateModule, provideNativeDateAdapter } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { RegisterLoanComponent } from '../../views/register-loan/register-loan.component';

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
    MatSnackBarModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatIconModule,
    
  ],
  templateUrl: './form-loan.component.html',
  styleUrl: './form-loan.component.css',
  providers: [provideNativeDateAdapter()]
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
    loan_date:[''],
    return_date:[''],
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
    const loan = this.form.value as loanCreate;
    this.loanService.createLoan(loan).subscribe(()=>{
      this.router.navigate(['/']);
    })
  }

  load = inject(RegisterLoanComponent)

  delete(loanId:number){
    this.loanService.deleteLoan(loanId).subscribe(()=>{
      console.log("Loan eliminado");
      this.load.loadLoan();
    })
  }




}

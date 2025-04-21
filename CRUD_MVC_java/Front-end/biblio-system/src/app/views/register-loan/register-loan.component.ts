import { RouterLink } from '@angular/router';
import { TopCardsComponent } from '../../components/top-cards/top-cards.component';
import { loan } from '../../models/loan.model';
import { LoanService } from '../../services/loan.service';
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { FormLoanComponent } from '../../components/form-loan/form-loan.component';
import { MatIcon } from '@angular/material/icon';
import { MatButton } from '@angular/material/button';

@Component({
  selector: 'app-register-loan',
  imports: [RouterLink,TopCardsComponent,CommonModule,MatTableModule,FormLoanComponent,MatIcon,MatButton],
  templateUrl: './register-loan.component.html',
  styleUrl: './register-loan.component.css'
})
export class RegisterLoanComponent {
  constructor(){
    this.loadLoan();
  }
  loanService = inject(LoanService);
  loan:loan[] = [];

  stats: { title: string; value: number; icon: string; isImage: boolean }[] =[];


  loadLoan(){
    this.loanService.getLoan().subscribe((data)=>{
      this.loan = data;
      this.updateStats();
    })
  }
  // displayedColumns: string[] = ['fullName', 'bookTitle','quantity'];
  displayedColumns: string[] = ['fullName', 'bookTitle','loan_date','return_date','status','actions'];

  updateStats() {
    this.stats = [{ title: 'Prestamos', value: this.loan.length, icon: 'book', isImage: false }];
  }

  delete(loanId:number){
    this.loanService.deleteLoan(loanId).subscribe(()=>{
      console.log("Loan eliminado");
      this.loadLoan();
    })
  }





}



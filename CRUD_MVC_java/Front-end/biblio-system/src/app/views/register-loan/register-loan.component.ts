import { RouterLink } from '@angular/router';
import { TopCardsComponent } from '../../components/top-cards/top-cards.component';
import { loan } from '../../models/loan.model';
import { LoanService } from '../../services/loan.service';
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { FormLoanComponent } from '../../components/form-loan/form-loan.component';

@Component({
  selector: 'app-register-loan',
  imports: [RouterLink,TopCardsComponent,CommonModule,MatTableModule,FormLoanComponent],
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
  displayedColumns: string[] = ['fullName', 'bookTitle','quantity'];

  updateStats() {
    this.stats = [{ title: 'Registros', value: this.loan.length, icon: 'book', isImage: false }];
  }





}



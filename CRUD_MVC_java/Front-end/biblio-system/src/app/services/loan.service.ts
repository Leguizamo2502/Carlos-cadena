import { loan, loanCreate } from './../models/loan.model';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoanService {

  constructor() { }

  private http = inject(HttpClient);
  private URltraer = environment.apiUrl + '/api/v1/loan/todo';
  public getLoan():Observable<loan[]>{
    return this.http.get<loan[]>(this.URltraer);
  }

  private URlbase = environment.apiUrl + '/api/v1/loan/';
  public createLoan(loan:loanCreate){
    return this.http.post(this.URlbase,loan);
  }

  public deleteLoan(id:number){
    return this.http.delete(this.URlbase+id);
  }
}

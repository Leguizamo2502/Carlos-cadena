import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { loan } from '../models/loan.model';

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
}

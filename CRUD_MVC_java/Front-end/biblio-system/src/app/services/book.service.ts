import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { book } from '../models/book.model';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor() { }
  private http = inject(HttpClient);
  private URLbase = 'http://localhost:8080/api/v1/book/';
  public getBook():Observable<book[]>{
    return this.http.get<book[]>(this.URLbase);
  }
}

import { book, bookCreated } from './../models/book.model';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor() { }
  private http = inject(HttpClient);
  
  private URLbase = environment.apiUrl + '/api/v1/book/todo';
  public getBook():Observable<book[]>{
    return this.http.get<book[]>(this.URLbase);
  }

  private URLfilter = environment.apiUrl+'/api/v1/book/filter';

  public getFilter(name:string):Observable<any>{
    return this.http.get(`${this.URLfilter}/${name}`);
  }

  private URLnormal = environment.apiUrl+'/api/v1/book/';
  public deleteBook(id:number):Observable<void>{
    return this.http.delete<void>(`${this.URLnormal}${id}`);
  }

  public createBook(book:bookCreated){
    return this.http.post(this.URLnormal,book)
  }


}

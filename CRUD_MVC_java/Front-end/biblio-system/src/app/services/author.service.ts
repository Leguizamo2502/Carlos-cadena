import { author, authorCrear } from './../models/author,model';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  constructor() { }
  private http = inject(HttpClient);
  private URLbase = environment.apiUrl + '/api/v1/author/';

  public getAuthor(): Observable<author[]>{
    return this.http.get<author[]>(this.URLbase);
  }

  public createdAuthor(authors:authorCrear){
    return this.http.post(this.URLbase,authors);
  }

}

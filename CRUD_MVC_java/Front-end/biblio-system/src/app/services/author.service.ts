import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { author } from '../models/author,model';

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
}

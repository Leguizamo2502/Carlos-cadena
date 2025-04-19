import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { user } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }
  private http = inject(HttpClient);
  private URLbase = environment.apiUrl + '/api/v1/user/';

  public getUser(): Observable<user[]>{
    return this.http.get<user[]>(this.URLbase);
  }
}

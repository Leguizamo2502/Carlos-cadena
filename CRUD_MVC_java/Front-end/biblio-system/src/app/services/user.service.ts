import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { user, userCreated } from '../models/user.model';

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

  public createdUser(users:userCreated){
    return this.http.post(this.URLbase,users);
  }

// private URLid = environment.apiUrl + '/api/v1/user'
public deleteUser(id:number){
  // return this.http.delete<void>(`${this.URLbase}${id}"`)
  return this.http.delete(this.URLbase+id)
}

}

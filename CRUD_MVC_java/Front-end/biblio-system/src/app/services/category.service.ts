import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { category } from '../models/category.model';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor() {}
  private http = inject(HttpClient);
  private URLbase = environment.apiUrl + '/api/v1/category/';

  public getCategory(): Observable<category[]> {
    return this.http.get<category[]>(this.URLbase);
  }
}

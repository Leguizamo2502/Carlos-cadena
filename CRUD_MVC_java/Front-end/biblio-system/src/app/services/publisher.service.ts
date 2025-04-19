import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { publisher } from '../models/publisher.model';

@Injectable({
  providedIn: 'root',
})
export class PublisherService {
  constructor() {}
  private http = inject(HttpClient);
  private URLbase = environment.apiUrl + '/api/v1/publisher/';

  public getPublisher(): Observable<publisher[]> {
    return this.http.get<publisher[]>(this.URLbase);
  }
}

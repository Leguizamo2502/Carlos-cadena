import { publisher, publisherCrear } from './../models/publisher.model';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

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

  public createdPublisher(publishers:publisherCrear){
    return this.http.post(this.URLbase,publishers);
  }
}

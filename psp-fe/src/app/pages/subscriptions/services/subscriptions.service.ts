import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {SubscriptionRequest} from "../model/subscriptions";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionsService {
  constructor(private httpClient: HttpClient) {}
  private readonly baseUrl: string = 'http://localhost:8081/api/subscriptions'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  subscribe(request: SubscriptionRequest): Observable<unknown>{
    return this.httpClient.put<unknown>(`${this.baseUrl}/subscribe`, request, {headers: this.headers})
  }
}

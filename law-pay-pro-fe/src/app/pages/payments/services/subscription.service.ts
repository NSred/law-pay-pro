import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  constructor(private httpClient: HttpClient) {}
  private readonly baseUrl: string = 'http://localhost:8070/api/subscription'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  getPaymentMethods(): Observable<string[]>{
    return this.httpClient.get<string[]>(`${this.baseUrl}/payment-methods`, {headers: this.headers})
  }
}

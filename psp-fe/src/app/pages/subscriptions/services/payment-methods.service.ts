import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {SubscriptionDto} from "../model/subscriptions";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaymentMethodsService {
  constructor(private httpClient: HttpClient) {}
  private readonly baseUrl: string = 'http://localhost:8081/api/payment-methods'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  getAllByMerchant(merchantId: string): Observable<SubscriptionDto[]> {
    const params = new HttpParams().set('merchantId', merchantId);
    return this.httpClient.get<SubscriptionDto[]>(this.baseUrl, { headers: this.headers, params });
  }
}

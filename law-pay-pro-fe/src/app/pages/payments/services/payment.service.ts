import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {PaymentDto} from "../requests/payment-requests";
import {Observable} from "rxjs";
import {PaymentResponse} from "../responses/payment-response";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  constructor(private httpClient: HttpClient) {}
  private readonly baseUrl: string = 'http://localhost:8070/api/payment'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  processPayment(request: PaymentDto): Observable<PaymentResponse>{
    return this.httpClient.post<PaymentResponse>(this.baseUrl, request,
      {headers: this.headers, responseType: 'text' as 'json'})
  }
}

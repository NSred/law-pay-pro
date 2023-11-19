import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {PaymentDto} from "../requests/payment-requests";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  constructor(private httpClient: HttpClient) {}
  private readonly baseUrl: string = 'http://localhost:8070/api/payment'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  processPayment(request: PaymentDto): Observable<string>{
    return this.httpClient.post<string>(this.baseUrl, request,
      {headers: this.headers, responseType: 'text' as 'json'})
  }
}

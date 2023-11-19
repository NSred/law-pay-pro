import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CardPaymentRequest} from "../requests/card-payment-request";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BankService {
  constructor(private httpClient: HttpClient) {}
  private readonly baseUrl: string = 'http://localhost:8060/bank'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  transferMoney(request: CardPaymentRequest): Observable<string>{
    return this.httpClient.post<string>(`${this.baseUrl}/pay`, request,
      {headers: this.headers, responseType: 'text' as 'json'})
  }
}

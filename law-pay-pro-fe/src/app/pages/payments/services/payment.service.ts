import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {PaymentDto} from "../requests/payment-requests";
import {Observable} from "rxjs";
import {PaymentResponseDto} from "../responses/payment-response";

export interface PayPalSubsRequest {
  userId: number,
  offerId: string
}

export interface PayPalSubsResponse {
  id: number,
  subscriptionId: string,
  subscriptionStatus: string,
  planId: string
}

export interface UpdatePayPalSubRequest {
  id: number,
  subscriptionId: string
}

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  constructor(private httpClient: HttpClient) {}
  private readonly baseUrl: string = 'http://localhost:8070/api/payment'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  processPayment(request: PaymentDto): Observable<PaymentResponseDto>{
    return this.httpClient.post<PaymentResponseDto>(this.baseUrl, request,
      {headers: this.headers})
  }

  getPayPalSubs(request: PayPalSubsRequest): Observable<PayPalSubsResponse>{
    return this.httpClient.post<PayPalSubsResponse>(this.baseUrl + "/payPalSubs", request)
  }

  updateSub(request: UpdatePayPalSubRequest): Observable<boolean> {
    return this.httpClient.put<boolean>(this.baseUrl + "/payPalSubs", request)
  }

  cancelSub(request: UpdatePayPalSubRequest): Observable<boolean> {
    return this.httpClient.put<boolean>(this.baseUrl + "/payPalSubs/cancel", request)
  }
}

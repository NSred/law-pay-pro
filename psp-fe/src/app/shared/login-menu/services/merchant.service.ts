import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginDto, RegistrationDto, TokenState} from "../model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MerchantService {

  constructor(private httpClient: HttpClient) { }

  private readonly baseUrl: string = 'http://localhost:8081/merchants';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  register(request: RegistrationDto): Observable<string>{
    return this.httpClient.post<string>(`${this.baseUrl}/register`, request,
      {headers: this.headers, responseType: 'text' as 'json'})
  }

  login(request: LoginDto): Observable<TokenState>{
    return this.httpClient.post<TokenState>(`${this.baseUrl}/login`, request,
      {headers: this.headers})
  }

}

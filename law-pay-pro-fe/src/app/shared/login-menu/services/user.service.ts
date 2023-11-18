import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginDto} from "../../model/login";
import {Observable} from "rxjs";
import {TokenState} from "../../model/token.state";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  private readonly baseUrl: string = 'http://localhost:8081/users'/*`http://localhost:8080/api/payment-service-provider/users`*/;
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  login(request: LoginDto): Observable<TokenState>{
    return this.httpClient.post<TokenState>(`${this.baseUrl}/login`, request, {headers: this.headers})
  }
}

import { Injectable } from '@angular/core';
import {Merchant} from "../model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private merchant: Merchant | null = null;
  constructor() { }

  isLoggedIn(){
    return !!localStorage.getItem('token')
  }

  logout(){
    localStorage.removeItem('token');
  }

  setToken(token: string) {
    localStorage.setItem('token', token)
  }

  getLoggedInMerchant(): Merchant | null {
    const token = localStorage.getItem('token');
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      if (payload) {
        return {
          id: payload.id,
          merchantId: payload.merchant
        };
      }
    }
    return null;
  }
}

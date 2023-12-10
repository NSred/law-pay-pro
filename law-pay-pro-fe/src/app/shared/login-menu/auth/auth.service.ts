import {Injectable} from '@angular/core';
import {User} from "../../model/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private user: User | null = null;
  constructor(){}

  isLoggedIn(){
    return !!localStorage.getItem('token')
  }

  setToken(token: string) {
    localStorage.setItem('token', token)
  }

  getLoggedInUser(): User | null {
    const token = localStorage.getItem('token');
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      if (payload) {
        return {
          id: payload.userId,
          name: payload.name,
          username: payload.sub,
          email: payload.email,
          role: payload.role
        };
      }
    }
    return null;
  }
}

import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {HttpEvent, HttpHandler, HttpRequest} from "@angular/common/http";
import {Observable, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor {

  constructor(private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(localStorage.getItem('token') != null) {
      const clonedRequest = req.clone({
        headers : req.headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
      });

      return next.handle(clonedRequest).pipe(
        tap(
          succ => {},
          err => {
            if(err.status == 401) {
              localStorage.removeItem('token');
              this.router.navigate(['login']).then();
            }
            else if(err.status == 403) {
              alert('Forbidden')
            }
          }
        )
      )
    }

    else {
      return next.handle(req.clone());
    }
  }
}

import {Component, inject} from '@angular/core';
import {Router} from "@angular/router";
import {NavbarButtonComponent} from "../navbar-button/navbar-button.component";
import {MatToolbarModule} from "@angular/material/toolbar";
import {NgIf, NgOptimizedImage} from "@angular/common";
import {AuthService} from "../../login-menu/auth/auth.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NavbarButtonComponent,
    MatToolbarModule,
    NgOptimizedImage,
    NgIf
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  private auth = inject(AuthService)
  private readonly router = inject(Router)

  isLoggedIn(): boolean {
    return this.auth.isLoggedIn()
  }

  goToLogin() {
    this.router.navigate(['login']).then();
  }

  goToHome() {
    this.router.navigate(['']).then();
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['login']).then()
  }

  goToRegistration() {
    this.router.navigate(['registration']).then();
  }

  goToSubscriptions() {
    this.router.navigate(['subscriptions']).then();
  }
}

import {Component, inject} from '@angular/core';
import {NgOptimizedImage} from '@angular/common';
import {MatToolbarModule} from "@angular/material/toolbar";
import {NavbarButtonComponent} from "../navbar-button/navbar-button.component";
import {Router} from "@angular/router";
import {NavbarStateService} from "../state/navbar-state.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbarModule, NgOptimizedImage, NavbarButtonComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  private readonly router = inject(Router)

  goToLogin() {
    this.router.navigate(['login']).then();
  }

  goToHome() {
    this.router.navigate(['']).then();
  }
}

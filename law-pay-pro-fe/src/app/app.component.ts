import {Component, inject} from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {NavbarComponent} from "./shared/navbar-menu/navbar/navbar.component";
import {NavbarStateService} from "./shared/navbar-menu/state/navbar-state.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  private readonly navbarState = inject(NavbarStateService)
  isNavbarVisible$: Observable<boolean> = this.navbarState.isVisible$;
  title = 'law-pay-pro-fe';
}

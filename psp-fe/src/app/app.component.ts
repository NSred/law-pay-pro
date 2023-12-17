import {Component, inject} from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {Observable} from "rxjs";
import {NavbarStateService} from "./shared/navbar-menu/state/navbar-state.service";
import {NavbarComponent} from "./shared/navbar-menu/navbar/navbar.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  private readonly navbarState = inject(NavbarStateService)
  isNavbarVisible$: Observable<boolean> = this.navbarState.isVisible$;
  title = 'psp-fe';
}

import {Component, Input} from '@angular/core';
import {MatIconModule} from "@angular/material/icon";

@Component({
  selector: 'app-navbar-button',
  standalone: true,
  imports: [
    MatIconModule
  ],
  templateUrl: './navbar-button.component.html',
  styleUrl: './navbar-button.component.scss'
})
export class NavbarButtonComponent {
  @Input() iconName: string = '';
}

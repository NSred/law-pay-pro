import { Component } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-fail',
  standalone: true,
    imports: [CommonModule, NgOptimizedImage],
  templateUrl: './fail.component.html',
  styleUrl: './fail.component.scss'
})
export class FailComponent {

}

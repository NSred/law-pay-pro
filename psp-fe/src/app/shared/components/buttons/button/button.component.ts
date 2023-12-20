import {Component, EventEmitter, Input, Output} from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss'
})
export class ButtonComponent {
  @Input() disabled: boolean =false;
  @Output() onClick: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() info: string = '';

  emitClick() {
    this.onClick.emit(true);
  }
}

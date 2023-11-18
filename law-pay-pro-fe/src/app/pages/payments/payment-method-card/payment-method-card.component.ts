import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {MatRadioChange, MatRadioModule} from "@angular/material/radio";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-payment-method-card',
  standalone: true,
  imports: [CommonModule, MatRadioModule, FormsModule, NgOptimizedImage],
  templateUrl: './payment-method-card.component.html',
  styleUrl: './payment-method-card.component.scss'
})
export class PaymentMethodCardComponent {
  @Input() label: string = '';
  @Input() imgPath: string = '';
}

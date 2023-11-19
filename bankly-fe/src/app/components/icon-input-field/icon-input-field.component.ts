import {Component, Input} from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import {CardNumberDirective} from "../../directives/card-number.directive";
import {ExpiryDateDirective} from "../../directives/expiry-date.directive";
import {SecurityCodeDirective} from "../../directives/security-code.directive";

@Component({
  selector: 'app-icon-input-field',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, CardNumberDirective, ExpiryDateDirective, SecurityCodeDirective],
  templateUrl: './icon-input-field.component.html',
  styleUrl: './icon-input-field.component.scss'
})
export class IconInputFieldComponent {
  @Input() control: FormControl = new FormControl();
  @Input() label: string = '';
  @Input() placeholder: string = '';
  @Input() type: string = 'text';
  @Input() icon: string = '';
  @Input() fieldType: string= '';
}

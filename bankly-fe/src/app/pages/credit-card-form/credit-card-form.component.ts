import {Component, inject} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {IconInputFieldComponent} from "../../components/icon-input-field/icon-input-field.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {cardNumberValidator} from "../../components/validators/card-number-validator";
import {CardNumberDirective} from "../../directives/card-number.directive";
import {expiryDateValidator} from "../../components/validators/expiry-date-validator";
import {cvcValidator} from "../../components/validators/security-code-validator";
import {ButtonComponent} from "../../components/button/button.component";
import {BankService} from "../../services/bank.service";

export interface CardForm {
  cardHolder: FormControl<string>;
  cardNumber: FormControl<string>;
  expiryDate: FormControl<string>;
  securityCode: FormControl<string>;
}

@Component({
  selector: 'app-credit-card-form',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage, IconInputFieldComponent, CardNumberDirective, ButtonComponent],
  templateUrl: './credit-card-form.component.html',
  styleUrl: './credit-card-form.component.scss'
})
export class CreditCardFormComponent {
  private readonly bankService = inject(BankService)
  formGroup = new FormGroup<CardForm>(<CardForm>{
    cardHolder: new FormControl('', [Validators.required]),
    cardNumber: new FormControl('', [Validators.required, cardNumberValidator]),
    expiryDate: new FormControl('', [Validators.required, expiryDateValidator]),
    securityCode: new FormControl('', [Validators.required, cvcValidator]),
  })
  disabled: boolean = false;

  makePayment() {
    console.log(this.formGroup.controls.cardHolder.value);
    console.log(this.formGroup.controls.cardNumber.value);
    console.log(this.formGroup.controls.expiryDate.value);
    console.log(this.formGroup.controls.securityCode.value);
  }
}

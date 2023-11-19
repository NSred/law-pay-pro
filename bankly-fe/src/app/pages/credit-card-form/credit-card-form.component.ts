import {Component, inject, OnInit} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {IconInputFieldComponent} from "../../components/icon-input-field/icon-input-field.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {cardNumberValidator} from "../../components/validators/card-number-validator";
import {CardNumberDirective} from "../../directives/card-number.directive";
import {expiryDateValidator} from "../../components/validators/expiry-date-validator";
import {cvcValidator} from "../../components/validators/security-code-validator";
import {ButtonComponent} from "../../components/button/button.component";
import {BankService} from "../../services/bank.service";
import {ActivatedRoute} from "@angular/router";
import {CardPaymentRequest} from "../../requests/card-payment-request";

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
export class CreditCardFormComponent implements OnInit{
  private readonly route = inject(ActivatedRoute)
  private readonly bankService = inject(BankService)
  formGroup = new FormGroup<CardForm>(<CardForm>{
    cardHolder: new FormControl('', [Validators.required]),
    cardNumber: new FormControl('', [Validators.required, cardNumberValidator]),
    expiryDate: new FormControl('', [Validators.required, expiryDateValidator]),
    securityCode: new FormControl('', [Validators.required, cvcValidator]),
  })
  disabled: boolean = false;
  paymentId: string = '';

  ngOnInit(): void {
    this.handlePageLoad()
  }

  handlePageLoad(){
    this.route.queryParams.subscribe(params => {
      this.paymentId = params['data'];
    });
  }

  makePayment() {
    let cardNum = this.formGroup.controls.cardNumber.value.replace(/\D/g, '');
    let expiryDate = this.formGroup.controls.expiryDate.value.replace(/\s*\/\s*/g, '/');
    let request: CardPaymentRequest = {
      paymentId: this.paymentId,
      securityCode: this.formGroup.controls.securityCode.value,
      cardHolderName: this.formGroup.controls.cardHolder.value,
      pan: cardNum,
      expirationDate: expiryDate,
    }
    this.bankService.transferMoney(request).subscribe({
      next: res => {
        console.log(res)
        window.location.href = res.url;
      }
    })
  }
}

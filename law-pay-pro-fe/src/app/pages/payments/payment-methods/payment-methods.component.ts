import {Component, inject, OnInit} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {OfferCardComponent} from "../../offers/offer-card/offer-card.component";
import {MatRadioChange, MatRadioModule} from "@angular/material/radio";
import {FormsModule} from "@angular/forms";
import {paymentType} from "../constants/payment.constants";
import {ButtonComponent} from "../../../shared/components/buttons/button/button.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-payment-methods',
  standalone: true,
  imports: [CommonModule, OfferCardComponent, MatRadioModule, FormsModule, NgOptimizedImage, ButtonComponent],
  templateUrl: './payment-methods.component.html',
  styleUrl: './payment-methods.component.scss'
})
export class PaymentMethodsComponent implements OnInit{
  private readonly router = inject(Router)
  protected readonly paymentType = paymentType;
  selectedValue: string = '';
  disabled: boolean = true;

  ngOnInit(): void {
    console.log(this.selectedValue)
  }

  onRadioButtonChange($event: MatRadioChange) {
    this.disabled = false
    console.log($event.value)
  }

  goBackToOffers() {
    this.router.navigate(['offers']).then()
  }
}

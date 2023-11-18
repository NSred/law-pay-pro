import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {offersMock} from "../../offers/model/offer";
import {OfferCardComponent} from "../../offers/offer-card/offer-card.component";
import {MatRadioChange, MatRadioModule} from "@angular/material/radio";
import {FormsModule} from "@angular/forms";
import {PaymentMethodCardComponent} from "../payment-method-card/payment-method-card.component";

@Component({
  selector: 'app-payment-methods',
  standalone: true,
  imports: [CommonModule, OfferCardComponent, MatRadioModule, FormsModule, PaymentMethodCardComponent],
  templateUrl: './payment-methods.component.html',
  styleUrl: './payment-methods.component.scss'
})
export class PaymentMethodsComponent {

  protected readonly offersMock = offersMock;
  selectedValue: string = '';

  onRadioButtonChange($event: MatRadioChange) {

  }
}

import {Component, EventEmitter, Input, Output} from '@angular/core';
import { CommonModule } from '@angular/common';
import {Offer} from "../model/offer";
import {ButtonComponent} from "../../../shared/components/buttons/button/button.component";

@Component({
  selector: 'app-offer-card',
  standalone: true,
  imports: [CommonModule, ButtonComponent],
  templateUrl: './offer-card.component.html',
  styleUrl: './offer-card.component.scss'
})
export class OfferCardComponent {
  @Input() offer: Offer = {id: '0', name: '', price: 0};
  @Output() onBuy: EventEmitter<Offer> = new EventEmitter<Offer>();

  buy(offer: Offer) {
    this.onBuy.emit(offer);
  }
}

import {Component, Input} from '@angular/core';
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
  @Input() offer: Offer = {id: '', name: '', price: 0};

  buy(offer: Offer) {

  }
}

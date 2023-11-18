import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {offersMock} from "../model/offer";
import {OfferCardComponent} from "../offer-card/offer-card.component";

@Component({
  selector: 'app-all-offers',
  standalone: true,
  imports: [CommonModule, OfferCardComponent],
  templateUrl: './all-offers.component.html',
  styleUrl: './all-offers.component.scss'
})
export class AllOffersComponent {
  offers = offersMock
}

import {Component, inject} from '@angular/core';
import { CommonModule } from '@angular/common';
import {Offer} from "../model/offer";
import {OfferCardComponent} from "../offer-card/offer-card.component";
import {Router} from "@angular/router";
import {OffersService} from "../services/offers.service";

@Component({
  selector: 'app-all-offers',
  standalone: true,
  imports: [CommonModule, OfferCardComponent],
  templateUrl: './all-offers.component.html',
  styleUrl: './all-offers.component.scss'
})
export class AllOffersComponent {
  private readonly router = inject(Router)
  private readonly offersService = inject(OffersService)
  offers$ = this.offersService.getOffers()

  goToPaymentPage(offer: Offer) {
    this.router.navigate(['payment/' + offer.id]).then();
  }
}

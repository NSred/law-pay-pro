import {Component, inject, OnInit} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {OfferCardComponent} from "../../offers/offer-card/offer-card.component";
import {MatRadioChange, MatRadioModule} from "@angular/material/radio";
import {FormsModule} from "@angular/forms";
import {paymentType} from "../constants/payment.constants";
import {ButtonComponent} from "../../../shared/components/buttons/button/button.component";
import {ActivatedRoute, Router} from "@angular/router";
import {PaymentService} from "../services/payment.service";
import {PaymentDto, PaymentType} from "../requests/payment-requests";
import {PaymentResponseDto} from "../responses/payment-response";

@Component({
  selector: 'app-payment-methods',
  standalone: true,
  imports: [CommonModule, OfferCardComponent, MatRadioModule, FormsModule, NgOptimizedImage, ButtonComponent],
  templateUrl: './payment-methods.component.html',
  styleUrl: './payment-methods.component.scss'
})
export class PaymentMethodsComponent implements OnInit{
  private readonly paymentService = inject(PaymentService)
  private readonly route = inject(ActivatedRoute)
  private readonly router = inject(Router)
  protected readonly paymentType = paymentType;
  selectedValue: string = '';
  disabled: boolean = true;
  offerId: string | null = '';

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.offerId = params.get('id');
    })
  }

  onRadioButtonChange($event: MatRadioChange) {
    this.disabled = false
  }

  goBackToOffers() {
    this.router.navigate(['offers']).then()
  }

  makePaymentRequest() {
    let request: PaymentDto = {
      offerId: Number(this.offerId),
      paymentType: PaymentType[this.selectedValue as keyof typeof PaymentType],
      userId: 1 //napravi methodu da dobavis ulogovanog usera
    }
    this.paymentService.processPayment(request).subscribe({
      next: res => {
        this.redirect(res)
      }
    })
  }

  redirect(response: PaymentResponseDto) {
    if (!response.paymentId) {
      window.location.href = response.paymentUrl
      return;
    }
    window.location.href = response.paymentUrl + '?data=' + encodeURIComponent(response.paymentId);
  }
}

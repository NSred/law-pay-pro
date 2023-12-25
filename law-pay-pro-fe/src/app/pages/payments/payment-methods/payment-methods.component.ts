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
import {AuthService} from "../../../shared/login-menu/auth/auth.service";
import {User} from "../../../shared/model/user";
import {SubscriptionService} from "../services/subscription.service";

@Component({
  selector: 'app-payment-methods',
  standalone: true,
  imports: [CommonModule, OfferCardComponent, MatRadioModule, FormsModule, NgOptimizedImage, ButtonComponent],
  templateUrl: './payment-methods.component.html',
  styleUrl: './payment-methods.component.scss'
})
export class PaymentMethodsComponent implements OnInit{
  private readonly subscriptionService = inject(SubscriptionService)
  private readonly auth = inject(AuthService)
  private readonly paymentService = inject(PaymentService)
  private readonly route = inject(ActivatedRoute)
  private readonly router = inject(Router)
  protected readonly paymentType = paymentType;
  selectedValue: string = '';
  disabled: boolean = true;
  offerId: string | null = '';
  private user : User | null = null;
  subscriptions: string[] = [];

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.offerId = params.get('id');
    })
    this.user = this.auth.getLoggedInUser()
    this.getPaymentMethods();
  }

  private getPaymentMethods() {
    this.subscriptionService.getPaymentMethods().subscribe({
      next: res => {
        this.subscriptions = res
        console.log(this.subscriptions)
      }
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
      offerId: this.offerId ? this.offerId : '9cca5e41-39a0-4be9-9d11-c605a32703a4',
      paymentType: PaymentType[this.selectedValue as keyof typeof PaymentType],
      userId: this.user !== null ?  this.user.id : 1//napravi methodu da dobavis ulogovanog usera
    }
    this.paymentService.processPayment(request).subscribe({
      next: res => {
        if (request.paymentType === PaymentType.CRYPTO_CURRENCY) {
          this.router.navigate(['crypto-payment'], {
            queryParams: {
              qrCode: res.sdkParams.qrCode,
              paymentAddress: res.sdkParams.paymentAddress
            }
          }).then();
          return;
        }
        if (request.paymentType === PaymentType.PAY_PAL){
          this.router.navigate(['paypal-payment/' + this.offerId], {
            queryParams: {
              paymentUrl: res.paymentUrl
            }
          }).then()
          return;
        }
        if (request.paymentType === PaymentType.CARD) {
          this.redirect(res)
          return;
        }
        if (request.paymentType === PaymentType.QR_CODE) {
          this.router.navigate(['qr-code-payment/' + this.offerId], {
            queryParams: {
              qrCode: res.sdkParams.qrCode,
              paymentId: res.paymentId
            }
          }).then();
          return;
        }
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

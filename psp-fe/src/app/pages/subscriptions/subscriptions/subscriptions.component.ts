import {Component, inject, OnInit} from '@angular/core';
import {SubscriptionDto, SubscriptionRequest} from "../model/subscriptions";
import {SubscriptionCardComponent} from "../subscription-card/subscription-card.component";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {PaymentMethodsService} from "../services/payment-methods.service";
import {AuthService} from "../../../shared/login-menu/auth/auth.service";
import {Merchant} from "../../../shared/login-menu/model";
import {Observable, of} from "rxjs";
import {SubscriptionsService} from "../services/subscriptions.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-subscriptions',
  standalone: true,
  imports: [
    SubscriptionCardComponent,
    NgForOf,
    NgIf,
    AsyncPipe
  ],
  templateUrl: './subscriptions.component.html',
  styleUrl: './subscriptions.component.scss'
})
export class SubscriptionsComponent implements OnInit {
  private readonly toastr = inject(ToastrService)
  private readonly subService = inject(SubscriptionsService)
  private readonly auth = inject(AuthService)
  private readonly paymentMethodsService = inject(PaymentMethodsService)
  subscriptions$: Observable<SubscriptionDto[]> = new Observable<SubscriptionDto[]>()
  merchant: Merchant | null = {id: 0, merchantId: ''}

  ngOnInit(): void {
    this.merchant = this.auth.getLoggedInMerchant();
    const merchantId = this.merchant !== null ? this.merchant.merchantId : "lawly"
    this.getAllByMerchant(merchantId);
  }

  private getAllByMerchant(merchantId: string) {
    this.paymentMethodsService.getAllByMerchant(merchantId).subscribe({
      next: res => {
        this.subscriptions$ = of(res);
      },
      error: err => {
        alert(err);
      }
    })
  }

  subscribe(sub: SubscriptionDto) {
    const merchantId = this.merchant !== null ? this.merchant.merchantId : "lawly"
    let request: SubscriptionRequest = {
      merchantId: merchantId,
      subscriptionType: 'SUBSCRIBE',
      paymentMethod: sub.name
    }
    this.subService.subscribe(request).subscribe({
      next: _ => {
        this.getAllByMerchant(merchantId);
        this.toastr.success('Successfully subscribed', 'Success');
      },
      error: _ => {
        this.toastr.error('Error occurred', 'Error');
      }
    })
  }

  unsubscribe(sub: SubscriptionDto) {
    const merchantId = this.merchant !== null ? this.merchant.merchantId : "lawly"
    let request: SubscriptionRequest = {
      merchantId: merchantId,
      subscriptionType: 'UNSUBSCRIBE',
      paymentMethod: sub.name
    }
    this.subService.subscribe(request).subscribe({
      next: _ => {
        this.getAllByMerchant(merchantId);
        this.toastr.success('Successfully unsubscribed', 'Success');
      },
      error: _ => {
        this.toastr.error('Error occurred', 'Error');
      }
    })
  }
}

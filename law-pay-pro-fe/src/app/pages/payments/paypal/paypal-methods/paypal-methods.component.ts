import {AfterViewInit, Component, inject, OnInit} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {ButtonComponent} from "../../../../shared/components/buttons/button/button.component";
import {MatRadioModule} from "@angular/material/radio";
import {ActivatedRoute, Router} from "@angular/router";
import {
  PaymentService,
  PayPalSubsRequest,
  PayPalSubsResponse,
  UpdatePayPalSubRequest
} from "../../services/payment.service";

declare var paypal: any

@Component({
  selector: 'app-paypal-methods',
  standalone: true,
    imports: [CommonModule, ButtonComponent, MatRadioModule, NgOptimizedImage],
  templateUrl: './paypal-methods.component.html',
  styleUrl: './paypal-methods.component.scss'
})
export class PaypalMethodsComponent implements OnInit, AfterViewInit{
  private paymentService = inject(PaymentService)
  private readonly router = inject(Router)
  private readonly route = inject(ActivatedRoute)
  private offerId: string | null = '';
  paymentUrl: string | null = '';
  disabled: boolean = false;
  payPalSub: PayPalSubsResponse = {id: 0, planId: '', subscriptionId: '', subscriptionStatus: ''}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.offerId = params.get('id');
      this.getPayPalSubs()
      this.route.queryParams.subscribe(params => {
        this.paymentUrl = params['paymentUrl'];
      })
    })
  }

  ngAfterViewInit(): void {
    if (!this.payPalSub.subscriptionId) {
      this.renderPayPalButton();
    }
  }

  private renderPayPalButton(): void {
    paypal.Buttons({
      fundingSource: paypal.FUNDING.PAYPAL,
      style: {
        layout: 'vertical'
      },
      createSubscription: (data: any, actions: any) => {
        return actions.subscription.create({
          'plan_id': this.payPalSub.planId
        });
      },
      onApprove: (data: any, actions: any) => {
        let request: UpdatePayPalSubRequest = {
          id: this.payPalSub.id,
          subscriptionId: data.subscriptionID
        }
        this.paymentService.updateSub(request).subscribe({
          next: res => {
            if (res)
              this.getPayPalSubs();
          }
        })
      }
    }).render('#paypal-button-container');
  }

  goBackToPaymentMethods() {
    this.router.navigate(['payment/' + this.offerId]).then();
  }

  makePaymentRequest() {
    if (this.paymentUrl) {
      window.location.href = this.paymentUrl
      return;
    }
    alert('payment url is null | undefined')
  }

  private getPayPalSubs() {
    let request: PayPalSubsRequest = {
      userId: 1,
      offerId: this.offerId ? this.offerId : ''
    }
    this.paymentService.getPayPalSubs(request).subscribe({
      next: res => {
        this.payPalSub = res
      }
    })
  }

  cancelSub() {
    let request: UpdatePayPalSubRequest = {
      id: this.payPalSub.id,
      subscriptionId: this.payPalSub.subscriptionId
    }
    this.paymentService.cancelSub(request).subscribe({
      next: res => {
        if (res) {
          location.reload();
        }
      }
    })
  }
}

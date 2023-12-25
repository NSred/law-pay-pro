import {Component, inject, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {LabeledInputComponent} from "../../../../shared/components/labeled-input/labeled-input.component";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {ButtonComponent} from "../../../../shared/components/buttons/button/button.component";
import {PaymentService, PayQrRequest} from "../../services/payment.service";

@Component({
  selector: 'app-qr-code-payment',
  standalone: true,
  imports: [CommonModule, LabeledInputComponent, ButtonComponent],
  templateUrl: './qr-code-payment.component.html',
  styleUrl: './qr-code-payment.component.scss'
})
export class QrCodePaymentComponent implements OnInit {
  private readonly paymentService = inject(PaymentService)
  private readonly router = inject(Router)
  private readonly route = inject(ActivatedRoute)
  qrCode: string = '';
  offerId: string | null = '';
  paymentId: string = '';
  base64: string = '';

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.offerId = params.get('id');
      this.route.queryParams.subscribe(params => {
        this.base64 = params['qrCode'];
        this.paymentId = params['paymentId'];
        this.qrCode = `data:image/png;base64,${this.base64}`;
      })
    })
  }

  confirmPayment() {
    const request: PayQrRequest = {
      qrCode: this.base64,
      paymentId: this.paymentId,
      accountNumber: ''
    }
    this.paymentService.payQr(request).subscribe({
      next: res => {
        window.location.href = res.url
      }
    })
  }

  goBack() {
    this.router.navigate(['payment/' + this.offerId]).then();
  }
}

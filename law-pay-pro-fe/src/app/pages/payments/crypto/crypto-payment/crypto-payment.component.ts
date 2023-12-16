import {Component, inject, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {LabeledInputComponent} from "../../../../shared/components/labeled-input/labeled-input.component";
import {generateQRCode} from "../../../../shared/generators/qrcode-generator";

@Component({
  selector: 'app-crypto-payment',
  standalone: true,
  imports: [CommonModule, LabeledInputComponent],
  templateUrl: './crypto-payment.component.html',
  styleUrl: './crypto-payment.component.scss'
})
export class CryptoPaymentComponent implements OnInit{
  private readonly route = inject(ActivatedRoute)
  private querySubscription: Subscription = new Subscription();
  qrCode: string = '';
  paymentAddress: string = 'iVBORw0KGgoAAAANSUhEUgAAACkAAAApAQAAAACAGz1bAAAA3UlEQVR4XmP4DwINDFipD+KTWdgbGL5f4/r7vYHhS3BEqziQCqy9C6IiwdT3i19MgXIfRENDgCr//1kgD9T3Wdaar72B4af9xHXrGxg+Hfzo7Q+ksiNZ04FUX9NVIPV/ZWXP+waGX0G8XiBKZ2FvfQPD73CroyCL8qfd2t/A8Jf1Ywo/0AbzCC59ICXQdQZo2Ef9iltADX8lWN/3NzD8S1ssYg60z9logjrQzI/97kCLPsg+TAXpuxo1vRxoWEjHhXAgFZt9xh5IxXB2yQPlLi00twf5z6BhO7qnUSgAmXmXof+BYvMAAAAASUVORK5CYII=';

  ngOnInit(): void {
    this.querySubscription = this.route.queryParams.subscribe(params => {
      const base64String = params['qrCode'];
      this.paymentAddress = params['paymentAddress'];
      this.qrCode = `data:image/png;base64,${base64String}`;
     // generateQRCode(this.qrCode)
    })
  }

  ngOnDestroy() {
    this.querySubscription.unsubscribe();
  }
}

import { Routes } from '@angular/router';
import {LoginComponent} from "./shared/login-menu/login/login.component";
import {RegistrationComponent} from "./shared/registration-menu/registration/registration.component";
import {AllOffersComponent} from "./pages/offers/all-offers/all-offers.component";
import {PaymentMethodsComponent} from "./pages/payments/payment-methods/payment-methods.component";
import {SuccessComponent} from "./pages/payments/success/success.component";
import {CancelComponent} from "./pages/payments/cancel/cancel.component";
import {CryptoPaymentComponent} from "./pages/payments/crypto/crypto-payment/crypto-payment.component";
import {PaypalMethodsComponent} from "./pages/payments/paypal/paypal-methods/paypal-methods.component";
import {QrCodePaymentComponent} from "./pages/payments/qr-code/qr-code-payment/qr-code-payment.component";

export const routes: Routes = [
  {
    path: '',
    component: AllOffersComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'offers',
    component: AllOffersComponent
  },
  {
    path: 'payment/:id',
    component: PaymentMethodsComponent
  },
  {
    path: 'payment-success/:paymentId',
    component: SuccessComponent
  },
  {
    path: 'payment-cancelled',
    component: CancelComponent
  },
  {
    path: 'crypto-payment',
    component: CryptoPaymentComponent
  },
  {
    path: 'paypal-payment/:id',
    component: PaypalMethodsComponent
  },
  {
    path: 'qr-code-payment/:id',
    component: QrCodePaymentComponent
  }
];

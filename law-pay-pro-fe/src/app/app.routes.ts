import { Routes } from '@angular/router';
import {LoginComponent} from "./shared/login-menu/login/login.component";
import {RegistrationComponent} from "./shared/registration-menu/registration/registration.component";
import {AllOffersComponent} from "./pages/offers/all-offers/all-offers.component";
import {PaymentMethodsComponent} from "./pages/payments/payment-methods/payment-methods.component";

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
  }
];

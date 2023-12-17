import { Routes } from '@angular/router';
import {LoginComponent} from "./shared/login-menu/login/login.component";
import {RegistrationComponent} from "./shared/registration-menu/registration/registration.component";
import {SubscriptionsComponent} from "./pages/subscriptions/subscriptions/subscriptions.component";

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'subscriptions',
    component: SubscriptionsComponent
  }
];

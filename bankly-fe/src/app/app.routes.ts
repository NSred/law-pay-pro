import { Routes } from '@angular/router';
import {CreditCardFormComponent} from "./pages/credit-card-form/credit-card-form.component";
import {SuccessComponent} from "./pages/success/success.component";
import {FailComponent} from "./pages/fail/fail.component";
import {ErrorComponent} from "./pages/error/error.component";

export const routes: Routes = [
  {
    path: '',
    component: CreditCardFormComponent
  },
  {
    path: 'success',
    component: SuccessComponent
  },
  {
    path: 'fail',
    component: FailComponent
  },
  {
    path: 'error',
    component: ErrorComponent
  }
];

import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient, withFetch, withInterceptors} from "@angular/common/http";
import {provideToastr} from "ngx-toastr";
import {provideAnimations} from "@angular/platform-browser/animations";
import {authInterceptor} from "./shared/login-menu/auth/auth.inetrceptor";

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withFetch(), withInterceptors([authInterceptor])),
    provideToastr({
      timeOut: 6000,
      closeButton: true,
      progressAnimation: 'increasing',
      newestOnTop: true,
      positionClass: 'toast-bottom-right',
    }),
    provideAnimations(),
  ]
};

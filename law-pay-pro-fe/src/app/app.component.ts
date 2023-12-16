import {Component, inject, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router, RouterOutlet} from '@angular/router';
import {NavbarComponent} from "./shared/navbar-menu/navbar/navbar.component";
import {NavbarStateService} from "./shared/navbar-menu/state/navbar-state.service";
import {Observable} from "rxjs";
import {PaymentStatusNotify, SseService} from "./pages/payments/services/sse.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  private readonly router = inject(Router)
  private readonly sseService = inject(SseService)
  private readonly navbarState = inject(NavbarStateService)
  isNavbarVisible$: Observable<boolean> = this.navbarState.isVisible$;
  title = 'law-pay-pro-fe';

  ngOnInit(): void {
    this.sseService.getEventStream('http://localhost:8081/api/notify/stream').subscribe({
      next: (notification: PaymentStatusNotify) => {
        console.log(notification)
        if (notification.paymentStatus === 'CANCEL') {
          this.router.navigate(['payment-cancelled']).then()
          //window.location.href = `http://localhost:4200/payment-cancelled`;
        } else if (notification.paymentStatus === 'SUCCESS') {
          this.router.navigate(['payment-success/' + notification.paymentId]).then()
          //window.location.href = `http://localhost:4200/payment-success/${notification.paymentId}`;
        }
      },
      error: err => console.error(err)
    });
  }
}

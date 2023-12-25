import {Injectable, NgZone} from '@angular/core';
import {Observable} from "rxjs";

export interface PaymentStatusNotify {
  paymentId: string;
  paymentStatus: string;
}

@Injectable({
  providedIn: 'root'
})
export class SseService {

  constructor(private zone: NgZone) { }

  getEventStream(url: string): Observable<PaymentStatusNotify> {
    return new Observable(observer => {
      const eventSource = new EventSource(url);
      eventSource.onmessage = event => {
        this.zone.run(() => {
          const data: PaymentStatusNotify = JSON.parse(event.data);
          observer.next(data);
        });
      };
      eventSource.onerror = error => {
        this.zone.run(() => {
          observer.error(error);
        });
      };
      return () => eventSource.close();
    });
  }
}

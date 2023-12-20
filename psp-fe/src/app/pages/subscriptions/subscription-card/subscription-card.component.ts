import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Subscription, SubscriptionDto} from "../model/subscriptions";
import {NgIf, NgOptimizedImage} from "@angular/common";
import {ButtonComponent} from "../../../shared/components/buttons/button/button.component";

@Component({
  selector: 'app-subscription-card',
  standalone: true,
  imports: [
    NgOptimizedImage,
    ButtonComponent,
    NgIf
  ],
  templateUrl: './subscription-card.component.html',
  styleUrl: './subscription-card.component.scss'
})
export class SubscriptionCardComponent{
  @Input() subscription: SubscriptionDto = {id: 0, name: '', imageUrl: '', subscribed: true}
  @Output() onSub: EventEmitter<SubscriptionDto> = new EventEmitter<SubscriptionDto>();
  @Output() onUnSub: EventEmitter<SubscriptionDto> = new EventEmitter<SubscriptionDto>();

  subscribe(subscription: SubscriptionDto) {
    this.onSub.emit(subscription);
  }

  unsubscribe(subscription: SubscriptionDto) {
    this.onUnSub.emit(subscription);
  }
}

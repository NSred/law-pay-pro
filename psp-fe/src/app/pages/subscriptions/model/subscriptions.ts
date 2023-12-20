export interface Subscription {
  id: number;
  name: string;
  imageUrl: string;
}

export interface SubscriptionDto {
  id: number;
  name: string;
  imageUrl: string;
  subscribed: boolean;
}

export interface SubscriptionRequest {
  merchantId: string;
  paymentMethod: string;
  subscriptionType: string;
}

export const subsMock: Subscription[] = [
  {
    id: 1,
    name: 'Card',
    imageUrl: '../../../../assets/images/atm-card.png'
  },
  {
    id: 2,
    name: 'Crypto',
    imageUrl: '../../../../assets/images/bitcoin.png'
  },
  {
    id: 3,
    name: 'PayPal',
    imageUrl: '../../../../assets/images/paypal.png'
  },
  {
    id: 4,
    name: 'PayPal',
    imageUrl: '../../../../assets/images/qr-code.png'
  }
]

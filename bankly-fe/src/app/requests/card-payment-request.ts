export interface CardPaymentRequest {
  pan: string;
  securityCode: string;
  cardHolderName: string;
  expirationDate: string;
  paymentId: string
}

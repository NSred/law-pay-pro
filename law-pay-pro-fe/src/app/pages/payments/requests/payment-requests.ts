export interface PaymentDto {
  offerId: string;
  paymentType: PaymentType;
  userId: number;
}

export enum PaymentType {
  CARD = 'CARD',
  QR_CODE = 'QR_CODE',
  PAY_PAL = 'PAY_PAL',
  CRYPTO_CURRENCY = 'CRYPTO_CURRENCY'
}

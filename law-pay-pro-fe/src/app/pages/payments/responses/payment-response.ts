export interface PaymentResponseDto {
  paymentUrl: string;
  paymentId: string;
  sdkParams: SdkParamsDto;
  errorMessage: string;
  errorCode: string;
}

export interface SdkParamsDto {
  qrCode: string;
  paymentAddress: string;
  tag: string;
}

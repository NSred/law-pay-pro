package rs.wepublishlaws.cryptoservice.adapter.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.wepublishlaws.cryptoservice.domain.model.callback.DepositCallbackResponseDto;
import rs.wepublishlaws.cryptoservice.domain.model.callback.TransactionStatus;
import rs.wepublishlaws.cryptoservice.domain.model.deposit.CoinspaidDepositRequestDto;
import rs.wepublishlaws.cryptoservice.domain.model.deposit.CoinspaidDepositResponseDto;
import rs.wepublishlaws.cryptoservice.domain.service.QRCodeGeneratorService;
import rs.wepublishlaws.shared.PaymentStatus;
import rs.wepublishlaws.shared.SdkParamsDto;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.messages.PaymentResponse;

@Component
public class CoinspaidMapper {

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    public CoinspaidDepositRequestDto mapToTransactionRequest(final PaymentMessage paymentMessage){
        final CoinspaidDepositRequestDto requestDto = new CoinspaidDepositRequestDto();
        requestDto.setCurrency("BTC");
        requestDto.setConvertTo("EUR");
        requestDto.setForeignId(paymentMessage.merchantId);
        return requestDto;
    }
    public PaymentResponse mapFromTransactionResponse(
            final PaymentMessage request,
            final CoinspaidDepositResponseDto response
    ) throws Exception {
//        final PaymentResponse responseDto = mapToResponseFromRequest(request);
        final PaymentResponse responseDto = new PaymentResponse();
        if (response.getErrors() != null) {
            final String errorMessage = "Error: " +
                    (response.getErrors().getForeignId() != null ? response.getErrors().getForeignId() :
                            (response.getErrors().getCurrency() != null ? response.getErrors().getCurrency() :
                                    (response.getErrors().getConvertTo() != null ? response.getErrors().getConvertTo() : "Unknown error field")));

            responseDto.setErrorCode(response.getErrorCode());
            responseDto.setErrorMessage(errorMessage);
            responseDto.setStatus(PaymentStatus.FAIL);
            return responseDto;
        } else {
            responseDto.setPaymentId(response.getData().getId().toString());
            responseDto.setStatus(PaymentStatus.INITIATED);
            String qrCode = qrCodeGeneratorService.generateQRCodeImage(response.getData().getAddress(), request.amount.toString());

            final SdkParamsDto sdkParams = mapSdkParams(response, qrCode);
            responseDto.setSdkParams(sdkParams);
        }

        return responseDto;
    }

//    private PaymentResponse mapToResponseFromRequest(final PaymentMessage request) {
//        final PaymentResponse response = new PaymentResponse();
//        response.setPaymentId(request.get());
//        response.setAccountId(request.getAccountId());
//        response.setProviderPaymentId(request.getProviderPaymentId());
//        response.setCurrencyCode(request.getCurrencyCode());
//        response.setAmount(request.getAmount());
//        response.setPaymentType(request.getPaymentType());
//        return response;
//    }

    private static SdkParamsDto mapSdkParams(final CoinspaidDepositResponseDto response, final String qrCode) {
        final SdkParamsDto sdkParams = new SdkParamsDto();
        sdkParams.setPaymentAddress(response.getData().getAddress());
        sdkParams.setQrCode(qrCode);
        if (response.getData().getTag() != null) {
            sdkParams.setTag(response.getData().getTag());
        }
        return sdkParams;
    }
    public PaymentResponse mapExceptionFromTransactionResponse(
            final PaymentMessage paymentMessage,
            String errorMessage
    ){
        final PaymentResponse response = new PaymentResponse();
        response.setPaymentUrl(paymentMessage.errorUrl);
        return response;
    }

    public PaymentResponse mapFromCallback(final DepositCallbackResponseDto callbackResponseDto) {
        final PaymentResponse responseDto = new PaymentResponse();
//        responseDto.setAmount(callbackResponseDto.getCurrency_received().getAmount());
//        responseDto.setCurrencyCode(callbackResponseDto.getCurrency_received().getCurrency());
        responseDto.setPaymentId(callbackResponseDto.getCrypto_address().getId().toString());
        responseDto.setStatus(getStatusFromCallbackStatus(callbackResponseDto.getStatus()));
        return responseDto;
    }

    private static PaymentStatus getStatusFromCallbackStatus(final TransactionStatus callbackStatus) {
        PaymentStatus status;
        switch (callbackStatus) {
            case confirmed:
                status = PaymentStatus.SUCCESS;
                break;
            case not_confirmed:
                status = PaymentStatus.PENDING;
                break;
            case pending:
                status = PaymentStatus.PENDING;
                break;
            case cancelled:
                status = PaymentStatus.FAIL;
                break;
            default:
                status = PaymentStatus.CANCEL;
                break;
        }
        return status;
    }
}

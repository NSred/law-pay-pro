INSERT INTO payment_method_services (id, name, queue_name, image_url) VALUES
  (1, 'CARD', 'card-service-queue', '../../../../assets/images/atm-card.png'),
  (2, 'PAY_PAL', 'paypal-service-queue', '../../../../assets/images/paypal.png'),
  (3, 'CRYPTO_CURRENCY', 'crypto-service-queue', '../../../../assets/images/bitcoin.png'),
  (4, 'QR_CODE', 'qrcode-service-queue', '../../../../assets/images/qr-code.png');


INSERT INTO subscriptions (id, merchant_id, payment_methods) VALUES
    (1, 'lawly', '["CARD", "PAY_PAL"]');
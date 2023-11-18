package com.example.bank.DTO;

public class CardTransactionRequestDTO{
        private String pan;
        private String securityCode;
        private String cardHolderName;
        private String expirationDate;

        public String getPan() {
            return pan;
        }

        public String getSecurityCode() {
            return securityCode;
        }

        public String getCardHolderName() {
            return cardHolderName;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        // Setter methods
        public void setPan(String pan) {
            this.pan = pan;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public void setCardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }
}
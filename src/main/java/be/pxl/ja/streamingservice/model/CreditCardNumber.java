package be.pxl.ja.streamingservice.model;

public class CreditCardNumber {

    private static final int LENGTH = 16;
    private static final int CVC_LENGTH = 3;

    private CreditCardType creditCardType;
    private String number;
    private String cvc;

    public CreditCardNumber(String cardNumber, String cvc) {
        this.number = validateNumber(removeBlanks(cardNumber));
        this.creditCardType = validateCreditCardType();
        this.cvc = validateCvc(removeBlanks(cvc));
    }

    private String validateCvc(String cvc) {
        cvc = cvc.replaceAll("\\s+","");
        if (!isNumeric(cvc) || cvc.length() != CVC_LENGTH) {
            throw new IllegalArgumentException("A CVC number must have " + CVC_LENGTH + " digits.");
        }
        return cvc;
    }

    private CreditCardType validateCreditCardType() {
        CreditCardType creditCardType = getCreditCardType();
        if (creditCardType == null) {
            throw new IllegalArgumentException("This is not a valid credit card");
        }
        return creditCardType;
    }

    private String validateNumber(String cardNumber) {
        if (!isNumeric(cardNumber) || cardNumber.length() != LENGTH) {
            throw new IllegalArgumentException("A card number must have " + LENGTH + " digits.");
        }
        return cardNumber;
    }

    public CreditCardType getType() {
        return creditCardType;
    }

    public String getCvc() {
        return cvc;
    }

    public String getNumber() {
        return number;
    }

    private String removeBlanks(String blanks) {
        return blanks.replaceAll("\\s+","");
    }

    private boolean isNumeric(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public CreditCardType getCreditCardType() {
        for (CreditCardType cardType : CreditCardType.values()) {
            if (cardType.getFirstNumber() == Integer.parseInt(this.number.substring(0, 1))) {
                return cardType;
            }
        }
        System.out.println(Integer.parseInt(this.number.substring(0, 1)));
        return null;
    }
}

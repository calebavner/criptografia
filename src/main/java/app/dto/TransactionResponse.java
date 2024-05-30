package app.dto;

import app.model.Transaction;

public record TransactionResponse(Long id,
                                  String userDocument,
                                  String creditCreditToken,
                                  Long value) {

    public static TransactionResponse fromEntity(Transaction t) {
        return new TransactionResponse(
            t.getId(),
            t.getRawUserDocument(),
            t.getRawCreditCardToken(),
            t.getTransactionValue()
        );
    }
}

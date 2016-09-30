package library.interfaces;

public interface IBorrowUI {

    void setState(EBorrowState state);

    void displayMemberDetails(int memberID, String memberName, String memberPhone);

    void displayExistingLoan(String loanDetails);

    void displayOverDueMessage();

    void displayAtLoanLimitMessage();

    void displayOutstandingFineMessage(float amountOwing);

    void displayOverFineLimitMessage(float amountOwing);

    void displayScannedBookDetails(String bookDetails);

    void displayPendingLoan(String loanDetails);

    void displayConfirmingLoan(String loanDetails);

    void displayErrorMessage(String errorMesg);

}

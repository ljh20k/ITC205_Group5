package library.entities;

import library.interfaces.entities.EMemberState;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member implements IMember {
    private String firstName;
    private String lastName;
    private String contactPhone;
    private String emailAddress;
    private int id;
    private EMemberState state;
    private List<ILoan> loanList;
    private float totalFines;

    public Member(String firstName, String lastName, String contactPhone, String email, int memberID) {
        if (!this.sane(firstName, lastName, contactPhone, email, memberID)) {
            throw new IllegalArgumentException("Member: constructor : bad parameters");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactPhone = contactPhone;
        this.emailAddress = email;
        this.id = memberID;
        this.loanList = new ArrayList<ILoan>();
        this.totalFines = 0.0f;
        this.state = EMemberState.BORROWING_ALLOWED;
    }

    private boolean sane(String firstName, String lastName, String contactPhone, String emailAddress, int memberID) {
        if (!(firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || contactPhone == null || contactPhone.isEmpty() || emailAddress == null || emailAddress.isEmpty() || memberID <= 0)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasOverDueLoans() {
        for (ILoan loan : this.loanList) {
            if (!loan.isOverDue()) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasReachedLoanLimit() {
        boolean b = this.loanList.size() >= 5;
        return b;
    }

    @Override
    public boolean hasFinesPayable() {
        boolean b = this.totalFines > 0.0f;
        return b;
    }

    @Override
    public boolean hasReachedFineLimit() {
        boolean b = this.totalFines >= 10.0f;
        return b;
    }

    @Override
    public float getFineAmount() {
        return this.totalFines;
    }

    @Override
    public void addFine(float fine) {
        if (fine < 0.0f) {
            throw new RuntimeException(String.format("Member: addFine : fine cannot be negative", new Object[0]));
        }
        this.totalFines += fine;
        this.updateState();
    }

    @Override
    public void payFine(float payment) {
        if (payment < 0.0f || payment > this.totalFines) {
            throw new RuntimeException(String.format("Member: addFine : payment cannot be negative or greater than totalFines", new Object[0]));
        }
        this.totalFines -= payment;
        this.updateState();
    }

    @Override
    public void addLoan(ILoan loan) {
        if (!this.borrowingAllowed().booleanValue()) {
            throw new RuntimeException(String.format("Member: addLoan : illegal operation in state: %s", new Object[]{this.state}));
        }
        this.loanList.add(loan);
        this.updateState();
    }

    @Override
    public List<ILoan> getLoans() {
        return Collections.unmodifiableList(this.loanList);
    }

    @Override
    public void removeLoan(ILoan loan) {
        if (loan == null || !this.loanList.contains(loan)) {
            throw new RuntimeException(String.format("Member: removeLoan : loan is null or not found in loanList", new Object[0]));
        }
        this.loanList.remove(loan);
        this.updateState();
    }

    @Override
    public EMemberState getState() {
        return this.state;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    private Boolean borrowingAllowed() {
        boolean b = !this.hasOverDueLoans() && !this.hasReachedFineLimit() && !this.hasReachedLoanLimit();
        return b;
    }

    private void updateState() {
        this.state = this.borrowingAllowed() != false ? EMemberState.BORROWING_ALLOWED : EMemberState.BORROWING_DISALLOWED;
    }

    public String toString() {
        return String.format("Id: %d\nName: %s %s\nContact Phone: %s\nEmail: %s\nOutstanding Charges: %0.2f", this.id, this.firstName, this.lastName, this.contactPhone, this.emailAddress, Float.valueOf(this.totalFines));
    }
}

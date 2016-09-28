package library.entities;

import library.interfaces.entities.EMemberState;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import java.util.List;

public class Member implements IMember {
    private String firstName;
    private String lastName;
    private String contactPhone;
    private String emailAddress;
    private int id;

    @Override
    public boolean hasOverDueLoans() {
        return false;
    }

    @Override
    public boolean hasReachedLoanLimit() {
        return false;
    }

    @Override
    public boolean hasFinesPayable() {
        return false;
    }

    @Override
    public boolean hasReachedFineLimit() {
        return false;
    }

    @Override
    public float getFineAmount() {
        return 0;
    }

    @Override
    public void addFine(float fine) {

    }

    @Override
    public void payFine(float payment) {

    }

    @Override
    public void addLoan(ILoan loan) {

    }

    @Override
    public List<ILoan> getLoans() {
        return null;
    }

    @Override
    public void removeLoan(ILoan loan) {

    }

    @Override
    public EMemberState getState() {
        return null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

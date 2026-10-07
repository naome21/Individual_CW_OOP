public abstract class Membership implements Chargeable {
    private String id;
    private String customerName;
    private boolean discountApplies;

    public Membership(String id, String customerName, boolean discountApplies)
            throws InvalidMembershipException {
        setId(id);
        this.customerName = validateCustomerName(customerName);
        this.discountApplies = discountApplies;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) throws InvalidMembershipException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidMembershipException("Membership ID cannot be blank.");
        }
        this.id = id.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) throws InvalidMembershipException {
        this.customerName = validateCustomerName(customerName);
    }

    private String validateCustomerName(String customerName) throws InvalidMembershipException {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidMembershipException("Customer name cannot be blank.");
        }
        return customerName.trim();
    }

    public boolean isDiscountApplies() {
        return discountApplies;
    }

    protected void setDiscountApplies(boolean discountApplies) {
        this.discountApplies = discountApplies;
    }

    public double getDiscountAmount() {
        return 0.0;
    }

    @Override
    public abstract double calculateCharge();

    public abstract String getRecordType();

    @Override
    public String toString() {
        return String.format("%-12s %-18s %-25s %,12.0f",
                id, getRecordType(), customerName, calculateCharge());
    }
}

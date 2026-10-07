public class MonthlyMember extends Membership {
    private int months;
    private boolean student;

    private static final double MONTHLY_RATE = 80000.0;
    private static final double DISCOUNT_RATE = 0.13;

    public MonthlyMember(String id, String customerName, int months, boolean student)
            throws InvalidMembershipException {
        super(id, customerName, student);
        setMonths(months);
        setStudent(student);
    }

    public int getMonths() {
        return months;
    }

    public final void setMonths(int months) throws InvalidMembershipException {
        if (months <= 0) {
            throw new InvalidMembershipException("Number of months must be greater than zero.");
        }
        this.months = months;
    }

    public boolean isStudent() {
        return student;
    }

    public final void setStudent(boolean student) {
        this.student = student;
        setDiscountApplies(student);
    }

    public double getBaseCharge() {
        return months * MONTHLY_RATE;
    }

    @Override
    public double getDiscountAmount() {
        return student ? getBaseCharge() * DISCOUNT_RATE : 0.0;
    }

    @Override
    public double calculateCharge() {
        return getBaseCharge() - getDiscountAmount();
    }

    @Override
    public String getRecordType() {
        return "MonthlyMember";
    }
}

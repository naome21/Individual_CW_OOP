public class DailyMember extends Membership {
    private int visits;

    private static final double RATE_PER_VISIT = 5000.0;

    public DailyMember(String id, String customerName, int visits)
            throws InvalidMembershipException {
        super(id, customerName, false);
        this.visits = validateVisits(visits);
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) throws InvalidMembershipException {
        this.visits = validateVisits(visits);
    }

    private static int validateVisits(int visits) throws InvalidMembershipException {
        if (visits <= 0) {
            throw new InvalidMembershipException("Number of visits must be greater than zero.");
        }
        return visits;
    }

    @Override
    public double calculateCharge() {
        return visits * RATE_PER_VISIT;
    }

    @Override
    public String getRecordType() {
        return "DailyMember";
    }
}

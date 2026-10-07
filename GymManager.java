import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GymManager {
    private final ArrayList<Membership> memberships = new ArrayList<>();

    public void addMembership(Membership member) throws InvalidMembershipException {
        if (member == null) {
            throw new InvalidMembershipException("Membership cannot be null.");
        }
        if (findById(member.getId()) != null) {
            throw new InvalidMembershipException("A membership with this ID already exists.");
        }
        memberships.add(member);
    }

    public Membership findById(String id) {
        if (id == null) {
            return null;
        }

        for (Membership member : memberships) {
            if (member.getId().equalsIgnoreCase(id.trim())) {
                return member;
            }
        }
        return null;
    }

    public boolean removeMembership(String id) {
        Membership member = findById(id);
        if (member == null) {
            return false;
        }
        return memberships.remove(member);
    }

    public double getTotalCharges() {
        double total = 0.0;
        for (Chargeable item : memberships) {
            total += item.calculateCharge();
        }
        return total;
    }

    public double getTotalDiscount() {
        double total = 0.0;
        for (Membership member : memberships) {
            total += member.getDiscountAmount();
        }
        return total;
    }

    public List<Membership> getSortedMemberships() {
        ArrayList<Membership> sorted = new ArrayList<>(memberships);

        // A = 7: sort by record type first, then customer name alphabetically.
        Comparator<Membership> comparator =
                Comparator.comparing(Membership::getRecordType)
                          .thenComparing(Membership::getCustomerName,
                                  String.CASE_INSENSITIVE_ORDER);

        sorted.sort(comparator);
        return sorted;
    }

    public int size() {
        return memberships.size();
    }
}

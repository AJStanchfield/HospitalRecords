 

/**
 * NodeType now represents a patient record in the hospital system.
 * The BST will be ordered by the patient's name (case-insensitive).
 */
public class NodeType {
    public String name;              // key used for BST ordering
    public String medicalHistory;
    public String admissionDate;     // simple String for framework; could be Date later
    public int roomNumber;

    public NodeType left;
    public NodeType right;

    public NodeType(String name, String medicalHistory, String admissionDate, int roomNumber) {
        this.name = name;
        this.medicalHistory = medicalHistory;
        this.admissionDate = admissionDate;
        this.roomNumber = roomNumber;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Room: %d | Admission: %s | History: %s",
                name, roomNumber, admissionDate, medicalHistory);
    }
}

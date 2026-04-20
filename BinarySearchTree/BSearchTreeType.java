 
/**
 * Binary search tree keyed by patient name (String, case-insensitive).
 */
public class BSearchTreeType extends BinaryTreeType {

    @Override
    public boolean search(String searchName) {
        NodeType current = root;
        while (current != null) {
            int cmp = searchName.compareToIgnoreCase(current.name);
            if (cmp == 0)
                return true;
            else if (cmp < 0)
                current = current.left;
            else
                current = current.right;
        }
        return false;
    }

    @Override
    public void insert(NodeType newNode) {
        if (newNode == null) return;
        if (root == null) {
            root = newNode;
            return;
        }

        NodeType current = root;
        NodeType trail = null;

        while (current != null) {
            trail = current;
            int cmp = newNode.name.compareToIgnoreCase(current.name);
            if (cmp == 0) {
                System.out.println("A patient with that name is already in the tree -- duplicates are not allowed.");
                return;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (newNode.name.compareToIgnoreCase(trail.name) < 0)
            trail.left = newNode;
        else
            trail.right = newNode;
    }

    @Override
    public void deleteNode(String deleteName) {
        root = deleteFromTree(root, deleteName);
    }

    private NodeType deleteFromTree(NodeType p, String deleteName) {
        if (p == null) {
            System.out.println("The patient to be deleted is not in the tree.");
            return null;
        }

        int cmp = deleteName.compareToIgnoreCase(p.name);
        if (cmp < 0) {
            p.left = deleteFromTree(p.left, deleteName);
        } else if (cmp > 0) {
            p.right = deleteFromTree(p.right, deleteName);
        } else {
            // found node
            if (p.left == null) return p.right;
            else if (p.right == null) return p.left;

            // node with two children: replace with inorder predecessor (max in left subtree)
            NodeType temp = p.left;
            while (temp.right != null) temp = temp.right;
            // copy predecessor's data into p
            p.name = temp.name;
            p.medicalHistory = temp.medicalHistory;
            p.admissionDate = temp.admissionDate;
            p.roomNumber = temp.roomNumber;
            // delete the predecessor node
            p.left = deleteFromTree(p.left, temp.name);
        }

        return p;
    }

    /**
     * Search by room number (scans the tree). Returns the first match found or null.
     */
    public NodeType searchByRoom(int roomNumber) {
        return searchByRoomRecursive(root, roomNumber);
    }

    private NodeType searchByRoomRecursive(NodeType p, int roomNumber) {
        if (p == null) return null;
        if (p.roomNumber == roomNumber) return p;
        NodeType left = searchByRoomRecursive(p.left, roomNumber);
        if (left != null) return left;
        return searchByRoomRecursive(p.right, roomNumber);
    }

    /**
     * Find and return the NodeType for a given patient name, or null if not found.
     */
    public NodeType getPatientByName(String name) {
        NodeType current = root;
        while (current != null) {
            int cmp = name.compareToIgnoreCase(current.name);
            if (cmp == 0) return current;
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null;
    }
}

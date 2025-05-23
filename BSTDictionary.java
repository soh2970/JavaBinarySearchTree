import java.util.List;
import java.util.ArrayList;

public class BSTDictionary implements BSTDictionaryADT {
    private BinarySearchTree bst;

    // Constructor: Initializes a new binary search tree
    public BSTDictionary() {
        this.bst = new BinarySearchTree();
    }

    // Returns the Record with the specified Key.
    // Returns null if the Record is not in the dictionary.
    public Record get(Key k) {
        BSTNode node = bst.get(bst.getRoot(), k);
        return node != null ? node.getRecord() : null;
    }

    // Inserts a Record into the ordered dictionary.
    // Throws a DictionaryException if a Record with the same Key already exists.
    public void put(Record d) throws DictionaryException {
        if (bst.getRoot() == null) {
            bst.insert(new BSTNode(null), d); // Initialize root if it's null
        } else {
            bst.insert(bst.getRoot(), d);
        }
    }

    // Removes the Record with the specified Key from the dictionary.
    // Throws a DictionaryException if the Record is not in the dictionary.
    public void remove(Key k) throws DictionaryException {
        if (bst.getRoot() == null) {
            throw new DictionaryException("Dictionary is empty");
        }
        bst.remove(bst.getRoot(), k);
    }

    // Returns the successor of the specified Key.
    // Returns null if the Key has no successor.
    public Record successor(Key k) {
        BSTNode node = bst.successor(bst.getRoot(), k);
        return node != null ? node.getRecord() : null;
    }

    // Returns the predecessor of the specified Key.
    // Returns null if the Key has no predecessor.
    public Record predecessor(Key k) {
        BSTNode node = bst.predecessor(bst.getRoot(), k);
        return node != null ? node.getRecord() : null;
    }

    // Returns the Record with the smallest Key in the dictionary.
    // Returns null if the dictionary is empty.
    public Record smallest() {
        BSTNode node = bst.smallest(bst.getRoot());
        return node != null ? node.getRecord() : null;
    }

    // Returns the Record with the largest Key in the dictionary.
    // Returns null if the dictionary is empty.
    public Record largest() {
        BSTNode node = bst.largest(bst.getRoot());
        return node != null ? node.getRecord() : null;
    }
    
    // Returns a list of records starting with a given prefix
    List<Record> getRecordsStartingWith(String prefix) {
        List<Record> matchedRecords = new ArrayList<>();
        inOrderTraversalForPrefix(bst.getRoot(), prefix.toLowerCase(), matchedRecords);
        return matchedRecords;
    }

    // Helper method for in-order traversal to find records starting with a prefix
    private void inOrderTraversalForPrefix(BSTNode node, String prefix, List<Record> matchedRecords) {
        if (node != null && node.getRecord() != null) {
            inOrderTraversalForPrefix(node.getLeftChild(), prefix, matchedRecords);
            if (node.getRecord().getKey().getLabel().startsWith(prefix)) {
                matchedRecords.add(node.getRecord());
            }
            inOrderTraversalForPrefix(node.getRightChild(), prefix, matchedRecords);
        }
    }
}

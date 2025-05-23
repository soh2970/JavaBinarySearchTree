public class BSTNode {
    private Record item;
    private BSTNode leftChild, rightChild, parent;
 
    // Constructor: Creates a BSTNode with the given Record
    public BSTNode(Record item) {
        this.item = item;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    // Returns the Record stored in the node
    public Record getRecord() {
        return item;
    }

    // Sets the Record of the node
    public void setRecord(Record d) {
        this.item = d;
    }

    // Returns the left child of the node
    public BSTNode getLeftChild() {
        return leftChild;
    }

    // Sets the left child of the node and updates its parent
    public void setLeftChild(BSTNode u) {
    	this.leftChild = u;
        if (u != null) {
            u.setParent(this); // Setting the parent of the left child
        }
    }

    // Returns the right child of the node
    public BSTNode getRightChild() {
        return rightChild;
    }

    // Sets the right child of the node and updates its parent
    public void setRightChild(BSTNode u) {
    	this.rightChild = u;
        if (u != null) {
            u.setParent(this); // Setting the parent of the right child
        }
    }

    // Returns the parent of the node
    public BSTNode getParent() {
        return parent;
    }

    // Sets the parent of the node
    public void setParent(BSTNode u) {
        this.parent = u;
    }

    // Checks if the node is a leaf (has no children)
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}

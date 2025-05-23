public class BinarySearchTree {
    private BSTNode root;
    
    // Constructor: Initializes an empty binary search tree
    public BinarySearchTree() {
        this.root = null;
    }
    
    // Returns the root node of the binary search tree
    public BSTNode getRoot() {
        return root;
    }
    
    // Recursively retrieves a node with the specified key from the tree
    public BSTNode get(BSTNode r, Key k) {
        if (r == null || r.getRecord() == null) {
            return null;
        }
        int compareResult = k.compareTo(r.getRecord().getKey());
        if (compareResult < 0) {
            return get(r.getLeftChild(), k);
        } else if (compareResult > 0) {
            return get(r.getRightChild(), k);
        } else {
            return r;
        }
    }
    
    // Inserts a new record into the tree, throwing an exception if the key already exists
    public void insert(BSTNode r, Record d) throws DictionaryException {
        if (root == null) {
        	root = new BSTNode(d);
            return;
        }
        int compareResult = d.getKey().compareTo(r.getRecord().getKey());
        if (compareResult < 0) {
            if (r.getLeftChild() == null) {
                r.setLeftChild(new BSTNode(d));
            } else {
                insert(r.getLeftChild(), d);
            }
        } else if (compareResult > 0) {
            if (r.getRightChild() == null) {
                r.setRightChild(new BSTNode(d));
            } else {
                insert(r.getRightChild(), d);
            }
        } else {
            throw new DictionaryException("Record with the same key already exists");
        }
    }
    
    // Removes a node with the specified key from the tree
    public void remove(BSTNode root, Key k) throws DictionaryException {
        this.root = removeRecursive(this.root, k);
    }

    
    // Helper method for recursively removing a node from the tree
    private BSTNode removeRecursive(BSTNode node, Key k) throws DictionaryException {
        if (node == null) {
            throw new DictionaryException("Key not found in the dictionary");
        }

        int compareResult = k.compareTo(node.getRecord().getKey());
        if (compareResult < 0) {
            node.setLeftChild(removeRecursive(node.getLeftChild(), k));
        } else if (compareResult > 0) {
            node.setRightChild(removeRecursive(node.getRightChild(), k));
        } else {
            // Case 1: No child or one child
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            // Case 2: Two children
            // Find the smallest node in the right subtree
            BSTNode smallestNode = smallest(node.getRightChild());
            node.setRecord(smallestNode.getRecord());
            node.setRightChild(removeRecursive(node.getRightChild(), smallestNode.getRecord().getKey()));
        }
        return node;
    }

    // Finds the successor (next higher key) of a given key in the tree

    public BSTNode successor(BSTNode r, Key k) {
        BSTNode current = get(r, k);
        if (current == null) {
            return null;
        }
        if (current.getRightChild() != null) {
            return smallest(current.getRightChild());
        } else {
            BSTNode parent = current.getParent();
            while (parent != null && current == parent.getRightChild()) {
                current = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }
    
    // Finds the predecessor (next lower key) of a given key in the tree

    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode current = get(r, k);
        if (current == null) {
            return null;
        }
        if (current.getLeftChild() != null) {
            return largest(current.getLeftChild());
        } else {
            BSTNode parent = current.getParent();
            while (parent != null && current == parent.getLeftChild()) {
                current = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }
    
    // Finds the smallest key node in a subtree

    public BSTNode smallest(BSTNode r) {
        if (r == null) {
            return null;
        }
        while (r.getLeftChild() != null) {
            r = r.getLeftChild();
        }
        return r;
    }

    // Finds the largest key node in a subtree

    public BSTNode largest(BSTNode r) {
        if (r == null) {
            return null;
        }
        while (r.getRightChild() != null) {
            r = r.getRightChild();
        }
        return r;
    }
}

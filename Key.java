
public class Key {
	private String label;
    private int type;
    
    // Constructor: Initializes a Key with a label and type
    public Key(String theLabel, int theType) {
        this.label = theLabel.toLowerCase();
        this.type = theType;
    }
    
    // Returns the label of the Key
    public String getLabel() {
        return label;
    }
    
    // Returns the type of the Key
    public int getType() {
        return type;
    }
    
    // Compares this Key to another Key
    public int compareTo(Key k) {
        if (this.label.equals(k.getLabel()) && this.type == k.getType()) {
            return 0;
        } else if (this.label.compareTo(k.getLabel()) < 0 || (this.label.equals(k.getLabel()) && this.type < k.getType())) {
            return -1;
        } else {
            return 1;
        }
    }

}

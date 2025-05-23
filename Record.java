public class Record {
    private Key theKey;
    private String data;

    // Constructor: Initializes a Record with a Key and data
    public Record(Key k, String theData) {
        this.theKey = k;
        this.data = theData;
    }

    // Returns the Key of the Record
    public Key getKey() {
        return theKey;
    }

    // Returns the data of the Record
    public String getDataItem() {
        return data;
    }
}

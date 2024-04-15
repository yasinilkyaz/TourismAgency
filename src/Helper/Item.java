package Helper;

public class Item {
    private int key;
    private int reference;
    private String value;
    private String value2;

    public Item(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public Item(int key, String value, String value2) {
        this.key = key;
        this.value = value;
        this.value2 = value2;
    }
    public Item(int key, int reference, String value) {
        this.key = key;
        this.reference = reference;
        this.value = value;
    }
    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}


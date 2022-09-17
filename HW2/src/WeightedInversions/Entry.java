public class Entry {
    
    private long value;
    private long originalPosition;
    private long inversionMagnitude;
    
    public Entry(int value, int originalPosition) {
        this.value = value;
        this.originalPosition = originalPosition;
    }
    
    public Entry(Entry entry) {
        this.value = entry.getValue();
        this.originalPosition = entry.getOriginalPosition();
        this.inversionMagnitude = 0;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getOriginalPosition() {
        return originalPosition;
    }

    public void setOriginalPosition(long originalPosition) {
        this.originalPosition = originalPosition;
    }

    public long getInversionMagnitude() {
        return inversionMagnitude;
    }

    public void setInversionMagnitude(long inversionMagnitude) {
        this.inversionMagnitude = inversionMagnitude;
    }
}

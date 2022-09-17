public class Planter {
    
    private int size;
    private boolean occupied;
    
    public Planter(int size, boolean occupied) {
        this.size = size;
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "Planter{" +
                "size=" + size +
                ", occupied=" + occupied +
                '}';
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}

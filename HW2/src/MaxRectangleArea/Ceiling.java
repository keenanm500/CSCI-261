public class Ceiling {
    
    Integer startX;
    Integer endX = null;
    Integer y;

    public Ceiling(Integer startX, Integer y) {
        this.startX = startX;
        this.y = y;
    }
    
    public int getArea() {
        
        if(endX == null) {
            return 0;
        }
        
        return (endX - startX) * y;
    }

    public Integer getStartX() {
        return startX;
    }

    public void setStartX(Integer startX) {
        this.startX = startX;
    }

    public Integer getEndX() {
        return endX;
    }

    public void setEndX(Integer endX) {
        this.endX = endX;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}

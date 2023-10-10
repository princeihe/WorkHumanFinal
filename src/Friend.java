public class Friend {
    private int userId;
    private String name;
    private double distance;

    public Friend(int userId, String name, double distance) {
        this.userId = userId;
        this.name = name;
        this.distance = distance;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }
} // end Friend class

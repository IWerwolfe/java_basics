public enum CoursesType {
    DESIGN(1),
    PROGRAMMING(2),
    MARKETING(3),
    MANAGEMENT(4),
    BUSINESS(5);
    int id;

    private CoursesType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CoursesType getType(Integer id) {
        if (id == null)
            return null;
        for (CoursesType g : values()) {
            if (g.getId() == id)
                return g;
        }
        return null;
    }
}

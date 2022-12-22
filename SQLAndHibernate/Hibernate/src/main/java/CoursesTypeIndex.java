public enum CoursesTypeIndex {
    DESIGN(1),
    PROGRAMMING(2),
    MARKETING(3),
    MANAGEMENT(4),
    BUSINESS(5);
    int id;

    private CoursesTypeIndex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CoursesTypeIndex getType(Integer id) {
        if (id == null)
            return null;
        for (CoursesTypeIndex g : values()) {
            if (g.getId() == id)
                return g;
        }
        return null;
    }
}

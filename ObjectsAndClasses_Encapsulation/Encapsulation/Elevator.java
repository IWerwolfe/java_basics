public class Elevator {
    private int currentFloor = 1;
    private final int minFloor;
    private final int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        if (getCurrentFloor() > minFloor) {
            currentFloor--;
        }
    }

    public void moveUp() {
        if (getCurrentFloor() < maxFloor) {
            currentFloor++;
        }
    }

    public void move(int floor) {

        if (floorIsCorrect(floor)) {
            while (getCurrentFloor() != floor) {

                System.out.println("Текущий этаж: " + getCurrentFloor() );

                if (getCurrentFloor()  < floor) {
                    moveUp();
                } else {
                    moveDown();
                }
            }

            System.out.println("Вы прибыли, добро пожаловать!");
            System.out.println();
        }
    }

    private boolean floorIsCorrect(int floor) {

        if (floor < minFloor) {
            System.out.println("Минимальный этаж в здании:" + minFloor);
            return false;
        } else if (floor > maxFloor) {
            System.out.println("Максимальный этаж в здании:" + maxFloor);
            return false;
        } else if (floor == getCurrentFloor()) {
            System.out.println("Вы находитесь на этом этаже");
            return false;
        } else {
            return true;
        }
    }
}

import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        staff.sort( ((o1, o2) -> {
            int comparison = o1.getSalary().compareTo(o2.getSalary());
            if (comparison == 0){
                return o1.getName().compareTo(o2.getName());
            }
            return comparison;
        }) );
    }
}
public class Arithmetic {
    int count1;
    int count2;

    public Arithmetic(int count1, int count2){
        this.count1 = count1;
        this.count2 = count2;
    }

    public int getSum(){
        return count1 + count2;
    }

    public int getNoum(){
        return count1 * count2;
    }

    public int getMaxCount(){
        if(count1 == count2){
            return -1;
        } else if (count1 > count2) {
            return count1;
        } else {
            return count2;
        }
    }

    public int getMinCount(){
        if(count1 == count2){
            return -1;
        } else if (count1 < count2) {
            return count1;
        } else {
            return count2;
        }
    }

    public String getRezultArithmetic(String title) {

        String rezult = "";

        if (!title.isEmpty()){
            rezult = title + "\n";
        }

        rezult = rezult + "Сумма чисел составляет: " + getSum() + "\n";
        rezult = rezult + "Произведение чисел составляет: " + getNoum() + "\n";

        int max = getMaxCount();
        if (max == -1) {
            rezult = rezult + "Числа равны\n";
        } else {
            rezult = rezult + "Максимальное число: " + max + "\n";
            rezult = rezult + "Минимальное число: " + getMinCount() + "\n";
        }
        return rezult;
    }
}

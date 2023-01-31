public class Loader {

    static String numberStr = "";
    static int padSize;

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        int[] regionCodes = {1, 2, 102, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 113, 14, 15, 16, 116, 716, 17, 18, 19,
                21, 121, 22, 23, 93, 123, 24, 84, 88, 124, 25, 125, 26, 126, 27, 28, 29, 30, 31, 32, 33, 34, 134, 35,
                36, 136, 37, 38, 85, 138, 39, 91, 40, 41, 42, 142, 43, 44, 45, 46, 47, 48, 49, 50, 90, 150, 190, 750,
                51, 52, 152, 53, 54, 154, 55, 56, 57, 58, 59, 81, 159, 60, 61, 161, 62, 63, 163, 64, 164, 65, 66, 96,
                196, 67, 68, 69, 70, 71, 72, 73, 173, 74, 174, 75, 80, 76, 77, 97, 99, 177, 197, 199, 777, 799, 977,
                78, 98, 178, 79, 82, 83, 86, 186, 87, 89, 92, 94, 95};
//        int[] regionCodes = {1, 2, 102, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 113, 14};
        String path = "res/";
        CarNumberGenerator.generationNumbers();

        for (int regionCode : regionCodes) {
            CarNumberGenerator carNumberGenerator = new CarNumberGenerator(regionCode, path);
            carNumberGenerator.start();
        }

        System.out.println("Main: " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("Count Thread: " + CarNumberGenerator.getCount());
    }
}

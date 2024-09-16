import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Сумма элементов последовательности = " + FirstEx());
        int[][] x = { {20, 34, 2}, {9, 12, 18}, {3, 4, 5} };
        System.out.println("Минимальный элемент массивов = " + SecondEx(x));
    }
    public static int FirstEx(){
        return IntStream.range(0, 1001).filter(x -> x%3 == 0 || x%5 == 0).sum();
    }
    public static int SecondEx(int[][] binArr){
        int min = Integer.MAX_VALUE;
        for (int[] arr : binArr) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] < min) min = arr[j];
            }
        }
        return min;
    }
}




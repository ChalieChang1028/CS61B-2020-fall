public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int sum = 0;
        while (x < 9) {
            sum += x;
            System.out.print(sum +  " ");
            x = x + 1;
        }
        sum += x;
        System.out.println(sum);
    }
}

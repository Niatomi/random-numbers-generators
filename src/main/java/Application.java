import lombok.SneakyThrows;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Application {


    /**
     * Подсчёт колличества чисел в мантиссе начального числа
     * @param num_1 входной параметр
     * @param sb StringBuilder для перевода числа в форамат String
     * @param a String создание мантисы числа
     * @param num буферная переменная на которой будет основываться подсчёт чисел
     * @param count счётчик чисел
     * @return колличество чисел в мантиссе начального числа
     */
    public static int DigitCounter(double num_1) {
        StringBuilder sb = new StringBuilder();
        sb.append(num_1);
        String a = sb.substring(2);
        int num = Integer.parseInt(a);
        int count = 0;
        while (num != 0) {
            num = num/10;
            count++;
        }
        return count;
    }


    /**
     * Метод для создания псевдо-случайных чисел по методу серединных квадратов
     * @param StartingPoint начальное число
     * @param Iterations Колличество итераций
     * @param sb SrtringBuilder для создания числа из мантиссы
     * @param bufferVar буфферная переменная для квадрата числа
     * @param plusdigit число для определения сколько нужно шагов от
     *                  4 позиции чтобы забрать середину числа
     * @param s переменная для создания числа из середины квадрата
     * @param zeroDot переменная для добавления 0. к мантиссе
     * @return result массив double[] из псево-случайных сгенерированных чисел
     */
    @SneakyThrows
    public static double[] MethodAvgSquares(double StartingPoint, int Iterations) {
        StringBuilder sb = new StringBuilder();
        double[] result = new double[Iterations];
        double bufferVar;
        int plusdigit = DigitCounter(StartingPoint);
        result[0] = StartingPoint;
        for (int i = 1; i < Iterations; i++) {
            sb.delete(0, sb.length());
            bufferVar = result[i-1]*result[i-1];
            if (bufferVar < 0.1) {
                bufferVar+=0.1;
            }
            if (bufferVar < 0.001) {
                result[i] = StartingPoint;
                break;
            }
            sb.append(bufferVar);
            sb.append("00000");
            String s = sb.substring(4, 4+plusdigit);
            sb.delete(0, sb.length());
            sb.append(s);
            String zeroDot = "0." + sb;
            result[i] = Double.parseDouble(zeroDot);
        }
        return result;
    }

    /**
     * Метод для создания псевдо-случаных чисел
     * по алгоритму линейного конгруэнтного генератора
     * Xn = (a*result[i-1] + c) mod m
     * @param a множитель
     * @param c приращение
     * @param m модуль
     * @param Xn начальное значение
     * @param iterations колличесвто итераций
     * @return result массив данных с пседо-случайными
     * числами по алгориму линейного конгруэнтного генератора
     */
    @SneakyThrows
    public static double[] MethodCongruateGenerator(int a, int c, double m, double Xn, int iterations) {
        double[] result = new double[iterations];
        result[0] = Xn;
        for (int i = 1; i < iterations; i++) {
            result[i] = (a*result[i-1] + c) % m;
        }
        return result;
    }

    /**
     * Создание массива псевдо-случайных чисел
     * в пределе от (0...1)
     * предоставляемое инструментами Java
     * @param iterations колличество итераций
     * @return result массив из псевдо-случайных чисел
     */
    public static double[] BasicJavaRand(int iterations) {
        double[] result = new double[iterations];
        for (int i = 0; i < iterations; i++) {
            result[i] = Math.random();
        }
        return result;
    }

    /**
     * Метод с которого начинается программа.
     *
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        CSVLibrary csvLibrary = new CSVLibrary();
        double m = Math.pow(2, 48);
        int a = 2;
        double StartingPoint = 0.3567;
        int Iterations = 120;
        double[] ArrayMethodAvgSquares = MethodAvgSquares(StartingPoint, Iterations);
        double[] ArrayMethodCongruateGenerator = MethodCongruateGenerator(a, 5, m, StartingPoint, Iterations);
        double[] ArrayBasicJavaRand = BasicJavaRand(Iterations);
        csvLibrary.writeToCsv(ArrayMethodAvgSquares, ArrayMethodCongruateGenerator, ArrayBasicJavaRand, Iterations);
    }
}

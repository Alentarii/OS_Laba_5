import java.io.*;
import java.util.Scanner;

public class OS_Laba_5 {

    public static void main(String[] args) throws IOException {
        Node list = new Node();
        File file = new File("D:\\OS_Laba_5\\recorses\\file");
        File file1 = new File("D:\\OS_Laba_5\\recorses\\read");
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размер блока: ");
        int n = in.nextInt();
        Node.initializing(list, file, n);
        for (;;) {
            System.out.println("\nДля выполнения следующих операций введите следующие значения: " + "\n" +
                    "Выделить блоки (1),\nвыделить ряд блоков (2),\nосвободить ранее выделенные блоки (3)," + "\n" +
                    "запись данных в указанные блоки(4),\nчтение данных из указанных блоков в файл(5)," + "\n" +
                    "получить данные о состоянии блочного пространства(6),\nвернуть последнее внесенное изменеие в пространстве(7)," + "\n" +
                    "для выхода (0).");
            n = in.nextInt();
            if (n == 0) {
                System.out.println("\nДо свидания!!!!!!");
                break;
            }
            if (n == 1) {
                Node.help(list);
                System.out.print("\nВведите количество блоков: ");
                n = in.nextInt();
                Node.block_selection(list, n);
                continue;
            }
            if (n == 2) {
                Node.help(list);
                System.out.print("\nВведите количество: ");
                n = in.nextInt();
                int[] mass = new int[n];
                System.out.print("\nВведите ряд блоков: ");
                for (int i = 0; i < n; i++)
                    mass[i] = in.nextInt();
                Node.block_selection(list, mass);
                continue;
            }
            if (n == 3) {
                Node.help(list);
                System.out.print("\nВведите количество: ");
                int m = in.nextInt();
                int[] mass = new int[m];
                System.out.print("\nВведите ряд блоков: ");
                for (int i = 0; i < m; i++)
                    mass[i] = in.nextInt();
                Node.block_release(list, mass);
                continue;
            }
            if (n == 4) {
                Node.help(list);
                Scanner inn = new Scanner(System.in);
                Scanner ins = new Scanner(System.in);
                System.out.print("\nВведите количество: ");
                int m = inn.nextInt();
                int[] mass = new int[m];
                System.out.print("\nВведите ряд блоков: ");
                for (int i = 0; i < m; i++)
                    mass[i] = in.nextInt();
                String[] messege = new String[m];
                System.out.print("\nВведите ряд сообщений: ");
                for (int i = 0; i  < m; i++)
                    messege[i] = ins.nextLine();
                Node.set_messeges(list, messege, mass);
                continue;
            }
            if (n == 5) {
                Node.help(list);
                System.out.print("\nВведите количество: ");
                int m = in.nextInt();
                int[] mass = new int[m];
                System.out.print("\nВведите ряд блоков: ");
                for (int i = 0; i < m; i++)
                    mass[i] = in.nextInt();
                Node.read(list, file1, mass);
                continue;
            }
            if (n == 6) {
                Node.help(list);
                Node.inf_get(list);
                continue;
            }
            if (n == 7) {
                Node.return_kash(list);
            }
        }
    }

}


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Node {
    static int linkssss = 0;

    static Node Kash = new Node();
    static int all_remember = 0;
    Node head; // глава списка
    Node next;
    int index;
    double remember;
    String messege;
    boolean existence;
    boolean have_messege;
    static File file;
    int[] link;

    Node () {}
    // Конструктор
    Node(int d, int leng) {
        all_remember = leng;
        this.index = d;
        this.messege = "not exictence";
        this.remember = 0;
        this.next = null;
        this.existence = false;
        this.have_messege = false;
        this.link = new int[leng + 10];
        for (int i = 0; i < leng + 10; i++)
            link[i] = -1;
    }


    public static Node initializing (Node list, File files, int leng) {
        file = files;
        for (int i = 0; i < 32; i++) {
            // Создаем новый узел с заданными данными
            Node new_node = new Node(i, leng);
            new_node.next = null;

            if (list.head == null) {
                list.head = new_node;
            } else {
                Node last = list.head;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = new_node;
            }
        }
        return list;
    }

    public static int block_selection (Node list, int namber) throws IOException { //выделение
        Node last = list.head;
        int k = 0;

        while (last.next != null) {
            if (!last.existence)
                k++;
            last = last.next;
        }

        if (k < namber) {
            System.out.println("ОШИБКА. Количество запрашиваемых блоков больше доступных для выделения!");
            return 0;
        }

        last = list.head;
        while (last.next != null & namber != 0) {
            if (!last.existence) {
                Kash.set_kash(last);
                last.existence = true;
                last.messege = "nulllllllllll";
                System.out.println("Bыделен блок " + last.index);
                namber--;
            }
            last = last.next;
        }
        FileWriter fileWriter = new FileWriter(file);
        last = list.head;
        int i = 0;
        while (last.next != null) {
            fileWriter.write(i + ": [" + last.messege + "]\n");
            last = last.next;
            i++;
        }
        fileWriter.close();
        return 1;
    }

    public static int block_selection (Node list, int[] namber) throws IOException { //выделение
        Node last = list.head;
        Arrays.sort(namber);
        int i = 0;

        while (last.next != null & i < namber.length) {
            if (last.index == namber[i]) {
                if (last.existence) {
                    System.out.println("ОШИБКА. Блок " + namber[i] + " уже выделен. Дальнейшая проверка остановлена");
                    return 0;
                }
                i++;
            }
            last = last.next;
        }

        last = list.head;
        i = 0;
        while (last.next != null & i < namber.length) {
            if (last.index == namber[i]) {
                Kash.set_kash(last);
                last.existence = true;
                last.messege = "nulllllllllll";
                System.out.println("Bыделен блок " + last.index);
                i++;
            }
            last = last.next;
        }
        FileWriter fileWriter = new FileWriter(file);
        last = list.head;
        i = 0;
        while (last.next != null) {
            fileWriter.write(i + ": [" + last.messege + "]\n");
            last = last.next;
            i++;
        }
        fileWriter.close();
        return 1;
    }

    public static int block_release (Node list, int[] namber) throws IOException { //освобождение
        Node last = list.head;
        Arrays.sort(namber);
        int i = 0;

        while (last.next != null & i < namber.length) {
            if (last.index == namber[i]) {
                if (!last.existence) {
                    System.out.println("ОШИБКА. Блок " + namber[i] + " уже освобожден. Дальнейшая проверка остановлена");
                    return 0;
                }
                i++;
            }
            last = last.next;
        }
        Node last1 = list.head;
        i = 0;
        while (last1.next != null & i < namber.length) {
            if (last1.index == namber[i]) {
                Kash.set_kash(last1);
                last = last1;
                while (last.next.next != null) {
                    for (int j = 0; j < last1.link.length; j++) {
                        for (int k = 0; k < last.next.link.length; k++) {
                            if (last1.link[j] == last.next.link[k] & last1.link[j] != -1 & last.next.link[k] != -1) {
                                if (k == last.next.messege.length() - 1)
                                    last.next.messege = last.next.messege.substring(0, k);
                                else
                                    last.next.messege = last.next.messege.substring(0, k) + last.next.messege.substring(k + 1);
                                for (int p = k; p < last.next.link.length - 1; p++) {
                                    int t = last.next.link[p];
                                    last.next.link[p] = last.next.link[p + 1];
                                    last.next.link[p + 1] = t;
                                }
                                last.next.link[last.next.link.length - 1] = -1;
                                k = -1;
                            }
                            if (last.next.messege.length() == 0) {
                                last.next.messege = "not exictence";
                                last.next.remember = 0;
                                last.next.existence = false;
                                last.next.have_messege = false;
                            }
                        }
                    }
                    last = last.next;
                }
                last1.messege = "not exictence";
                last1.remember = 0;
                last1.existence = false;
                last1.have_messege = false;
                Arrays.fill(last.next.link, -1);
                i++;
            }
            last1 = last1.next;
        }
        FileWriter fileWriter = new FileWriter(file);
        last = list.head;
        i = 0;
        while (last.next != null) {
            fileWriter.write(i + ": [" + last.messege + "]\n");
            last = last.next;
            i++;
        }
        fileWriter.close();
        return 1;
    }


    public static int set_messeges (Node list, String[] messege, int[] namber) throws IOException {
        int[] nambers = namber;
        String[] messeges = messege;
        Arrays.sort(namber);

        for (int i = 0; i < namber.length; i++)
            for (int j = 0; j < nambers.length; j++)
                if (namber[i] == nambers[j])
                    messege[i] = messeges[j];
        int i = 0;

        Node last = list.head;
        while (last.next != null & i < namber.length) {
            if (last.index == namber[i]) {
                if (last.link[all_remember - 1] != -1) {
                    int k = all_remember;
                    while (last.link[k - 1] != -1 & k < last.link.length) {
                        if (last.link[k] == -1) {
                            linkssss++;
                            last.link[k] = linkssss;
                            linkssss--;
                            break;
                        }
                        k++;
                    }
                }
                if (messege[i].length() < all_remember) {
                    if (last.messege.length() == 13) {
                        Kash.set_kash(last);
                        last.messege = messege[i];
                        linkssss++;
                        for (int j = 0; j < last.messege.length(); j++)
                            last.link[j] = linkssss;
                        last.remember = messege[i].length();
                        last.have_messege = true;
                        last.existence = true;
                        messege[i] = "";
                    }
                    if ((all_remember - last.messege.length()) == messege[i].length() | (all_remember - last.messege.length()) > messege[i].length()) {
                        Kash.set_kash(last);
                        last.messege += messege[i];
                        linkssss++;
                        for (int j = messege[i].length() - 1; j >= last.messege.length() - messege[i].length(); j--)
                            last.link[j] = linkssss;
                        last.remember += messege[i].length();
                        last.have_messege = true;
                        last.existence = true;
                        messege[i] = "";
                    }
                    if ((all_remember - last.messege.length()) < messege[i].length()){
                        linkssss++;
                        if (last.messege.length() > 5)
                            last.messege = "";
                        while (last.next != null & (all_remember - last.messege.length()) < messege[i].length() & last.messege.length() != 13) {
                            if (last.messege.length() > 5)
                                last.messege = "";
                            String help = messege[i].substring(0, all_remember - last.messege.length());
                            messege[i] = messege[i].substring(all_remember - last.messege.length());
                            Kash.set_kash(last);
                            last.messege += help;
                            for (int j = last.messege.length() - 1; j >= last.messege.length() - help.length(); j--)
                                last.link[j] = linkssss;
                            last.remember += help.length();
                            last.have_messege = true;
                            last.existence = true;
                            if (last.next != null)
                                last = last.next;
                        }
                        Kash.set_kash(last);
                        if (last.messege.length() == 13) {
                            last.messege = messege[i];
                            last.remember = messege[i].length();
                            for (int j = 0; j < last.messege.length(); j++)
                                last.link[j] = linkssss;
                        }
                        else {
                            last.messege += messege[i];
                            last.remember += messege[i].length();
                            for (int j = last.messege.length() - 1; j >= last.messege.length() - messege[i].length(); j--)
                                last.link[j] = linkssss;
                        }
                        last.have_messege = true;
                        last.existence = true;
                        messege[i] = "";
                    }

                    i++;
                    continue;
                }

                if (messege[i].length() == all_remember) {
                    if (last.messege.length() == 13) {
                        Kash.set_kash(last);
                        last.messege = messege[i];
                        linkssss++;
                        for (int j = 0; j < messege[i].length(); j++)
                            last.link[j] = linkssss;
                        last.remember = messege[i].length();
                        last.have_messege = true;
                        last.existence = true;
                        messege[i] = "";
                    } else if ((all_remember - last.messege.length()) < messege[i].length()) {
                        linkssss++;
                        if (last.messege.length() > 5)
                            last.messege = "";
                        while (last.next != null & (all_remember - last.messege.length()) < messege[i].length() & last.messege.length() != 13) {
                            if (last.messege.length() > 5)
                                last.messege = "";
                            String help = messege[i].substring(0, all_remember - last.messege.length());
                            messege[i] = messege[i].substring(all_remember - last.messege.length());
                            Kash.set_kash(last);
                            last.messege += help;
                            for (int j = last.messege.length() - 1; j >= last.messege.length() - help.length(); j--)
                                last.link[j] = linkssss;
                            last.remember += help.length();
                            last.have_messege = true;
                            last.existence = true;
                            if (last.next != null)
                                last = last.next;
                        }
                        Kash.set_kash(last);
                        if (last.messege.length() == 13) {
                            last.messege = messege[i];
                            last.remember = messege[i].length();
                            for (int j = 0; j < last.messege.length(); j++)
                                last.link[j] = linkssss;
                        }
                        else {
                            last.messege += messege[i];
                            last.remember += messege[i].length();
                            for (int j = last.messege.length() - 1; j >= last.messege.length() - messege[i].length(); j--)
                                last.link[j] = linkssss;
                        }
                        last.have_messege = true;
                        last.existence = true;
                        messege[i] = "";
                    }
                    i++;
                    continue;
                }

                if (messege[i].length() > all_remember) {
                    if (last.messege.length() > 5)
                        last.messege = "";
                    linkssss++;
                    while (last.next != null & (all_remember - last.messege.length()) < messege[i].length() & last.messege.length() != 13) {
                        if (last.next.messege.length() > 5)
                            last.next.messege = "";
                        String help = messege[i].substring(0, all_remember - last.messege.length());
                        messege[i] = messege[i].substring(all_remember - last.messege.length());
                        Kash.set_kash(last);
                        last.messege += help;
                        for (int j = last.messege.length() - 1; j >= last.messege.length() - help.length(); j--)
                            last.link[j] = linkssss;
                        last.remember += help.length();
                        last.have_messege = true;
                        last.existence = true;
                        if (last.next != null)
                            last = last.next;
                    }
                    Kash.set_kash(last);
                    if (last.messege.length() == 13) {
                        last.messege = messege[i];
                        last.remember = messege[i].length();
                        for (int j = 0; j < last.messege.length(); j++)
                            last.link[j] = linkssss;
                    }
                    else {
                        last.messege += messege[i];
                        last.remember += messege[i].length();
                        for (int j = last.messege.length() - 1; j >= last.messege.length() - messege[i].length(); j--)
                            last.link[j] = linkssss;
                    }
                    last.have_messege = true;
                    last.existence = true;
                    messege[i] = "";
                    i++;
                    continue;
                }
            }
            last = last.next;
        }

        FileWriter fileWriter = new FileWriter(file);
        last = list.head;
        i = 0;
        while (last.next != null) {
            fileWriter.write(i + ": [" + last.messege + "]\n");
            last = last.next;
            i++;
        }
        fileWriter.close();
        return 1;
    }

    public static int read (Node list, File filee, int[] namber) throws IOException {
        Node last = list.head;
        Arrays.sort(namber);
        String messeges = "";
        int i = 0;

        while (last.next != null & i < namber.length) {
            if (last.index == namber[i]) {
                if (!last.existence) {
                    System.out.println("ОШИБКА. Блок " + namber[i] + " не выделен. Дальнейшая проверка остановлена");
                    return 0;
                }
                i++;
            }
            last = last.next;
        }

        last = list.head;
        i = 0;
        while (last.next != null & i < namber.length) {
            if (last.index == namber[i]) {
                messeges += last.messege;
                i++;
            }
            last = last.next;
        }

        FileWriter fileWriter = new FileWriter(filee);
        fileWriter.write("[" + messeges + "]\n");
        fileWriter.close();
        return 1;
    }

    public static void inf_get (Node list) {
        int remeber = 0, count = 0, count_false = 0;
        Node last = list.head;
        while (last.next != null) {
            if (last.existence)
                count++;
            if (!last.have_messege & last.existence)
                count_false++;
            else
                remeber += last.remember;
            last = last.next;
        }

        int i = 0;
        int[][] Remember = new int[32][2];
        last = list.head;
        while (last.next != null) {
            int k = 0;
            while (last.existence & !last.have_messege) {
                k++;
                last = last.next;
            }
            if (k != 0) {
                Remember[i][0] = last.index - k;
                Remember[i][1] = k;
                i++;
            }
            last = last.next;
        }

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Размер блока данных: " + all_remember);
        System.out.println("Общее количество блоков: " + count);
        System.out.print("Свободные блоки: ");
        for (int j = 0; j < i; j++)
            System.out.print("(" + Remember[j][0] + ";" + Remember[j][1] + ") ");
        System.out.println();
        System.out.println("Количество пустых блоков: " + count_false);
        System.out.println("Размер памяти, занятой под служебную информацию: " + remeber);
        System.out.println("Размер транзакционного кэша: " + remember_KASH());
        System.out.println("---------------------------------------------------------------------------");
    }

    static public double remember_KASH () {
        if (Kash.remember == -1)
            return 0;
        else {
            double remember = 0;
            remember = Kash.remember + 4;
            return remember;
        }
    }

    static public int return_kash (Node list) throws IOException {
        Path path = Path.of("D:\\OS_Laba_5\\recorses\\a");
        List<String> l = Files.readAllLines(path);

        FileWriter fileWriter = new FileWriter("D:\\OS_Laba_5\\recorses\\file");
        for (int i = 0; i < 30; i++) {
            fileWriter.write(l.get(i) + "\n");
        }
        fileWriter.close();
//        Node last = list.head;
//        while (last.next != null) {
//            if (last.index == Kash.index) {
//                last.remember = Kash.remember;
//                last.have_messege = Kash.have_messege;
//                last.existence = Kash.existence;
//                last.messege = Kash.messege;
//                Kash.set_kash(last);
//                return 1;
//            }
//            last = last.next;
//        }
//        FileWriter fileWriter = new FileWriter(file);
//        last = list.head;
//        int i = 0;
//        while (last.next != null) {
//            fileWriter.write(i + ": [" + last.messege + "]\n");
//            last = last.next;
//            i++;
//        }
//        fileWriter.close();
        return 0;
    }

    static public void help(Node list) throws IOException {
        FileWriter fileWriter = new FileWriter("D:\\OS_Laba_5\\recorses\\a");
        Node last = list.head;
        int i = 0;
        while (last.next != null) {
            fileWriter.write(i + ": [" + last.messege + "]\n");
            last = last.next;
            i++;
        }
        fileWriter.close();
    }

    public void set_kash(Node node){
        Kash.messege = node.messege;
        Kash.have_messege = node.have_messege;
        Kash.existence = node.existence;
        Kash.remember = node.remember;
        Kash.index = node.index;
    }
}
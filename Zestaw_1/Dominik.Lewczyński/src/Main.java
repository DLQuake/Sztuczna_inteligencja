import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.util.stream.Collectors.toCollection;

public class Main {

    public static ArrayList<String> read_lines(String sciezka) throws FileNotFoundException {
        ArrayList<String> wynik = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(sciezka))) {
            while (s.hasNext()) {
                wynik.add(s.nextLine());
            }
            return wynik;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String nazwaPlikuZDanymi = "dane/australian.txt";
        String nazwaPlikuZTypamiAtrybutow = "dane/australian-type.txt";

        // Zadanie 3
        System.out.println("Zadanie 3");
        ArrayList<String> lines = read_lines(nazwaPlikuZDanymi);

        //a)
        int liczba_obiektow = lines.size();
        int liczba_atrybutow = lines.get(0).split(" ").length - 1;

        String[] decyzje = new String[liczba_obiektow];
        for (int i = 0; i < liczba_obiektow; ++i) {
            decyzje[i] = lines.get(i).split(" ")[liczba_atrybutow];
        }
        Object[] decision_classes = Arrays.stream(decyzje).distinct().toArray();
        System.out.println("a)");
        System.out.println("Klasy decyzyjne");
        for (Object i : decision_classes) {
            System.out.println(i);
        }

        System.out.println();

        // b)
        HashMap<Object, Integer> liczba_obiektow_w_klasach = new HashMap<>();
        for (Object i : decision_classes) {
            int sum = 0;
            for (int j = 0; j < liczba_obiektow; ++j) {
                if (decyzje[j].equals(i)) {
                    ++sum;
                }
            }
            liczba_obiektow_w_klasach.put(i, sum);
        }
        System.out.println("b)");
        System.out.println("liczba obiektów w klasach decyzyjnych:");
        for (Object i : decision_classes) {
            System.out.println(i + " - " + liczba_obiektow_w_klasach.get(i));
        }

        System.out.println();

        //c)
        lines = read_lines(nazwaPlikuZTypamiAtrybutow);
        String numeric = "n";
        String[] nazwy_atrybutow = new String[liczba_atrybutow];
        String[] typy_atrybutow = new String[liczba_atrybutow];
        for (int i = 0; i < liczba_atrybutow; ++i) {
            nazwy_atrybutow[i] = lines.get(i).split(" ")[0];
            typy_atrybutow[i] = lines.get(i).split(" ")[1];
        }

        lines = read_lines(nazwaPlikuZDanymi);
        String[][] atrybuty = new String[liczba_obiektow][liczba_atrybutow];
        for (int i = 0; i < liczba_obiektow; ++i) {
            String[] line = lines.get(i).split(" ");
            atrybuty[i] = Arrays.copyOfRange(line, 0, liczba_atrybutow);
        }

        ArrayList<Integer> indeks_atrybutow_liczbowych = new ArrayList<>();
        for (int i = 0; i < liczba_atrybutow; ++i)
            if (typy_atrybutow[i].equals(numeric)) {
                indeks_atrybutow_liczbowych.add(i);
            }
        double[][] numeric_attributes = new double[liczba_obiektow][indeks_atrybutow_liczbowych.size()];
        for (int i = 0; i < liczba_obiektow; ++i)
            for (int j = 0; j < indeks_atrybutow_liczbowych.size(); ++j)
                numeric_attributes[i][j] = Double.parseDouble(atrybuty[i][indeks_atrybutow_liczbowych.get(j)]);

        double[] max = new double[numeric_attributes[0].length];
        double[] min = new double[numeric_attributes[0].length];
        for (int i = 0; i < numeric_attributes[0].length; ++i) {
            max[i] = numeric_attributes[0][i];
            min[i] = numeric_attributes[0][i];
        }
        for (int i = 0; i < liczba_obiektow; ++i)
            for (int j = 0; j < numeric_attributes[0].length; ++j) {
                if (numeric_attributes[i][j] > max[j]) {
                    max[j] = numeric_attributes[i][j];
                }
                if (numeric_attributes[i][j] < min[j]) {
                    min[j] = numeric_attributes[i][j];
                }
            }

        System.out.println("c)");
        System.out.println("minimalne i maksymalne wartości poszczególnych atrybutów:");
        for (int i = 0; i < numeric_attributes[0].length; ++i) {
            System.out.println(nazwy_atrybutow[indeks_atrybutow_liczbowych.get(i)] + ":");
            System.out.println("max - " + max[i]);
            System.out.println("min - " + min[i]);
        }

        System.out.println();

        // d) oraz e)
        ArrayList<ArrayList<String>> different_available_values = new ArrayList<>();
        for (int i = 0; i < liczba_atrybutow; ++i) {
            String[] attribute = new String[liczba_obiektow];
            for (int j = 0; j < liczba_obiektow; ++j)
                attribute[j] = atrybuty[j][i];
            different_available_values.add(Arrays.stream(attribute).distinct().collect(toCollection(ArrayList::new)));
        }

        System.out.println("d)");
        System.out.println("liczba różnych dostępnych wartości:");
        for (int i = 0; i < liczba_atrybutow; ++i) {
            System.out.println(nazwy_atrybutow[i] + " - " + different_available_values.get(i).size());
        }
        System.out.println("e)");
        System.out.println("wszystkie różne dostępne wartości:");
        for (int i = 0; i < liczba_atrybutow; ++i) {
            System.out.println(nazwy_atrybutow[i] + " - " + different_available_values.get(i));
        }

        System.out.println();

        // f)
        System.out.println("f)");
        System.out.println("odchylenie standardowe dla poszczególnych atrybutów w całym systemie:");
        for (int i = 0; i < numeric_attributes[0].length; ++i) {
            double[] array = new double[liczba_obiektow];
            for (int j = 0; j < liczba_obiektow; ++j)
                array[j] = numeric_attributes[j][i];
            System.out.println(nazwy_atrybutow[indeks_atrybutow_liczbowych.get(i)] + " - " + odchylenie_standardowe(array));
        }
        System.out.println("odchylenie standardowe dla poszczególnych atrybutów w klasach decyzyjnych:");
        for (Object d : decision_classes) {
            System.out.println(d);
            for (int i = 0; i < numeric_attributes[0].length; ++i) {
                double[] array = new double[liczba_obiektow_w_klasach.get(d)];
                int index = 0;
                for (int j = 0; j < liczba_obiektow; ++j)
                    if (decyzje[j].equals(d)) {
                        array[index++] = numeric_attributes[j][i];
                    }
                System.out.println(nazwy_atrybutow[indeks_atrybutow_liczbowych.get(i)] + " - " + odchylenie_standardowe(array));
            }
        }

        System.out.println();

        // Zadanie4
        // a)
        System.out.println("Zadanie 4");
        System.out.println("a)");
        for (int i = 0; i < numeric_attributes[0].length; ++i) {
            int[] array = generate_missing_values(liczba_obiektow);
            Arrays.sort(array);
            for (int j : array)
                atrybuty[j][indeks_atrybutow_liczbowych.get(i)] = "?";
        }

        for (int i = 0; i < numeric_attributes[0].length; ++i) {
            String[] wynik = new String[liczba_obiektow];
            for (int j = 0; j < liczba_obiektow; ++j) {
                wynik[j] = atrybuty[j][indeks_atrybutow_liczbowych.get(i)];
            }
            System.out.println(nazwy_atrybutow[indeks_atrybutow_liczbowych.get(i)] + " - " + most_popular(wynik));
        }
    }

    private static double odchylenie_standardowe(double[] array) {
        double wynik = 0;
        double suma = 0;
        for(double i : array) {
            suma = suma + i;
            wynik = wynik + pow(i, 2);
        }
        wynik = (wynik/array.length) - pow((suma/array.length), 2);
        return wynik;
    }

    public static int [] generate_missing_values(int number_of_objects) {
        int [] array = new int[(int)(0.1*number_of_objects)];
        for(int i = 0; i < array.length; ++i) {
            int value = (int) (Math.random()*number_of_objects);
            boolean unique = true;
            for(int j = 0; j < i; ++j)
                if (array[j] == value) {
                    unique = false;
                    break;
                }
            if(unique)
                array[i] = value;
            else
                --i;
        }
        return array;
    }

    public static String most_popular(String [] array) {
        HashMap<String, Integer> counter = new HashMap<>();
        for(String i : array)
            if(!i.equals("?"))
                if(counter.get(i) == null)
                    counter.put(i, 0);
                else
                    counter.replace(i, counter.get(i)+1);
        int most_popular_number_of_appearances = 0;
        String most_popular_value = "";
        for(String i : counter.keySet())
            if(counter.get(i) > most_popular_number_of_appearances)
                most_popular_value = i;
        return most_popular_value;
    }
}
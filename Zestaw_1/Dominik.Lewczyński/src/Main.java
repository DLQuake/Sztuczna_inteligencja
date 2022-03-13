import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Math.pow;
import static java.util.stream.Collectors.toCollection;

public class Main {

    public static <T> String TablicaDoString(T[][] tab){
        StringBuilder wynik = new StringBuilder();
        for (T[] ts : tab) {
            for (T t : ts) {
                wynik.append(t);
                wynik.append(" \n");
            }
        }
        return(wynik.toString());
    }

    private static Double StringToDouble(String liczba){
        liczba = liczba.trim();
        return Double.parseDouble(liczba);
    }

    private static Integer StringToInt(String liczba){
        liczba = liczba.trim();
        return Integer.parseInt(liczba);
    }

    public static String[][] StringToTablica(String sciezkaDoPliku) throws FileNotFoundException {
        StringBuilder wynik = new StringBuilder();

        File trescPliku = new File(sciezkaDoPliku);
        InputStream celowaSciezka = new FileInputStream(trescPliku);

        try{
            Reader r = new InputStreamReader(celowaSciezka, StandardCharsets.UTF_8);
            int c;
            while((c = r.read()) != -1){
                wynik.append((char) c);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        String[] wiersze = wynik.toString().trim().split("\n");
        String[][] wczytaneDane = new String[wiersze.length][];
        for(int i = 0; i < wiersze.length; i++){
            String wiersz = wiersze[i].trim();
            String[] cyfry = wiersz.split(" ");
            wczytaneDane[i] = new String[cyfry.length];
            for(int j = 0; j < cyfry.length; j++){
                String cyfra = cyfry[j].trim();
                wczytaneDane[i][j] = cyfra;
            }
        }
        return wczytaneDane;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String nazwaPlikuZDanymi = "dane/australian.txt";
        String nazwaPlikuZTypamiAtrybutow = "dane/australian-type.txt";

        String[][] wczytaneDane = StringToTablica(nazwaPlikuZDanymi);
        String[][] atrType = StringToTablica(nazwaPlikuZTypamiAtrybutow);

        System.out.println("Dane systemu: ");

        String wynik = TablicaDoString(wczytaneDane);

        System.out.print(wynik);
        System.out.println();
        System.out.println("Data from file with types: ");

        String wynikAtrType = TablicaDoString(atrType);
        System.out.print(wynikAtrType);

        //****************** Miejsce na rozwiązanie *********************************//

        // Zadanie 3

        //a)
        System.out.println("Zadanie 3");
        System.out.println("a)");
        System.out.println("Klasy decyzyjne");
        int liczba_obiektow = wczytaneDane.length;
        int liczba_atrybutow = atrType.length;

        String[] decyzje = new String[liczba_obiektow];
        for (int i = 0; i < liczba_obiektow; i++) {
            decyzje[i] = wczytaneDane[i][liczba_atrybutow];
        }
        Object[] klasyDecyzyjne = Arrays.stream(decyzje).distinct().toArray();
        for (Object i : klasyDecyzyjne) {
            System.out.println(i);
        }

        System.out.println();

        // b)
        System.out.println("b)");
        System.out.println("liczba obiektów w klasach decyzyjnych");
        HashMap<Object, Integer> liczba_obiektow_w_klasach = new HashMap<>();
        for (Object i : klasyDecyzyjne) {
            int suma = 0;
            for (int j = 0; j < liczba_obiektow; ++j) {
                if (decyzje[j].equals(i)) {
                    ++suma;
                }
            }
            liczba_obiektow_w_klasach.put(i, suma);
        }
        for (Object i : klasyDecyzyjne) {
            System.out.println(i + " - " + liczba_obiektow_w_klasach.get(i));
        }

        System.out.println();

        //c)
        System.out.println("c)");
        System.out.println("Minimalne i maksymalne wartości poszczególnych atrybutów");
        double min = 999999;
        double max = -999999;
        for(int i = 0; i < atrType.length; i++){
            for(int j = 0; j < wczytaneDane.length-1; j++){
                try{
                    if(StringToInt(wczytaneDane[j][i]) > max) {
                        max = StringToInt(wczytaneDane[j][i]);
                    }
                    if(StringToInt(wczytaneDane[j][i]) < min) {
                        min = StringToInt(wczytaneDane[j][i]);
                    }
                } catch(NumberFormatException e) {
                    if(StringToDouble(wczytaneDane[j][i]) > max) {
                        max = StringToDouble(wczytaneDane[j][i]);
                    }
                    if(StringToDouble(wczytaneDane[j][i]) < min) {
                        min = StringToDouble(wczytaneDane[j][i]);
                    }
                } catch(Exception e) {
                    System.out.println("EROOR" + e);
                }
            }
            System.out.println("Maksymalna wartosc w " + atrType[i][0] + " wynosi: " + max);
            System.out.println("Minimalna wartosc w " + atrType[i][0] + " wynosi: " + min);
            min = 999999;
            max = -999999;
        }

        System.out.println();

        // d)
        System.out.println("d)");
        System.out.println("Dla każdego atrybutu wypisujemy liczbę różnych dostępnych wartości");
        HashSet<String> pom = new HashSet<>();
        for(int i = 0; i < atrType.length; i++) {
            for (int j = 0; j < wczytaneDane.length - 1; j++) {
                pom.add(wczytaneDane[j][i]);
            }
            System.out.println(atrType[i][0] + " - " + pom.size());
            pom.clear();
        }

        System.out.println();

        // e)
        System.out.println("e)");
        System.out.println("Dla każdego atrybutu wypisujemy listę wszystkich różnych dostępnych wartości");
        HashSet<String> pom1 = new HashSet<>();
        for(int i = 0; i < atrType.length; i++) {
            for (int j = 0; j < wczytaneDane.length - 1; j++) {
                pom1.add(wczytaneDane[j][i]);
            }
            System.out.println(atrType[i][0] + " - " + pom1);
            pom1.clear();
        }

        System.out.println();

        // f)
        String numeric = "n";
        String[] nazwy_atrybutow = new String[liczba_atrybutow];
        String[] typy_atrybutow = new String[liczba_atrybutow];
        for (int i = 0; i < liczba_atrybutow; ++i) {
            nazwy_atrybutow[i] = atrType[i][0];
            typy_atrybutow[i] = atrType[i][1];
        }

        String[][] atrybuty = new String[liczba_obiektow][liczba_atrybutow];
        for (int i = 0; i < liczba_obiektow; ++i) {
            String[] line = wczytaneDane[i];
            atrybuty[i] = Arrays.copyOfRange(line, 0, liczba_atrybutow);
        }

        ArrayList<Integer> indeks_atrybutow_liczbowych = new ArrayList<>();
        for (int i = 0; i < liczba_atrybutow; ++i)
            if (typy_atrybutow[i].equals(numeric)) {
                indeks_atrybutow_liczbowych.add(i);
            }
        double[][] atrybuty_typu_numeric = new double[liczba_obiektow][indeks_atrybutow_liczbowych.size()];
        for (int i = 0; i < liczba_obiektow; ++i)
            for (int j = 0; j < indeks_atrybutow_liczbowych.size(); ++j)
                atrybuty_typu_numeric[i][j] = Double.parseDouble(atrybuty[i][indeks_atrybutow_liczbowych.get(j)]);

        AtomicReference<ArrayList<ArrayList<String>>> different_available_values = new AtomicReference<>(new ArrayList<>());
        for (int i = 0; i < liczba_atrybutow; ++i) {
            String[] atrybut = new String[liczba_obiektow];
            for (int j = 0; j < liczba_obiektow; ++j)
                atrybut[j] = atrybuty[j][i];
            different_available_values.get().add(Arrays.stream(atrybut).distinct().collect(toCollection(ArrayList::new)));
        }
        System.out.println("f");
        System.out.println("Odchylenie standardowe dla poszczególnych atrybutów w całym systemie");
        for (int i = 0; i < atrybuty_typu_numeric[0].length; ++i) {
            double[] tablica = new double[liczba_obiektow];
            for (int j = 0; j < liczba_obiektow; ++j)
                tablica[j] = atrybuty_typu_numeric[j][i];
            System.out.println(nazwy_atrybutow[indeks_atrybutow_liczbowych.get(i)] + " - " + odchylenie_standardowe(tablica));
        }
        System.out.println("Odchylenie standardowe dla poszczególnych atrybutów w klasach decyzyjnych");
        for (Object d : klasyDecyzyjne) {
            System.out.println(d);
            for (int i = 0; i < atrybuty_typu_numeric[0].length; ++i) {
                double[] tablica = new double[liczba_obiektow_w_klasach.get(d)];
                int index = 0;
                for (int j = 0; j < liczba_obiektow; ++j)
                    if (decyzje[j].equals(d)) {
                        tablica[index++] = atrybuty_typu_numeric[j][i];
                    }
                System.out.println(nazwy_atrybutow[indeks_atrybutow_liczbowych.get(i)] + " - " + odchylenie_standardowe(tablica));
            }
        }

        System.out.println();

        // Zadanie4
        // a)
        System.out.println("Zadanie 4");
        System.out.println("a)");
        for (int i = 0; i < atrybuty_typu_numeric[0].length; ++i) {
            int[] array = generowanie_brakujacych_wartosci(liczba_obiektow);
            Arrays.sort(array);
            for (int j : array)
                atrybuty[j][indeks_atrybutow_liczbowych.get(i)] = "?";
        }

        for (int i = 0; i < atrybuty_typu_numeric[0].length; ++i) {
            String[] wynik1 = new String[liczba_obiektow];
            for (int j = 0; j < liczba_obiektow; ++j) {
                wynik1[j] = atrybuty[j][indeks_atrybutow_liczbowych.get(i)];
            }
            System.out.println(nazwy_atrybutow[indeks_atrybutow_liczbowych.get(i)] + " - " + najbardziej_popularny(wynik1));
        }

        System.out.println();
        // b)
        System.out.println("b)");

        System.out.println();
        // c)
        System.out.println("c)");

        System.out.println();
        // d)
        System.out.println("d)");

        //****************** Koniec miejsca na rozwiązanie ********************************//
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

    public static int [] generowanie_brakujacych_wartosci(int numer_objektu) {
        int [] tab = new int[(int)(0.1*numer_objektu)];
        for(int i = 0; i < tab.length; ++i) {
            int value = (int) (Math.random()*numer_objektu);
            boolean unique = true;
            for(int j = 0; j < i; ++j)
                if (tab[j] == value) {
                    unique = false;
                    break;
                }
            if(unique)
                tab[i] = value;
            else
                --i;
        }
        return tab;
    }

    public static String najbardziej_popularny(String [] tab) {
        HashMap<String, Integer> pom = new HashMap<>();
        for(String i : tab)
            if(!i.equals("?"))
                if(pom.get(i) == null)
                    pom.put(i, 0);
                else
                    pom.replace(i, pom.get(i)+1);
        int najpopularniejsza_liczba_wystapienia = 0;
        String najbardziej_popularna_wartosc = "";
        for(String i : pom.keySet())
            if(pom.get(i) > najpopularniejsza_liczba_wystapienia)
                najbardziej_popularna_wartosc = i;
        return najbardziej_popularna_wartosc;
    }

}

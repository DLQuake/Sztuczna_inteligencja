import java.util.*;

class Graph {
    private final Map<Wierzcholek, List<Wierzcholek>> wierzcholki;

    public Graph() {
        this.wierzcholki = new HashMap<>();
    }

    public void dodaj_krawedz(String wartosc, Boolean czy_brudne, String wartosc_sasiada, Boolean czy_brudne_sasiad){
        Wierzcholek v = new Wierzcholek(wartosc, czy_brudne);
        Wierzcholek neighbor = new Wierzcholek(wartosc_sasiada, czy_brudne_sasiad);
        wierzcholki.put(v, new ArrayList<>());
        wierzcholki.get(v).add(neighbor);
    }

    public List<Wierzcholek> getwierzcholki(String label, Boolean czy_brudne){
        return wierzcholki.get(new Wierzcholek(label, czy_brudne));
    }

    class Wierzcholek {
        public String wartosc;
        public boolean czy_brudne;

        Wierzcholek(String wartosc, Boolean czy_brudne){
            this.czy_brudne = czy_brudne;
            this.wartosc = wartosc;
        }

        public int hashCode() {
            final int prime = 31;
            int wynik = 1;
            wynik = prime * wynik + getOuterType().hashCode();
            wynik = prime * wynik + ((wartosc == null) ? 0 : wartosc.hashCode());
            return wynik;
        }

        public boolean equals(Object obiekt) {
            if (this == obiekt)
                return true;
            if (obiekt == null)
                return false;
            if (getClass() != obiekt.getClass())
                return false;
            Wierzcholek pom = (Wierzcholek) obiekt;
            if (!getOuterType().equals(pom.getOuterType()))
                return false;
            if (wartosc == null) {
                return pom.wartosc == null;
            } else return wartosc.equals(pom.wartosc);
        }

        public String toString() {
            return wartosc;
        }

        private Graph getOuterType() {
            return Graph.this;
        }
    }
}

class Zadanie_1 {
    private class StanOdkurzacza{
        StanOdkurzacza rodzic;
        boolean[] lokalizacja;
        boolean[] czy_brudne;
        String akcja;

        StanOdkurzacza(StanOdkurzacza rodzic, int pozycja, boolean[] stanPokoi, String akcja){
            this.rodzic = rodzic;
            lokalizacja = new boolean[liczbaPokoi];
            czy_brudne = stanPokoi;
            this.akcja = akcja;

            Arrays.fill(lokalizacja, false);

            lokalizacja[pozycja] = true;
        }

        StanOdkurzacza(int pozycja){
            rodzic = null;
            lokalizacja = new boolean[liczbaPokoi];
            czy_brudne = new boolean[liczbaPokoi];
            akcja = null;

            Arrays.fill(lokalizacja, false);
            Arrays.fill(czy_brudne, true);

            lokalizacja[pozycja] = true;
        }
    }
    
    private final int liczbaPokoi;
    StanOdkurzacza poczatek;
    List<StanOdkurzacza> odwiedzone;
    List<String> akcje;

    public void wypiszAkcje(){
        if(akcje.isEmpty()) System.out.println("Nie udalo sie wyczyscic wszystkich pokoi");
        else{
            for(String akcja: akcje){
                System.out.println(akcja);
            }
        }
    }

    private StanOdkurzacza ruszaj_w_lewo(StanOdkurzacza wezel){
        int i = 0;
        while(!wezel.lokalizacja[i]) i++;
        i--;
        return new StanOdkurzacza(wezel, i, wezel.czy_brudne, "Przejdz w lewo do pokoju "+i);
    }

    private StanOdkurzacza ruszaj_w_prawo(StanOdkurzacza wezel){
        int i = 0;
        while(!wezel.lokalizacja[i]) i++;
        i++;
        return new StanOdkurzacza(wezel, i, wezel.czy_brudne, "Przejdz w prawo do pokoju "+i);
    }

    private StanOdkurzacza ssac(StanOdkurzacza wezel){
        int i = 0;
        while(!wezel.lokalizacja[i]) i++;
        boolean[] kopia = Arrays.copyOf(wezel.czy_brudne, liczbaPokoi);
        kopia[i] = false;
        return new StanOdkurzacza(wezel, i, kopia, "Pokoj "+i+": ssanie");
    }

    private void Breadth_First_Search(){
        Queue<StanOdkurzacza> Q = new LinkedList<>();
        StanOdkurzacza wezel;

        Q.add(poczatek);

        while(!Q.isEmpty() && !czyCzysto(Q.peek().czy_brudne)){
            wezel = Q.remove();

            if(!wezel.lokalizacja[0]){
                StanOdkurzacza nowyWezel = ruszaj_w_lewo(wezel);
                odwiedzone.add(nowyWezel);
                Q.add(nowyWezel);
            }
            if(!wezel.lokalizacja[liczbaPokoi - 1]){
                StanOdkurzacza nowyWezel = ruszaj_w_prawo(wezel);
                odwiedzone.add(nowyWezel);
                Q.add(nowyWezel);
            }
            if(wezel.czy_brudne[nrpokoju_gdzie_jest_odkurzacz(wezel.lokalizacja)]){
                StanOdkurzacza nowyWezel = ssac(wezel);
                odwiedzone.add(nowyWezel);
                Q.add(nowyWezel);
            }
        }
        akcje = new ArrayList<>();
        if(!Q.isEmpty()){
            wezel = Q.remove();

            while(wezel != poczatek){
                akcje.add(0, wezel.akcja);
                wezel=wezel.rodzic;
            }
        }
    }

    private boolean czyCzysto(boolean[] pokoje){
        for(boolean pokoj: pokoje)
            if(pokoj) return false;
        return true;
    }

    private int nrpokoju_gdzie_jest_odkurzacz(boolean[] pokoje){
        for(int i = 0; i < pokoje.length; i++)
            if(pokoje[i]) return i;
        return 1001;
    }

    Zadanie_1(int liczbaPokoi, int start){
        this.liczbaPokoi = liczbaPokoi;
        odwiedzone = new ArrayList<>();
        poczatek = new StanOdkurzacza(start);
        odwiedzone.add(poczatek);

        Breadth_First_Search();
    }
}

class Zadanie_2 {
    public List<String> algorytm_przeszukiwania_astar(Graph graph, String poczatek, String koniec) {
        Map<String, Double> punkt_g = new HashMap<>();
        Map<String, Double> punkt_f = new HashMap<>();
        Map<String, String> idz_z = new HashMap<>();
        PriorityQueue<String> rozpoczecie = new PriorityQueue<>();
        Set<String> zakonczenie = new HashSet<>();

        punkt_g.put(poczatek, 0.0);
        punkt_f.put(poczatek, fumkcja_heurystyczna(poczatek, koniec));
        rozpoczecie.add(poczatek);

        while (!rozpoczecie.isEmpty()) {
            String obecny_stan = rozpoczecie.poll();
            if (obecny_stan.equals(koniec)) {
                return rekonstrukcja_sciezki(idz_z, obecny_stan);
            }
            zakonczenie.add(obecny_stan);
            for (Graph.Wierzcholek v : graph.getwierzcholki(obecny_stan, true)) {
                if (zakonczenie.contains(v.wartosc)) {
                    continue;
                }
                double wstepny_wynik_punktu_g = punkt_g.get(obecny_stan) + 1; // 1 is the cost of moving to a neighbor
                if (!rozpoczecie.contains(v.wartosc) || wstepny_wynik_punktu_g < punkt_g.get(v.wartosc)) {
                    rozpoczecie.add(v.wartosc);
                } else if (wstepny_wynik_punktu_g >= punkt_g.get(v.wartosc)) {
                    continue;
                }
                idz_z.put(v.wartosc, obecny_stan);
                punkt_g.put(v.wartosc, wstepny_wynik_punktu_g);
                punkt_f.put(v.wartosc, punkt_g.get(v.wartosc) + fumkcja_heurystyczna(v.wartosc, koniec));
            }
        }
        return null;
    }

    private List<String> rekonstrukcja_sciezki(Map<String, String> idz_z, String obecny_stan) {
        List<String> sciezka = new ArrayList<>();
        while (idz_z.containsKey(obecny_stan)) {
            sciezka.add(obecny_stan);
            obecny_stan = idz_z.get(obecny_stan);
        }
        return sciezka;
    }

    private double fumkcja_heurystyczna(String obecny_stan, String koniec) {
        System.out.println("Sprawdzenie kosztu z " + obecny_stan + " do " + koniec);
        return Math.abs(obecny_stan.charAt(0) - koniec.charAt(0)) + Math.abs(obecny_stan.charAt(1) - koniec.charAt(1));
    }
}

class Zadanie_3 {
    public static int MAX(int[] tab){
        int max = tab[0];
        for(int i = 1; i < tab.length; i++)
            if(tab[i] > max) max = tab[i];
        return max;
    }
}

public class Main {
    public static void main(String[] args){

        System.out.println("Zadanie 1");
        Zadanie_1 o1 = new Zadanie_1(2, 1);
        o1.wypiszAkcje();

        System.out.println("\n\n");

        System.out.println("Zadanie 2");
        Graph graph = new Graph();

        graph.dodaj_krawedz("v1", true, "v2", true);
        graph.dodaj_krawedz("v1", true, "v3", true);
        graph.dodaj_krawedz("v3", true, "v4", true);
        graph.dodaj_krawedz("v2", true, "v6", true);
        graph.dodaj_krawedz("v6", true, "v5", true);
        graph.dodaj_krawedz("v5", true, "v7", true);
        graph.dodaj_krawedz("v4", true, "v8", true);
        graph.dodaj_krawedz("v8", true, "v8", true);

        List<String> path = new Zadanie_2().algorytm_przeszukiwania_astar(graph, "v1", "v8");
        System.out.println("Path: " + path);

        System.out.println("\n\n");

        System.out.println("Zadanie 3");

        int[] tab = {1,2,3,4,5,6,7,8,9,10};
        System.out.println("MAX: " + Zadanie_3.MAX(tab));
    }
}
import java.lang.Math;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class Main {

    public static void main(String[] args) {
        System.out.println("Zadanie 1");
        int iloscZmiennych = 3;
        int numerMozliwegoPrzypisnia = (int) Math.pow(2, iloscZmiennych);
        boolean[][] tab = new boolean[numerMozliwegoPrzypisnia][iloscZmiennych];
        for (int i = iloscZmiennych - 1; i >= 0; --i) {
            int pom = ((int) Math.pow(2, iloscZmiennych - i));
            for (int j = 0; j < numerMozliwegoPrzypisnia; ++j) {
                tab[j][i] = (j % pom) >= (pom / 2);
            }
        }
        String[] nazwyWartosci = {"p", "q", "r"};
        // ¬, ∧, ∨, ⇒, ⇔, (, )
        String S = "p ∧ (q ∨ r)";
        for (String i : nazwyWartosci)
            System.out.print(" " + i + " │");
        System.out.println(" " + S);
        for (boolean[] wartoscLogiczna : tab) {
            HashMap<String, Integer> m = new HashMap<>();
            for (int j = 0; j < tab[0].length; ++j) {
                if (wartoscLogiczna[j]) {
                    System.out.print(" 1 │");
                    m.put(nazwyWartosci[j], 1);
                } else {
                    System.out.print(" 0 │");
                    m.put(nazwyWartosci[j], 0);
                }
            }
            for (int j = 0; j < S.length() / 2; ++j)
                System.out.print(" ");
            if (PLTRUE(S, m))
                System.out.println(1);
            else
                System.out.println(0);
        }
    }

    public static boolean PLTRUE(String S, HashMap<String, Integer> m) {
        if (S.contains("(")) {
            while (S.contains("(")) {
                int indeksNawiasuZamknietego = S.indexOf(")");
                Integer indeksNawaisuOtwartego = null;
                for (int i = indeksNawiasuZamknietego; i >= 0 && indeksNawaisuOtwartego == null; --i)
                    if (("" + S.charAt(i)).equals("(")) {
                        indeksNawaisuOtwartego = i;
                    }
                String bezNawiasu = S.substring(indeksNawaisuOtwartego + 1, indeksNawiasuZamknietego);
                if (PLTRUE(bezNawiasu, m))
                    S = S.substring(0, indeksNawaisuOtwartego) + "1" + S.substring(indeksNawiasuZamknietego + 1);
                else
                    S = S.substring(0, indeksNawaisuOtwartego) + "0" + S.substring(indeksNawiasuZamknietego + 1);
            }
        }
        String[] operatoryLogiczne = {"∧", "∨", "⇒", "⇔"};
        S = S.replace(" ", "");
        for (String i : m.keySet()) {
            S = S.replace(i, m.get(i).toString());
        }
        while (S.contains("¬")) {
            int indeks = S.indexOf("¬");
            String wartosc = "" + S.charAt(indeks + 1);
            if (wartosc.equals("1"))
                S = S.substring(0, indeks) + "0" + S.substring(indeks + 2);
            else
                S = S.substring(0, indeks) + "1" + S.substring(indeks + 2);
        }
        for (String operator : operatoryLogiczne) {
            while (S.contains(operator)) {
                int index = S.indexOf(operator);
                String pierwszyArgument = "" + S.charAt(index - 1);
                String drugiArgument = "" + S.charAt(index + 1);
                if (operator.equals("∧")) {
                    if (pierwszyArgument.equals("1") && drugiArgument.equals("1"))
                        S = S.substring(0, index - 1) + "1" + S.substring(index + 2);
                    else
                        S = S.substring(0, index - 1) + "0" + S.substring(index + 2);
                }
                if (operator.equals("∨")) {
                    if (pierwszyArgument.equals("0") && drugiArgument.equals("0"))
                        S = S.substring(0, index - 1) + "0" + S.substring(index + 2);
                    else
                        S = S.substring(0, index - 1) + "1" + S.substring(index + 2);
                }
                if (operator.equals("⇒")) {
                    if (pierwszyArgument.equals("1") && drugiArgument.equals("0"))
                        S = S.substring(0, index - 1) + "0" + S.substring(index + 2);
                    else
                        S = S.substring(0, index - 1) + "1" + S.substring(index + 2);
                }
                if (operator.equals("⇔")) {
                    if (pierwszyArgument.equals(drugiArgument))
                        S = S.substring(0, index - 1) + "1" + S.substring(index + 2);
                    else
                        S = S.substring(0, index - 1) + "0" + S.substring(index + 2);
                }
            }
        }
        return S.equals("1");
    }
}
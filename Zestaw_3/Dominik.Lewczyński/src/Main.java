import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Zad 1");

        Variable v1 = new Variable("v1");
        Variable v2 = new Variable("v2");
        Variable v3 = new Variable("v3");
        Domain X1 = new Domain(new ArrayList<String>() {{
            add("R");
            add("B");
            add("G");
        }});
        Domain X2 = new Domain(new ArrayList<String>() {{
            add("R");
        }});
        Domain X3 = new Domain(new ArrayList<String>() {{
            add("G");
        }});

        ArrayList<Variable> variables = new ArrayList<>();
        variables.add(v1);
        variables.add(v2);
        variables.add(v3);

        CSP csp = new CSP(variables);
        csp.setDomain(v1, X1);
        csp.setDomain(v2, X1);
        csp.setDomain(v3, X1);
        csp.setDomain(v1, X2);
        csp.setDomain(v2, X3);

        System.out.println("X1: " + csp.getDomain(v3));
        System.out.println("X2: " + csp.getDomain(v1));
        System.out.println("X3: " + csp.getDomain(v2));


        System.out.println();
        System.out.println("Zad 2");
        String systemDecyzyjny = "doZadania2.txt";

        String[][] sysdec = StringToTablica(systemDecyzyjny);

        int xSize = sysdec[0].length;
        int ySize = sysdec.length;


        int[][] atrybuty = new int[ySize][xSize - 1];
        int[] decyzje = new int[ySize];

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize - 1; j++) {
                atrybuty[i][j] = Integer.parseInt(sysdec[i][j]);
            }
            decyzje[i] = Integer.parseInt(sysdec[i][xSize - 1]);
        }

        System.out.println("System decyzyjny");
        for (int[] row : atrybuty) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Reguly:");
        sequentialCovering(atrybuty, decyzje);
    }

    public static String[][] StringToTablica(String sciezkaDoPliku) throws IOException {
        String trescPliku = Files.readString(Path.of(sciezkaDoPliku));
        String[] wiersze = trescPliku.trim().split("\n");
        String[][] wczytaneDane = new String[wiersze.length][];

        for (int i = 0; i < wiersze.length; i++) {
            String wiersz = wiersze[i].trim();
            String[] cyfry = wiersz.split(" ");
            wczytaneDane[i] = new String[cyfry.length];

            for (int j = 0; j < cyfry.length; j++) {
                String cyfra = cyfry[j].trim();
                wczytaneDane[i][j] = cyfra;
            }
        }
        return wczytaneDane;
    }

    public static void sequentialCovering(int[][] atrybuty, int[] decyzje) {
        int ySize = atrybuty.length;

        int numberOfAttributes = atrybuty[0].length;
        ArrayList<int[]> kombinacje = new ArrayList<>();
        ArrayList<int[]> iElementsFromN;
        for (int i = 1; i <= numberOfAttributes; ++i) {
            iElementsFromN = generate_Combinations(numberOfAttributes, i);
            kombinacje.addAll(iElementsFromN);
        }

        ArrayList<Integer> rozwazania = new ArrayList<>();
        for (int i = 0; i < ySize; ++i)
            rozwazania.add(i);

        for (int kombinacje_iterator = 0; kombinacje_iterator < kombinacje.size() && rozwazania.size() > 0; ++kombinacje_iterator) {
            for (int i = 0; i < rozwazania.size(); ++i) {
                boolean poprawny = true;
                int obj = rozwazania.get(i);
                for (int j = 0; j < ySize; ++j) {
                    boolean wartosci_tych_samych_atrybutow = true;
                    for (int k : kombinacje.get(kombinacje_iterator))
                        if (atrybuty[obj][k] != atrybuty[j][k]) {
                            wartosci_tych_samych_atrybutow = false;
                            break;
                        }
                    if (wartosci_tych_samych_atrybutow)
                        if (decyzje[obj] != decyzje[j])
                            poprawny = false;
                }
                if (poprawny) {
                    System.out.print("o" + (obj + 1) + ": ");
                    for (int j = 0; j < kombinacje.get(kombinacje_iterator).length; ++j) {
                        int attribute = kombinacje.get(kombinacje_iterator)[j];
                        System.out.print("(a" + (attribute + 1) + " = " + atrybuty[obj][attribute] + ") ");
                        if (j < kombinacje.get(kombinacje_iterator).length - 1)
                            System.out.print("AND ");
                    }
                    System.out.print("==> d = " + decyzje[obj]);
                    System.out.print(" wyrzucamy z rozwazan");
                    ArrayList<Integer> usuniecie_z_rozwazania = new ArrayList<>();
                    for (int j = 0; j < ySize; ++j) {
                        boolean wartosci_tych_samych_atrybutow = true;
                        for (int k : kombinacje.get(kombinacje_iterator))
                            if (atrybuty[obj][k] != atrybuty[j][k]) {
                                wartosci_tych_samych_atrybutow = false;
                                break;
                            }
                        if (wartosci_tych_samych_atrybutow)
                            usuniecie_z_rozwazania.add(j);
                    }
                    if (usuniecie_z_rozwazania.size() > 1)
                        System.out.print(" obiekty: ");
                    else
                        System.out.print(" obiekt: ");
                    for (int j : usuniecie_z_rozwazania)
                        System.out.print("o" + (j + 1) + " ");
                    System.out.println();
                    for (int k = usuniecie_z_rozwazania.size() - 1; k >= 0; --k) {
                        rozwazania.remove(usuniecie_z_rozwazania.get(k));
                    }
                    if (usuniecie_z_rozwazania.size() > 0) {
                        i = -1;
                    }
                }
            }
        }
    }

    public static ArrayList<int[]> generate_Combinations(int n, int r) {
        int[] data = new int[r];
        int start = 0;
        int end = n - 1;
        int index = 0;

        ArrayList<int[]> combinations = new ArrayList<>();
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            index++;
            POM_generate_Combinations(start + 1, end, index, data, combinations);
            index--;
            data[index] = end;
            index++;
            POM_generate_Combinations(start, end - 1, index, data, combinations);
        }
        return combinations;
    }

    public static void POM_generate_Combinations(int start, int end, int index, int[] data, ArrayList<int[]> combinations) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            index++;
            POM_generate_Combinations(start + 1, end, index, data, combinations);
            index--;
            data[index] = end;
            index++;
            POM_generate_Combinations(start, end - 1, index, data, combinations);
        }
    }
}
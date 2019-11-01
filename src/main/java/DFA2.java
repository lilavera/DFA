import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DFA2 {

    String line;
    int count = 0;
    int width = 0;
    final List<String> lines = new ArrayList<String>();
    List<List<String>> listOfStates = new ArrayList<List<String>>();
    int numberOfColumns;
    int stateposition = 0;
    File stateMatrix2 = new File("C:/Users/lenovo/Desktop/fsm1.txt");
    String[][] matrixOfState;
    BufferedReader abc = new BufferedReader(new FileReader(stateMatrix2));

    public DFA2() throws FileNotFoundException {
    }

    public static int charToColumn(char symbol){
        int column = 2;
        switch (symbol){
            case 'a':
                column = 0;
                break;
            case 'b':
                column = 1;
                break;
            case 'c':
                column = 2;
                break;

        }
        return column;
    }

    public void readMatrix() throws IOException {
        while ((line = abc.readLine()) != null) {

            String[] linePieces = line.split(" ");
            List<String> csvPieces = new ArrayList<String>(linePieces.length);
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != ' ')
                    count++;
            }
            for (String piece : linePieces) {
                csvPieces.add(piece);
            }
            listOfStates.add(csvPieces);
        }
        numberOfColumns = count / listOfStates.size();
        matrixOfState = new String[listOfStates.size()][numberOfColumns];
        for (List<String> nestedList : listOfStates) {
            matrixOfState[width++] = nestedList.toArray(new String[nestedList.size()]);
        }
    }

    public void runMachine() throws IOException {
        System.out.println("Enter your word : ");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        readMatrix();
        for (int index = 0; index < word.length(); index++) {
            char symbol = word.charAt(index);
            stateposition = Integer.parseInt(matrixOfState[stateposition][charToColumn(symbol)]);
        }
        if (stateposition == 4 || stateposition == 5 || stateposition == 6) {
            System.out.println("Accept");
        } else {
            System.out.println("Reject");
        }
    }
}

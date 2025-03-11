package generic;

import basicgraphics.FileUtility;
import overWorld.TileHandler;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvHandler {
    private final String DATA_FILE;
    //private static final String[] DATA_HEADER = {"player, bob"};
    // arrays are nasty, just do a string



    public static String[][] getData(String fileName, TileHandler tileHandler) {
        String[][] data = new String[tileHandler.getUsableGridHeight()][tileHandler.getUsableGridWidth()];
        try {
            //FileReader fr = new FileReader(fileName);
            URI src = FileUtility.findFile(fileName);
            if (src == null) {throw new NullPointerException("File not found: " + fileName);}
            BufferedReader br = new BufferedReader(new InputStreamReader(src.toURL().openStream()));

            String line = br.readLine();
            int y = 0;
            while (line != null) {
                List<String> lineData = Arrays.asList(line.split(","));
                if (y<tileHandler.getUsableGridHeight()) {
                    for (int i = 0; i < tileHandler.getUsableGridWidth(); i++) {
                        data[y][i] = lineData.get(i);
                        System.out.println(data[y][i]);
                    }
                    y++;
                    line = br.readLine();
                }
                for (int i = 0; i < tileHandler.getUsableGridHeight(); i++) {
                    for (int j = 0; j < tileHandler.getUsableGridWidth(); j++) {
                        System.out.print(data[i][j] + " ");
                    }
                    System.out.println();
                }

            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
    private int columns = 2;

    public CsvHandler(String filename) throws IOException {
       DATA_FILE = filename;
        createFileIfMissing();
    }

    private void createFileIfMissing() throws IOException {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println("uh, bob");
            }
        }

    }

    public String getCell(int row, int col) throws IOException {
        if (col<0||col>=columns){
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            reader.readLine();
            for (int i = 0; i < row; i++) {
                reader.readLine();
            }

            String line = reader.readLine();
            if (line==null){return null;}

            String[] parts = line.split(",");
            if (parts.length>col){
                return parts[col].trim();
            }
            return null;
        }
    }

    public void setCell(int row, int col, String cell) throws IOException {
        if (col<0||col>=columns){
            throw new IndexOutOfBoundsException("Hey! bad idea. cant setcell with those cols");
        }


        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //really proud of this line of code :) remember from learning C!!
                lines.add(line);
            }
        }

        int targetRow = row + 1;
        if (targetRow>=lines.size()){
            throw new IndexOutOfBoundsException("Hey! bad idea. cant setcell with those rows");

        }

        String[] columns = lines.get(targetRow).split(",",-1); //allegedly preserves empty fields
        columns[col] = cell;
        lines.set(targetRow, String.join(",",columns));

        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (String line : lines){
                writer.println(line);
            }
        }



    }


    //readwrite checker



    public static void main(String[] args) {
        try {
            CsvHandler handler = new CsvHandler("data.csv");

            String bob = handler.getCell(0, 1);
            System.out.println(bob);


            handler.setCell(0,1, "susan");
            System.out.println(bob);


            //Get cell at row 1, col 2
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }




}

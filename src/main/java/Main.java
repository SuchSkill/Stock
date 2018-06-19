import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Price> list = new ArrayList<>();

        readCSV(list);

//        for (Price price : list) {
//            System.out.println(price);
//        }
//        System.out.println(list.size());


        calcLinear(list, 0);
        calcLinear(list, 50);
        calcLinear(list, 100);
        calcLinear(list, 150);
        calcLinear(list, 200);

    }

    private static void calcLinear(List<Price> list, int in) {
        SimpleRegression regression = new SimpleRegression();
        for (int i = in; i < list.size(); i++) {
            regression.addData(i, list.get(i).getClose());
        }
        System.out.println("With " + (list.size()- in) +" data points back prediction is " + regression.predict(list.size()));
    }

    private static void readCSV(List<Price> list) {
        String csvFile = "src\\main\\java\\TSLA.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] row = line.split(cvsSplitBy);
                Price price = new Price(LocalDate.parse(row[0]), Float.parseFloat(row[1]), Float.parseFloat(row[2]), Float.parseFloat(row[3]), Float.parseFloat(row[4]));
                list.add(price);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

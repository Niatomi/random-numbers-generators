import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CSVLibrary {
    public void writeToCsv(double[] Array1,double[] Array2,double[] Array3, int iterations){
        String csv = "src/main/resources/data.csv";
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(csv));
            for (int i = 0; i < iterations; i++) {
                String buff1 = Double.toString(Array1[i]);
                String buff2 = Double.toString(Array2[i]);
                String buff3 = Double.toString(Array3[i]);
                String [] record = (i+","+buff1+","+buff2+","+buff3).split(",");
                writer.writeNext(record);
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package ro.tuc.tp.tema4.DataLayer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import ro.tuc.tp.tema4.BusinessLayer.BaseProduct;
import ro.tuc.tp.tema4.BusinessLayer.MenuItem;

/**
 * Citirea excelului
 */
public class ReadExcel {

    public static List<MenuItem> read(String inputFile) {
        List<MenuItem> products = new ArrayList<>();
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);

            Class cls = Class.forName("ro.tuc.tp.tema4.BusinessLayer.BaseProduct");
            Constructor cnstr = cls.getConstructor(String.class, Double.class, Integer.class,Integer.class, Integer.class, Integer.class, Integer.class);

            for (int i = 1; i < sheet.getRows(); i++) {
                Object[] rows = new Object[7];
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    rows[j] = cell.getContents();
                }
                Object obj = cnstr.newInstance((String) rows[0], Double.valueOf((String) rows[1]), Integer.valueOf((String) rows[2]), Integer.valueOf((String) rows[3]), Integer.valueOf((String) rows[4]), Integer.valueOf((String) rows[5]),Integer.valueOf((String) rows[6]));
                BaseProduct p = (BaseProduct) obj;
                boolean exists = products.stream().anyMatch(pr -> pr.getTitle().toLowerCase().equals(p.getTitle().toLowerCase()));
                if (!exists)
                    products.add(p);
            }
        } catch (BiffException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
        return products;
    }

}
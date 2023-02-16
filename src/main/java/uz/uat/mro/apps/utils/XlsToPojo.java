package uz.uat.mro.apps.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class XlsToPojo {

    public static void main(String[] args) throws IOException {

        FileInputStream excelFile = new FileInputStream(new File("data/data_file_name"));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = datatypeSheet.iterator();

        while (iterator.hasNext()) {

            Row currentRow = iterator.next();

            Iterator<Cell> cellIterator = currentRow.iterator();
            Cell uCell = currentRow.getCell(0);
            Cell countCell = currentRow.getCell(1);

            while (cellIterator.hasNext()) {
                // if(cellIterator.next()==)
            }

            while (cellIterator.hasNext()) {

                Cell currentCell = cellIterator.next();

                if (currentCell.getCellType() == CellType.STRING) {
                    System.out.print(currentCell.getStringCellValue() + "--");
                } else if (currentCell.getCellType() == CellType.NUMERIC) {
                    System.out.print(currentCell.getNumericCellValue() + "--");
                } else if (currentCell.getCellType() == CellType._NONE) { // проверка на пустое значение ячейки
                                                                          // таблицы xlsx файла
                    System.out.print("null" + "--"); // вывод null, если ячейка пустая
                }

            } // end of inner while loop

            System.out.println(); // end of row

        } // end of outer while loop

    } // end of main method

    public static class PojoEntity { // POJO entity class with fields from xls file
        private String itemNumber; // item_number field from xls file
        private String ammReference; // amm_reference field from xls file
        private String cat; // cat field from xls file
        private String pgm; // pgm field from xls file
        private String zone; // zone field from xls file
    }
}

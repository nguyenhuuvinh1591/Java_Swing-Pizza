/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelAndReport;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcel{
    public void ExportExcel(String name, JTable table) {
            TableModel tblData = table.getModel();
            FileOutputStream excelFOS = null;
            BufferedOutputStream excelBOS = null;
            XSSFWorkbook wb = null;
            JFileChooser excelFileChooser = new JFileChooser();
            excelFileChooser.setDialogTitle("Save As");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "ods");
            excelFileChooser.setFileFilter(fnef);
            int excelChooser = excelFileChooser.showSaveDialog(null);
            if (excelChooser == JFileChooser.APPROVE_OPTION) {
                try {
                    wb = new XSSFWorkbook();
                    XSSFSheet sheet = wb.createSheet(name);
                    XSSFRow row = sheet.createRow(0);
                    for (int c = 0; c < tblData.getColumnCount(); c++) {
                        XSSFCell cell = row.createCell(c);
                        cell.setCellValue(tblData.getColumnName(c));
                    }
                    for (int i = 0; i < tblData.getRowCount(); i++) {
                        XSSFRow excelRow = sheet.createRow(i + 1);
                        for (int j = 0; j < tblData.getColumnCount(); j++) {
                            XSSFCell excelCell = excelRow.createCell(j);
                            excelCell.setCellValue(tblData.getValueAt(i, j).toString());
                        }
                    }
                    excelFOS = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                    excelBOS = new BufferedOutputStream(excelFOS);
                    wb.write(excelBOS);
                    JOptionPane.showMessageDialog(null, "Successfully saved.");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (excelBOS != null) {
                            excelBOS.close();
                        }
                        if (excelFOS != null) {
                            excelFOS.close();
                        }
                        if (wb != null) {
                            wb.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
}
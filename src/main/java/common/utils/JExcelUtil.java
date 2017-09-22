package common.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * @author zouyang
 * @date 2017/9/21 19:47
 * @description 使用POI实现Java对excel的操作，HSSF-xls  XSSF-xlsx
 * 官方文档地址：http://poi.apache.org/spreadsheet/index.html
 */
public class JExcelUtil {
    public static void writeXlsByHSSF(String dirPath, String fileName, List<Map<String, Object>> list) {
        Workbook workbook = new HSSFWorkbook();
        if (dirPath == null || "".equals(dirPath)) {
            throw new IllegalArgumentException("文件存放地址不能为空");
        }
        if (fileName == null || "".equals(fileName)) {
            throw new IllegalArgumentException("文件名不能为空");
        }
        File file = new File(dirPath + "/" + fileName + ".xls");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeExcel(workbook, file, list);
    }

    public static void writeXlsxByXSSF(String dirPath, String fileName, List<Map<String, Object>> list) {
        Workbook workbook = new XSSFWorkbook();
        if (dirPath == null || "".equals(dirPath)) {
            throw new IllegalArgumentException("文件存放地址不能为空");
        }
        if (fileName == null || "".equals(fileName)) {
            throw new IllegalArgumentException("文件名不能为空");
        }
        File file = new File(dirPath + "/" + fileName + ".xlsx");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeExcel(workbook, file, list);
    }

    private static void writeExcel(Workbook workbook, File file, List<Map<String, Object>> list) {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            Sheet sheet = workbook.createSheet(file.getName());
            if (list != null && list.size() > 0) {
                //插入所有列名到表格中，作为第一行
                Row row = sheet.createRow(0);
                int j = 0;
                for (Map.Entry<String, Object> entry : list.get(0).entrySet()) {
                    Cell cell = row.createCell(j);
                    //设置列宽
                    sheet.setColumnWidth(j, 25 * 256);
                    //设置值
                    cell.setCellValue(entry.getKey());
                    j++;
                }
            }
            //插入所有的数据到表格中
            for (int i = 0; i < list.size(); i++) {
                //由于第一行为列名所以插入数据时要从第二行开始
                Row row = sheet.createRow(i + 1);
                int j = 0;
                for (Map.Entry<String, Object> entry : list.get(i).entrySet()) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(entry.getValue().toString());
                    j++;
                }
            }
            workbook.write(bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileOutputStream.close();
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("c1", "1234");
        map.put("c2", "zouyang");
        list.add(map);
        String dirPath = "C:\\Users\\TZ\\Desktop\\";
        String fileName = "testPoi";
        writeXlsByHSSF(dirPath, fileName, list);
        writeXlsxByXSSF(dirPath, fileName, list);
    }
}

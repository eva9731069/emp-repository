package com.example.demo;


import com.util.JdbcConnector;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@SpringBootTest
class DemoApplicationTests {

    static List<String> exlHeaders = new ArrayList<String>();

    static List<Object> dataList = new ArrayList<>();


    static {
        exlHeaders.addAll(Arrays.asList(new String[] { "產品代號", "發行機構", "募集起日", "募集迄日", "主架構", "次架構", "幣別", "保本率%",
                "產品期限", "產品期限單位", "固定配息%", "服務類型" }));

    }


    static Connection connection = JdbcConnector.getConnection();


    DemoApplicationTests() throws SQLException {
    }


    public static Sheet creatSheet(Workbook workbook, String sheetName) {
        // 創建第二個工作表
        Sheet sheet2 = workbook.createSheet(sheetName);

        System.out.println("getSheetName=>"+sheet2.getSheetName());


        // 在第二個工作表中寫入資料
        Row row2 = sheet2.createRow(0);
        Cell cell2 = row2.createCell(0);
        cell2.setCellValue("Hello from Sheet2");


        return sheet2;
    }

    public static void callPoi() {
        List<String> sheetNames = new ArrayList<>();

        sheetNames.add("0101");

        sheetNames.add("0105");

        sheetNames.add("0505");


        List<Object> rowData1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, "4", "d"); // 設置第一行的數據

//        List<Object> rowData2 = Arrays.asList(1, 2, 3, 8, 5, "", 7, 8, 6, 0, "4", "g"); // 設置第一行的數據

        dataList.add(rowData1);

        Workbook workbook = new XSSFWorkbook();

        for(String name:sheetNames){
            poi(name,workbook,dataList);
        }

        // 儲存Excel檔案
        try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\eva97\\Desktop\\CTBC相關設定檔\\example.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Excel檔案成功寫入。");
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void poi(String sheetNames, Workbook workbook, List<Object> dataList) {
        System.out.println("begin");
        // 創建新的工作簿


        // 創建工作表
        Sheet sheet = creatSheet(workbook,sheetNames);

        // 創建表頭樣式
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);


        // 定義表頭資料

        // 在第一列中建立表頭
        Row headerRow = sheet.createRow(0);

        // 寫入表頭資料
        for (int i = 0; i < exlHeaders.size(); i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(exlHeaders.get(i));

        }


//        dataList.add(rowData2);

        // 寫入數據
        for (int rowIndex = 0; rowIndex < DemoApplicationTests.dataList.size(); rowIndex++) {
            Row dataRow = sheet.createRow(rowIndex + 1);
            List<Object> rowData = (List<Object>) DemoApplicationTests.dataList.get(rowIndex);
            for (int colIndex = 0; colIndex < rowData.size(); colIndex++) {
                Cell dataCell = dataRow.createCell(colIndex);
                Object cellValue = rowData.get(colIndex);
                if (cellValue instanceof Integer) {
                    dataCell.setCellValue((Integer) cellValue);
                } else if (cellValue instanceof String) {
                    dataCell.setCellValue((String) cellValue);
                } else {
                    // 處理其他資料類型
                    dataCell.setCellValue(cellValue.toString());
                }
            }
        }

//        creatSheet(workbook);



        // 關閉工作簿
//        try {
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("end");
    }

    public static void main (String[]args) throws ParseException {
        callPoi();
        //        ====================
//        System.out.println(dao.queryById("xxxxxxxx"));
//        System.out.println("start");
//        String sql = "update ATTENDANCE_REC set ch_name='7' where emp_no='Emp123'";
//        System.out.println("end");
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        ====================

    }


}

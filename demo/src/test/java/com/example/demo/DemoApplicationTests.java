package com.example.demo;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.util.JdbcConnector;
import com.util.MongoDBConnector;
import com.vo.CheckVo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import org.bson.Document;
import java.text.SimpleDateFormat;
import java.util.*;


@SpringBootTest
class DemoApplicationTests {

    static List<String> exlHeaders = new ArrayList<String>();

    static List<CheckVo> dataList = new ArrayList<>();


    static {
        exlHeaders.addAll(Arrays.asList(new String[]{"產品代號", "發行機構", "募集起日", "募集迄日", "主架構", "次架構", "幣別", "保本率%",
                "產品期限", "產品期限單位", "固定配息%", "服務類型"}));

    }


    static Connection connection = JdbcConnector.getConnection();

    static MongoDatabase mongodbConnection = MongoDBConnector.getConnection();


    DemoApplicationTests() throws SQLException {
    }


    public static Sheet creatSheet(Workbook workbook, String sheetName) {
        // 創建第二個工作表
        Sheet sheet2 = workbook.createSheet(sheetName);


        return sheet2;
    }

    public static void callPoi() {
        List<String> sheetNames = new ArrayList<>();

        sheetNames.add("0101");

        sheetNames.add("0105");

        sheetNames.add("0505");


//        List<Object> rowData1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, "4", "d"); // 設置第一行的數據

//        List<Object> rowData2 = Arrays.asList(1, 2, 3, 8, 5, "", 7, 8, 6, 0, "4", "g"); // 設置第一行的數據

        CheckVo vo = new CheckVo();
        vo.setEmpNo("emp123");
        vo.setChName("jim");
        vo.setSheetName("0101");

        CheckVo vo3 = new CheckVo();
        vo3.setEmpNo("emp567");
        vo3.setChName("tomo");
        vo3.setSheetName("0101");


        CheckVo vo2 = new CheckVo();
        vo2.setEmpNo("emp234");
        vo2.setChName("alex");
        vo2.setSheetName("0105");

        List<CheckVo> rowData1 = new ArrayList<>(); // 設置第一行的數據
        rowData1.add(vo);
        rowData1.add(vo3);
        rowData1.add(vo2);

        dataList.addAll(rowData1);

        Workbook workbook = new XSSFWorkbook();

        for (String name : sheetNames) {
            poi(name, workbook, dataList);
        }

        // 儲存Excel檔案
        try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\eva97\\Desktop\\CTBC相關設定檔\\example.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Excel檔案成功寫入。");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void poi(String sheetNames, Workbook workbook, List<CheckVo> dataList) {

        System.out.println("begin");
        // 創建新的工作簿


        // 創建工作表
        Sheet sheet = creatSheet(workbook, sheetNames);


        System.out.println("sheet.getSheetName();" + sheet.getSheetName());

        // 創建表頭樣式
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
//            Sheet sheet = null;
//if(sheet.issheet.getSheetName())
//            Sheet sheet = creatSheet(workbook, data.getSheetName());

//            Sheet sheet = workbook.getSheet(data.getSheetName());


        // 定義表頭資料

        // 在第一列中建立表頭
        Row headerRow = sheet.createRow(0);

        // 寫入表頭資料
        for (int i = 0; i < exlHeaders.size(); i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(exlHeaders.get(i));
        }

        // 寫入數據
        for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {


            Row dataRow = sheet.createRow(rowIndex + 1);
            CheckVo checkVo = dataList.get(rowIndex);
            List<Object> rowData = new ArrayList<>();
            rowData.add(checkVo.getEmpNo());
            rowData.add(checkVo.getChName());


//            List<Object> rowData = (List<Object>) dataList.get(rowIndex);
            for (int colIndex = 0; colIndex < rowData.size(); colIndex++) {
                Cell dataCell = dataRow.createCell(colIndex);
                Object cellValue = rowData.get(colIndex);
                System.out.println("cellValue=>" + cellValue);
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

    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
        MongoCollection<Document> collection = mongodbConnection.getCollection("test");
        System.out.println("集合 test 选择成功");

        Document document = new Document("title", "MongoDBttt").
                append("description", "databasettt").
                append("likes", 100333).
                append("by", "Flyttt");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        System.out.println("文档插入成功");

        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            System.out.println(mongoCursor.next());
        }
    }
    

}

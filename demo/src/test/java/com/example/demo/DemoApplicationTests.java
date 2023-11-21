package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.util.JdbcConnector;
import com.util.MongoDBConnector;
import com.vo.CheckVo;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
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
        //生產者
//        Properties properties = new Properties();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        //网络传输,对key和value进行序列化
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        //创建消息生产对象，需要从properties对象或者从properties文件中加载信息
//        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
//
//        try {
//
//                //设置消息内容
//                String msg = "Hello," + new Random().nextInt(100);
//                //将消息内容封装到ProducerRecord中
//                ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", msg);
//                kafkaProducer.send(record);
//                System.out.println("消息发送成功:" + msg);
//                Thread.sleep(500000);
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            kafkaProducer.close();
//        }


        //消費者
        Properties p = new Properties();
        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        //指定组名
        p.put(ConsumerConfig.GROUP_ID_CONFIG, "test-groupB");
        p.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");//重製offset，從舊(index=0)的訊息開始讀


        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(p);
        kafkaConsumer.subscribe(Collections.singletonList("test"));// 订阅消息

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);

            System.out.println(records);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("topic:%s,offset:%d,消息:%s", record.topic(), record.offset(), record.value()));
            }

        }
    }



    

}

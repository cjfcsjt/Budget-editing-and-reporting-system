package com.cjf.ysxt.tool;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ViewExcel extends AbstractExcelView {

private ArrayList<String> titles;
    
    //传入指定的标题头
    public ViewExcel(ArrayList<String> titles) {
        this.titles=titles;
    }
    
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //获取数据
    	Map<String, Map<String, Float>> list = (Map<String, Map<String, Float>>) model.get("excelList");
        //在workbook添加一个sheet
        HSSFSheet sheet = workbook.createSheet();
        sheet.setDefaultColumnWidth(4);
        HSSFCell cell=null;
        //遍历标题
        for (int i = 0; i < titles.size(); i++) {
            //获取位置
            cell = getCell(sheet, 0, i);
            setText(cell, titles.get(i));
        }
        //数据写出
        int i = 0;
        for (String collegeName: list.keySet()) {
            //获取每一个map
            Map<String, Float> map= list.get(collegeName);
            //一个map一行数据
            HSSFRow row = sheet.createRow(i+1);
            i=i+1;
            for (int j = 0; j < titles.size(); j++) {
                //遍历标题，把key与标题匹配
                String title=titles.get(j);
                if(j==0)
                	row.createCell(j).setCellValue(collegeName);
                //判断该内容存在mapzhong
                if(map.containsKey(title)){
                    row.createCell(j).setCellValue(map.get(title));
                }
            }
        }
         //设置下载时客户端Excel的名称     
        String filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls";  
        response.setContentType("application/vnd.ms-excel");     
        response.setHeader("Content-disposition", "attachment;filename=" + filename); 
        OutputStream ouputStream = response.getOutputStream();     
        workbook.write(ouputStream);     
        ouputStream.flush();     
        ouputStream.close();     
    }

}

package com.demo.adminsystem.core.plugins.excel;

import com.alibaba.fastjson.JSONObject;
import io.github.biezhi.excel.plus.ExcelPlus;
import io.github.biezhi.excel.plus.enums.ParseType;
import io.github.biezhi.excel.plus.exception.ExcelException;
import io.github.biezhi.excel.plus.reader.Reader;
import io.github.biezhi.excel.plus.writer.ResponseWrapper;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 16:48
 * @version: V1.0
 * @detail: ParseExcel Excel 操作 插件地址 https://biezhi.github.io/excel-plus/#/?id=%E5%BF%AB%E9%80%9F%E5%BC%80%E5%A7%8B
 **/
public class ParseExcel<T> {

    private T t;

    public List<T> parseExcel(File file) throws ExcelException {
        ExcelPlus excelPlus = new ExcelPlus();
        Reader reader = Reader.create()
                .parseType(ParseType.SAX)
                .startRowIndex(2)
                .sheetIndex(0)
                .excelFile(file);

        List<T> list = (List<T>) excelPlus.read(t.getClass(), reader).asList();
        return list;
    }

    public void downLoadExcelTemplate() {

    }

    public void downLoadData(List<T> list, String name, HttpServletResponse response) throws ExcelException {
        new ExcelPlus().export(list).writeAsResponse(ResponseWrapper.create(response, name));
    }

//    public static void main(String[] args) throws ExcelException {
//
//    }
}

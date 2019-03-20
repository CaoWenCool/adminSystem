package com.demo.adminsystem.core.util.gencode;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.SqlServer2012Style;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.MapperCodeGen;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:36
 * @version: V1.0
 * @detail: GenProjectCode 代码生成器
 **/
public class GenProjectCode {
    String appPath = "G:\\Project\\adminsystem\\src\\main\\resources\\application.properties";

    public Properties getEnv() {
        try {
            InputStream inStream = new FileInputStream(new File(appPath));
            Properties env = new Properties();
            env.load(inStream);
            return env;
        } catch (Exception e) {
            return null;
        }
    }

    public void genBeetlCode() throws Exception {
        Properties env = getEnv();
        ConnectionSource connectionSource = ConnectionSourceHelper.getSimple(
                env.getProperty("spring.datasource.driver-class-name"),
                env.getProperty("spring.datasource.url"),
                env.getProperty("spring.datasource.username"),
                env.getProperty("spring.datasource.password"));
        SQLManager sqlManager = new SQLManager(new SqlServer2012Style(), new ClasspathLoader("/sql"), connectionSource, new UnderlinedNameConversion(), new Interceptor[]{new DebugInterceptor()});

        // List<Tables> list = sqlManager.execute(new SQLReady("select Name FROM SysObjects Where XType='U' ORDER BY Name"),Tables.class);
        List<Tables> list = new ArrayList<>();
        Tables tables = new Tables();
        tables.setName("tb_system_logs");
        list.add(tables);
        for (Tables t : list) {
//            System.out.println(table.getName());
//        }
//        // entity + SQL
//        for (String table : new String[]{"tb_system_user", "tb_system_role", "tb_system_permission_menu", "tb_system_permission_point", "tb_system_role_permission",
//                "tb_system_permission_form", "tb_system_permission_form_role"}) {
            String table = t.getName();
            GenConfig config = new GenConfig();
            config.preferBigDecimal(true);
            MapperCodeGen mapper = new MapperCodeGen("com.demo.adminsystem.core.dao");
            config.codeGens.add(mapper);
            sqlManager.genPojoCode(table, "com.demo.adminsystem.core.entity", config);
            sqlManager.genSQLFile(table);
        }
    }

    public static void main(String[] args) throws Exception {
        GenProjectCode genProjectCode = new GenProjectCode();
        genProjectCode.genBeetlCode();
    }
}

package com.code.generator.controller;

import com.code.generator.domain.GenConf;
import com.code.generator.domain.TableInfo;
import com.code.generator.service.IGenService;
import com.code.generator.common.core.controller.BaseController;
import com.code.generator.common.core.page.TableDataInfo;
import com.code.generator.common.core.text.Convert;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 代码生成 操作处理
 *
 * @author azhong
 */
@Controller
@RequestMapping("/index")
public class GenController extends BaseController {

    @Autowired
    private IGenService genService;

    @GetMapping()
    public String index() {
        return "/index";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TableInfo tableInfo) {
        startPage();
        List<TableInfo> list = genService.selectTableList(tableInfo);
        return getDataTable(list);
    }

    /**
     * 生成代码
     */
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName, GenConf genConf) throws IOException {
        byte[] data = genService.generatorCode(tableName, genConf);
        response.reset();
        response.setHeader("Content-Disposition" , "attachment; filename=\"generator.zip\"");
        response.addHeader("Content-Length" , "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response, String tables, GenConf genConf) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genService.generatorCode(tableNames, genConf);
        response.reset();
        response.setHeader("Content-Disposition" , "attachment; filename=\"generator.zip\"");
        response.addHeader("Content-Length" , "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}

package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.service.MemberService;
import com.itheima.health.service.ReportService;
import com.itheima.health.service.SetmealService;
import com.itheima.health.service.UserService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    MemberService memberService;

    @Reference
    SetmealService setmealService;

    @Reference
    ReportService reportService;

    //会员按照月份注册数量折线图统计
    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        //组织数据
        Map<String,Object> map = new HashMap<>();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH,-12);//获得当前日期之前的12个月的月份
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                calendar.add(Calendar.MONTH,1);//12个月之前往后推一个月
                list.add(new SimpleDateFormat("YYYY-MM").format(calendar.getTime()));//将时间转成字符串
            }
            map.put("months",list);
            //根据时间获取会员的注册数量
            List<Integer> memberCount = memberService.findMemberCountByMonth(list);
            map.put("memberCount",memberCount);
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    //套餐预约占比饼状图
    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        Map<String,Object> map = new HashMap();
        List<Map<String,Object>> list = setmealService.findSetmealOrderCount();
        map.put("setmealCount",list);//("setmealCount")list:[{"name":"套餐1","value":10},
//                                                            {"name":"套餐2","value":30},
//                                                            {"name":"套餐3","value":25}]
        List<String> setmealNames = new ArrayList<>();
        for (Map<String, Object> m : list) {
            String name = (String)m.get("name");
            setmealNames.add(name);
        }
        map.put("setmealNames",setmealNames);
        return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
    }

    //运营数据统计
    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
        try {
            Map<String,Object> result = reportService.getBusinessReport();
            return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }

    //数据统计表导出
    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport(HttpServletResponse response, HttpServletRequest request) {
        try {
            //获取数据表的数据
            Map<String,Object> map = reportService.getBusinessReport();
            String reportDate = (String) map.get("reportDate");
            Integer todayNewMember = (Integer) map.get("todayNewMember");
            Integer totalMember = (Integer) map.get("totalMember");
            Integer thisWeekNewMember = (Integer) map.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) map.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) map.get("todayOrderNumber");
            Integer todayVisitsNumber = (Integer) map.get("todayVisitsNumber");
            Integer thisWeekOrderNumber = (Integer) map.get("thisWeekOrderNumber");
            Integer thisWeekVisitsNumber = (Integer) map.get("thisWeekVisitsNumber");
            Integer thisMonthOrderNumber = (Integer) map.get("thisMonthOrderNumber");
            Integer thisMonthVisitsNumber = (Integer) map.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) map.get("hotSetmeal");

            //获取文档的绝对路径
            String templateRealPath = request.getSession().getServletContext().getRealPath("template")+ File.separator+"report_template.xlsx";

            //将数据填写到对应的单元格中
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(templateRealPath)));//创建表格对象3
            //获取第一个工作表
            XSSFSheet sheet = workbook.getSheetAt(0);
            //获取第三行
            XSSFRow row = sheet.getRow(3);
            //把值填入第6个单元格
            row.getCell(5).setCellValue(reportDate);

            row = sheet.getRow(4);
            row.getCell(5).setCellValue(todayNewMember);//新增会员数（本日）
            row.getCell(7).setCellValue(totalMember);//总会员数

            row = sheet.getRow(5);
            row.getCell(5).setCellValue(thisWeekNewMember);//本周新增会员数
            row.getCell(7).setCellValue(thisMonthNewMember);//本月新增会员数

            row = sheet.getRow(7);
            row.getCell(5).setCellValue(todayOrderNumber);//今日预约数
            row.getCell(7).setCellValue(todayVisitsNumber);//今日到诊数

            row = sheet.getRow(8);
            row.getCell(5).setCellValue(thisWeekOrderNumber);//本周预约数
            row.getCell(7).setCellValue(thisWeekVisitsNumber);//本周到诊数

            row = sheet.getRow(9);
            row.getCell(5).setCellValue(thisMonthOrderNumber);//本月预约数
            row.getCell(7).setCellValue(thisMonthVisitsNumber);//本月到诊数

            //热门套餐
            int rowNum = 12;
            for (Map map1 : hotSetmeal) {
                String name = (String) map1.get("name");
                Long setmeal_count = (Long) map1.get("setmeal_count");
                BigDecimal proportion = (BigDecimal) map1.get("proportion");
                row = sheet.getRow(rowNum++);
                row.getCell(4).setCellValue(name);//套餐名称
                row.getCell(5).setCellValue(setmeal_count);//预约数量
                row.getCell(6).setCellValue(proportion.doubleValue());//占比
            }


            //使用输出流,将文件输出到缓存区,用于下载
            ServletOutputStream outputStream = response.getOutputStream();
            //设置文件下载的类型
            response.setContentType("application/vnd.ms-excel");//HTTP ContentType 对照表
            //设置文件下载的形式(头)(两种形式:一种内连(inline),另一种附件)
            response.setHeader("Content-Disposition","attachment;filename=report.xlsx");

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL,null);
        }
        return null;
    }
}

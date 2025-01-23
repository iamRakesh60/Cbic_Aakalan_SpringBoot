package com.Cbic_Aaklan_Project.Controller;

import com.Cbic_Aaklan_Project.Service.CustomGreadeScore;
import com.Cbic_Aaklan_Project.Service.GstSubParameterService;
import com.Cbic_Aaklan_Project.dao.Query.GstSubParameterWiseQuery;
import com.Cbic_Aaklan_Project.dao.result.GetExecutionSQL;
import com.Cbic_Aaklan_Project.entity.GST4A;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

// In this controller we will try to fatch data from gst8a, gst8b and try to make parameter
//    private Logger logger = LoggerFactory.getLogger(TestingController3.class);
@Controller
@RequestMapping("/cbicApi/cbic/testing")
public class TestingController3 {
    CustomGreadeScore score = new CustomGreadeScore();
    GstSubParameterService gstSubParameterService = new GstSubParameterService();

    @ResponseBody
    @RequestMapping(value = "/gst8a")  // only zone wise working
    //  http://localhost:8080/cbicApi/cbic/testing/gst8a?month_date=2023-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/testing/gst8a?month_date=2023-10-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/testing/gst8a?month_date=2023-10-01&type=all_commissary
    public Object getGst8a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList=new ArrayList<>();
        try {
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst8a_ZoneWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(gstSubParameterService.gst8aZone(rsGst14aa));

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst8a_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(gstSubParameterService.gst8aZoneWiseCommissionary(rsGst14aa));

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa=  new GstSubParameterWiseQuery().QueryFor_gst8a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(gstSubParameterService.gst8aAllCommissionary(rsGst14aa));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }

    @ResponseBody
    @RequestMapping(value = "/gst8b")
    //  http://localhost:8080/cbicApi/cbic/testing/gst8b?month_date=2023-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/testing/gst8b?month_date=2023-10-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/testing/gst8b?month_date=2023-10-01&type=all_commissary
    public Object getGst8b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList=new ArrayList<>();
        try {
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst8b_ZoneWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(gstSubParameterService.gst8bZone(rsGst14aa));

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst8b_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(gstSubParameterService.gst8bZoneWiseCommissionary(rsGst14aa));

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa=  new GstSubParameterWiseQuery().QueryFor_gst8b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(gstSubParameterService.gst8bAllCommissionary(rsGst14aa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score)).collect(Collectors.toList());
    }
}

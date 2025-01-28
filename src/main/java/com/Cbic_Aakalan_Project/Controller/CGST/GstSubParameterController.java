package com.Cbic_Aakalan_Project.Controller.CGST;

import com.Cbic_Aakalan_Project.Service.DateCalculate;
import com.Cbic_Aakalan_Project.Service.CGST.GstGradeScore;
import com.Cbic_Aakalan_Project.Service.CGST.GstSubParameterService;
import com.Cbic_Aakalan_Project.Service.RelevantAspect;
import com.Cbic_Aakalan_Project.dao.Query.CGST.GstSubParameterWiseQuery;
import com.Cbic_Aakalan_Project.dao.pool.JDBCConnection;
import com.Cbic_Aakalan_Project.dao.result.GetExecutionSQL;
import com.Cbic_Aakalan_Project.entity.GSTCUS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/*
 * @Author: @Rakesh shit
 */
//@CrossOrigin
@RequestMapping("/cbicApi/cbic") //....
@Controller
public class GstSubParameterController {
    private Logger logger = LoggerFactory.getLogger(GstSubParameterController.class);
    GstGradeScore score=new GstGradeScore();
    GstSubParameterService gstSubParameterService = new GstSubParameterService();

    /*
     * Date: May 04, 2024
     * created: RKS, may 17, 2024
     * updated: RKS, jan 24, 2025
     */

    @ResponseBody
    @RequestMapping(value = "/gst1a")
    //  http://localhost:8080/cbicApi/cbic/gst1a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1a?month_date=2024-10-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst1a?month_date=2024-10-01&type=all_commissary
    public Object getGst1A(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()) {

            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1a_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1aZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1aZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1aAllCommissionary(rsGst14aa));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst1b =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst1b")
    //  http://localhost:8080/cbicApi/cbic/gst1b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1b?month_date=2024-10-01&zone_code=70&type=commissary
    public Object getGst1B(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1b_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1bZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1b_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1bZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1b_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1bAllCommissionary(rsGst14aa));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream().sorted(Comparator.comparing(GSTCUS::getTotal_score))
                .collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst1c =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst1c")
    //  http://localhost:8080/cbicApi/cbic/gst1c?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1c?month_date=2024-10-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst1c?month_date=2024-10-01&type=all_commissary
    public Object getGst1C(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1c_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1cZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1c_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1cZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1c_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1cAllCommissionary(rsGst14aa));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream().sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed())
                .collect(Collectors.toList());

    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst1c =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst1d")
    //  http://localhost:8080/cbicApi/cbic/gst1d?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1d?month_date=2023-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst1d?month_date=2023-04-01&type=all_commissary
    public Object getGst1D(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1d_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1dZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1d_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1dZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1d_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1dAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream().sorted(Comparator.comparing(GSTCUS::getTotal_score)
        ).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst1e =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst1e")
    //  http://localhost:8080/cbicApi/cbic/gst1e?month_date=2024-10-01&type=zone
//  http://localhost:8080/cbicApi/cbic/gst1e?month_date=2024-10-01&zone_code=51&type=commissary
//	  http://localhost:8080/cbicApi/cbic/gst1e?month_date=2024-10-01&type=all_commissary
    public Object getGst1E(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1e_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1eZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1e_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1eZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1e_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1eAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace() ;
        }
        return allGstaList.stream().sorted(Comparator.comparing(GSTCUS::getTotal_score)
        ).collect(Collectors.toList());
    }



    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst1f =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst1f")
    //  http://localhost:8080/cbicApi/cbic/gst1f?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1f?month_date=2023-04-01&zone_code=51&type=commissary
    //  http://localhost:8080/cbicApi/cbic/gst1f?month_date=2023-04-01&type=all_commissary
    public Object getGst1F(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1f_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1fZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1f_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1fZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1f_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst1fAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream().sorted(Comparator.comparing(GSTCUS::getTotal_score)
        ).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst2 =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst2")
//	  http://localhost:8080/cbicApi/cbic/gst2?month_date=2023-04-01&type=zone
//	  http://localhost:8080/cbicApi/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
//	  http://localhost:8080/cbicApi/cbic/gst2?month_date=2023-04-01&type=all_commissary
    public Object getGst2(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst2_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst2Zone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst2_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst2ZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst2_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst2AllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst3a =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
//    @ResponseBody
//    @RequestMapping(value = "/gst3a")
//    // http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-04-01&type=zone
//    //  http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-04-01&zone_code=70&type=commissary
//    public Object getGst3A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
//
//        List<GST4A> allGstaList = new ArrayList<>();
//        GST4A gsta = null;
//        int rank = 0;
//        double total = 0.00;
//
//        try {
//            if("zone".equalsIgnoreCase(type)) {
//                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
//                // Query string
//                String queryGst14aa= "WITH current_month_data AS (\n" +
//                        "    SELECT\n" +
//                        "        zc.ZONE_NAME,\n" +
//                        "        cc.ZONE_CODE,\n" +
//                        "        SUM(14c.SCRUTINIZED_ASMT_10) AS col5,\n" +
//                        "        SUM(14c.RETURN_SCRUTINY) AS col2\n" +
//                        "    FROM\n" +
//                        "        mis_gst_commcode AS cc\n" +
//                        "        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                        "    WHERE\n" +
//                        "        14c.MM_YYYY = '"+ month_date+"'\n" +
//                        "    GROUP BY\n" +
//                        "        cc.ZONE_CODE\n" +
//                        "),\n" +
//                        "previous_month_data AS (\n" +
//                        "    SELECT\n" +
//                        "        zc.ZONE_NAME,\n" +
//                        "        cc.ZONE_CODE,\n" +
//                        "        SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col6,\n" +
//                        "        SUM(14c.RETURN_SCRUTINY) AS col3\n" +
//                        "    FROM\n" +
//                        "        mis_gst_commcode AS cc\n" +
//                        "        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                        "    WHERE\n" +
//                        "        14c.MM_YYYY =  '" + prev_month_new + "'\n" +
//                        "    GROUP BY\n" +
//                        "        cc.ZONE_CODE\n" +
//                        ")\n" +
//                        "SELECT\n" +
//                        "    curr.ZONE_NAME,\n" +
//                        "    curr.ZONE_CODE,\n" +
//                        "    curr.col5 AS col5_current_month,\n" +
//                        "    curr.col2 AS col2_current_month,\n" +
//                        "    prev.col6 AS col6_previous_month,\n" +
//                        "    prev.col3 AS col3_previous_month\n" +
//                        "FROM\n" +
//                        "    current_month_data AS curr\n" +
//                        "    LEFT JOIN previous_month_data AS prev ON curr.ZONE_CODE = prev.ZONE_CODE;";
//
//                //Result Set
//                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
//                while(rsGst14aa.next()) {
//                    String ra = RelevantAspect.Gst3A_RA;
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    int col6 = rsGst14aa.getInt("col6_previous_month");
//                    int col5 = rsGst14aa.getInt("col5_current_month");
//                    int col3 = rsGst14aa.getInt("col3_previous_month");
//                    int col2 = rsGst14aa.getInt("col2_current_month");
//                    String absval = String.valueOf(col6 + col5) + "/" + String.valueOf(col3 + col2);
//                    if (col3 + col2 != 0) {
//                        total = ((double) (col6 + col5) / (col3 + col2));
//                    }
//                    rank = score.marks3a(total);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore, rank, absval, zoneCode, ra);
//                    allGstaList.add(gsta);
//                }
//            }else if ("commissary".equalsIgnoreCase(type)) {
//                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
//                // Query string
//                String queryGst14aa="WITH SecondQuery AS (\n" +
//                        "    SELECT cc.COMM_CODE, 14c.CLOSING_NO AS col2\n" +
//                        "    FROM mis_gst_commcode AS cc \n" +
//                        "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                        "    WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + prev_month_new + "'\n" +
//                        ")\n" +
//                        "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
//                        "       14c.SCRUTINIZED_ASMT_10 AS col6, \n" +
//                        "       14c.SCRUTINIZED_DISCRIPANCY_FOUND AS col5, \n" +
//                        "       14c.RETURN_SCRUTINY AS col3,\n" +
//                        "       sq.col2\n" +
//                        "FROM mis_gst_commcode AS cc \n" +
//                        "RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                        "LEFT JOIN SecondQuery AS sq ON sq.COMM_CODE = cc.COMM_CODE\n" +
//                        "WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';\n";
//
//
//
//                //Result Set
//                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
//                while(rsGst14aa.next()) {
//                    String commname=rsGst14aa.getString("COMM_NAME");
//                    String ra=RelevantAspect.Gst3A_RA;
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    int col6=rsGst14aa.getInt("col6");
//                    int col5=rsGst14aa.getInt("col5");
//                    int col3=rsGst14aa.getInt("col3");
//                    int col2=rsGst14aa.getInt("col2");
//                    String absval = String.valueOf(col6 + col5) + "/" + String.valueOf(col3 + col2);
//
//                    if ((col3+col2) != 0){
//                        total =((double) (col6+col5)/(col3+col2));
//                    }
//
//                    rank=score.marks3a(total);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//
//                    gsta = new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
//                    allGstaList.add(gsta);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return allGstaList;
//    }

    //                              that code for temporary

    @ResponseBody
    @RequestMapping(value = "/gst3a")
    // http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-05-01&type=zone
    // http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-05-01&zone_code=69&type=commissary
    // http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-05-01&type=all_commissary
    public Object getGst3A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst3a_ZoneWise(month_date);
            } else if (type.equalsIgnoreCase("commissary")) {
                queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst3a_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst3a_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 10 in a loop
                for (int i = 1; i <= 10; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(11, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(gstSubParameterService.gst3aZone(rsGst14aa));
                } else if (type.equalsIgnoreCase("commissary")) {
                    allGstaList.addAll(gstSubParameterService.gst3aZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(gstSubParameterService.gst3aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst3b =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

//    @ResponseBody
//    @RequestMapping(value = "/gst3b")
//    //  http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-04-01&type=zone
//    //  http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-04-01&zone_code=70&type=commissary
//    public Object getGst3B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
//
//        List<GST4A> allGstaList = new ArrayList<>();
//        GST4A gsta = null;
//        int rank = 0;
//        double total = 0.00;
//        //Connection done
//        Connection con= JDBCConnection.getTNConnection();
//
//        try {
//            if("zone".equalsIgnoreCase(type)) {
//                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
//                // Query string
//                String queryGst14aa = "WITH current_month_data AS (\n" +
//                        "    SELECT\n" +
//                        "        zc.ZONE_NAME,\n" +
//                        "        cc.ZONE_CODE,\n" +
//                        "      \n" +
//                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS col14,\n" +
//                        "        SUM(14c.AMOUNT_RECOVERED_TAX+14c.AMOUNT_RECOVERED_INTEREST+14c.AMOUNT_RECOVERED_PENALTY) AS col22\n" +
//                        "    FROM\n" +
//                        "        mis_gst_commcode AS cc\n" +
//                        "        RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                        "    WHERE\n" +
//                        "        14c.MM_YYYY = '" + month_date + "'\n" +
//                        "    GROUP BY\n" +
//                        "        cc.ZONE_CODE\n" +
//                        "),\n" +
//                        "previous_month_data AS (\n" +
//                        "    SELECT\n" +
//                        "        zc.ZONE_NAME,\n" +
//                        "        cc.ZONE_CODE,\n" +
//                        "        SUM(14c.AMOUNT_RECOVERED_TAX+14c.AMOUNT_RECOVERED_INTEREST+14c.AMOUNT_RECOVERED_PENALTY) AS col22_1,\n" +
//                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS col14_1\n" +
//                        "    FROM\n" +
//                        "        mis_gst_commcode AS cc\n" +
//                        "        RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                        "    WHERE\n" +
//                        "        14c.MM_YYYY = '" + prev_month_new + "'\n" +
//                        "    GROUP BY\n" +
//                        "        cc.ZONE_CODE\n" +
//                        ")\n" +
//                        "SELECT\n" +
//                        "    curr.ZONE_NAME,\n" +
//                        "    curr.ZONE_CODE,\n" +
//                        "    curr.col22 AS col22_current_month,\n" +
//                        "    curr.col14 AS col14_current_month,\n" +
//                        "    prev.col22_1 AS col22_1_previous_month,\n" +
//                        "    prev.col14_1 AS col14_1_previous_month\n" +
//                        "FROM\n" +
//                        "    current_month_data AS curr\n" +
//                        "    LEFT JOIN previous_month_data AS prev ON curr.ZONE_CODE = prev.ZONE_CODE;";
//
//                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);
//
//                //Result Set
//                ResultSet rsGst14aa = psGst14aa.executeQuery();
//
//
//                while (rsGst14aa.next()) {
//                    String ra = RelevantAspect.Gst3B_RA;
//                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    int col22 = rsGst14aa.getInt("col22_current_month");
//                    int col14 = rsGst14aa.getInt("col14");
//                    if (col14 != 0) {
//                        total = ((double) col22 / col14);
//                    }
//                    rank = score.marks3b(total);
//                    String absval = String.valueOf(col22) + "/" + String.valueOf(col14);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", (Double) totalScore, rank, absval, zoneCode, ra);
//                    allGstaList.add(gsta);
//                }
//            }else if ("commissary".equalsIgnoreCase(type)) {
//
//                // Query string
//                String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.amount_recovered_penalty AS col22, "
//                        +"14c.tax_liability_detectect AS col14 "
//                        +"FROM mis_gst_commcode AS cc "
//                        +"RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
//                        + "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
//                        + "WHERE 14c.MM_YYYY ='" +month_date+"' AND zc.ZONE_CODE = '"+ zone_code +"';";
//
//                //Prepared Statement
//                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);
//                //Result Set
//                ResultSet rsGst14aa= psGst14aa.executeQuery();
//
//                while(rsGst14aa.next() ) {
//                    String commname=rsGst14aa.getString("COMM_NAME");
//                    String ra=RelevantAspect.Gst3B_RA;
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    int col22=rsGst14aa.getInt("col22");
//                    int col14=rsGst14aa.getInt("col14");
//                    String absval=String.valueOf(col22)+"/"+String.valueOf(col14);
//
//                    if (col14 != 0){
//                        total =((double) col22/col14);
//                    }
//
//                    rank=score.marks3b(total);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    gsta=new GST4A(zoneName, commname,totalScore, rank, absval, zoneCode,ra);
//                    allGstaList.add(gsta);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return allGstaList;
//    }

    //                              that code for temporary
    @ResponseBody
    @RequestMapping(value = "/gst3b") // msg:- 3b zone testing done, commmisonary not testing
    //  http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-04-01&zone_code=70&type=commissary
    // http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-05-01&type=all_commissary
    public Object getGst3B(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        String start_date=DateCalculate.getFinancialYearStart(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst3b_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, start_date);
                    pstmt.setString(2, month_date);
                    pstmt.setString(3, start_date);
                    pstmt.setString(4, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst3bZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst3b_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, start_date);
                    pstmt.setString(2, month_date);
                    pstmt.setString(3, start_date);
                    pstmt.setString(4, month_date);
                    pstmt.setString(5, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst3bZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst3b_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, start_date);
                    pstmt.setString(2, month_date);
                    pstmt.setString(3, start_date);
                    pstmt.setString(4, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst3bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4a =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst4a")
    //  http://localhost:8080/cbicApi/cbic/gst4a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst4a?month_date=2024-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst4a?month_date=2024-04-01&type=all_commissary
    public Object getGst4A(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {

        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4a_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4aZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4aZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4b =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst4b")
    //  http://localhost:8080/cbicApi/cbic/gst4b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst4b?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst4b?month_date=2023-04-01&type=all_commissary
    public Object getGst4B(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {

        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4b_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4bZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4b_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4bZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4b_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());

    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4c =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst4c")
    //  http://localhost:8080/cbicApi/cbic/gst4c?month_date=2024-04-01&type=zone                        // updated
    //  http://localhost:8080/cbicApi/cbic/gst4c?month_date=2024-04-01&zone_code=70&type=commissary     //  updated
    //	http://localhost:8080/cbicApi/cbic/gst4c?month_date=2024-04-01&type=all_commissary              //  updated
    public Object getGst4AC(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList=new ArrayList<>();


        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4c_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4cZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4c_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, month_date);
                    pstmt.setString(3, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4cZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4c_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4cAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4d =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst4d")
    //  http://localhost:8080/cbicApi/cbic/gst4d?month_date=2023-05-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst4d?month_date=2023-05-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst4d?month_date=2023-05-01&type=all_commissary
    public Object getGst4d(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList=new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4d_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4dZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4dZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4d_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst4dAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst5a =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst5a")
    //  http://localhost:8080/cbicApi/cbic/gst5a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst5a?month_date=2023-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst5a?month_date=2024-04-01&type=all_commissary
    public Object getGst5A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst5a_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst5aZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst5a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst5aZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst5a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst5aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst5b =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst5b")
    //  http://localhost:8080/cbicApi/cbic/gst5b?month_date=2023-05-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst5b?month_date=2023-05-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst5b?month_date=2023-05-01&type=all_commissary
    public Object getGst5B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        String next_month_new = DateCalculate.getNextMonth(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst5b_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, next_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst5bZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst5b_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    pstmt.setString(3, next_month_new);
                    pstmt.setString(4, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst5bZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst5b_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, next_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst5bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getSub_parameter_weighted_average).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6a =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst6a")
    //  http://localhost:8080/cbicApi/cbic/gst6a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6a?month_date=2024-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst6a?month_date=2024-04-01&type=all_commissary
    public Object getGst6A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();


        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6a_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6aZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    pstmt.setString(3, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6aZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6b =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst6b")
    //  http://localhost:8080/cbicApi/cbic/gst6b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6b?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst6b?month_date=2023-04-01&type=all_commissary
    public Object getGst6B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6b_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6bZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6b_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6bZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6b_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6c =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst6c")
    //  http://localhost:8080/cbicApi/cbic/gst6c?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6c?month_date=2024-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst6c?month_date=2024-04-01&type=all_commissary
    public Object getGst6C(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6c_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6cZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6c_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    pstmt.setString(3, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6cZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6c_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6cAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6d =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst6d")
    //  http://localhost:8080/cbicApi/cbic/gst6d?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6d?month_date=2024-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst6d?month_date=2024-04-01&type=all_commissary
    public Object getGst6D(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6d_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6dZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6d_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6dZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6d_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst6dAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst7 =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst7")
    //  http://localhost:8080/cbicApi/cbic/gst7?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst7?month_date=2023-04-01&zone_code=70&type=commissary
    //  http://localhost:8080/cbicApi/cbic/gst7?month_date=2023-04-01&type=all_commissary
    public Object getGst7(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst7_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst7Zone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst7_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst7ZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst7_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst7AllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst8a =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst8a")  // only zone wise working
    //  http://localhost:8080/cbicApi/cbic/gst8a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst8a?month_date=2024-10-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst8a?month_date=2024-10-01&type=all_commissary
    public Object getGst8a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList=new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst8a_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst8aZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst8a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst8aZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst8a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst8aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst8b =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst8b")
    //  http://localhost:8080/cbicApi/cbic/gst8b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst8b?month_date=2024-10-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst8b?month_date=2024-10-01&type=all_commissary
    public Object getGst8b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList=new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst8b_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst8bZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst8b_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst8bZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst8b_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst8bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }



    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst9a =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/gst9a")
    //  http://localhost:8080/cbicApi/cbic/gst9a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst9a?month_date=2023-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst9a?month_date=2023-04-01&type=all_commissary
    public Object getGst9a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        String start_date=DateCalculate.getFinancialYearStart(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst9a_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, start_date);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst9aZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst9a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, zone_code);
                    pstmt.setString(2, start_date);
                    pstmt.setString(3, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst9aZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst9a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, start_date);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst9aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst9b =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst9b")
    //  http://localhost:8080/cbicApi/cbic/gst9b?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst9b?month_date=2024-04-01&zone_code=51&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst9b?month_date=2024-04-01&type=all_commissary
    public Object getGst9b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst9b_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst9bZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst9b_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, month_date);
                    pstmt.setString(3, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst9bZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst9b_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst9bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10a =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst10a")
    //  http://localhost:8080/cbicApi/cbic/gst10a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst10a?month_date=2024-10-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst10a?month_date=2024-10-01&type=all_commissary
    public Object getGst10A(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();

        int N = convertMonthToFinancialMonth(month_date);
        //System.out.println(month_date + "-" + N);

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10a_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst10aZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    pstmt.setString(3, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst10aZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst10aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    private int convertMonthToFinancialMonth(String month_date) {
        int month = Integer.parseInt(month_date.substring(5, 7)); //month_date.substring(5, 7) extracts the month part from the date string. For example, if month_date is "2023-04-01", month_date.substring(5, 7) would yield "04". Integer.parseInt converts this string representation of the month to an integer. Thus, for "2023-04-01", month would be 4.
        return (month >= 4) ? (month - 3) : (month + 9); //The ternary operator (condition) ? value_if_true : value_if_false is used here to determine the financial month.
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10b =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst10b")
    //  http://localhost:8080/cbicApi/cbic/gst10b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst10b?month_date=2024-10-01&zone_code=70&type=commissary
    // http://localhost:8080/cbicApi/cbic/gst10b?month_date=2024-10-01&type=all_commissary
    public Object getGst10b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10b_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst10bZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10b_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    pstmt.setString(3, prev_month_new);
                    pstmt.setString(4, zone_code);

                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst10bZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10b_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst10bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10c =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst10c")
    //  http://localhost:8080/cbicApi/cbic/gst10c?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst10c?month_date=2024-10-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst10c?month_date=2024-10-01&type=all_commissary
    public Object getGst10c(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        String start_date=DateCalculate.getFinancialYearStart(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10c_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, start_date);
                    pstmt.setString(2, month_date);
                    pstmt.setString(3, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst10cZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10c_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, start_date);
                    pstmt.setString(2, month_date);
                    pstmt.setString(3, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst10cZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10c_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, start_date);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst10cAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed())
                .collect(Collectors.toList());

    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11a =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst11a")
    //  http://localhost:8080/cbicApi/cbic/gst11a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst11a?month_date=2024-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst11a?month_date=2024-04-01&type=all_commissary
    public Object getGst11A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst11a_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst11aZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst11a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    pstmt.setString(3, prev_month_new);
                    pstmt.setString(4, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst11aZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst11a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst11aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11b =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst11b")
    //  http://localhost:8080/cbicApi/cbic/gst11b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst11b?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst11b?month_date=2023-04-01&type=all_commissary
    public Object getGst11B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst11b_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst11bZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst11b_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    pstmt.setString(3, month_date);
                    pstmt.setString(4, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst11bZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst11b_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst11bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11c =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/gst11c")
    //  http://localhost:8080/cbicApi/cbic/gst11c?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst11c?month_date=2023-04-01&zone_code=70&type=commissary
    // http://localhost:8080/cbicApi/cbic/gst11c?month_date=2024-04-01&type=all_commissary
    public Object getGst11C(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst11c_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst11cZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst11c_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    pstmt.setString(3, zone_code);

                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst11cZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst11c_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, prev_month_new);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(gstSubParameterService.gst11cAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11d =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody // incomplete code lack of resources
    @RequestMapping(value = "/gst11d")
    //  http://localhost:8080/cbicApi/cbic/gst11d?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst11d?month_date=2023-04-01&zone_code=70&type=commissary
    public Object getGst11D(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst49 = new GstSubParameterWiseQuery().QueryFor_gst11d_ZoneWise(month_date);

                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11D_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    //String commname=rsGst46b.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10A = rsGst46b.getInt("col10A");
                    int col12A = rsGst46b.getInt("col12A");
                    int col10B = rsGst46b.getInt("col10B");
                    int col12B = rsGst46b.getInt("col12B");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    int numerator = rsGst46b.getInt("numerator");
                    int denominator = rsGst46b.getInt("denominator");


                    if (denominator != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    // int way_to_grade = score.marks11b(totalScore);
                    int way_to_grade = score.marks11d(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.25;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GSTCUS(rsGst46b.getString("ZONE_NAME"),"All",totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst49 = new GstSubParameterWiseQuery().QueryFor_gst11d_CommissonaryWise(month_date,zone_code);



                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11D_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname=rsGst46b.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10A = rsGst46b.getInt("col10A");
                    int col12A = rsGst46b.getInt("col12A");
                    int col10B = rsGst46b.getInt("col10B");
                    int col12B = rsGst46b.getInt("col12B");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    int numerator = rsGst46b.getInt("numerator");
                    int denominator = rsGst46b.getInt("denominator");


                    if (denominator != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11d(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.25;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GSTCUS(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst49 = new GstSubParameterWiseQuery().QueryFor_gst11d_AllCommissonaryWise(month_date);
                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11D_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname=rsGst46b.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10A = rsGst46b.getInt("col10A");
                    int col12A = rsGst46b.getInt("col12A");
                    int col10B = rsGst46b.getInt("col10B");
                    int col12B = rsGst46b.getInt("col12B");
                    int Zonal_rank = 0;
                    String gst = "no";
                    // int way_to_grade = 0;
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    int numerator = rsGst46b.getInt("numerator");
                    int denominator = rsGst46b.getInt("denominator");


                    if (denominator != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11d(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.25;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GSTCUS(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }

  //  String queryGst46a="SELECT " +
//                        "cc.ZONE_CODE, " +
//                        " cc.COMM_NAME,"+
//                        "zc.ZONE_NAME, " +
//                        "(14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_1 " +
//                        "FROM mis_gst_commcode AS cc " +
//                        "RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';";
}
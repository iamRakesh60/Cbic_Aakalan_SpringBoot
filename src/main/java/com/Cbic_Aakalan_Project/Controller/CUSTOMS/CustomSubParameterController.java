package com.Cbic_Aakalan_Project.Controller.CUSTOMS;

import com.Cbic_Aakalan_Project.Service.CUSTOMS.CustomGreadeScore;
import com.Cbic_Aakalan_Project.Service.CUSTOMS.CustomRelaventAspect;
import com.Cbic_Aakalan_Project.Service.CUSTOMS.CustomSubParameterService;
import com.Cbic_Aakalan_Project.Service.DateCalculate;
import com.Cbic_Aakalan_Project.dao.Query.CGST.GstSubParameterWiseQuery;
import com.Cbic_Aakalan_Project.dao.Query.CUSTOMS.CustomSubParameterWiseQuery;
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
 * @Author: @RKS & @Nishant
 */

//@CrossOrigin
@RequestMapping("/cbicApi/cbic/custom")
@Controller
public class CustomSubParameterController {
    private Logger logger = LoggerFactory.getLogger(CustomSubParameterController.class);
    CustomGreadeScore score = new CustomGreadeScore();
    CustomSubParameterService customSubParameterService = new CustomSubParameterService();

    // conpleted codes are - 1, 2a, 4d, 5a, 5b,5c, 6a,6b,6c,6d,6e,6f,9a,9b,12A,12B

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus1*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus1")
    //  http://localhost:8080/cbicApi/cbic/custom/cus1?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus1?month_date=2024-04-01&zone_code=62&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus1?month_date=2024-04-01&type=all_commissary
    public Object getCus1(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus1_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(customSubParameterService.cus1Zone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus1a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(customSubParameterService.cus1ZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus1a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(customSubParameterService.cus1AllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus2a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus2a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus2a?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus2a?month_date=2024-10-01&type=all_commissary
    public Object Custom2a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus2a_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus2a_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus2a_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 10 in a loop
                for (int i = 1; i <= 8; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(9, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus2aZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus2aZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus2aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2b*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus2b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus2b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus2b?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus2b?month_date=2024-10-01&type=all_commissary
    public Object Custom2b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus2b_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus2b_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus2b_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 8 in a loop
                for (int i = 1; i <= 8; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(9, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus2bZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus2bZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus2bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2c*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus2c")
    //  http://localhost:8080/cbicApi/cbic/custom/cus2c?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus2c?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus2c?month_date=2024-10-01&type=all_commissary
    public Object Custom2c(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus2c_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus2c_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus2c_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 10 in a loop
                for (int i = 1; i <= 4; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(5, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus2cZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus2cZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus2cAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus3A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus3a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus3a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus3a?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus3a?month_date=2024-10-01&type=all_commissary
    public Object Custom3a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();

        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus3a_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus3a_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus3a_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 10 in a loop
                for (int i = 1; i <= 8; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(9, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus3aZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus3aZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus3aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus3B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus3b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus3b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus3b?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus3b?month_date=2024-10-01&type=all_commissary
    public Object Custom3b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus3b_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus3b_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus3b_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 10 in a loop
                for (int i = 1; i <= 8; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(9, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus3bZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus3bZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus3bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus3C*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus3c")
    //  http://localhost:8080/cbicApi/cbic/custom/cus3c?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus3c?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus3c?month_date=2024-10-01&type=all_commissary
    public Object Custom3c(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus3c_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus3c_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus3c_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 4 in a loop
                for (int i = 1; i <= 4; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(5, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus3cZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus3cZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus3cAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus4a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus4a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus4a?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus4a?month_date=2024-10-01&type=all_commissary
    public Object Custom4a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4a_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4a_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4a_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 7 in a loop
                for (int i = 1; i <= 7; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(8, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4aZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4aZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus4b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus4b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus4b?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus4b?month_date=2024-10-01&type=all_commissary
    public Object Custom4b(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4b_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4b_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4b_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 10 in a loop
                for (int i = 1; i <= 6; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(7, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4bZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4bZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4bAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
//        return allGstaList.stream()
//                .sorted(Comparator
//                        .comparing((GST4A gst4a) -> gst4a.getAbsolutevale().equals("0/0"))
//                        .thenComparing(GST4A::getTotal_score))
//                .collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4C*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus4c")
    //  http://localhost:8080/cbicApi/cbic/custom/cus4c?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus4c?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus4c?month_date=2024-10-01&type=all_commissary
    public Object Custom4c(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4c_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4c_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4c_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 4 in a loop
                for (int i = 1; i <= 4; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(5, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4cZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4cZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4cAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
//        return allGstaList.stream().filter(gst4a -> !"0/0".equals(gst4a.getAbsolutevale()))
//                .sorted(Comparator.comparing(GST4A::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4D*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus4d")
    //  http://localhost:8080/cbicApi/cbic/custom/cus4d?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus4d?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus4d?month_date=2024-10-01&type=all_commissary
    public Object Custom4d(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()) {
            String queryGst14aa;

            if("zone".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4d_ZoneWise(month_date);
            } else if ("commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4d_CommissonaryWise(month_date, zone_code);
            }else if ("all_commissary".equalsIgnoreCase(type)) {
                queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus4d_AllCommissonaryWise(month_date);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                // Set `month_date` for indices 1 to 10 in a loop
                for (int i = 1; i <= 4; i++) {
                    pstmt.setString(i, month_date);
                }
                if (type.equalsIgnoreCase("commissary")) {
                    pstmt.setString(5, zone_code); // Add zone_code for commissary
                }

                ResultSet rsGst14aa = pstmt.executeQuery();

                // Add result processing logic
                if("zone".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4dZone(rsGst14aa));
                } else if ("commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4dZoneWiseCommissionary(rsGst14aa));
                }else if ("all_commissary".equalsIgnoreCase(type)) {
                    allGstaList.addAll(customSubParameterService.cus4dAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus5A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus5a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus5a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus5a?month_date=2024-04-01&zone_code=69&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus5a?month_date=2024-04-01&type=all_commissary
    public Object getCus5a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        List<GSTCUS> allGstaList = new ArrayList<>();
        try (Connection con = JDBCConnection.getTNConnection()){
            if("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus5a_ZoneWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(customSubParameterService.cus5aZone(rsGst14aa));
                }

            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus5a_CommissonaryWise(month_date,zone_code);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, prev_month_new);
                    pstmt.setString(2, month_date);
                    pstmt.setString(3, zone_code);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(customSubParameterService.cus5aZoneWiseCommissionary(rsGst14aa));
                }

            }else if ("all_commissary".equalsIgnoreCase(type)) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus5a_AllCommissonaryWise(month_date);
                try (PreparedStatement pstmt = con.prepareStatement(queryGst14aa)) {
                    pstmt.setString(1, prev_month_new);
                    pstmt.setString(2, month_date);
                    ResultSet rsGst14aa = pstmt.executeQuery();
                    allGstaList.addAll(customSubParameterService.cus5aAllCommissionary(rsGst14aa));
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
//        return allGstaList.stream().filter(gst4a -> !"0/0".equals(gst4a.getAbsolutevale()))
//                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus5B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus5b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus5b?month_date=2022-02-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus5b?month_date=2022-02-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus5b?month_date=2022-02-01&type=all_commissary
    public Object CustomGst5b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;


        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus5b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {

                }

            } else if (type.equalsIgnoreCase("commissary")) {  // cus5b
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus5b_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {

                }
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus5b
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus5b_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
//        return allGstaList.stream().filter(gst4a -> !"0/0".equals(gst4a.getAbsolutevale()))
//                .sorted(Comparator.comparing(GST4A::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus5c*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus5c")
    //  http://localhost:8080/cbicApi/cbic/custom/cus5c?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus5c?month_date=2024-04-01&zone_code=58&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus5c?month_date=2024-04-01&type=all_commissary
    public Object CustomGst5c(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa =new CustomSubParameterWiseQuery().QueryFor_cus5c_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String zonename = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    if (zoneCode == null) {
                        continue;
                    }
                    String ra= CustomRelaventAspect.cus5c_RA;
                    String commname= "ALL";
                    String absval= rsGst14aa.getString("absval");

                    total = rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5c(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zonename,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) {  // cus5c
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus5c_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String zonename = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    if (zoneCode == null) {
                        continue;
                    }
                    String ra= CustomRelaventAspect.cus5c_RA;
                    String commname= rsGst14aa.getString("COMM_NAME");
                    String absval= rsGst14aa.getString("absval");
                    total = rsGst14aa.getDouble("total_score")  * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5c(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zonename,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus5c
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus5c_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String zonename = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    if (zoneCode == null) {
                        continue;
                    }
                    String ra= CustomRelaventAspect.cus5c_RA;
                    String commname= rsGst14aa.getString("COMM_NAME");
                    String absval= rsGst14aa.getString("absval");
                    total = rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    rank=score.c_marks5c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5c(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zonename,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
               // System.out.println("CUS 5C");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus6a?month_date=2015-03-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus6a?month_date=2015-03-01&zone_code=69&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus6a?month_date=2024-04-01&type=all_commissary
    public Object getCus6a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        double median = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryCustom6a = new CustomSubParameterWiseQuery().QueryFor_cus6a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6a);
                allGstaList.addAll(customSubParameterService.cus6aZone(rsGst14aa));
            }else if (type.equalsIgnoreCase("commissary")) { // cus 1
                String queryCustom5b = new CustomSubParameterWiseQuery().QueryFor_cus6a_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom5b);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col9_3a = rsGst14aa.getInt("col9_3a");
                    int col9_3b = rsGst14aa.getInt("col9_3b");
                    int col3_3a = rsGst14aa.getInt("col3_3a");
                    int col3_3b = rsGst14aa.getInt("col3_3b");
                    median = rsGst14aa.getDouble("median_total_9");
                    Double numerator_6c = rsGst14aa.getDouble("total_9");
                    //col3a is giving null for any date column, that reason total_score is o
                    int Zonal_rank = 0;
                    String gst = "no";
                    // String absval=String.valueOf(col9_3a+col9_3b)+"/"+String.valueOf(col3_3a+col3_3b);
                    String absval = "";
                    if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)) {
                        absval = String.valueOf(col9_3a+col9_3b ) + "/" + String.valueOf(col3_3a+col3_3b);
                    }
                    if((col3_3a+col3_3b) != 0) {
                        total = ((double) (col9_3a+col9_3b) * 100 / (col3_3a+col3_3b));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6a(totalScore);
                    int insentavization = score.c_marks6a(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    double sub_parameter_weighted_average = insentavization * 0.2;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)) {
                        gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                                Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                        allGstaList.add(gsta);}
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }System.out.println("median 6a commi wise :-" + median);
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryCustom5b = new CustomSubParameterWiseQuery().QueryFor_cus6a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom5b);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col9_3a = rsGst14aa.getInt("col9_3a");
                    int col9_3b = rsGst14aa.getInt("col9_3b");
                    int col3_3a = rsGst14aa.getInt("col3_3a");
                    int col3_3b = rsGst14aa.getInt("col3_3b");
                    median = rsGst14aa.getDouble("median_total_9");
                    Double numerator_6c = rsGst14aa.getDouble("total_9");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(col9_3a+col9_3b)+"/"+String.valueOf(col3_3a+col3_3b);
                    String absval = "";
                    if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)) {
                        absval = String.valueOf(col9_3a+col9_3b ) + "/" + String.valueOf(col3_3a+col3_3b);
                    }
                    if((col3_3a+col3_3b) != 0) {
                        total = ((double) (col9_3a+col9_3b) * 100 / (col3_3a+col3_3b));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6a(totalScore);
                    int insentavization = score.c_marks6a(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    double sub_parameter_weighted_average = insentavization * 0.2;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)) {
                        gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                                Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                        allGstaList.add(gsta);}
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }
                System.out.println("median 6a commi wise :-" + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus6b?month_date=2015-03-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus6b?month_date=2015-03-01&zone_code=69&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus6b?month_date=2024-04-01&type=all_commissary
    public Object getCus6b(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryCustom6b = new CustomSubParameterWiseQuery().QueryFor_cus6b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6b);
                allGstaList.addAll(customSubParameterService.cus6BZone(rsGst14aa));
            }else if (type.equalsIgnoreCase("commissary")) { // cus 1
                String queryCustom5b = new CustomSubParameterWiseQuery().QueryFor_cus6b_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom5b);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6b_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col9_3a = rsGst14aa.getInt("col18_3a");
                    int col9_3b = rsGst14aa.getInt("col18_3b");
                    int col3_3a = rsGst14aa.getInt("col12_3a");
                    int col3_3b = rsGst14aa.getInt("col12_3b");
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                    //String absval=String.valueOf(col9_3a+col9_3b)+"/"+String.valueOf(col3_3a+col3_3b);
                    String absval = "";
                    if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)) {
                        absval = String.valueOf(col9_3a+col9_3b ) + "/" + String.valueOf(col3_3a+col3_3b);
                    }
                    if((col3_3a+col3_3b) != 0) {
                        total = ((double) (col9_3a+col9_3b) * 100 / (col3_3a+col3_3b));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6b(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.2;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)){
                        gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                                Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                        allGstaList.add(gsta);}
                    allGstaList.sort((a, b) -> Double.compare(a.getTotal_score(), b.getTotal_score()));
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryCustom5b = new CustomSubParameterWiseQuery().QueryFor_cus6b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom5b);

                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6b_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col9_3a = rsGst14aa.getInt("col18_3a");
                    int col9_3b = rsGst14aa.getInt("col18_3b");
                    int col3_3a = rsGst14aa.getInt("col12_3a");
                    int col3_3b = rsGst14aa.getInt("col12_3b");
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                    // String absval=String.valueOf(col9_3a+col9_3b)+"/"+String.valueOf(col3_3a+col3_3b);
                    String absval = "";
                    if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)) {
                        absval = String.valueOf(col9_3a+col9_3b ) + "/" + String.valueOf(col3_3a+col3_3b);
                    }
                    if((col3_3a+col3_3b) != 0) {
                        total = ((double) (col9_3a+col9_3b) * 100 / (col3_3a+col3_3b));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6b(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.2;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)){
                        gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                                Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                        allGstaList.add(gsta);}
                    allGstaList.sort((a, b) -> Double.compare(a.getTotal_score(), b.getTotal_score()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6C*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6c")
    //  http://localhost:8080/cbicApi/cbic/custom/cus6c?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus6c?month_date=2024-03-01&zone_code=69&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus6c?month_date=2024-04-01&type=all_commissary
    public Object getCus6c(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryCustom6c = new CustomSubParameterWiseQuery().QueryFor_cus6c_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6c);
                allGstaList.addAll(customSubParameterService.cus6cZone(rsGst14aa));
            }else if (type.equalsIgnoreCase("commissary")) { // cus 1
                String queryCustom6c = new CustomSubParameterWiseQuery().QueryFor_cus6c_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6c);

                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6c_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col18_1 = rsGst14aa.getInt("col18_1");
                    int col8_ddm = rsGst14aa.getInt("col8_ddm");
                    int col9_ddm = rsGst14aa.getInt("col9_ddm");
                    double median = rsGst14aa.getDouble("median_6c");
                    Double numerator_6c = rsGst14aa.getDouble("col18_1");
                    String absval = rsGst14aa.getString("abs_value_pq");
                    //col3a is giving null for any date column, that reason total_score is o
                    int Zonal_rank = 0;
                    String gst = "no";
                    if((col8_ddm+col9_ddm) != 0) {
                        total = ((double) (col18_1) * 100 / (col8_ddm+col9_ddm));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6c(totalScore);
                    int insentavization = score.c_marks6c(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    double sub_parameter_weighted_average = insentavization * 0.2;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryCustom6c = new CustomSubParameterWiseQuery().QueryFor_cus6c_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6c);

                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6c_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col18_1 = rsGst14aa.getInt("col18_1");
                    int col8_ddm = rsGst14aa.getInt("col8_ddm");
                    int col9_ddm = rsGst14aa.getInt("col9_ddm");
                    double median = rsGst14aa.getDouble("median_6c");
                    Double numerator_6c = rsGst14aa.getDouble("col18_1");
                    String absval = rsGst14aa.getString("abs_value_pq");
                    //col3a is giving null for any date column, that reason total_score is o
                    int Zonal_rank = 0;
                    String gst = "no";
                    if((col8_ddm+col9_ddm) != 0) {
                        total = ((double) (col18_1) * 100 / (col8_ddm+col9_ddm));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6c(totalScore);
                    int insentavization = score.c_marks6c(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    double sub_parameter_weighted_average = insentavization * 0.2;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6D*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6d")
    //  http://localhost:8080/cbicApi/cbic/custom/cus6d?month_date=2015-03-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus6d?month_date=2015-03-01&zone_code=69&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus6d?month_date=2024-04-01&type=all_commissary
    public Object getCus6d(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryCustom6d = new CustomSubParameterWiseQuery().QueryFor_cus6d_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6d);
                allGstaList.addAll(customSubParameterService.cus6dZone(rsGst14aa));
            }else if (type.equalsIgnoreCase("commissary")) { // cus 1

                String queryCustom6d = new CustomSubParameterWiseQuery().QueryFor_cus6d_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6d);

                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6d_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col4_7 = rsGst14aa.getInt("col4_7");
                    int col5_cus3a = rsGst14aa.getInt("col5_cus3a");
                    int col8_cus3a = rsGst14aa.getInt("col8_cus3a");
                    int col5_cus3b = rsGst14aa.getInt("col5_cus3b");
                    int col8_cus3b = rsGst14aa.getInt("col8_cus3b");
                    double median = rsGst14aa.getDouble("median_6c");
                    Double numerator_6c = rsGst14aa.getDouble("col4_7");
                    String absval = rsGst14aa.getString("absolute_value");
                    total = rsGst14aa.getDouble("total_score");
                    //col3a is giving null for any date column, that reason total_score is o
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
//                    String absval=String.valueOf(col9_3a+col9_3b)+"/"+String.valueOf(col3_3a+col3_3b);
//                    if((col8_ddm+col9_ddm) != 0) {
//                        total = ((double) (col18_1) * 100 / (col8_ddm+col9_ddm));
//                    }else {
//                        total = 0.00;
//                    }

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6d(totalScore);
                    int insentavization = score.c_marks6d(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.1;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryCustom6d = new CustomSubParameterWiseQuery().QueryFor_cus6d_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6d);

                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6d_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    // String zoneName = rsGst14aa.getString("ZONE_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col4_7 = rsGst14aa.getInt("col4_7");
                    int col5_cus3a = rsGst14aa.getInt("col5_cus3a");
                    int col8_cus3a = rsGst14aa.getInt("col8_cus3a");
                    int col5_cus3b = rsGst14aa.getInt("col5_cus3b");
                    int col8_cus3b = rsGst14aa.getInt("col8_cus3b");
                    double median = rsGst14aa.getDouble("median_6c");
                    Double numerator_6c = rsGst14aa.getDouble("col4_7");
                    String absval = rsGst14aa.getString("absolute_value");
                    total = rsGst14aa.getDouble("total_score");

                    //col3a is giving null for any date column, that reason total_score is o
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
                    //String absval=String.valueOf(col9_3a+col9_3b)+"/"+String.valueOf(col3_3a+col3_3b);
//                        if((col8_ddm+col9_ddm) != 0) {
//                            total = ((double) (col18_1) * 100 / (col8_ddm+col9_ddm));
//                        }else {
//                            total = 0.00;
//                        }

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6d(totalScore);
                    int insentavization = score.c_marks6d(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.1;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6E*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6e")
    //  http://localhost:8080/cbicApi/cbic/custom/cus6e?month_date=2015-03-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus6e?month_date=2015-03-01&zone_code=69&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus6e?month_date=2024-04-01&type=all_commissary
    public Object getCus6e(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryCustom6e = new CustomSubParameterWiseQuery().QueryFor_cus6e_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6e);
                allGstaList.addAll(customSubParameterService.cus6eZone(rsGst14aa));
            }else if (type.equalsIgnoreCase("commissary")) { // cus 6e

                String queryCustom6e = new CustomSubParameterWiseQuery().QueryFor_cus6e_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6e);

                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6d_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col9_3a = rsGst14aa.getInt("col9_3a");
                    int col3_3a = rsGst14aa.getInt("col3_3a");
                    double median = rsGst14aa.getDouble("median_6e");
                    Double numerator_6c = rsGst14aa.getDouble("col9_3a");
                    //String absval = rsGst14aa.getString("absolute_value");
                    //total = rsGst14aa.getDouble("total_score");

                    int Zonal_rank = 0;
                    String gst = "no";
                    // int insentavization = 0;
                    String absval=String.valueOf(col9_3a)+"/"+String.valueOf(col3_3a);
                    if((col3_3a) != 0) {
                        total = ((double) (col9_3a) * 100 / (col3_3a));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6e(totalScore);
                    int insentavization = score.c_marks6e(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.1;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // cus6e
                String queryCustom6e = new CustomSubParameterWiseQuery().QueryFor_cus6e_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6e);

                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6e_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col9_3a = rsGst14aa.getInt("col9_3a");
                    int col3_3a = rsGst14aa.getInt("col3_3a");
                    double median = rsGst14aa.getDouble("median_6e");
                    Double numerator_6c = rsGst14aa.getDouble("col9_3a");
                    //String absval = rsGst14aa.getString("absolute_value");
                    //total = rsGst14aa.getDouble("total_score");

                    int Zonal_rank = 0;
                    String gst = "no";
                    // int insentavization = 0;
                    String absval=String.valueOf(col9_3a)+"/"+String.valueOf(col3_3a);
                    if((col3_3a) != 0) {
                        total = ((double) (col9_3a) * 100 / (col3_3a));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6e(totalScore);
                    int insentavization = score.c_marks6e(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.1;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;

    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6F*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6f")
    //  http://localhost:8080/cbicApi/cbic/custom/cus6f?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus6f?month_date=2024-04-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus6f?month_date=2024-04-01&type=all_commissary
    public Object CustomGst6f(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;
        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus6f_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus6fZone(rsGst14aa));

            } else if (type.equalsIgnoreCase("commissary")) {  // cus6f
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus6f_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus6f_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String absval = rsGst14aa.getString("absvl");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    String commname= rsGst14aa.getString("COMM_NAME");
                    Double numerator_6f = rsGst14aa.getDouble("col9");
                    median = rsGst14aa.getDouble("median6f");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6f(totalScore);
                    int insentavization = score.c_marks6f(totalScore);

                    if (numerator_6f > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "CUS6F";

                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.2 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("cus 6F median commissionary rate wise :- "+ median);
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus6f
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus6f_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus6f_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String absval = rsGst14aa.getString("absvl");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    String commname= rsGst14aa.getString("COMM_NAME");
                    Double numerator_6f = rsGst14aa.getDouble("col9");
                    median = rsGst14aa.getDouble("median6f");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6f(totalScore);
                    int insentavization = score.c_marks6f(totalScore);

                    if (numerator_6f > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "CUS6F";

                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.2 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("cus 6F median commissionary rate wise :- " + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus7A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus7a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus7a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus7a?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus7a?month_date=2024-10-01&type=all_commissary
    public Object Custom7a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus7a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus7aZone(rsGst14aa));
            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus7a_CommissonaryWise(month_date, zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus7aZoneWiseCommissionary(rsGst14aa));
            } else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus7a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus7aAllCommissionary(rsGst14aa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus7B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus7b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus7b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus7b?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus7b?month_date=2024-10-01&type=all_commissary
    public Object Custom7b(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus7b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus7bZone(rsGst14aa));
            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus7b_CommissonaryWise(month_date, zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus7bZoneWiseCommissionary(rsGst14aa));
            } else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus7b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus7bAllCommissionary(rsGst14aa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus8A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus8a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus8a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus8a?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus8a?month_date=2024-10-01&type=all_commissary
    public Object Custom8a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus8a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus8aZone(rsGst14aa));
            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus8a_CommissonaryWise(month_date, zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus8aZoneWiseCommissionary(rsGst14aa));
            } else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus8a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus8aAllCommissionary(rsGst14aa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus8B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus8b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus8b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus8b?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus8b?month_date=2024-10-01&type=all_commissary
    public Object Custom8b(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus8b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus8bZone(rsGst14aa));
            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus8b_CommissonaryWise(month_date, zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus8bZoneWiseCommissionary(rsGst14aa));
            } else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus8b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus8bAllCommissionary(rsGst14aa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus9A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus9a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus9a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus9a?month_date=2024-04-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus9a?month_date=2024-04-01&type=all_commissary
    public Object CustomGst9a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;

        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus9a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus9a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= "ALL";
                    double s3col9=rsGst14aa.getDouble("s3col9");
                    double s3col12=rsGst14aa.getDouble("s3col12");
                    double s3col3 =rsGst14aa.getDouble("s3col3");
                    double s6col9=rsGst14aa.getDouble("s6col9");
                    double s6col12=rsGst14aa.getDouble("s6col12");
                    double s6col3 =rsGst14aa.getDouble("s6col3");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    median = rsGst14aa.getDouble("median_9a");
                    Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s3col9 + s3col12 + s6col9 + s6col12) + "/" + String.format("%.2f", s3col3 + s6col3);


                    rank=score.c_marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks9a(totalScore);
                    int insentavization = score.c_marks9a(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_9a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    //System.out.println("insentavization3b after :-" + insentavization);

                    int Zonal_rank = 0;
                    String gst = "no";

                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("cus 9a median zone wise :- "+median);

            } else if (type.equalsIgnoreCase("commissary")) {  // cus9a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus9a_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus9a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s3col9=rsGst14aa.getDouble("s3col9");
                    double s3col12=rsGst14aa.getDouble("s3col12");
                    double s3col3 =rsGst14aa.getDouble("s3col3");
                    double s6col9=rsGst14aa.getDouble("s6col9");
                    double s6col12=rsGst14aa.getDouble("s6col12");
                    double s6col3 =rsGst14aa.getDouble("s6col3");
                    median = rsGst14aa.getDouble("median_9a");
                    Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";

                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s3col9 + s3col12 + s6col9 + s6col12) + "/" + String.format("%.2f", s3col3 + s6col3);

                    rank=score.c_marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks9a(totalScore);
                    int insentavization = score.c_marks9a(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_9a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }System.out.println("cus 9a median commissionary rate wise :- "+median);
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus9a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus9a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus9a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s3col9=rsGst14aa.getDouble("s3col9");
                    double s3col12=rsGst14aa.getDouble("s3col12");
                    double s3col3=rsGst14aa.getDouble("s3col3");
                    double s6col9=rsGst14aa.getDouble("s6col9");
                    double s6col12=rsGst14aa.getDouble("s6col12");
                    double s6col3=rsGst14aa.getDouble("s6col3");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    median = rsGst14aa.getDouble("median_9a");
                    Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s3col9 + s3col12 + s6col9 + s6col12) + "/" + String.format("%.2f", s3col3 + s6col3);


                    rank=score.c_marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks9a(totalScore);
                    int insentavization = score.c_marks9a(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_9a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("cus 9a median commissionary rate wise :- " +median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus9B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus9b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus9b?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus9b?month_date=2024-04-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus9b?month_date=2024-04-01&type=all_commissary
    public Object CustomGst9b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;

        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus9b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus9b_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= "ALL";
                    double s5col13 =rsGst14aa.getDouble("s5col13");
                    double s5col11 =rsGst14aa.getDouble("s5col11");
                    median =rsGst14aa.getDouble("Median");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    Double numerator_9b = rsGst14aa.getDouble("s5col13");
                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);


                    // rank=score.c_marks9b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks9b(totalScore);
                    int insentavization = score.c_marks9b(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_9b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    //System.out.println("insentavization9b after :-" + insentavization);
                    int Zonal_rank = 0;
                    String gst = "CUS9B";

                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("cus 9b median zone wise :- "+median);

            } else if (type.equalsIgnoreCase("commissary")) {  // cus9b
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus9b_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus9b_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s5col13 =rsGst14aa.getDouble("s5col13");
                    double s5col11 =rsGst14aa.getDouble("s5col11");
                    median =rsGst14aa.getDouble("median_value");
                    Double numerator_9b = rsGst14aa.getDouble("s5col13");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);

                    // rank=score.c_marks9b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks9b(totalScore);
                    int insentavization = score.c_marks9b(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_9b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("cus 9b median commissionary rate wise :- "+ median);
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus9b
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus9b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus9b_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s5col13 =rsGst14aa.getDouble("s5col13");
                    double s5col11 =rsGst14aa.getDouble("s5col11");
                    median =rsGst14aa.getDouble("median_value");
                    Double numerator_9b = rsGst14aa.getDouble("s5col13");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);

                    // rank=score.c_marks9b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks9b(totalScore);
                    int insentavization = score.c_marks9b(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_9b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("cus 9b median commissionary rate wise :- " + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus10A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus10a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus10a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus10a?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus10a?month_date=2024-10-01&type=all_commissary
    public Object Custom10a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        int N = convertMonthToFinancialMonth(month_date);

        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus10a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus10aZone(rsGst14aa, N));

            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus10a_CommissonaryWise(month_date, zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus10aZoneWiseCommissionary(rsGst14aa, N));

            } else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus10a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus10aAllCommissionary(rsGst14aa, N));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

//    private int convertMonthToFinancialMonth(String month_date) {
//        int month = Integer.parseInt(month_date.substring(5, 7)); //month_date.substring(5, 7) extracts the month part from the date string. For example, if month_date is "2023-04-01", month_date.substring(5, 7) would yield "04". Integer.parseInt converts this string representation of the month to an integer. Thus, for "2023-04-01", month would be 4.
//        return (month >= 4) ? (month - 3) : (month + 9); //The ternary operator (condition) ? value_if_true : value_if_false is used here to determine the financial month.
//    }

    private int convertMonthToFinancialMonth(String month_date) {
        int month = Integer.parseInt(month_date.substring(5, 7)); // Extracts the month part from the date string.

        // Map the month directly to its numeric representation (January = 1, December = 12).
        // This function is already structured to map months correctly:
        // month_date "2024-10-01" will return 10, "2024-01-01" will return 1, and so on.
        return month;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus10B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus10b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus10b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus10b?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus10b?month_date=2024-10-01&type=all_commissary
    public Object Custom10b(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus10b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus10bZone(rsGst14aa));

            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus10b_CommissonaryWise(month_date, zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus10bZoneWiseCommissionary(rsGst14aa));

            } else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus10b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus10bAllCommissionary(rsGst14aa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus11A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus11a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus11a?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus11a?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus11a?month_date=2024-10-01&type=all_commissary
    public Object Custom11a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus11a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus11aZone(rsGst14aa));
            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus11a_CommissonaryWise(month_date, zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus11aZoneWiseCommissionary(rsGst14aa));
            } else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus11a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus11aAllCommissionary(rsGst14aa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus11B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus11b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus11b?month_date=2024-10-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus11b?month_date=2024-10-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus11b?month_date=2024-10-01&type=all_commissary
    public Object Custom11b(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus11b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus11bZone(rsGst14aa));
            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus11b_CommissonaryWise(month_date, zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus11bZoneWiseCommissionary(rsGst14aa));
            } else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus11b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(customSubParameterService.cus11bAllCommissionary(rsGst14aa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus12A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus12a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus12a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus12a?month_date=2024-04-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus12a?month_date=2024-04-01&type=all_commissary
    public Object CustomGst12a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;
        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus12a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= "ALL";
                    total=rsGst14aa.getDouble("total_score") * 100;
                    Double numerator_12a = rsGst14aa.getDouble("numerator12A");
                    String absval = rsGst14aa.getString("absvl");
                    median = rsGst14aa.getDouble("median12A");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12a(totalScore,numerator_12a);
                    int insentavization = score.c_marks12a(totalScore,numerator_12a);

                    if (numerator_12a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "CUS12A";

                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("cus 12A median zone wise :- "+median);

            } else if (type.equalsIgnoreCase("commissary")) {  // cus11a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus12a_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    median =rsGst14aa.getDouble("median12A");
                    Double numerator_12a = rsGst14aa.getDouble("numerator12A");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    String absval = rsGst14aa.getString("absvl");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12a(totalScore,numerator_12a);
                    int insentavization = score.c_marks12a(totalScore,numerator_12a);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_12a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("cus 12a median commissionary rate wise :- "+ median);
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus11a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus12a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    median =rsGst14aa.getDouble("median12A");
                    Double numerator_12a = rsGst14aa.getDouble("numerator12A");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    String absval = rsGst14aa.getString("absvl");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12a(totalScore,numerator_12a);
                    int insentavization = score.c_marks12a(totalScore,numerator_12a);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_12a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating  point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("cus 12a median commissionary rate wise :- " + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GSTCUS::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus12B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus12b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus12b?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus12b?month_date=2024-04-01&zone_code=76&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus12b?month_date=2024-04-01&type=all_commissary
    public Object CustomGst12b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;
        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus12b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12b_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= "ALL";
                    total=rsGst14aa.getDouble("total_score") * 100;
                    String absval = rsGst14aa.getString("absvl");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12b(totalScore);
                    int insentavization = 0;

                    int Zonal_rank = 0;
                    String gst = "CUS12B";

                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) {  // cus12b
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus12b_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12b_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    String absval = rsGst14aa.getString("absvl");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12b(totalScore);
                    int insentavization = 0;

                    int Zonal_rank = 0;
                    String gst = "CUS12B";

                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus12b
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus12b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12b_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    String absval = rsGst14aa.getString("absvl");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12b(totalScore);
                    int insentavization = 0;

                    int Zonal_rank = 0;
                    String gst = "CUS12B";

                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
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
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13C*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13D*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13E*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
}

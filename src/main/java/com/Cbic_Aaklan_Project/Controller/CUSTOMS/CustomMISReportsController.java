package com.Cbic_Aaklan_Project.Controller.CUSTOMS;

import com.Cbic_Aaklan_Project.Service.CustomMISReportsService;
import com.Cbic_Aaklan_Project.dao.Query.CustomMISReportsQuery;
import com.Cbic_Aaklan_Project.dao.result.GetExecutionSQL;
import com.Cbic_Aaklan_Project.entity.CustomMISReports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.Cbic_Aaklan_Project.dao.pool.JDBCConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@CrossOrigin
@RequestMapping("/cbicApi/cbic/CustomMISReports")
@Controller
public class CustomMISReportsController {
    private Logger logger = LoggerFactory.getLogger(CustomMISReportsController.class);
    CustomMISReportsService customMISReportsService = new CustomMISReportsService();


    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Timely payment of  Refunds__1__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/TimelyPaymentOfRefunds")
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=6_Month
    public Object TimelyPaymentOfRefunds(@RequestParam String month_date, @RequestParam String type){
        List<CustomMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";


        // Define the expected zone codes from 54 to 81
        List<String> excludedZoneCodes = Arrays.asList("59", "61", "64", "65");
        List<String> expectedZoneCodes = IntStream.rangeClosed(54, 81)
                .mapToObj(String::valueOf)
                .filter(zoneCode -> !excludedZoneCodes.contains(zoneCode))
                .collect(Collectors.toList());
        // Add "DD" as an additional zone code for ZONE :- DRI DG (in antarang that zone name is  DGRI)
                expectedZoneCodes.add("DD");

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_CurrentMonth_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_1_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_2_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_3_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_4_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_5_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsGst14aa != null) rsGst14aa.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Map<String, CustomMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(CustomMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
               // String zoneName = customMISReportsService.getZoneName(zoneCode);
                resultMap.put(zoneCode, new CustomMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "Timely payment of Refunds"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(CustomMISReports::getZone_code))
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Export Obligation(EPCG)__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Export Obligation(AA)__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Disposal / Pendency of Provisional Assessments__4__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication__5__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/Adjudication")
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/Adjudication?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/Adjudication?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/Adjudication?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/Adjudication?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/Adjudication?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/Adjudication?month_date=2024-07-01&type=6_Month
    public Object Adjudication(@RequestParam String month_date, @RequestParam String type){
        List<CustomMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";


        // Define the expected zone codes from 54 to 81
        List<String> excludedZoneCodes = Arrays.asList("59", "61", "64", "65");
        List<String> expectedZoneCodes = IntStream.rangeClosed(54, 81)
                .mapToObj(String::valueOf)
                .filter(zoneCode -> !excludedZoneCodes.contains(zoneCode))
                .collect(Collectors.toList());
        // Add "DD" as an additional zone code for ZONE :- DRI DG (in antarang that zone name is  DGRI)
                 expectedZoneCodes.add("DD");

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_Adjudication_CurrentMonth_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_Adjudication_1_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_Adjudication_2_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_Adjudication_3_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_Adjudication_4_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_Adjudication_5_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsGst14aa != null) rsGst14aa.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Map<String, CustomMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(CustomMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
               // String zoneName = customMISReportsService.getZoneName(zoneCode);
                resultMap.put(zoneCode, new CustomMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "Adjudication"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(CustomMISReports::getZone_code))
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Investigation__6__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

//    @GetMapping("/total-sub-parameter-weighted-average")
//    public ResponseEntity<Double> getTotalSubParameterWeightedAverage() {
//        Double totalSum = customMISReportsService.calculateSubParameterWeightedAverageSum();
//        return ResponseEntity.ok(totalSum);
//    }
    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Arrests and Prosecution__7__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Monitoring of un-cleared/unclaimed cargo__8__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Disposal of confiscated Gold and NDPS__9__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/DisposalOfConfiscatedGoldAndNDPS")
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-07-01&type=6_Month
    public Object DisposalOfConfiscatedGoldAndNDPS(@RequestParam String month_date, @RequestParam String type){
        List<CustomMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";


        // Define the expected zone codes from 54 to 81
        List<String> excludedZoneCodes = Arrays.asList("59", "61", "64", "65");
        List<String> expectedZoneCodes = IntStream.rangeClosed(54, 81)
                .mapToObj(String::valueOf)
                .filter(zoneCode -> !excludedZoneCodes.contains(zoneCode))
                .collect(Collectors.toList());
        // Add "DD" as an additional zone code for ZONE :- DRI DG (in antarang that zone name is  DGRI)
        expectedZoneCodes.add("DD");

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_CurrentMonth_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_DisposalOfConfiscatedGoldAndNDPS(rsGst14aa, formattedDate, "Disposal of confiscated Gold and NDPS"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_1_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_DisposalOfConfiscatedGoldAndNDPS(rsGst14aa, formattedDate, "Disposal of confiscated Gold and NDPS"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_2_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_DisposalOfConfiscatedGoldAndNDPS(rsGst14aa, formattedDate, "Disposal of confiscated Gold and NDPS"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_3_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_DisposalOfConfiscatedGoldAndNDPS(rsGst14aa, formattedDate, "Disposal of confiscated Gold and NDPS"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_4_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_DisposalOfConfiscatedGoldAndNDPS(rsGst14aa, formattedDate, "Disposal of confiscated Gold and NDPS"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_5_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_DisposalOfConfiscatedGoldAndNDPS(rsGst14aa, formattedDate, "Disposal of confiscated Gold and NDPS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsGst14aa != null) rsGst14aa.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Map<String, CustomMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(CustomMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
               // String zoneName = customMISReportsService.getZoneName(zoneCode);
                resultMap.put(zoneCode, new CustomMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "Disposal of confiscated Gold and NDPS"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(CustomMISReports::getZone_code))
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Recovery of Arrears__10__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Warehousing bonds__11__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Commissioner (Appeals)__12__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/CommissionerAppeals")
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/CommissionerAppeals?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/CommissionerAppeals?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/CommissionerAppeals?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/CommissionerAppeals?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/CommissionerAppeals?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/CommissionerAppeals?month_date=2024-07-01&type=6_Month
    public Object CommissionerAppeals(@RequestParam String month_date, @RequestParam String type){
        List<CustomMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";


        // Define the expected zone codes from 54 to 81
        List<String> excludedZoneCodes = Arrays.asList("59", "61", "64", "65");
        List<String> expectedZoneCodes = IntStream.rangeClosed(54, 81)
                .mapToObj(String::valueOf)
                .filter(zoneCode -> !excludedZoneCodes.contains(zoneCode))
                .collect(Collectors.toList());
        // Add "DD" as an additional zone code for ZONE :- DRI DG (in antarang that zone name is  DGRI)
        expectedZoneCodes.add("DD");

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_CommissionerAppeals_CurrentMonth_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_CommissionerAppeals(rsGst14aa, formattedDate, "Commissioner (Appeals)"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_CommissionerAppeals_1_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_CommissionerAppeals(rsGst14aa, formattedDate, "Commissioner (Appeals)"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_CommissionerAppeals_2_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_CommissionerAppeals(rsGst14aa, formattedDate, "Commissioner (Appeals)"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_CommissionerAppeals_3_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_CommissionerAppeals(rsGst14aa, formattedDate, "Commissioner (Appeals)"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_CommissionerAppeals_4_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_CommissionerAppeals(rsGst14aa, formattedDate, "Commissioner (Appeals)"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_CommissionerAppeals_5_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_CommissionerAppeals(rsGst14aa, formattedDate, "Commissioner (Appeals)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsGst14aa != null) rsGst14aa.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Map<String, CustomMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(CustomMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
               // String zoneName = customMISReportsService.getZoneName(zoneCode);
                resultMap.put(zoneCode, new CustomMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "Commissioner (Appeals)"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(CustomMISReports::getZone_code))
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Audit__13__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

}

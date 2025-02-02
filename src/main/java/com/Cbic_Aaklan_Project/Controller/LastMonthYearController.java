package com.Cbic_Aaklan_Project.Controller;

import com.Cbic_Aaklan_Project.Service.DateCalculate;
import com.Cbic_Aaklan_Project.dao.result.GetExecutionSQL;
import com.Cbic_Aaklan_Project.entity.MonthlyYearlyScore;
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
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin
@RequestMapping("/cbicApi/cbic/MonthYear")
@Controller
public class LastMonthYearController {
    private Logger logger = LoggerFactory.getLogger(LastMonthYearController.class);


    @ResponseBody
    @RequestMapping(value = "return_filling/month")
    //  http://localhost:8080/cbicApi/cbic/MonthYear/return_filling/month?type=last3month&month_date=2024-04-01&zone_code=70
    // http://localhost:8080/cbicApi/cbic/MonthYear/return_filling/month?type=monthly&month_date=2024-04-30
    // http://localhost:8080/cbicApi/cbic/MonthYear/return_filling/month?type=comm_name&month_date=2024-04-30&zone_code=70
    public Object returnFiling(@RequestParam String type, @RequestParam String month_date, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String comm_name) {
        List<MonthlyYearlyScore> allGstaList = new ArrayList<>();
        MonthlyYearlyScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa= null;
        ResultSet rsGst14aa_gst2= null;ResultSet rsGst14aa_gst7= null;
        ResultSet currentMonthValue= null;
        double total_score_of_year = 0;
        double total_score_previous_year = 0;
        double total_score_previous_year_2 = 0;
        try {

            if (type.equalsIgnoreCase("last3month")) { // for parameter 1
                //     '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'

//					String curentMonth = "SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-%d') AS month_date;";
//					currentMonthValue = GetExecutionSQL.getResult(curentMonth);
//					String month_date = currentMonthValue.getString("last_month");
//					String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//		            String next_month_new = DateCalculate.getNextMonth(month_date);

                String query_assessment_for_returnFiling= "SELECT zc.ZONE_NAME,cc.ZONE_CODE,'Return Filling' AS parameter_name,\n"
                        + "    (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score_of_month,\n"
                        + "    (SELECT (SUM(sub1.GSTR_3BM_F - sub1.GSTR_3BM_D) / NULLIF(SUM(sub1.GSTR_3BM_F), 0)) * 100\n"
                        + "     FROM mis_gst_gst_2 AS sub1\n"
                        + "     JOIN mis_gst_commcode AS cc1 ON cc1.COMM_CODE = sub1.COMM_CODE\n"
                        + "     WHERE sub1.MM_YYYY = DATE_FORMAT(DATE_SUB('" + month_date + "', INTERVAL 1 MONTH), '%Y-%m-01')AND cc1.ZONE_CODE = cc.ZONE_CODE\n"
                        + "    ) AS total_score_previous_month,\n"
                        + "    (SELECT (SUM(sub2.GSTR_3BM_F - sub2.GSTR_3BM_D) / NULLIF(SUM(sub2.GSTR_3BM_F), 0)) * 100\n"
                        + "     FROM mis_gst_gst_2 AS sub2\n"
                        + "     JOIN mis_gst_commcode AS cc2 ON cc2.COMM_CODE = sub2.COMM_CODE\n"
                        + "     WHERE sub2.MM_YYYY = DATE_FORMAT(DATE_SUB('" + month_date + "', INTERVAL 2 MONTH), '%Y-%m-01')AND cc2.ZONE_CODE = cc.ZONE_CODE\n"
                        + "    ) AS total_score_previous_month_2\n"
                        + "FROM mis_gst_commcode AS cc \n"
                        + "RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                        + "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                        + "WHERE 14c.MM_YYYY = '" + month_date + "'  AND cc.ZONE_CODE = '" + zone_code + "'\n"
                        + "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE;\n"
                        + "";


                rsGst14aa_gst2 = GetExecutionSQL.getResult(query_assessment_for_returnFiling);

                while (rsGst14aa_gst2.next()) {
                    String zoneName = rsGst14aa_gst2.getString("ZONE_NAME");
                    zone_code = rsGst14aa_gst2.getString("ZONE_CODE");
                    double current_t_score = rsGst14aa_gst2.getDouble("total_score_of_month");
                    double previous_t_score = rsGst14aa_gst2.getDouble("total_score_previous_month");
                    double previous_t_score_2 = rsGst14aa_gst2.getDouble("total_score_previous_month_2");
                    String commName = "ALL";
                    String parameter_name = rsGst14aa_gst2.getString("parameter_name");

                    // Formatting the total score
                    String formattedTotal = String.format("%.2f", current_t_score);
                    double total_score_of_month = Double.parseDouble(formattedTotal);

                    String formattedTotal2 = String.format("%.2f", previous_t_score);
                    double total_score_previous_month = Double.parseDouble(formattedTotal2);

                    String formattedTotal3 = String.format("%.2f", previous_t_score_2);
                    double total_score_previous_month_2 = Double.parseDouble(formattedTotal3);

                    totalScore = new MonthlyYearlyScore(zoneName, commName, zone_code, parameter_name, total_score_of_month, total_score_previous_month,
                            total_score_previous_month_2, total_score_of_year, total_score_previous_year, total_score_previous_year_2);
                    allGstaList.add(totalScore);
                }
                System.out.println("last 3 month");
            } else if (type.equalsIgnoreCase("monthly")) { // all zone-wise monthly score calculation
                //String query_assessment;
                String query_assessment= "WITH monthly_scores AS (" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, 'Return Filling' AS parameter_name,YEAR(14c.MM_YYYY) AS year, MONTH(14c.MM_YYYY) AS month, " +
                        "           SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3, " +
                        "           (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score " +
                        "    FROM mis_gst_commcode AS cc " +
                        "    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
                        "    WHERE 14c.MM_YYYY >= DATE_SUB(DATE '" + month_date + "', INTERVAL 3 MONTH) AND " +
                        "          14c.MM_YYYY < DATE '" + month_date + "' " +
                        "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, YEAR(14c.MM_YYYY), MONTH(14c.MM_YYYY) " +
                        "), " +
                        "monthly_totals AS (" +
                        "    SELECT ZONE_NAME, ZONE_CODE,'Return Filling' AS parameter_name, " +
                        "           MAX(CASE WHEN month = MONTH(DATE_SUB(DATE '" + month_date + "', INTERVAL 2 MONTH)) THEN total_score ELSE NULL END) AS  total_score_previous_month_2, " +
                        "           MAX(CASE WHEN month = MONTH(DATE_SUB(DATE '" + month_date + "', INTERVAL 1 MONTH)) THEN total_score ELSE NULL END) AS total_score_previous_month, " +
                        "           MAX(CASE WHEN month = MONTH(DATE '" + month_date + "') THEN total_score ELSE NULL END) AS total_score_of_month " +
                        "    FROM monthly_scores " +
                        "    GROUP BY ZONE_NAME, ZONE_CODE " +
                        "), " +
                        "three_month_total AS (" +
                        "    SELECT ZONE_NAME, ZONE_CODE, " +
                        "           COALESCE(total_score_previous_month_2, 0) + COALESCE(total_score_previous_month, 0) + COALESCE(total_score_of_month, 0) AS total_score_last_3_months " +
                        "    FROM monthly_totals " +
                        "), " +
                        "ranked_scores AS (" +
                        "    SELECT ZONE_NAME, ZONE_CODE, total_score_last_3_months AS total_score, " +
                        "           DENSE_RANK() OVER (ORDER BY total_score_last_3_months DESC) AS z_rank " +
                        "    FROM three_month_total " +
                        ") " +
                        "SELECT r.z_rank, m.ZONE_NAME, m.ZONE_CODE, m.total_score_previous_month_2, m.total_score_previous_month, m.total_score_of_month, r.total_score " +
                        "FROM ranked_scores r " +
                        "JOIN monthly_totals m ON r.ZONE_NAME = m.ZONE_NAME AND r.ZONE_CODE = m.ZONE_CODE " +
                        "ORDER BY r.z_rank;";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    double current_t_score = rsGst14aa.getDouble("total_score_of_month");
                    double previous_t_score  = rsGst14aa.getDouble("total_score_previous_month");
                    double previous_t_score_2  = rsGst14aa.getDouble("total_score_previous_month_2");
                    double total_score = rsGst14aa.getDouble("total_score");
                    int z_rank = rsGst14aa.getInt("z_rank");

//                         String parameter_name = rsGst14aa.getString("parameter_name");


                    // Formatting the total score
                    String formattedTotal = String.format("%.2f", current_t_score);
                    double total_score_of_month = Double.parseDouble(formattedTotal);

                    String formattedTotal2 = String.format("%.2f", previous_t_score);
                    double total_score_previous_month = Double.parseDouble(formattedTotal2);

                    String formattedTotal3 = String.format("%.2f", previous_t_score_2);
                    double total_score_previous_month_2 = Double.parseDouble(formattedTotal3);
                    totalScore = new MonthlyYearlyScore(zoneName, "ALL", zone_code, "Return Filling", total_score_of_month, total_score_previous_month,
                            total_score_previous_month_2, total_score_of_year, total_score_previous_year, total_score_previous_year_2);
                    allGstaList.add(totalScore);


//                        totalScore = new MonthlyYearlyScore(zoneName, "ALL", zone_code, "Return Filling",current_t_score, previous_t_score, previous_t_score_2, total_score,0,0  );
//                        allGstaList.add(totalScore);
                }
                System.out.println("lasttttttttttttttttttttttttttt");
            } else if (type.equalsIgnoreCase("comm_name")) {
                String query_assessment= "WITH monthly_scores AS (\r\n"
                        + "    SELECT\r\n"
                        + "        zc.ZONE_NAME,\r\n"
                        + "        cc.ZONE_CODE,\r\n"
                        + "        cc.COMM_NAME,  -- Include COMM_NAME here\r\n"
                        + "        YEAR(14c.MM_YYYY) AS year,\r\n"
                        + "        MONTH(14c.MM_YYYY) AS month,\r\n"
                        + "        SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21,\r\n"
                        + "        SUM(14c.GSTR_3BM_F) AS col3,\r\n"
                        + "        (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\r\n"
                        + "    FROM\r\n"
                        + "        mis_gst_commcode AS cc\r\n"
                        + "    RIGHT JOIN\r\n"
                        + "        mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\r\n"
                        + "    LEFT JOIN\r\n"
                        + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                        + "    WHERE\r\n"
                        + "        14c.MM_YYYY >= DATE_SUB(DATE '" + month_date + "', INTERVAL 3 MONTH) AND\r\n"
                        + "        14c.MM_YYYY < DATE '" + month_date + "'\r\n"
                        + "    GROUP BY\r\n"
                        + "        zc.ZONE_NAME,\r\n"
                        + "        cc.ZONE_CODE,\r\n"
                        + "        cc.COMM_NAME,  -- Group by COMM_NAME\r\n"
                        + "        YEAR(14c.MM_YYYY),\r\n"
                        + "        MONTH(14c.MM_YYYY)\r\n"
                        + "),\r\n"
                        + "monthly_totals AS (\r\n"
                        + "    SELECT\r\n"
                        + "        ZONE_NAME,\r\n"
                        + "        ZONE_CODE,\r\n"
                        + "        COMM_NAME,  -- Include COMM_NAME here\r\n"
                        + "        MAX(CASE WHEN month = MONTH(DATE_SUB(DATE '" + month_date + "', INTERVAL 2 MONTH)) THEN total_score ELSE NULL END) AS total_score_previous_month_2,\r\n"
                        + "        MAX(CASE WHEN month = MONTH(DATE_SUB(DATE '" + month_date + "', INTERVAL 1 MONTH)) THEN total_score ELSE NULL END) AS total_score_previous_month,\r\n"
                        + "        MAX(CASE WHEN month = MONTH(DATE '" + month_date + "') THEN total_score ELSE NULL END) AS total_score_of_month\r\n"
                        + "    FROM\r\n"
                        + "        monthly_scores\r\n"
                        + "    GROUP BY\r\n"
                        + "        ZONE_NAME,\r\n"
                        + "        ZONE_CODE,\r\n"
                        + "        COMM_NAME  -- Group by COMM_NAME\r\n"
                        + "),\r\n"
                        + "three_month_total AS (\r\n"
                        + "    SELECT\r\n"
                        + "        ZONE_NAME,\r\n"
                        + "        ZONE_CODE,\r\n"
                        + "        COMM_NAME,  -- Include COMM_NAME here\r\n"
                        + "        COALESCE(total_score_previous_month_2, 0) + COALESCE(total_score_previous_month, 0) + COALESCE(total_score_of_month, 0) AS total_score_last_3_months\r\n"
                        + "    FROM\r\n"
                        + "        monthly_totals\r\n"
                        + "),\r\n"
                        + "ranked_scores AS (\r\n"
                        + "    SELECT\r\n"
                        + "        ZONE_NAME,\r\n"
                        + "        ZONE_CODE,\r\n"
                        + "        COMM_NAME,  -- Include COMM_NAME here\r\n"
                        + "        total_score_last_3_months AS total_score,\r\n"
                        + "        DENSE_RANK() OVER (ORDER BY total_score_last_3_months ) AS z_rank\r\n"
                        + "    FROM\r\n"
                        + "        three_month_total\r\n"
                        + ")\r\n"
                        + "SELECT\r\n"
                        + "    r.z_rank,\r\n"
                        + "    m.ZONE_NAME,\r\n"
                        + "    m.ZONE_CODE,\r\n"
                        + "    m.COMM_NAME,  -- Select COMM_NAME here\r\n"
                        + "    m.total_score_previous_month_2,\r\n"
                        + "    m.total_score_previous_month,\r\n"
                        + "    m.total_score_of_month,\r\n"
                        + "    r.total_score\r\n"
                        + "FROM\r\n"
                        + "    ranked_scores r\r\n"
                        + "JOIN\r\n"
                        + "    monthly_totals m ON r.ZONE_NAME = m.ZONE_NAME AND r.ZONE_CODE = m.ZONE_CODE AND r.COMM_NAME = m.COMM_NAME\r\n"
                        + "    WHERE\r\n"
                        + "    m.ZONE_CODE = '" + zone_code + "'\r\n"
                        + "\r\n"
                        + "ORDER BY \r\n"
                        + "    r.z_rank;\r\n"
                        + "";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commName=rsGst14aa.getString("COMM_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double current_t_score = rsGst14aa.getDouble("total_score_of_month");
                    double previous_t_score  = rsGst14aa.getDouble("total_score_previous_month");
                    double previous_t_score_2  = rsGst14aa.getDouble("total_score_previous_month_2");
                    double total_score = rsGst14aa.getDouble("total_score");
                    int z_rank = rsGst14aa.getInt("z_rank");

//                     String parameter_name = rsGst14aa.getString("parameter_name");



                    // Formatting the total score
                    String formattedTotal = String.format("%.2f", current_t_score);
                    double total_score_of_month = Double.parseDouble(formattedTotal);

                    String formattedTotal2 = String.format("%.2f", previous_t_score);
                    double total_score_previous_month = Double.parseDouble(formattedTotal2);

                    String formattedTotal3 = String.format("%.2f", previous_t_score_2);
                    double total_score_previous_month_2 = Double.parseDouble(formattedTotal3);
                    totalScore = new MonthlyYearlyScore(zoneName,commName, zone_code,"Return Filling", total_score_of_month, total_score_previous_month,
                            total_score_previous_month_2, total_score_of_year, total_score_previous_year, total_score_previous_year_2);
                    allGstaList.add(totalScore);
                }
                System.out.println("last3 month commi");
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
        return allGstaList;
    }
    // **********************************************Scrutiny & Assessment last 3 month details************************************************************************************************
    @ResponseBody
    @RequestMapping(value = "scrutiny/assessment/month")
    //  http://localhost:8080/cbicApi/cbic/MonthYear/scrutiny/assessment/month?type=last3month&month_date=2024-04-01&zone_code=56
    // http://localhost:8080/cbicApi/cbic/MonthYear/scrutiny/assessment/month?type=comm_name&month_date=2024-04-01&zone_code=56
    public Object scrutiny(@RequestParam String type, @RequestParam String month_date, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String comm_name) {
        List<MonthlyYearlyScore> allGstaList = new ArrayList<>();
        MonthlyYearlyScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa= null;
        ResultSet rsGst14aa_gst2= null;ResultSet rsGst14aa_gst7= null;
        ResultSet currentMonthValue= null;
        double total_score_of_year = 0;
        double total_score_previous_year = 0;
        double total_score_previous_year_2 = 0;
        try {

            if (type.equalsIgnoreCase("last3month")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String prev_month_two = DateCalculate.getPreviousMonthTwo(month_date);
                String prev_month_first = DateCalculate.getPreviousMonthfirst(month_date);
                // for parameter 1
                //     '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'

//					String curentMonth = "SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-%d') AS month_date;";
//					currentMonthValue = GetExecutionSQL.getResult(curentMonth);
//					String month_date = currentMonthValue.getString("last_month");
//					String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//		            String next_month_new = DateCalculate.getNextMonth(month_date);

                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String query_assessment_for_refunds="WITH ranked_data_3a AS (\n" +
                        "    SELECT \n" +
                        "        current_data.ZONE_NAME, \n" +
                        "        current_data.ZONE_CODE,\n" +
                        "        (SUM(current_data.col4 + current_data.col9 + current_data.col10) * 100) /\n" +
                        "        SUM(current_data.col2 + previous_data.col1) AS score_of_subpara3a\n" +
                        "    FROM (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,\n" +
                        "            SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, \n" +
                        "            SUM(14c.OUTCOME_SECTION_61) AS col10,\n" +
                        "            SUM(14c.RETURN_SCRUTINY) AS col2\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + month_date + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "    ) AS current_data\n" +
                        "    JOIN (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            SUM(14c.CLOSING_NO) AS col1\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY ='" + prev_month_new + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "    ) AS previous_data\n" +
                        "    ON \n" +
                        "        current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                        "    GROUP BY \n" +
                        "        current_data.ZONE_NAME, current_data.ZONE_CODE\n" +
                        "), \n" +
                        "final_data_3b AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        cc.ZONE_CODE,\n" +
                        "        (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) /\n" +
                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                        "    FROM \n" +
                        "        mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN \n" +
                        "        mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        14c.MM_YYYY = '" + month_date + "' \n" +
                        "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "    GROUP BY \n" +
                        "        cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "),\n" +
                        "ranked_data_3a_prev1 AS (\n" +
                        "    SELECT \n" +
                        "        current_data.ZONE_NAME, \n" +
                        "        current_data.ZONE_CODE,\n" +
                        "        (SUM(current_data.col4 + current_data.col9 + current_data.col10) * 100) /\n" +
                        "        SUM(current_data.col2 + previous_data.col1) AS score_of_subpara3a\n" +
                        "    FROM (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,\n" +
                        "            SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, \n" +
                        "            SUM(14c.OUTCOME_SECTION_61) AS col10,\n" +
                        "            SUM(14c.RETURN_SCRUTINY) AS col2\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + prev_month_new + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "    ) AS current_data\n" +
                        "    JOIN (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            SUM(14c.CLOSING_NO) AS col1\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + prev_month_two + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "    ) AS previous_data\n" +
                        "    ON \n" +
                        "        current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                        "    GROUP BY \n" +
                        "        current_data.ZONE_NAME, current_data.ZONE_CODE\n" +
                        "),\n" +
                        "final_data_3b_prev1 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        cc.ZONE_CODE,\n" +
                        "        (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) /\n" +
                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                        "    FROM \n" +
                        "        mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN \n" +
                        "        mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        14c.MM_YYYY = '" + prev_month_new + "' \n" +
                        "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "    GROUP BY \n" +
                        "        cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "),\n" +
                        "ranked_data_3a_prev2 AS (\n" +
                        "    SELECT \n" +
                        "        current_data.ZONE_NAME, \n" +
                        "        current_data.ZONE_CODE,\n" +
                        "        (SUM(current_data.col4 + current_data.col9 + current_data.col10) * 100) /\n" +
                        "        SUM(current_data.col2 + previous_data.col1) AS score_of_subpara3a\n" +
                        "    FROM (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,\n" +
                        "            SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, \n" +
                        "            SUM(14c.OUTCOME_SECTION_61) AS col10,\n" +
                        "            SUM(14c.RETURN_SCRUTINY) AS col2\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY =  '" + prev_month_two + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "    ) AS current_data\n" +
                        "    JOIN (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            SUM(14c.CLOSING_NO) AS col1\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + prev_month_first + "'\n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "    ) AS previous_data\n" +
                        "    ON \n" +
                        "        current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                        "    GROUP BY \n" +
                        "        current_data.ZONE_NAME, current_data.ZONE_CODE\n" +
                        "),\n" +
                        "final_data_3b_prev2 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        cc.ZONE_CODE,\n" +
                        "        (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) /\n" +
                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                        "    FROM \n" +
                        "        mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN \n" +
                        "        mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        14c.MM_YYYY = '" + prev_month_two + "'\n" +
                        "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "    GROUP BY \n" +
                        "        cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        ")\n" +
                        "SELECT \n" +
                        "    m3a.ZONE_NAME, \n" +
                        "    m3a.ZONE_CODE,\n" +
                        "    'Scrutiny/Assessment' AS parameter_name,\n" +
                        "    (m3a.score_of_subpara3a + f3b.score_of_parameter3b) AS total_score_of_month,\n" +
                        "    (m3a_prev1.score_of_subpara3a + f3b_prev1.score_of_parameter3b) AS total_score_previous_month,\n" +
                        "    (m3a_prev2.score_of_subpara3a + f3b_prev2.score_of_parameter3b) AS total_score_two_months_prior\n" +
                        "FROM \n" +
                        "    ranked_data_3a AS m3a\n" +
                        "JOIN \n" +
                        "    final_data_3b AS f3b\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = f3b.ZONE_CODE\n" +
                        "JOIN \n" +
                        "    ranked_data_3a_prev1 AS m3a_prev1\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = m3a_prev1.ZONE_CODE\n" +
                        "JOIN \n" +
                        "    final_data_3b_prev1 AS f3b_prev1\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = f3b_prev1.ZONE_CODE\n" +
                        "JOIN \n" +
                        "    ranked_data_3a_prev2 AS m3a_prev2\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = m3a_prev2.ZONE_CODE\n" +
                        "JOIN \n" +
                        "    final_data_3b_prev2 AS f3b_prev2\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = f3b_prev2.ZONE_CODE;";

                rsGst14aa_gst2 = GetExecutionSQL.getResult(query_assessment_for_refunds);

                while (rsGst14aa_gst2.next()) {
                    String zoneName = rsGst14aa_gst2.getString("ZONE_NAME");
                    zone_code = rsGst14aa_gst2.getString("ZONE_CODE");
                    double current_t_score = rsGst14aa_gst2.getDouble("total_score_of_month");
                    double previous_t_score = rsGst14aa_gst2.getDouble("total_score_previous_month");
                    double previous_t_score_2 = rsGst14aa_gst2.getDouble("total_score_two_months_prior");
                    String commName = "ALL";
                    String parameter_name = "Scrutiny & Assessment";

                    // Formatting the total score
                    String formattedTotal = String.format("%.2f", current_t_score);
                    double total_score_of_month = Double.parseDouble(formattedTotal);

                    String formattedTotal2 = String.format("%.2f", previous_t_score);
                    double total_score_previous_month = Double.parseDouble(formattedTotal2);

                    String formattedTotal3 = String.format("%.2f", previous_t_score_2);
                    double total_score_previous_month_2 = Double.parseDouble(formattedTotal3);

                    totalScore = new MonthlyYearlyScore(zoneName, commName, zone_code, parameter_name, total_score_of_month, total_score_previous_month,
                            total_score_previous_month_2, total_score_of_year, total_score_previous_year, total_score_previous_year_2);
                    allGstaList.add(totalScore);
                }
                System.out.println("last 3 month");
            }  else if (type.equalsIgnoreCase("comm_name")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String prev_month_two = DateCalculate.getPreviousMonthTwo(month_date);
                String prev_month_first = DateCalculate.getPreviousMonthfirst(month_date);
                String query_assessment= "WITH ranked_data_3a AS (\n" +
                        "    SELECT \n" +
                        "        current_data.ZONE_NAME, \n" +
                        "        current_data.ZONE_CODE,\n" +
                        "        current_data.COMM_NAME,\n" +
                        "        (SUM(current_data.col4 + current_data.col9 + current_data.col10) * 100) /\n" +
                        "        SUM(current_data.col2 + previous_data.col1) AS score_of_subpara3a\n" +
                        "    FROM (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            cc.COMM_NAME,  -- Added COMM_NAME\n" +
                        "            SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,\n" +
                        "            SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, \n" +
                        "            SUM(14c.OUTCOME_SECTION_61) AS col10,\n" +
                        "            SUM(14c.RETURN_SCRUTINY) AS col2\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + month_date + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME  -- Added COMM_NAME\n" +
                        "    ) AS current_data\n" +
                        "    JOIN (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            cc.COMM_NAME,  -- Added COMM_NAME\n" +
                        "            SUM(14c.CLOSING_NO) AS col1\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + prev_month_new + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME  -- Added COMM_NAME\n" +
                        "    ) AS previous_data\n" +
                        "    ON \n" +
                        "        current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                        "    GROUP BY \n" +
                        "        current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.COMM_NAME\n" +
                        "), \n" +
                        "final_data_3b AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        cc.ZONE_CODE,\n" +
                        "        cc.COMM_NAME,  -- Added COMM_NAME\n" +
                        "        (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) /\n" +
                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                        "    FROM \n" +
                        "        mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN \n" +
                        "        mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        14c.MM_YYYY = '" + month_date + "' \n" +
                        "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "    GROUP BY \n" +
                        "        cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME  -- Added COMM_NAME\n" +
                        "),\n" +
                        "ranked_data_3a_prev1 AS (\n" +
                        "    SELECT \n" +
                        "        current_data.ZONE_NAME, \n" +
                        "        current_data.ZONE_CODE,\n" +
                        "        current_data.COMM_NAME,\n" +
                        "        (SUM(current_data.col4 + current_data.col9 + current_data.col10) * 100) /\n" +
                        "        SUM(current_data.col2 + previous_data.col1) AS score_of_subpara3a\n" +
                        "    FROM (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            cc.COMM_NAME,  -- Added COMM_NAME\n" +
                        "            SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,\n" +
                        "            SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, \n" +
                        "            SUM(14c.OUTCOME_SECTION_61) AS col10,\n" +
                        "            SUM(14c.RETURN_SCRUTINY) AS col2\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + prev_month_new + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME  -- Added COMM_NAME\n" +
                        "    ) AS current_data\n" +
                        "    JOIN (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            cc.COMM_NAME,  -- Added COMM_NAME\n" +
                        "            SUM(14c.CLOSING_NO) AS col1\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + prev_month_two + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME  -- Added COMM_NAME\n" +
                        "    ) AS previous_data\n" +
                        "    ON \n" +
                        "        current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                        "    GROUP BY \n" +
                        "        current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.COMM_NAME\n" +
                        "),\n" +
                        "final_data_3b_prev1 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        cc.ZONE_CODE,\n" +
                        "        cc.COMM_NAME,  -- Added COMM_NAME\n" +
                        "        (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) /\n" +
                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                        "    FROM \n" +
                        "        mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN \n" +
                        "        mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        14c.MM_YYYY = '" + prev_month_new + "' \n" +
                        "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "    GROUP BY \n" +
                        "        cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME  -- Added COMM_NAME\n" +
                        "),\n" +
                        "ranked_data_3a_prev2 AS (\n" +
                        "    SELECT \n" +
                        "        current_data.ZONE_NAME, \n" +
                        "        current_data.ZONE_CODE,\n" +
                        "        current_data.COMM_NAME,\n" +
                        "        (SUM(current_data.col4 + current_data.col9 + current_data.col10) * 100) /\n" +
                        "        SUM(current_data.col2 + previous_data.col1) AS score_of_subpara3a\n" +
                        "    FROM (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            cc.COMM_NAME,  -- Added COMM_NAME\n" +
                        "            SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,\n" +
                        "            SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, \n" +
                        "            SUM(14c.OUTCOME_SECTION_61) AS col10,\n" +
                        "            SUM(14c.RETURN_SCRUTINY) AS col2\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + prev_month_two + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME  -- Added COMM_NAME\n" +
                        "    ) AS current_data\n" +
                        "    JOIN (\n" +
                        "        SELECT \n" +
                        "            zc.ZONE_NAME, \n" +
                        "            cc.ZONE_CODE, \n" +
                        "            cc.COMM_NAME,  -- Added COMM_NAME\n" +
                        "            SUM(14c.CLOSING_NO) AS col1\n" +
                        "        FROM \n" +
                        "            mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN \n" +
                        "            mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "        LEFT JOIN \n" +
                        "            mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "        WHERE \n" +
                        "            14c.MM_YYYY = '" + prev_month_first + "' \n" +
                        "            AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "        GROUP BY \n" +
                        "            cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME  -- Added COMM_NAME\n" +
                        "    ) AS previous_data\n" +
                        "    ON \n" +
                        "        current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                        "    GROUP BY \n" +
                        "        current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.COMM_NAME\n" +
                        "),\n" +
                        "final_data_3b_prev2 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        cc.ZONE_CODE,\n" +
                        "        cc.COMM_NAME,  -- Added COMM_NAME\n" +
                        "        (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) /\n" +
                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                        "    FROM \n" +
                        "        mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN \n" +
                        "        mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        14c.MM_YYYY = '" + prev_month_two + "' \n" +
                        "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "    GROUP BY \n" +
                        "        cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME  -- Added COMM_NAME\n" +
                        ")\n" +
                        "SELECT \n" +
                        "    m3a.ZONE_NAME, \n" +
                        "    m3a.ZONE_CODE,\n" +
                        "    m3a.COMM_NAME,\n" +
                        "    'Scrutiny/Assessment' AS parameter_name,\n" +
                        "    (m3a.score_of_subpara3a + f3b.score_of_parameter3b) AS total_score_of_month,\n" +
                        "    (m3a_prev1.score_of_subpara3a + f3b_prev1.score_of_parameter3b) AS total_score_previous_month,\n" +
                        "    (m3a_prev2.score_of_subpara3a + f3b_prev2.score_of_parameter3b) AS total_score_two_months_prior\n" +
                        "FROM \n" +
                        "    ranked_data_3a AS m3a\n" +
                        "JOIN \n" +
                        "    final_data_3b AS f3b\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = f3b.ZONE_CODE AND m3a.COMM_NAME = f3b.COMM_NAME  -- Added COMM_NAME\n" +
                        "JOIN \n" +
                        "    ranked_data_3a_prev1 AS m3a_prev1\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = m3a_prev1.ZONE_CODE AND m3a.COMM_NAME = m3a_prev1.COMM_NAME  -- Added COMM_NAME\n" +
                        "JOIN \n" +
                        "    final_data_3b_prev1 AS f3b_prev1\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = f3b_prev1.ZONE_CODE AND m3a.COMM_NAME = f3b_prev1.COMM_NAME  -- Added COMM_NAME\n" +
                        "JOIN \n" +
                        "    ranked_data_3a_prev2 AS m3a_prev2\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = m3a_prev2.ZONE_CODE AND m3a.COMM_NAME = m3a_prev2.COMM_NAME  -- Added COMM_NAME\n" +
                        "JOIN \n" +
                        "    final_data_3b_prev2 AS f3b_prev2\n" +
                        "ON \n" +
                        "    m3a.ZONE_CODE = f3b_prev2.ZONE_CODE AND m3a.COMM_NAME = f3b_prev2.COMM_NAME;  -- Added COMM_NAME\n";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commName=rsGst14aa.getString("COMM_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double current_t_score = rsGst14aa.getDouble("total_score_of_month");
                    double previous_t_score  = rsGst14aa.getDouble("total_score_previous_month");
                    double previous_t_score_2  = rsGst14aa.getDouble("total_score_two_months_prior");
                    //double total_score = rsGst14aa.getDouble("total_score");
                    // int z_rank = rsGst14aa.getInt("z_rank");

//                     String parameter_name = rsGst14aa.getString("parameter_name");



                    // Formatting the total score
                    String formattedTotal = String.format("%.2f", current_t_score);
                    double total_score_of_month = Double.parseDouble(formattedTotal);

                    String formattedTotal2 = String.format("%.2f", previous_t_score);
                    double total_score_previous_month = Double.parseDouble(formattedTotal2);

                    String formattedTotal3 = String.format("%.2f", previous_t_score_2);
                    double total_score_previous_month_2 = Double.parseDouble(formattedTotal3);
                    totalScore = new MonthlyYearlyScore(zoneName,commName, zone_code,"Scrutiny & Assessment", total_score_of_month, total_score_previous_month,
                            total_score_previous_month_2, total_score_of_year, total_score_previous_year, total_score_previous_year_2);
                    allGstaList.add(totalScore);
                }
                System.out.println("last3 month commi scrutiny & assessment");
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
        return allGstaList;
    }
// **********************************************refund last 3 month details************************************************************************************************

    @ResponseBody
    @RequestMapping(value = "refund/month")
    //  http://localhost:8080/cbicApi/cbic/MonthYear/refund/month?type=last3month&month_date=2024-04-01&zone_code=70
    // http://localhost:8080/cbicApi/cbic/MonthYear/refund/month?type=comm_name&month_date=2024-04-30&zone_code=70
    public Object Refund(@RequestParam String type, @RequestParam String month_date, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String comm_name) {
        List<MonthlyYearlyScore> allGstaList = new ArrayList<>();
        MonthlyYearlyScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa= null;
        ResultSet rsGst14aa_gst2= null;ResultSet rsGst14aa_gst7= null;
        ResultSet currentMonthValue= null;
        double total_score_of_year = 0;
        double total_score_previous_year = 0;
        double total_score_previous_year_2 = 0;
        try {

            if (type.equalsIgnoreCase("last3month")) { // for parameter 1
                //     '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'

//					String curentMonth = "SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-%d') AS month_date;";
//					currentMonthValue = GetExecutionSQL.getResult(curentMonth);
//					String month_date = currentMonthValue.getString("last_month");
//					String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//		            String next_month_new = DateCalculate.getNextMonth(month_date);

                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String query_assessment_for_refunds="WITH CTE AS (\n" +
                        "    SELECT cc.ZONE_CODE,zc.ZONE_NAME,\n" +
                        "        -- Calculate values for the given month\n" +
                        "        SUM(CASE WHEN 14c.MM_YYYY = '" + month_date + "' THEN (14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) END) AS col16_current_month,\n" +
                        "        SUM(CASE WHEN 14c.MM_YYYY = '" + month_date + "' THEN 14c.age_breakup_above60_no END) AS col22_current_month,\n" +
                        "        -- Calculate values for the previous month\n" +
                        "        SUM(CASE WHEN 14c.MM_YYYY = DATE_SUB('" + month_date + "', INTERVAL 1 MONTH) THEN (14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) END) AS col16_previous_month,\n" +
                        "        SUM(CASE WHEN 14c.MM_YYYY = DATE_SUB('" + month_date + "', INTERVAL 1 MONTH) THEN 14c.age_breakup_above60_no END) AS col22_previous_month,\n" +
                        "        -- Calculate values for the month before the previous month\n" +
                        "        SUM(CASE WHEN 14c.MM_YYYY = DATE_SUB('" + month_date + "', INTERVAL 2 MONTH) THEN (14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) END) AS col16_previous_month_2,\n" +
                        "        SUM(CASE WHEN 14c.MM_YYYY = DATE_SUB('" + month_date + "', INTERVAL 2 MONTH) THEN 14c.age_breakup_above60_no END) AS col22_previous_month_2\n" +
                        "    FROM mis_gst_commcode AS cc \n" +
                        "    RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "    WHERE 14c.MM_YYYY IN ('" + month_date + "', DATE_SUB('" + month_date + "', INTERVAL 1 MONTH), DATE_SUB('" + month_date + "', INTERVAL 2 MONTH))\n" +
                        "        AND cc.ZONE_CODE = '" + zone_code + "' \n" +
                        "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        ")\n" +
                        "SELECT ZONE_CODE, ZONE_NAME,\n" +
                        "    (col22_current_month * 100 / col16_current_month) AS total_score_gst7_of_month,\n" +
                        "    (col22_previous_month * 100 / col16_previous_month) AS total_score_gst7_previous_month,\n" +
                        "    (col22_previous_month_2 * 100 / col16_previous_month_2) AS total_score_gst7_previous_month_2\n" +
                        "FROM CTE;\n";

                rsGst14aa_gst2 = GetExecutionSQL.getResult(query_assessment_for_refunds);

                while (rsGst14aa_gst2.next()) {
                    String zoneName = rsGst14aa_gst2.getString("ZONE_NAME");
                    zone_code = rsGst14aa_gst2.getString("ZONE_CODE");
                    double current_t_score = rsGst14aa_gst2.getDouble("total_score_gst7_of_month");
                    double previous_t_score = rsGst14aa_gst2.getDouble("total_score_gst7_previous_month");
                    double previous_t_score_2 = rsGst14aa_gst2.getDouble("total_score_gst7_previous_month_2");
                    String commName = "ALL";
                    String parameter_name = "Refunds";

                    // Formatting the total score
                    String formattedTotal = String.format("%.2f", current_t_score);
                    double total_score_of_month = Double.parseDouble(formattedTotal);

                    String formattedTotal2 = String.format("%.2f", previous_t_score);
                    double total_score_previous_month = Double.parseDouble(formattedTotal2);

                    String formattedTotal3 = String.format("%.2f", previous_t_score_2);
                    double total_score_previous_month_2 = Double.parseDouble(formattedTotal3);

                    totalScore = new MonthlyYearlyScore(zoneName, commName, zone_code, parameter_name, total_score_of_month, total_score_previous_month,
                            total_score_previous_month_2, total_score_of_year, total_score_previous_year, total_score_previous_year_2);
                    allGstaList.add(totalScore);
                }
                System.out.println("last 3 month");
            }  else if (type.equalsIgnoreCase("comm_name")) {
                String query_assessment= "WITH monthly_scores AS (\r\n"
                        + "    SELECT\r\n"
                        + "        zc.ZONE_NAME,\r\n"
                        + "        cc.ZONE_CODE,\r\n"
                        + "        cc.COMM_NAME,\r\n"
                        + "        YEAR(14c.MM_YYYY) AS year,\r\n"
                        + "        MONTH(14c.MM_YYYY) AS month,\r\n"
                        + "        SUM(14c.age_breakup_above60_no) AS col22,\r\n"
                        + "        SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16,\r\n"
                        + "        (SUM(14c.age_breakup_above60_no) * 100 / NULLIF(SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO), 0)) AS total_score\r\n"
                        + "    FROM\r\n"
                        + "        mis_gst_commcode AS cc\r\n"
                        + "    RIGHT JOIN\r\n"
                        + "        mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\r\n"
                        + "    LEFT JOIN\r\n"
                        + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                        + "    WHERE\r\n"
                        + "        14c.MM_YYYY >= DATE_SUB(DATE '" + month_date + "', INTERVAL 3 MONTH)\r\n"
                        + "        AND 14c.MM_YYYY < DATE '" + month_date + "'\r\n"
                        + "        AND cc.ZONE_CODE = '" + zone_code + "'\r\n"
                        + "    GROUP BY\r\n"
                        + "        zc.ZONE_NAME,\r\n"
                        + "        cc.ZONE_CODE,\r\n"
                        + "        cc.COMM_NAME,\r\n"
                        + "        YEAR(14c.MM_YYYY),\r\n"
                        + "        MONTH(14c.MM_YYYY)\r\n"
                        + "),\r\n"
                        + "monthly_totals AS (\r\n"
                        + "    SELECT\r\n"
                        + "        ZONE_NAME,\r\n"
                        + "        ZONE_CODE,\r\n"
                        + "        COMM_NAME,\r\n"
                        + "        MAX(CASE WHEN month = MONTH(DATE_SUB(DATE '" + month_date + "', INTERVAL 2 MONTH)) THEN total_score ELSE NULL END) AS total_score_previous_month_2,\r\n"
                        + "        MAX(CASE WHEN month = MONTH(DATE_SUB(DATE '" + month_date + "', INTERVAL 1 MONTH)) THEN total_score ELSE NULL END) AS total_score_previous_month,\r\n"
                        + "        MAX(CASE WHEN month = MONTH(DATE '" + month_date + "') THEN total_score ELSE NULL END) AS total_score_of_month\r\n"
                        + "    FROM\r\n"
                        + "        monthly_scores\r\n"
                        + "    GROUP BY\r\n"
                        + "        ZONE_NAME,\r\n"
                        + "        ZONE_CODE,\r\n"
                        + "        COMM_NAME\r\n"
                        + "),\r\n"
                        + "three_month_total AS (\r\n"
                        + "    SELECT\r\n"
                        + "        ZONE_NAME,\r\n"
                        + "        ZONE_CODE,\r\n"
                        + "        COMM_NAME,\r\n"
                        + "        COALESCE(total_score_previous_month_2, 0) + COALESCE(total_score_previous_month, 0) + COALESCE(total_score_of_month, 0) AS total_score_last_3_months\r\n"
                        + "    FROM\r\n"
                        + "        monthly_totals\r\n"
                        + "),\r\n"
                        + "ranked_scores AS (\r\n"
                        + "    SELECT\r\n"
                        + "        ZONE_NAME,\r\n"
                        + "        ZONE_CODE,\r\n"
                        + "        COMM_NAME,\r\n"
                        + "        total_score_last_3_months AS total_score,\r\n"
                        + "        DENSE_RANK() OVER (ORDER BY total_score_last_3_months DESC) AS z_rank\r\n"
                        + "    FROM\r\n"
                        + "        three_month_total\r\n"
                        + ")\r\n"
                        + "SELECT\r\n"
                        + "    r.z_rank,\r\n"
                        + "    m.ZONE_NAME,\r\n"
                        + "    m.ZONE_CODE,\r\n"
                        + "    m.COMM_NAME,\r\n"
                        + "    m.total_score_previous_month_2,\r\n"
                        + "    m.total_score_previous_month,\r\n"
                        + "    m.total_score_of_month,\r\n"
                        + "    r.total_score\r\n"
                        + "FROM\r\n"
                        + "    ranked_scores r\r\n"
                        + "JOIN\r\n"
                        + "    monthly_totals m ON r.ZONE_NAME = m.ZONE_NAME \r\n"
                        + "                     AND r.ZONE_CODE = m.ZONE_CODE \r\n"
                        + "                     AND r.COMM_NAME = m.COMM_NAME\r\n"
                        + "WHERE\r\n"
                        + "    m.ZONE_CODE = '" + zone_code + "'\r\n"
                        + "ORDER BY \r\n"
                        + "    r.z_rank;\r\n"
                        + "";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commName=rsGst14aa.getString("COMM_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double current_t_score = rsGst14aa.getDouble("total_score_of_month");
                    double previous_t_score  = rsGst14aa.getDouble("total_score_previous_month");
                    double previous_t_score_2  = rsGst14aa.getDouble("total_score_previous_month_2");
                    double total_score = rsGst14aa.getDouble("total_score");
                    int z_rank = rsGst14aa.getInt("z_rank");

//                     String parameter_name = rsGst14aa.getString("parameter_name");



                    // Formatting the total score
                    String formattedTotal = String.format("%.2f", current_t_score);
                    double total_score_of_month = Double.parseDouble(formattedTotal);

                    String formattedTotal2 = String.format("%.2f", previous_t_score);
                    double total_score_previous_month = Double.parseDouble(formattedTotal2);

                    String formattedTotal3 = String.format("%.2f", previous_t_score_2);
                    double total_score_previous_month_2 = Double.parseDouble(formattedTotal3);
                    totalScore = new MonthlyYearlyScore(zoneName,commName, zone_code,"Return Filling", total_score_of_month, total_score_previous_month,
                            total_score_previous_month_2, total_score_of_year, total_score_previous_year, total_score_previous_year_2);
                    allGstaList.add(totalScore);
                }
                System.out.println("last3 month commi refunds");
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
        return allGstaList;
    }
}
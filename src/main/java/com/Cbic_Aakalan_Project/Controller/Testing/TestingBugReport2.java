package com.Cbic_Aakalan_Project.Controller.Testing; //TestingBugReport2

import com.Cbic_Aakalan_Project.Service.CGST.GstGradeScore;
import com.Cbic_Aakalan_Project.Service.CGST.RelevantAspect;
import com.Cbic_Aakalan_Project.entity.GSTCUS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.Cbic_Aakalan_Project.dao.pool.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/cbicApi/cbic/testingbug")
@Controller
public class TestingBugReport2 {
    private Logger logger = LoggerFactory.getLogger(TestingController.class);
    GstGradeScore score = new GstGradeScore();

    @ResponseBody
    @RequestMapping(value = "/gst3aTesting", method = RequestMethod.GET)
    // http://localhost:8080/cbicApi/cbic/testingbug/gst3aTesting?month_date=2024-10-01&type=parameter
    public Object refunds(
            @RequestParam String month_date,
            @RequestParam String type,
            @RequestParam(required = false) String zone_code,
            @RequestParam(required = false) String come_name) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;

        try (Connection con = JDBCConnection.getTNConnection()) {
            if (type.equalsIgnoreCase("parameter")) {
                // Modified query to include the zone_code condition
                String query ="WITH CTE AS (\n"
                        + "    SELECT\n"
                        + "        zc.ZONE_NAME,\n"
                        + "        cc.ZONE_CODE,\n"
                        + "        cc.COMM_NAME, -- Added COMM_NAME\n"
                        + "        -- Calculate col1 for the previous month\n"
                        + "        SUM(CASE\n"
                        + "                WHEN 14c.MM_YYYY = DATE_FORMAT(DATE_SUB(?, INTERVAL 1 MONTH), '%Y-%m-%d')\n"
                        + "                THEN COALESCE(14c.CLOSING_NO, 0)\n"
                        + "                ELSE 0\n"
                        + "            END) AS col1,\n"
                        + "        -- Other columns for the specified month only\n"
                        + "        SUM(CASE WHEN 14c.MM_YYYY = ? THEN COALESCE(14c.RETURN_SCRUTINY, 0) ELSE 0 END) AS col2,\n"
                        + "        SUM(CASE WHEN 14c.MM_YYYY = ? THEN COALESCE(14c.SCRUTINIZED_DISCRIPANCY_FOUND, 0) ELSE 0 END) AS col4,\n"
                        + "        SUM(CASE WHEN 14c.MM_YYYY = ? THEN COALESCE(14c.OUTCOME_ASMT_12_ISSUED, 0) ELSE 0 END) AS col8,\n"
                        + "        SUM(CASE WHEN 14c.MM_YYYY = ? THEN COALESCE(14c.OUTCOME_SECTION_61_ACTION_65_66, 0) ELSE 0 END) AS col9,\n"
                        + "        SUM(CASE WHEN 14c.MM_YYYY = ? THEN COALESCE(14c.OUTCOME_SECTION_61_ACTION_67, 0) ELSE 0 END) AS col10,\n"
                        + "        SUM(CASE WHEN 14c.MM_YYYY = ? THEN COALESCE(14c.OUTCOME_SCN_SECTION_73_74, 0) ELSE 0 END) AS col11,\n"
                        + "        -- Calculate col3a as (col4 + col8 + col9 + col10 + col11)\n"
                        + "        SUM(CASE\n"
                        + "                WHEN 14c.MM_YYYY = ? THEN\n"
                        + "                    COALESCE(14c.SCRUTINIZED_DISCRIPANCY_FOUND, 0)\n"
                        + "                    + COALESCE(14c.OUTCOME_ASMT_12_ISSUED, 0)\n"
                        + "                    + COALESCE(14c.OUTCOME_SECTION_61_ACTION_65_66, 0)\n"
                        + "                    + COALESCE(14c.OUTCOME_SECTION_61_ACTION_67, 0)\n"
                        + "                    + COALESCE(14c.OUTCOME_SCN_SECTION_73_74, 0)\n"
                        + "                ELSE 0\n"
                        + "            END) AS col3a\n"
                        + "    FROM\n"
                        + "        mis_gst_zonecode AS zc\n"
                        + "        LEFT JOIN mis_gst_commcode AS cc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                        + "        LEFT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
                        + "    WHERE\n"
                        + "        14c.MM_YYYY IN (?, DATE_FORMAT(DATE_SUB(?, INTERVAL 1 MONTH), '%Y-%m-%d'))\n"
                        + "    GROUP BY\n"
                        + "        zc.ZONE_NAME,\n"
                        + "        cc.ZONE_CODE,\n"
                        + "        cc.COMM_NAME -- Added COMM_NAME to GROUP BY\n"
                        + "),\n"
                        + "MedianCalc AS (\n"
                        + "    SELECT \n"
                        + "        col3a, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY col3a) AS row_num,\n"
                        + "        COUNT(*) OVER () AS total_rows\n"
                        + "    FROM CTE\n"
                        + "),\n"
                        + "MedianResult AS (\n"
                        + "    SELECT \n"
                        + "        AVG(col3a) AS median_3a\n"
                        + "    FROM MedianCalc\n"
                        + "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n"
                        + ")\n"
                        + "SELECT\n"
                        + "    COALESCE(ZONE_NAME, 'N/A') AS ZONE_NAME,\n"
                        + "    COALESCE(ZONE_CODE, 'N/A') AS ZONE_CODE,\n"
                        + "    COALESCE(COMM_NAME, 'N/A') AS COMM_NAME, -- Replace NULL with 'N/A'\n"
                        + "    COALESCE(col1, 0) AS col1,\n"
                        + "    COALESCE(col2, 0) AS col2,\n"
                        + "    COALESCE(col4, 0) AS col4,\n"
                        + "    COALESCE(col8, 0) AS col8,\n"
                        + "    COALESCE(col9, 0) AS col9,\n"
                        + "    COALESCE(col10, 0) AS col10,\n"
                        + "    COALESCE(col11, 0) AS col11,\n"
                        + "    COALESCE(col3a, 0) AS col3a,\n"
                        + "    CONCAT(COALESCE(col3a, 0), '/', (COALESCE(col1, 0) + COALESCE(col2, 0))) AS absval, -- Add absval column in p/q form\n"
                        + "    COALESCE((SELECT median_3a FROM MedianResult), 0) AS median_3a,\n"
                        + "    COALESCE(((col3a / NULLIF((col1 + col2), 0)) * 100), 0) AS total_score -- Added total_score column\n"
                        + "FROM CTE\n"
                        + "WHERE ZONE_CODE = ? -- Added filter for ZONE_CODE\n"
                        + "ORDER BY\n"
                        + "    total_score DESC, -- Keep total_score in descending order\n"
                        + "    ZONE_NAME,\n"
                        + "    ZONE_CODE;\n"
                        + "";
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setString(1, month_date);
                    pstmt.setString(2, month_date);
                    pstmt.setString(3, month_date);
                    pstmt.setString(4, month_date);
                    pstmt.setString(5, month_date);
                    pstmt.setString(6, month_date);
                    pstmt.setString(7, month_date);
                    pstmt.setString(8, month_date);
                    pstmt.setString(9, month_date);
                    pstmt.setString(10, month_date);// Safely set the month_date parameter
                    pstmt.setString(11, zone_code); // Setting the zone_code parameter to '65'

                    ResultSet rsGst14aa = pstmt.executeQuery();
                    while(rsGst14aa.next()) {
                        String ra = RelevantAspect.Gst3A_RA;
                        String zoneCode = rsGst14aa.getString("ZONE_CODE");
                        String commname=rsGst14aa.getString("COMM_NAME");
                        String absval = rsGst14aa.getString("absval");
                        double median = rsGst14aa.getDouble("median_3a");
                        Double numerator_3a = rsGst14aa.getDouble("col3a");
                        Double t_score = rsGst14aa.getDouble("total_score");
                        String formattedTotal = String.format("%.2f", t_score);
                        double totalScore = Double.parseDouble(formattedTotal);
                        int way_to_grade = score.marks3a(totalScore);
                        int insentavization = score.marks3a(totalScore);

                        if (numerator_3a > median && way_to_grade < 10) {
                            insentavization += 1;
                        }
                        int Zonal_rank = 0;
                        String gst = "no";

                        Double sub_parameter_weighted_average = insentavization * 0.5 ;
                        gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                                Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                        allGstaList.add(gsta);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQL Exception", e);
        }
        return allGstaList;
    }
}
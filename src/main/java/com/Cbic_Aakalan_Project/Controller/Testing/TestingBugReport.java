package com.Cbic_Aakalan_Project.Controller.Testing; //TestingBugReport

import com.Cbic_Aakalan_Project.Service.CGST.GstGradeScore;
import com.Cbic_Aakalan_Project.entity.GSTCUS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class TestingBugReport {
    private Logger logger = LoggerFactory.getLogger(TestingController.class);
    GstGradeScore score = new GstGradeScore();

    @ResponseBody
    @RequestMapping(value = "/refundsTesting")
    // http://localhost:8080/cbicApi/cbic/testingbug/refundsTesting?month_date=2023-04-01&zone_code=65&type=parameter
    public Object refunds(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<GSTCUS> allGstaList = new ArrayList<>();
        GSTCUS gsta = null;
        int rank = 0;
        double total = 0.00;

        try (Connection con = JDBCConnection.getTNConnection()) {
            if (type.equalsIgnoreCase("parameter")) {
                // Modified query to include the zone_code condition
                String query = "SELECT " +
                        "SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, " +
                        "SUM(14c.age_breakup_above60_no) AS col22, " +
                        "cc.ZONE_CODE, zc.ZONE_NAME " +
                        "FROM mis_gst_commcode AS cc " +
                        "RIGHT JOIN mis_dpm_gst_4 AS 14c " +
                        "ON cc.COMM_CODE = 14c.COMM_CODE " +
                        "LEFT JOIN mis_gst_zonecode AS zc " +
                        "ON zc.ZONE_CODE = cc.ZONE_CODE " +
                        "WHERE 14c.MM_YYYY = ? " +
                        "AND cc.ZONE_CODE = ? " + // Added condition for zone_code
                        "GROUP BY cc.ZONE_CODE";

                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setString(1, month_date); // Safely set the month_date parameter
                    pstmt.setString(2, zone_code); // Setting the zone_code parameter to '65'

                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        String ra = "GST7";
                        String zoneCode = rs.getString("ZONE_CODE");
                        String commname = "ALL";
                        Double col22 = rs.getDouble("col22");
                        Double col16 = rs.getDouble("col16");
                        int Zonal_rank = 0;
                        String gst = "no";
                        int insentavization = 0;

                        // Calculating values
                        int col22ab = rs.getInt("col22");
                        int col16ab = rs.getInt("col16");
                        String absval = String.valueOf(col22ab) + "/" + String.valueOf(col16ab);

                        // Calculating total score
                        if (col16 != 0) {
                            total = ((col22 * 100) / col16);
                        } else {
                            total = 0;
                        }
                        String formattedTotal = String.format("%.2f", total);
                        double totalScore = Double.parseDouble(formattedTotal);

                        int way_to_grade = score.marks7(totalScore);
                        int sub_parameter_weighted_average = way_to_grade;

                        gsta = new GSTCUS(rs.getString("ZONE_NAME"), commname, totalScore, absval, zoneCode, ra,
                                Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
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
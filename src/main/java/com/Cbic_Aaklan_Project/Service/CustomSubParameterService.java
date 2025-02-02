package com.Cbic_Aaklan_Project.Service;

import com.Cbic_Aaklan_Project.entity.GSTCUS;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomSubParameterService {
    CustomGreadeScore score = new CustomGreadeScore();
    Double total;
    GSTCUS gsta = null;
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus1 zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2b zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2c zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3A All Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus3aZone(ResultSet rs) throws SQLException {
        Double median = 0.0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3a_RA;
            String commname = "ALL";
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col17f = rs.getInt("col17f");
            int col11 = rs.getInt("col11");
            median = rs.getDouble("median_3a");
            Double numerator_3a = rs.getDouble("col17f");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
            if (!(col17f == 0 && col11== 0)) {
                absval = String.valueOf(col17f) + "/" + String.valueOf(col11);
            }
            if(col11 != 0) {
                total = ((double) (col17f) * 100 / (col11));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks3a(totalScore);
            int insentavization = score.c_marks3a(totalScore);
            if (numerator_3a > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3A median zone wise :- "+median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3A Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus3aZoneWiseCommissionary(ResultSet rs) throws SQLException {
        Double median = 0.0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3a_RA;
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col17f = rs.getInt("col17f");
            int col11 = rs.getInt("col11");
            median = rs.getDouble("median_3a");
            Double numerator_3a = rs.getDouble("col17f");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
            if (!(col17f == 0 && col11== 0)) {
                absval = String.valueOf(col17f) + "/" + String.valueOf(col11);
            }
            if(col11 != 0) {
                total = ((double) (col17f) * 100 / (col11));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks3a(totalScore);
            int insentavization = score.c_marks3a(totalScore);
            if (numerator_3a > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3A median zone wise :- "+median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3A All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus3aAllCommissionary(ResultSet rs) throws SQLException {
        Double median = 0.0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3a_RA;
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col17f = rs.getInt("col17f");
            int col11 = rs.getInt("col11");
            median = rs.getDouble("median_3a");
            Double numerator_3a = rs.getDouble("col17f");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
            if (!(col17f == 0 && col11== 0)) {
                absval = String.valueOf(col17f) + "/" + String.valueOf(col11);
            }
            if(col11 != 0) {
                total = ((double) (col17f) * 100 / (col11));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks3a(totalScore);
            int insentavization = score.c_marks3a(totalScore);
            if (numerator_3a > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3A median zone wise :- "+median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3B All Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus3bZone(ResultSet rs) throws SQLException {
        Double median = 0.0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3b_RA;
            String commname = "ALL";
           // String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col17b = rs.getInt("col17b");
            int col11 = rs.getInt("col11");
            median = rs.getDouble("median_3b");
            Double numerator_3b = rs.getDouble("col17b");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
            if (!(col17b == 0 && col11== 0)) {
                absval = String.valueOf(col17b) + "/" + String.valueOf(col11);
            }
            if(col11 != 0) {
                total = ((double) (col17b) * 100 / (col11));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks3b(totalScore);
//            int insentavization = score.c_marks3a(totalScore);
//            if (numerator_3b > median && way_to_grade < 10) {
//                insentavization += 1;
//            }
            double sub_parameter_weighted_average = way_to_grade * 0.4 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        // System.out.println("CUS 3b median zone wise :- "+median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3B Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus3bZoneWiseCommissionary(ResultSet rs) throws SQLException {
        Double median = 0.0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3b_RA;
            //String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col17b = rs.getInt("col17b");
            int col11 = rs.getInt("col11");
            median = rs.getDouble("median_3b");
            Double numerator_3b = rs.getDouble("col17b");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
            if (!(col17b == 0 && col11== 0)) {
                absval = String.valueOf(col17b) + "/" + String.valueOf(col11);
            }
            if(col11 != 0) {
                total = ((double) (col17b) * 100 / (col11));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks3b(totalScore);
//            int insentavization = score.c_marks3b(totalScore);
//            if (numerator_3b > median && way_to_grade < 10) {
//                insentavization += 1;
//            }
            double sub_parameter_weighted_average = way_to_grade * 0.4 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        // System.out.println("CUS 3B median zone wise :- "+median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3B All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus3bAllCommissionary(ResultSet rs) throws SQLException {
        Double median = 0.0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3b_RA;
            //String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col17b = rs.getInt("col17b");
            int col11 = rs.getInt("col11");
            median = rs.getDouble("median_3b");
            Double numerator_3b = rs.getDouble("col17b");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
            if (!(col17b == 0 && col11== 0)) {
                absval = String.valueOf(col17b) + "/" + String.valueOf(col11);
            }
            if(col11 != 0) {
                total = ((double) (col17b) * 100 / (col11));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks3b(totalScore);
//            int insentavization = score.c_marks3b(totalScore);
//            if (numerator_3b > median && way_to_grade < 10) {
//                insentavization += 1;
//            }
            double sub_parameter_weighted_average = way_to_grade * 0.4 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
       // System.out.println("CUS 3B median zone wise :- "+median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3C All Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus3cZone(ResultSet rs) throws SQLException {
        Double median = 0.0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3c_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            long col14_16 = rs.getLong("col14_16");
            long col13 = rs.getLong("col13");
             median = rs.getDouble("median_3c");
            double total;
            String absval = rs.getString("absvl");

            // String absval = (col14_16 != 0 || col13 != 0) ? col14_16 + "/" + col13 : "";
            total = col13 != 0 ? (double) col14_16 * 100 / col13 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks3c(totalScore);
            int insentavization = score.c_marks3c(totalScore);
            if (col14_16 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.3;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3C Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus3cZoneWiseCommissionary(ResultSet rs) throws SQLException {
        Double median = 0.0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3c_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            long col14_16 = rs.getLong("col14_16");
            long col13 = rs.getLong("col13");
             median = rs.getDouble("median_3c");
            double total;
            String absval = rs.getString("absvl");
            // String absval = (col14_16 != 0 || col13 != 0) ? col14_16 + "/" + col13 : "";
            total = col13 != 0 ? (double) col14_16 * 100 / col13 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks3c(totalScore);
            int insentavization = score.c_marks3c(totalScore);
            if (col14_16 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.3;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        // System.out.println("CUS 3C median zone wise :- "+median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3C All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus3cAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3c_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "ALL";
            String commname = rs.getString("COMM_NAME");
            long col14_16 = rs.getLong("col14_16");
            long col13 = rs.getLong("col13");
            median = rs.getDouble("median_3c");
            double total;
            String absval = rs.getString("absvl");
            // String absval = (col14_16 != 0 || col13 != 0) ? col14_16 + "/" + col13 : "";
            total = col13 != 0 ? (double) col14_16 * 100 / col13 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks3c(totalScore);
            int insentavization = score.c_marks3c(totalScore);
            if (col14_16 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.3;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3C median zone wise :- " + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus4A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4aZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4a_RA;
            String commname = "ALL";
           // String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col13 = rs.getInt("col13");
            int col15 = rs.getInt("col15");
            int col17 = rs.getInt("col17");
            int col9 = rs.getInt("col3");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
//            if (!((col13 + col15 + col17) == 0 && col9== 0)) {
//                absval = String.valueOf(col13 + col15 + col17) + "/" + String.valueOf(col9);
//            }

            absval = String.valueOf(col13 + col15 + col17) + "/" + String.valueOf(col9);

            if(col9 != 0) {
                total = ((double) (col13 + col15 + col17) * 100 / (col9));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks4a(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS4A Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4aZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4a_RA;
            //String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col13 = rs.getInt("col13");
            int col15 = rs.getInt("col15");
            int col17 = rs.getInt("col17");
            int col9 = rs.getInt("col3");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
//            if (!((col13 + col15 + col17) == 0 && col9== 0)) {
//                absval = String.valueOf(col13 + col15 + col17) + "/" + String.valueOf(col9);
//            }

            absval = String.valueOf(col13 + col15 + col17) + "/" + String.valueOf(col9);

            if(col9 != 0) {
                total = ((double) (col13 + col15 + col17) * 100 / (col9));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks4a(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS4A All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4aAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4a_RA;
           // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col13 = rs.getInt("col13");
            int col15 = rs.getInt("col15");
            int col17 = rs.getInt("col17");
            int col9 = rs.getInt("col9");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
//            if (!((col13 + col15 + col17) == 0 && col9== 0)) {
//                absval = String.valueOf(col13 + col15 + col17) + "/" + String.valueOf(col9);
//            }

            absval = String.valueOf(col13 + col15 + col17) + "/" + String.valueOf(col9);

            if(col9 != 0) {
                total = ((double) (col13 + col15 + col17) * 100 / (col9));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks4a(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4bZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4b_RA;
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col14 = rs.getInt("col14");
            int col15 = rs.getInt("col15");
            int col16 = rs.getInt("col16");
            int col17 = rs.getInt("col17");
            int col9 = rs.getInt("col9");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";

            absval = String.valueOf(col14 + col15 + col16 + col17) + "/" + String.valueOf(col9);

            if(col9 != 0) {
                total = ((double) (col14 + col15 + col16 + col17) * 100 / (col9));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks4b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS4B Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4bZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4b_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col14 = rs.getInt("col14");
            int col15 = rs.getInt("col15");
            int col16 = rs.getInt("col16");
            int col17 = rs.getInt("col17");
            int col9 = rs.getInt("col9");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";

            absval = String.valueOf(col14 + col15 + col16 + col17) + "/" + String.valueOf(col9);

            if(col9 != 0) {
                total = ((double) (col14 + col15 + col16 + col17) * 100 / (col9));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks4b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS4B All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4bAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4b_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col14 = rs.getInt("col14");
            int col15 = rs.getInt("col15");
            int col16 = rs.getInt("col16");
            int col17 = rs.getInt("col17");
            int col9 = rs.getInt("col9");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";

            absval = String.valueOf(col14 + col15 + col16 + col17) + "/" + String.valueOf(col9);

            if(col9 != 0) {
                total = ((double) (col14 + col15 + col16 + col17) * 100 / (col9));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks4b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4C zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4cZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4c_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            long col7 = rs.getLong("col7");
            long col3 = rs.getLong("col3");
            double median = rs.getDouble("median_4c");
            double total;
            String absval = String.valueOf(col7) + "/" + String.valueOf(col3);
            total = col3 != 0 ? (double) col7 * 100 / col3 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks4c(totalScore);
            int insentavization = score.c_marks4c(totalScore);
            if (col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.3;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS4C Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus4cZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4c_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "ALL";
            String commname = rs.getString("COMM_NAME");
            long col7 = rs.getLong("col7");
            long col3 = rs.getLong("col3");
            median = rs.getDouble("median_4c");
            double total;
            String absval = String.valueOf(col7) + "/" + String.valueOf(col3);

            total = col3 != 0 ? (double) col7 * 100 / col3 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks4c(totalScore);
            int insentavization = score.c_marks4c(totalScore);
            if (col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.3;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 4C median zone wise :- " + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS4C All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus4cAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4c_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "ALL";
            String commname = rs.getString("COMM_NAME");
            long col7 = rs.getLong("col7");
            long col3 = rs.getLong("col3");
            median = rs.getDouble("median_4c");
            double total;
            String absval = String.valueOf(col7) + "/" + String.valueOf(col3);

            total = col3 != 0 ? (double) col7 * 100 / col3 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks4c(totalScore);
            int insentavization = score.c_marks4c(totalScore);
            if (col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.3;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 4C median zone wise :- " + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=CUS4D zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4dZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4d_RA;
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col15 = rs.getInt("col15");
            int col17 = rs.getInt("col17");
            int col9 = rs.getInt("col9");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";

            absval = String.valueOf( col15 + col17) + "/" + String.valueOf(col9);

            if(col9 != 0) {
                total = ((double) (col15 + col17) * 100 / (col9));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks4d(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.1 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS4B Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4dZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4d_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col15 = rs.getInt("col15");
            int col17 = rs.getInt("col17");
            int col9 = rs.getInt("col9");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";

            absval = String.valueOf( col15  + col17) + "/" + String.valueOf(col9);

            if(col9 != 0) {
                total = ((double) ( col15 +  col17) * 100 / (col9));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks4d(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.1 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS4B All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus4dAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus4d_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col15 = rs.getInt("col15");
            int col17 = rs.getInt("col17");
            int col9 = rs.getInt("col9");
            int Zonal_rank = 0;
            String gst = "no";

            String absval = String.valueOf( col15 +  col17) + "/" + String.valueOf(col9);

            if(col9 != 0) {
                total = ((double) ( col15 +  col17) * 100 / (col9));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks4d(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.1;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus6aZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus6a_RA;
            String commname = "ALL";
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col9_3a = rs.getInt("col9_3a");
            int col9_3b = rs.getInt("col9_3b");
            int col3_3a = rs.getInt("col3_3a");
            int col3_3b = rs.getInt("col3_3b");
            median = rs.getDouble("median_6a");
            Double numerator_6c = rs.getDouble("numerator_9");
            int Zonal_rank = 0;
            String gst = "no";
            //String absval=String.valueOf(col9_3a+col9_3b)+"/"+String.valueOf(col3_3a+col3_3b);
            String absval = "";
            if (!(col9_3a + col9_3b == 0 && col3_3a + col3_3b == 0)) {
                absval = String.valueOf(col9_3a + col9_3b) + "/" + String.valueOf(col3_3a + col3_3b);
            }
            if ((col3_3a + col3_3b) != 0) {
                total = ((double) (col9_3a + col9_3b) * 100 / (col3_3a + col3_3b));
            } else {
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
            if (!(col9_3a + col9_3b == 0 && col3_3a + col3_3b == 0)) {
                gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra,
                        Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                allGstaList.add(gsta);
            }
            allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));

        }
        System.out.println("median cus6a zone wise :-" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6B Zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus6BZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus6b_RA;
            String commname = "ALL";
            String zoneCode = rs.getString("ZONE_CODE");
            int col18_3a = rs.getInt("col18_3a");
            int col18_3b = rs.getInt("col18_3b");
            int col12_3a = rs.getInt("col12_3a");
            int col12_3b = rs.getInt("col12_3b");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval = "";
            if (!(col18_3a+col18_3b == 0 && col12_3a+col12_3b== 0)) {
                absval = String.valueOf(col18_3a+col18_3b ) + "/" + String.valueOf(col12_3a+col12_3b);
            }
            if((col12_3a+col12_3b) != 0) {
                total = ((double) (col18_3a+col18_3b) * 100 / (col12_3a+col12_3b));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks6b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.2;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            if (!(col18_3a+col18_3b == 0 && col12_3a+col12_3b== 0)){
                gsta=new GSTCUS(rs.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                        Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                allGstaList.add(gsta);}
            allGstaList.sort((a, b) -> Double.compare(a.getTotal_score(), b.getTotal_score()));
        }
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6C zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus6cZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rsGst14aa.next()) {
            String ra = CustomRelaventAspect.cus6c_RA;
            String commname = "ALL";
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col18_1 = rsGst14aa.getInt("col18_1");
            int col8_ddm = rsGst14aa.getInt("col8_ddm");
            int col9_ddm = rsGst14aa.getInt("col9_ddm");
            median = rsGst14aa.getDouble("median_6c");
            Double numerator_6c = rsGst14aa.getDouble("col18_1");
            String absval = rsGst14aa.getString("abs_value_pq");
            int Zonal_rank = 0;
            String gst = "no";
            if ((col8_ddm + col9_ddm) != 0) {
                total = ((double) (col18_1) * 100 / (col8_ddm + col9_ddm));
            } else {
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
            gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore, absval, zoneCode, ra,
                    Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
            allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));

        }
        System.out.println("median cus6c zone wise :-" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6D zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus6dZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rsGst14aa.next()) {
            String ra = CustomRelaventAspect.cus6d_RA;
            String commname = "ALL";
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col4_7 = rsGst14aa.getInt("col4_7");
            int col5_cus3a = rsGst14aa.getInt("col5_cus3a");
            int col8_cus3a = rsGst14aa.getInt("col8_cus3a");
            int col5_cus3b = rsGst14aa.getInt("col5_cus3b");
            int col8_cus3b = rsGst14aa.getInt("col8_cus3b");
            median = rsGst14aa.getDouble("median_6c");
            Double numerator_6c = rsGst14aa.getDouble("col4_7");
            String absval = rsGst14aa.getString("absolute_value");
            total = rsGst14aa.getDouble("total_score");

            int Zonal_rank = 0;
            String gst = "no";
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
            gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore, absval, zoneCode, ra,
                    Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
            allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));

        }
        System.out.println("median cus6d zone wise :-" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6E zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus6eZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rsGst14aa.next()) {
            String ra = CustomRelaventAspect.cus6e_RA;
            String commname = "ALL";
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col9_3a = rsGst14aa.getInt("col9_3a");
            int col3_3a = rsGst14aa.getInt("col3_3a");
            median = rsGst14aa.getDouble("median_6e");
            Double numerator_6c = rsGst14aa.getDouble("col9_3a");
            //String absval = rsGst14aa.getString("absolute_value");
            //total = rsGst14aa.getDouble("total_score");

            int Zonal_rank = 0;
            String gst = "no";
            // int insentavization = 0;
            String absval = String.valueOf(col9_3a) + "/" + String.valueOf(col3_3a);
            if ((col3_3a) != 0) {
                total = ((double) (col9_3a) * 100 / (col3_3a));
            } else {
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
            gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore, absval, zoneCode, ra,
                    Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
            allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));

        }
        System.out.println("median cus6e zone wise :-" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6F zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus6fZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rsGst14aa.next()) {
            String ra= CustomRelaventAspect.cus6f_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zoneName =rsGst14aa.getString("ZONE_NAME");
            String absval = rsGst14aa.getString("absvl");
            total=rsGst14aa.getDouble("total_score") * 100;
            String commname= "ALL";
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
        }System.out.println("CUS 6F median zone wise :- "+median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus7A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus7aZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus7a_RA;
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col8 = rs.getInt("col8");
            int col5 = rs.getInt("col5");
            int Zonal_rank = 0;
            String gst = "no";

            String absval = String.valueOf(col8) + "/" + String.valueOf(col5);

            if(col5 != 0) {
                total = ((double) (col8 ) * 100 / (col5));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks7a(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.6 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS7A Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus7aZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus7a_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col8 = rs.getInt("col8");
            int col5 = rs.getInt("col5");
            int Zonal_rank = 0;
            String gst = "no";

            String absval = String.valueOf(col8) + "/" + String.valueOf(col5);

            if(col5 != 0) {
                total = ((double) (col8 ) * 100 / (col5));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks7a(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.6 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS7A All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus7aAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus7a_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col8 = rs.getInt("col8");
            int col5 = rs.getInt("col5");
            int Zonal_rank = 0;
            String gst = "no";

            String absval = String.valueOf(col8) + "/" + String.valueOf(col5);

            if(col5 != 0) {
                total = ((double) (col8 ) * 100 / (col5));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks7a(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.6 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus7B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus7bZone(ResultSet rs) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus7b_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            long col7 = rs.getLong("col7");
            long col10 = rs.getLong("col10");
             median = rs.getDouble("median_7b");
            double total;
            String absval = String.valueOf(col7) + "/" + String.valueOf(col10);
            total = col10 != 0 ? (double) col7 * 100 / col10 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks7b(totalScore);
            int insentavization = score.c_marks7b(totalScore);
            if (col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.4;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 4C median zone wise :- " + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS7B Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus7bZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus7b_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            long col7 = rs.getLong("col7");
            long col10 = rs.getLong("col10");
             median = rs.getDouble("median_7b");
            double total;
            String absval = String.valueOf(col7) + "/" + String.valueOf(col10);
            total = col10 != 0 ? (double) col7 * 100 / col10 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks7b(totalScore);
            int insentavization = score.c_marks7b(totalScore);
            if (col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.4;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 4C median commi zone wise :- " + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS7B All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus7bAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus7b_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            //String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            long col7 = rs.getLong("col7");
            long col10 = rs.getLong("col10");
            median = rs.getDouble("median_7b");
            double total;
            String absval = String.valueOf(col7) + "/" + String.valueOf(col10);
            total = col10 != 0 ? (double) col7 * 100 / col10 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks7b(totalScore);
            int insentavization = score.c_marks7b(totalScore);
            if (col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.4;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 4C median commi wise :- " + median);
        return allGstaList;
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus8A zone *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus8aZone(ResultSet rs) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus8a_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            long col7 = rs.getLong("col7");
            long col3 = rs.getLong("col3");
            median = rs.getDouble("median_8a");
            double total;
            String absval = String.valueOf(col7) + "/" + String.valueOf(col3);
            total = col3 != 0 ? (double) col7 * 100 / col3 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks8a(totalScore);
            int insentavization = score.c_marks8a(totalScore);
            if (col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.5;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 8A median zone wise :- " + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS8A Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus8aZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus8a_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            long col7 = rs.getLong("col7");
            long col3 = rs.getLong("col3");
            median = rs.getDouble("median_8a");
            double total;
            String absval = String.valueOf(col7) + "/" + String.valueOf(col3);
            total = col3 != 0 ? (double) col7 * 100 / col3 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks8a(totalScore);
            int insentavization = score.c_marks8a(totalScore);
            if (col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.5;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 8A median commi zone wise :- " + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS8A All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus8aAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus8a_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            long col7 = rs.getLong("col7");
            long col3 = rs.getLong("col3");
            median = rs.getDouble("median_8a");
            double total;
            String absval = String.valueOf(col7) + "/" + String.valueOf(col3);
            total = col3 != 0 ? (double) col7 * 100 / col3 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks8a(totalScore);
            int insentavization = score.c_marks8a(totalScore);
            if (col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.5;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 8A median commi wise :- " + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus8B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus8bZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus8b_RA;
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col13c = rs.getInt("col13c");
            int col11 = rs.getInt("col11");
            int Zonal_rank = 0;
            String gst = "no";

            String absval = String.valueOf(col13c) + "/" + String.valueOf(col11);

            if(col11 != 0) {
                total = ((double) (col13c ) * 100 / (col11));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks8b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS7A Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus8bZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus8b_RA;
            //String commname = "ALL";
             String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col13c = rs.getInt("col13c");
            int col11 = rs.getInt("col11");
            int Zonal_rank = 0;
            String gst = "no";

            String absval = String.valueOf(col13c) + "/" + String.valueOf(col11);

            if(col11 != 0) {
                total = ((double) (col13c ) * 100 / (col11));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks8b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS7A All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus8bAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus8b_RA;
            //String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col13c = rs.getInt("col13c");
            int col11 = rs.getInt("col11");
            int Zonal_rank = 0;
            String gst = "no";

            String absval = String.valueOf(col13c) + "/" + String.valueOf(col11);

            if(col11 != 0) {
                total = ((double) (col13c ) * 100 / (col11));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks8b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus10A ZONE *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus10aZone(ResultSet rs, int N) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus10a_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            String commname = "All";
            // String commname = rs.getString("COMM_NAME");
            double col15 = rs.getDouble("col15");
            double col3 = rs.getDouble("col3");
            median = rs.getDouble("median_10a");

            double x = col3 / (12 * N);
            String absval = String.format("%.2f/%.2f", col15, x);
            double total = (x != 0) ? (col15 * 100 / x) : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks10a(totalScore);
            int insentavization = score.c_marks10a(totalScore);
            if (col15 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.5;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no",
                    way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 10A median zone wise :- " + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS10A Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus10aZoneWiseCommissionary(ResultSet rs, int N) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus10a_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "All";
            String commname = rs.getString("COMM_NAME");
            double col15 = rs.getDouble("col15");
            double col3 = rs.getDouble("col3");
            median = rs.getDouble("median_10a");

            double x = col3 / (12 * N);
            String absval = String.format("%.2f/%.2f", col15, x);
            double total = (x != 0) ? (col15 * 100 / x) : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks10a(totalScore);
            int insentavization = score.c_marks10a(totalScore);

            if (col15 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.5;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no",
                    way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 10A median all commi wise :- " + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS10A All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus10aAllCommissionary(ResultSet rs, int N) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus10a_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "All";
            String commname = rs.getString("COMM_NAME");
            double col15 = rs.getDouble("col15");
            double col3 = rs.getDouble("col3");
            median = rs.getDouble("median_10a");

            double x = col3 / (12 * N);
            String absval = String.format("%.2f/%.2f", col15, x);

            double total = (x != 0) ? (col15 * 100 / x) : 0.00;

            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks10a(totalScore);
            int insentavization = score.c_marks10a(totalScore);

            if (col15 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.5;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no",
                    way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 10A median all commi wise :- " + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus10B Zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus10bZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus10b_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            long col21 = rs.getLong("col21");
            long col23 = rs.getLong("col23");
            double total;
            String absval = String.valueOf(col21 - col23) + "/" + String.valueOf(col21);
            total = col21 != 0 ? (double) (col21 - col23) * 100 / col21 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks10b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, 0, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS8A Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus10bZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus10b_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            long col21 = rs.getLong("col21");
            long col23 = rs.getLong("col23");
            double total;
            String absval = String.valueOf(col21 - col23) + "/" + String.valueOf(col21);
            total = col21 != 0 ? (double) (col21 - col23) * 100 / col21 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks10b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, 0, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS8A All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus10bAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus10b_RA;
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            long col21 = rs.getLong("col21");
            long col23 = rs.getLong("col23");
            double total;
            String absval = String.valueOf(col21 - col23) + "/" + String.valueOf(col21);
            total = col21 != 0 ? (double) (col21 - col23) * 100 / col21 : 0.00;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.c_marks10b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "no", way_to_grade, 0, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus11A Zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus11aZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus11a_RA;
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col9 = rs.getInt("col9");
            int col1 = rs.getInt("col1");
            int col3 = rs.getInt("col3");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";

            absval = String.valueOf(col9) + "/" + String.valueOf(col1 + col3);

            if((col1 + col3) != 0) {
                total = ((double) (col9) * 100 / (col1 + col3));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks11a(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS11A Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus11aZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus11a_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col9 = rs.getInt("col9");
            int col1 = rs.getInt("col1");
            int col3 = rs.getInt("col3");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";

            absval = String.valueOf(col9) + "/" + String.valueOf(col1 + col3);

            if((col1 + col3) != 0) {
                total = ((double) (col9) * 100 / (col1 + col3));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks11a(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS11A All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus11aAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus11a_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col9 = rs.getInt("col9");
            int col1 = rs.getInt("col1");
            int col3 = rs.getInt("col3");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";

            absval = String.valueOf(col9) + "/" + String.valueOf(col1 + col3);

            if((col1 + col3) != 0) {
                total = ((double) (col9) * 100 / (col1 + col3));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks11a(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus11B Zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> cus11bZone(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus11b_RA;
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            double col11 = rs.getInt("col11");
            double col2 = rs.getInt("col2");
            double col4 = rs.getInt("col4");
            median = rs.getDouble("median_11b");
            String absval = rs.getString("absval11b");

            int Zonal_rank = 0;
            String gst = "no";


            if((col2 + col4) != 0) {
                total = ((double) (col11) * 100 / (col2 + col4));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks11b(totalScore);
            int insentavization = score.c_marks11b(totalScore);
            if (col11 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS11B Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus11bZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus11b_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col11 = rs.getInt("col11");
            int col2 = rs.getInt("col2");
            int col4 = rs.getInt("col4");
            median = rs.getDouble("median_11b");

            int Zonal_rank = 0;
            String gst = "no";

            String absval = rs.getString("absval11b");

            if((col2 + col4) != 0) {
                total = ((double) (col11) * 100 / (col2 + col4));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks11b(totalScore);
            int insentavization = score.c_marks11b(totalScore);
            if (col11 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS11B All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GSTCUS> cus11bAllCommissionary(ResultSet rs) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        double median = 0;
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus11b_RA;
            // String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col11 = rs.getInt("col11");
            int col2 = rs.getInt("col2");
            int col4 = rs.getInt("col4");
            median = rs.getDouble("median_11b");

            int Zonal_rank = 0;
            String gst = "no";
            String absval = rs.getString("absval11b");


            if((col2 + col4) != 0) {
                total = ((double) (col11) * 100 / (col2 + col4));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks11b(totalScore);
            int insentavization = score.c_marks11b(totalScore);
            if (col11 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

}

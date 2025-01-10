package com.Cbic_Aaklan_Project.Service;

import com.Cbic_Aaklan_Project.entity.GST4A;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomSubParameterService {
    CustomGreadeScore score = new CustomGreadeScore();
    Double total;
    Double median;
    GST4A gsta = null;
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus1 zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2b zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2c zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3A All Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GST4A> cus3aZone(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3A median zone wise :- "+median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3A Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GST4A> cus3aZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3A median zone wise :- "+median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3A All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GST4A> cus3aAllCommissionary(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3A median zone wise :- "+median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3B All Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GST4A> cus3bZone(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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
            int way_to_grade = score.c_marks3a(totalScore);
            int insentavization = score.c_marks3a(totalScore);
            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.4 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3b median zone wise :- "+median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3B Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GST4A> cus3bZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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
            int way_to_grade = score.c_marks3a(totalScore);
            int insentavization = score.c_marks3a(totalScore);
            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.4 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3B median zone wise :- "+median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3B All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GST4A> cus3bAllCommissionary(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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
            int way_to_grade = score.c_marks3a(totalScore);
            int insentavization = score.c_marks3a(totalScore);
            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.4 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3B median zone wise :- "+median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3C All Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GST4A> cus3cZone(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3c_RA;
            String commname = "ALL";
            // String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            long col14_16 = rs.getInt("col14_16");
            long col13 = rs.getInt("col13");
            median = rs.getDouble("median_3c");
            Double numerator_3c = rs.getDouble("col14_16");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
            if (!(col14_16 == 0 && col13 == 0)) {
                absval = String.valueOf(col14_16) + "/" + String.valueOf(col13);
            }
            if(col13 != 0) {
                total = ((double) (col14_16) * 100 / (col13));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks3a(totalScore);
            int insentavization = score.c_marks3a(totalScore);
            if (numerator_3c > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3c median zone wise :- "+median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3C Particular Zone=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GST4A> cus3cZoneWiseCommissionary(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3c_RA;
            //String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            int col14_16 = rs.getInt("col14_16");
            int col13 = rs.getInt("col13");
            median = rs.getDouble("median_3c");
            Double numerator_3c = rs.getDouble("col14_16");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
            if (!(col14_16 == 0 && col13 == 0)) {
                absval = String.valueOf(col14_16) + "/" + String.valueOf(col13);
            }
            if(col13 != 0) {
                total = ((double) (col14_16) * 100 / (col13));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks3a(totalScore);
            int insentavization = score.c_marks3a(totalScore);
            if (numerator_3c > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3C median zone wise :- "+median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= CUS3C All Commissionary=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GST4A> cus3cAllCommissionary(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus3c_RA;
            //String commname = "ALL";
            String commname= rs.getString("COMM_NAME");
            String zoneName = rs.getString("ZONE_NAME");
            String zoneCode = rs.getString("ZONE_CODE");
            long col14_16 = rs.getInt("col14_16");
            long col13 = rs.getInt("col13");
            median = rs.getDouble("median_3c");
            Double numerator_3c = rs.getDouble("col14_16");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = "";
            if (!(col14_16 == 0 && col13 == 0)) {
                absval = String.valueOf(col14_16) + "/" + String.valueOf(col13);
            }
            if(col13 != 0) {
                total = ((double) (col14_16) * 100 / (col13));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks3a(totalScore);
            int insentavization = score.c_marks3a(totalScore);
            if (numerator_3c > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.3 ;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("CUS 3C median zone wise :- "+median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4C zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*



    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GST4A> cus6aZone(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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
                gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                        Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                allGstaList.add(gsta);}
            allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));

        }
        System.out.println("median cus6a zone wise :-" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6B Zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GST4A> cus6BZone(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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
                gsta=new GST4A(rs.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                        Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                allGstaList.add(gsta);}
            allGstaList.sort((a, b) -> Double.compare(a.getTotal_score(), b.getTotal_score()));
        }
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6C zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GST4A> cus6cZone(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        while (rsGst14aa.next()) {
            String ra = CustomRelaventAspect.cus6c_RA;
            String commname = "ALL";
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col18_1 = rsGst14aa.getInt("col18_1");
            int col8_ddm = rsGst14aa.getInt("col8_ddm");
            int col9_ddm = rsGst14aa.getInt("col9_ddm");
            double median = rsGst14aa.getDouble("median_6c");
            Double numerator_6c = rsGst14aa.getDouble("col18_1");
            String absval = rsGst14aa.getString("abs_value_pq");
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
            gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
            allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));

        }System.out.println("median cus6c zone wise :-" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6D zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GST4A> cus6dZone(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        while (rsGst14aa.next()) {
            String ra = CustomRelaventAspect.cus6d_RA;
            String commname = "ALL";
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col4_7 = rsGst14aa.getInt("col4_7");
            int col5_cus3a = rsGst14aa.getInt("col5_cus3a");
            int col8_cus3a = rsGst14aa.getInt("col8_cus3a");
            int col5_cus3b = rsGst14aa.getInt("col5_cus3b");
            int col8_cus3b = rsGst14aa.getInt("col8_cus3b");
            double median = rsGst14aa.getDouble("median_6c");
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
            gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
            allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));

        }System.out.println("median cus6d zone wise :-" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6E zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GST4A> cus6eZone(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        while (rsGst14aa.next()) {
            String ra = CustomRelaventAspect.cus6e_RA;
            String commname = "ALL";
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
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
            gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
            allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));

        }System.out.println("median cus6e zone wise :-" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus6F zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GST4A> cus6fZone(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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
            gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }System.out.println("CUS 6F median zone wise :- "+median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= cus7A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

}

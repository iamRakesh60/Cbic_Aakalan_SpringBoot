package com.Cbic_Aaklan_Project.Service.CGST;


import com.Cbic_Aaklan_Project.Service.RelevantAspect;
import com.Cbic_Aaklan_Project.entity.GSTCUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GstSubParameterService {
    GstGradeScore score = new GstGradeScore();
    Double total;
    Double median;
    GSTCUS gsta = null;


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1aZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST1A_RA;
            String commname = "ALL";
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col6 = rsGst14aa.getInt("col6");
            int col2 = rsGst14aa.getInt("col2");
            int col4 = rsGst14aa.getInt("col4");
            median = rsGst14aa.getDouble("median_1a");
            Double numerator_3a = rsGst14aa.getDouble("col6");
            String gst = "GST1A";


            String absval = String.valueOf(col6) + "/" + String.valueOf(col2 + col4);

            if ((col2 + col4) != 0) {
                total = (((double) col6 * 100) / (col2 + col4));
            } else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1a(totalScore);
            int insentavization = score.marks1a(totalScore);

            if (numerator_3a > median && way_to_grade < 10) {
                insentavization += 1;
            }
            Double sub_parameter_weighted_average = insentavization * 0.1;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore, absval, zoneCode, ra,
                    0, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1a zone wise median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1A COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST1A_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname = rsGst14aa.getString("COMM_NAME");
            //String zoneName = rsGst14aa.getString("ZONE_NAME");
            int col6 = rsGst14aa.getInt("col6");
            int col2 = rsGst14aa.getInt("col2");
            int col4 = rsGst14aa.getInt("col4");
            median = rsGst14aa.getDouble("median_1a");
            Double numerator_3a = rsGst14aa.getDouble("col6");
            String gst = "GST1A";


            String absval = String.valueOf(col6) + "/" + String.valueOf(col2 + col4);

            if ((col2 + col4) != 0) {
                total = (((double) col6 * 100) / (col2 + col4));
            } else {
                total = 0.00;
            }

            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1a(totalScore);
            int insentavization = score.marks1a(totalScore);

            if (numerator_3a > median && way_to_grade < 10) {
                insentavization += 1;
            }
            Double sub_parameter_weighted_average = insentavization * 0.1;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore, absval, zoneCode, ra,
                    0, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1a all_cmsnry_median wise median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1A ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST1A_RA;
            String ZONE_NAME = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname = rsGst14aa.getString("COMM_NAME");
            int col6 = rsGst14aa.getInt("col6");
            int col2 = rsGst14aa.getInt("col2");
            int col4 = rsGst14aa.getInt("col4");
            median = rsGst14aa.getDouble("median_1a");
            Double numerator_3a = rsGst14aa.getDouble("col6");
            String gst = "no";


            String absval = String.valueOf(col6) + "/" + String.valueOf(col2 + col4);

            if ((col2 + col4) != 0) {
                total = (((double) col6 * 100) / (col2 + col4));
            } else {
                total = 0.00;
            }

            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1a(totalScore);
            int insentavization = score.marks1a(totalScore);

            if (numerator_3a > median && way_to_grade < 10) {
                insentavization += 1;
            }
            Double sub_parameter_weighted_average = insentavization * 0.1;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            gsta = new GSTCUS(ZONE_NAME, commname, totalScore, absval, zoneCode, ra,
                    0, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1a all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1bZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST1B_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname = "ALL";
            int col10=rsGst14aa.getInt("col10");
            int col7=rsGst14aa.getInt("col7");
            String gst = "GST1B";
            int insentavization = 0;
            int sub_parameter_weighted_average = 0;
            if(col7 != 0) {
                total = (((double) col10 * 100) / col7);
            }else{
                total = 0.00;
            }
            String absval = String.valueOf(col10) + "/" + String.valueOf(col7);

            int way_to_grade = score.marks1b(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1B COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST1B_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname = rsGst14aa.getString("COMM_NAME");
            int col10=rsGst14aa.getInt("col10");
            int col7=rsGst14aa.getInt("col7");
            String gst = "GST1B";
            int insentavization = 0;
            int sub_parameter_weighted_average = 0;
            if(col7 != 0) {
                total = (((double) col10 * 100) / col7);
            }else{
                total = 0.00;
            }
            String absval = String.valueOf(col10) + "/" + String.valueOf(col7);

            int way_to_grade = score.marks1b(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1b all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1B ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1b all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1C zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1cZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1c zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1C COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1cZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1c all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1C ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1cAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1c all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1D zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1dZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1d zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1D COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1dZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1d all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1D ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1dAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1d all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1E zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1eZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1e zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1E COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1eZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1e all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1E ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1eAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1e all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1F zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1fZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1f zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1F COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1fZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1f all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1F ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1fAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst1f all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 4D zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GSTCUS> gst4dZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        List<Double> s2col7Values = new ArrayList<>();  // List to store all s2col7 values

        // First, collect all the s2col7 values
        while(rsGst14aa.next()) {
            double s2col7 = rsGst14aa.getInt("s2col7");
            s2col7Values.add(s2col7);
        }

        // Sort the list of s2col7 values to calculate median
        Collections.sort(s2col7Values);

        // Calculate the median
        double median = 0.00;
        int size = s2col7Values.size();
        if (size > 0) {
            if (size % 2 == 0) {
                // Even number of elements, average the two middle elements
                median = (s2col7Values.get(size / 2 - 1) + s2col7Values.get(size / 2)) / 2.0;
            } else {
                // Odd number of elements, pick the middle element
                median = s2col7Values.get(size / 2);
            }
        }

        // Now iterate through the ResultSet again to create GST4A objects
        rsGst14aa.beforeFirst();  // Reset the cursor to the beginning
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST4D_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String commname = "ALL";
            double s2col7 = rsGst14aa.getInt("s2col7");
            double s1col7 = rsGst14aa.getInt("s1col7");
            double totalScore = (s1col7 != 0) ? (s2col7 / s1col7) * 100 : 0.00;
            String formattedTotal = String.format("%.2f", totalScore);
            totalScore = Double.parseDouble(formattedTotal);
            String absval = String.valueOf(s2col7) + "/" + String.valueOf(s1col7);
            int way_to_grade = score.marks4d(totalScore);
            int Zonal_rank = 0;
            String gst = "GST 4D";

            int insentavization = score.marks4d(totalScore);

            if (s2col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

            GSTCUS gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst8aZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra=RelevantAspect.GST8A_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String absval = rsGst14aa.getString("absval");
            Double t_score = rsGst14aa.getDouble("total_score8A");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            String gst = "no";
            median = rsGst14aa.getDouble("median_col15");
            Double numerator_3b = rsGst14aa.getDouble("col15");
            int way_to_grade = score.marks8a(totalScore);
            int insentavization = score.marks8a(totalScore);
            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average_bfore = insentavization * 0.6;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

            gsta = new GSTCUS(zoneName, "ALL", totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst8a zone wise median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8A COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst8aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.GST8A_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");

            String absval = rsGst14aa.getString("absval");
            median = rsGst14aa.getDouble("median_col15");
            Double t_score = rsGst14aa.getDouble("total_score8A");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";
            Double numerator_3b = rsGst14aa.getDouble("col15");
            int way_to_grade = score.marks8a(totalScore);
            int insentavization = score.marks8a(totalScore);
            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average_bfore = insentavization * 0.6;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst8a all_cmsnry_median wise median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8A ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst8aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.GST8A_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String absval = rsGst14aa.getString("absval");
            median = rsGst14aa.getDouble("median_col15");
            Double numerator_3b = rsGst14aa.getDouble("col15");
            Double t_score = rsGst14aa.getDouble("total_score8A");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";

            int way_to_grade = score.marks8a(totalScore);
            int insentavization = score.marks8a(totalScore);
            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average_bfore = insentavization * 0.6;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst8a all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst8bZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String ra=RelevantAspect.GST8B_RA;
//            int col21=rsGst14aa.getInt("col21");
//            int col23=rsGst14aa.getInt("col23");
            Double t_score = rsGst14aa.getDouble("total_score");
            String absval=rsGst14aa.getString("abs_value");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks8b(totalScore);
            Double sub_parameter_weighted_average_before = way_to_grade * 0.4;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8B COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst8bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String commname=rsGst14aa.getString("COMM_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            Double t_score = rsGst14aa.getDouble("total_score");
            String ra=RelevantAspect.GST8B_RA;
//            int col21=rsGst14aa.getInt("col21");
//            int col23=rsGst14aa.getInt("col23");
            String absval=rsGst14aa.getString("abs_value");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks8b(totalScore);
            Double sub_parameter_weighted_average_before = way_to_grade * 0.4;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8B ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst8bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String commname=rsGst14aa.getString("COMM_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            Double t_score = rsGst14aa.getDouble("total_score");
            String ra=RelevantAspect.GST8B_RA;
//            int col21=rsGst14aa.getInt("col21");
//            int col23=rsGst14aa.getInt("col23");
            String absval=rsGst14aa.getString("abs_value");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks8b(totalScore);
            Double sub_parameter_weighted_average_before = way_to_grade * 0.4;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 9A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst9aZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST9A_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname= "ALL";
            int col8=rsGst14aa.getInt("col8");
            int col5=rsGst14aa.getInt("col5");
            double total = rsGst14aa.getDouble("total_score9A") ;
            String gst = "no";

            String absval=String.valueOf(col8)+"/"+String.valueOf(col5);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks9a(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.5;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 9A COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst9aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST9A_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col8=rsGst14aa.getInt("col8");
            int col5=rsGst14aa.getInt("col5");

            double total = rsGst14aa.getDouble("total_score9A") ;
            String gst = "no";
            String absval=String.valueOf(col8)+"/"+String.valueOf(col5 );
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks9a(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.5;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 9A ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst9aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST9A_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col8=rsGst14aa.getInt("col8");
            int col5=rsGst14aa.getInt("col5");

            double total = rsGst14aa.getDouble("total_score9A") ;
            String gst = "no";
            String absval=String.valueOf(col8)+"/"+String.valueOf(col5 );
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks9a(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.5;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,0,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 9B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst9bZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST9B_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zone_name = rsGst14aa.getString("ZONE_NAME");
            String commname= "ALL";
            int col4=rsGst14aa.getInt("col4_4");
            int col4_1=rsGst14aa.getInt("col1_4");
            Double t_score = rsGst14aa.getDouble("score_of_subparameter9b");
            double median = rsGst14aa.getDouble("median9_b");
            Double numerator_6a = rsGst14aa.getDouble("col4_4");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks9b(totalScore);
            int insentavization = score.marks9b(totalScore);

            if (numerator_6a > median && way_to_grade < 10) {
                insentavization += 1;
            }
            String gst = "no";
            double sub_parameter_weighted_average = insentavization * 0.5;
            String absval = String.valueOf(col4) + "/" + String.valueOf(col4_1);
            gsta = new GSTCUS(zone_name, commname, totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 9B COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst9bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST9B_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            String zone_name = rsGst14aa.getString("ZONE_NAME");

            Double t_score = rsGst14aa.getDouble("score_of_subparameter9b");
            int col4_4=rsGst14aa.getInt("col4_4");
            int col1_4=rsGst14aa.getInt("col1_4");
            double median = rsGst14aa.getDouble("median_9b");
            Double numerator_6a = rsGst14aa.getDouble("col4_4");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks9b(totalScore);
            int insentavization = score.marks9b(totalScore);

            if (numerator_6a > median && way_to_grade < 10) {
                insentavization += 1;
            }
            String gst = "no";
            double sub_parameter_weighted_average = insentavization * 0.5;
            String absval = String.valueOf(col4_4) + "/" + String.valueOf(col1_4);
            gsta = new GSTCUS(zone_name, commname, totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 9B ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst9bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST9B_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zone_name = rsGst14aa.getString("ZONE_NAME");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col4_4=rsGst14aa.getInt("col4_4");
            int col1_4=rsGst14aa.getInt("col1_4");
            Double t_score = rsGst14aa.getDouble("score_of_subparameter9b");

            double median = rsGst14aa.getDouble("median_9b");
            Double numerator_6a = rsGst14aa.getDouble("col4_4");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks9b(totalScore);
            int insentavization = score.marks9b(totalScore);

            if (numerator_6a > median && way_to_grade < 10) {
                insentavization += 1;
            }
            String gst = "no";
            double sub_parameter_weighted_average = insentavization * 0.5;
            String absval = String.valueOf(col4_4) + "/" + String.valueOf(col1_4);
            gsta = new GSTCUS(zone_name, commname, totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

}

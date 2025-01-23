package com.Cbic_Aaklan_Project.Service;


import com.Cbic_Aaklan_Project.entity.GST4A;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GstSubParameterService {
    GstGradeScore score = new GstGradeScore();
    Double totalScore;
    Double median;
    GST4A gsta = null;

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
//    public List<GST4A> gst3bZone(ResultSet rsGst14aa) throws SQLException {
//        List<GST4A> allGstaList = new ArrayList<>();
//
//        while (rsGst14aa.next()) {
//            String commname = "All";
//            String ra = RelevantAspect.Gst3B_RA;
//            String zoneName = rsGst14aa.getString("ZONE_NAME");
//            String zoneCode = rsGst14aa.getString("ZONE_CODE");
//            Double col22 = rsGst14aa.getDouble("col22");
//            Double col14 = rsGst14aa.getDouble("col14");
//
//            double totalScore = (col14 != 0) ? (col22 / col14) * 100 : 0.00;
//            String formattedTotal = String.format("%.2f", totalScore);
//            totalScore = Double.parseDouble(formattedTotal);
//
//            String absval = String.valueOf(col22) + "/" + String.valueOf(col14);
//            int way_to_grade = score.marks3b(totalScore);
//
//            int Zonal_rank = 0;
//            String gst = "GST 3B";
//
//            Integer insentavization = 0;
//            double sub_parameter_weighted_average = 0.00;
//            gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
//            allGstaList.add(gsta);
//        }
//        return allGstaList;
//    }
        // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 4D zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GST4A> gst4dZone(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
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

            GST4A gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8A zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GST4A> gst8aZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GST4A> allGstaList = new ArrayList<>();
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

            gsta = new GST4A(zoneName, "ALL", totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst8a zone wise median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8A COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GST4A> gst8aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GST4A> allGstaList = new ArrayList<>();
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

            gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst8a all_cmsnry_median wise median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8A ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GST4A> gst8aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GST4A> allGstaList = new ArrayList<>();
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

            gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst8a all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GST4A> gst8bZone(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String ra=RelevantAspect.GST8B_RA;
            int col21=rsGst14aa.getInt("col21");
            int col23=rsGst14aa.getInt("col23");
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
            gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8B COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GST4A> gst8bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String commname=rsGst14aa.getString("COMM_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            Double t_score = rsGst14aa.getDouble("total_score");
            String ra=RelevantAspect.GST8B_RA;
            int col21=rsGst14aa.getInt("col21");
            int col23=rsGst14aa.getInt("col23");
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
            gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 8B ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GST4A> gst8bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GST4A> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String commname=rsGst14aa.getString("COMM_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            Double t_score = rsGst14aa.getDouble("total_score");
            String ra=RelevantAspect.GST8B_RA;
            int col21=rsGst14aa.getInt("col21");
            int col23=rsGst14aa.getInt("col23");
            String absval=rsGst14aa.getString("abs_value");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            // int sub_parameter_weighted_average = 0;
//                    total = col20 != 0 ? (((double) (col20 - col22) * 100) / col20) : 0.0;
//
//                    total=((double) (col20-col22)/ col20);
//                    //}
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks8b(totalScore);
            Double sub_parameter_weighted_average_before = way_to_grade * 0.4;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

            gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

}

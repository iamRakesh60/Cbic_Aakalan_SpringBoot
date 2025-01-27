package com.Cbic_Aakalan_Project.Service.CGST;


import com.Cbic_Aakalan_Project.Service.RelevantAspect;
import com.Cbic_Aakalan_Project.entity.GSTCUS;

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
            String ra= RelevantAspect.GST1C_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname="ALL";
            int col13 = rsGst14aa.getInt("col13");
            int col2 = rsGst14aa.getInt("col2");
            int col3 = rsGst14aa.getInt("col3");
            int col4 = rsGst14aa.getInt("col4");
            int col5 = rsGst14aa.getInt("col5");
            Double t_score = rsGst14aa.getDouble("total_score1c");
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col13)+"/"+String.valueOf(col2 + col3+col4+col5);

            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.marks1c(totalScore);
            double sub_parameter_weighted_average_bfore = way_to_grade * 0.1;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1C COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1cZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST1C_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col13 = rsGst14aa.getInt("col13");
            int col2 = rsGst14aa.getInt("col2");
            int col3 = rsGst14aa.getInt("col3");
            int col4 = rsGst14aa.getInt("col4");
            int col5 = rsGst14aa.getInt("col5");
            Double t_score = rsGst14aa.getDouble("total_score1c");
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col13)+"/"+String.valueOf(col2 + col3+col4+col5);

            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.marks1c(totalScore);
            double sub_parameter_weighted_average_bfore = way_to_grade * 0.1;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1C ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1cAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST1C_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col13 = rsGst14aa.getInt("col13");
            int col2 = rsGst14aa.getInt("col2");
            int col3 = rsGst14aa.getInt("col3");
            int col4 = rsGst14aa.getInt("col4");
            int col5 = rsGst14aa.getInt("col5");
            Double t_score = rsGst14aa.getDouble("total_score1c");
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col13)+"/"+String.valueOf(col2 + col3+col4+col5);

            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade =score.marks1c(totalScore);
            double sub_parameter_weighted_average_bfore = way_to_grade * 0.1;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    0,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1D zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1dZone(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST1D_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col17 = rsGst14aa.getInt("col17");
            int col1 = rsGst14aa.getInt("col1");
            int col2 = rsGst14aa.getInt("col2");
            int col3 = rsGst14aa.getInt("col3");
            int col4 = rsGst14aa.getInt("col4");
            int col5 = rsGst14aa.getInt("col5");
            Double t_score = rsGst14aa.getDouble("total_score");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col17)+"/"+String.valueOf((col1 + col2 + col3+col4+col5));
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1d(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.1;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1D COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1dZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST1D_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col17 = rsGst14aa.getInt("col17");
            int col1 = rsGst14aa.getInt("col1");
            int col2 = rsGst14aa.getInt("col2");
            int col3 = rsGst14aa.getInt("col3");
            int col4 = rsGst14aa.getInt("col4");
            int col5 = rsGst14aa.getInt("col5");
            Double t_score = rsGst14aa.getDouble("total_score");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col17)+"/"+String.valueOf((col1 + col2 + col3));
//                    if((col1 + col2 + col3) != 0) {
//                        total = ((double) col14 * 100 / (col1 + col2 + col3));
//                    }else{
//                        total = 0.00;
//                    }
            //rank = score.marks1d(total);
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade =score.marks1d(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.1;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1D ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1dAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST1D_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col17 = rsGst14aa.getInt("col17");
            int col1 = rsGst14aa.getInt("col1");
            int col2 = rsGst14aa.getInt("col2");
            int col3 = rsGst14aa.getInt("col3");
            int col4 = rsGst14aa.getInt("col4");
            int col5 = rsGst14aa.getInt("col5");
            Double t_score = rsGst14aa.getDouble("total_score");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col17)+"/"+String.valueOf((col1 + col2 + col3+col4+col5));


//                    if((col1 + col2 + col3) != 0) {
//                        total = ((double) col14 * 100/ (col1 + col2 + col3));
//                    }else{
//                        total = 0.00;
//                    }
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1d(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.1;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1E zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1eZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST1E_RA;
            String commname="ALL";
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col9 = rsGst14aa.getInt("col9");
            int col1 = rsGst14aa.getInt("col1");
            int col2 = rsGst14aa.getInt("col2");
            int col5 = rsGst14aa.getInt("col5");
            int col6 = rsGst14aa.getInt("col6");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

            if((col1 + col2 + col5 + col6) != 0) {
                total = (((double) col9 * 100)/ (col1 + col2 + col5 + col6));
            }else {
                total=0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1e(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.3;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1e zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1E COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1eZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST1E_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col9 = rsGst14aa.getInt("col9");
            int col1 = rsGst14aa.getInt("col1");
            int col2 = rsGst14aa.getInt("col2");
            int col5 = rsGst14aa.getInt("col5");
            int col6 = rsGst14aa.getInt("col6");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

            if((col1 + col2 + col5 + col6) != 0) {
                total = (((double) col9 * 100)/ (col1 + col2 + col5 + col6));
            }else {
                total=0.00;
            }
            //rank = score.marks1e(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1e(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.3;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1e all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1E ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1eAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST1E_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col9 = rsGst14aa.getInt("col9");
            int col1 = rsGst14aa.getInt("col1");
            int col2 = rsGst14aa.getInt("col2");
            int col5 = rsGst14aa.getInt("col5");
            int col6 = rsGst14aa.getInt("col6");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

            if((col1 + col2 + col5 + col6) != 0) {
                total = (((double) col9 * 100)/ (col1 + col2 + col5 + col6));
            }else {
                total=0.00;
            }
            //rank = score.marks1e(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1e(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.3;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1e all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1F zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1fZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.GST1F_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col14 = rsGst14aa.getInt("col14");
            int col10 = rsGst14aa.getInt("col10");
            int col11 = rsGst14aa.getInt("col11");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col14)+"/"+String.valueOf(col10 + col11);

            if ((col10 + col11) != 0) {
                total = (((double) col14 * 100)/ (col10 + col11));
            }else{
                total=0.00;
            }

            //rank = score.marks1f(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1f(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.3;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1f zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1F COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1fZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST1F_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            String zonename = rsGst14aa.getString("ZONE_NAME");
            int col14 = rsGst14aa.getInt("col14");
            int col10 = rsGst14aa.getInt("col10");
            int col11 = rsGst14aa.getInt("col11");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval = String.valueOf(col14) + "/" + String.valueOf(col10 + col11);

            if ((col10 + col11) != 0) {
                total = (((double) col14 * 100)/ (col10 + col11));
            } else {
                total = 0.00;
            }

            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1f(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.3;
            gsta = new GSTCUS(zonename, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1f all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 1F ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst1fAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST1F_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            String zonename = rsGst14aa.getString("ZONE_NAME");
            int col14 = rsGst14aa.getInt("col14");
            int col10 = rsGst14aa.getInt("col10");
            int col11 = rsGst14aa.getInt("col11");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval = String.valueOf(col14) + "/" + String.valueOf(col10 + col11);

            if ((col10 + col11) != 0) {
                total = (((double) col14 * 100)/ (col10 + col11));
            } else {
                total = 0.00;
            }

            //rank = score.marks1f(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks1f(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.3;
            gsta = new GSTCUS(zonename, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst1f all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 2 zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst2Zone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra= RelevantAspect.Gst2_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname= "ALL";
            //String zoneName = rsGst14aa.getString("ZONE_NAME");
            int col21=rsGst14aa.getInt("col21");
            int col3=rsGst14aa.getInt("col3");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col21)+"/"+String.valueOf(col3);
            total = rsGst14aa.getDouble("total_score");

            //total=((double) col21 / col3);
            //}
            //rank=score.marks2(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks2(totalScore);
            int sub_parameter_weighted_average = way_to_grade;
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst2 zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 2 COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst2ZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra=RelevantAspect.Gst2_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            int col21=rsGst14aa.getInt("col21");
            int col3=rsGst14aa.getInt("col3");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col21)+"/"+String.valueOf(col3);

            total=(((double) col21) * 100 / col3);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks2(totalScore);
            int sub_parameter_weighted_average = way_to_grade;

            // rank=score.marks2(total);
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst2 all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 2 ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst2AllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra=RelevantAspect.Gst2_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            //String zoneName = rsGst14aa.getString("ZONE_NAME");
            int col21=rsGst14aa.getInt("col21");
            int col3=rsGst14aa.getInt("col3");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;
            String absval=String.valueOf(col21)+"/"+String.valueOf(col3);

            total=(((double) col21) *100 / col3);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks2(totalScore);
            int sub_parameter_weighted_average = way_to_grade;
            //rank=score.marks2(total);
            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst2 all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst3a zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst3aZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname = "All";
            String ra = RelevantAspect.Gst3A_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String absval = rsGst14aa.getString("absval");
            Double t_score = rsGst14aa.getDouble("total_score");
            median = rsGst14aa.getDouble("median_3a");
            Double numerator_3a = rsGst14aa.getDouble("col3a");

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

            gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst3a zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst3a COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst3aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.Gst3A_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            String absval = rsGst14aa.getString("absval");
            median = rsGst14aa.getDouble("median_3a");
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
        System.out.println("gst3a all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst3a ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst3aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.Gst3A_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            String absval = rsGst14aa.getString("absval");

            median = rsGst14aa.getDouble("median_3a");
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
        System.out.println("gst3a all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst3b zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst3bZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname = "ALL";
            String ra = RelevantAspect.Gst3B_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String absval = rsGst14aa.getString("absval");
            double t_score = rsGst14aa.getDouble("score_of_parameter");
            median = rsGst14aa.getDouble("median_numerator_3b");
            Double numerator_3b = rsGst14aa.getDouble("col26");


            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks3b(totalScore);
            int insentavization = score.marks3b(totalScore);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }
            int Zonal_rank = 0;
            String gst = "no";

            Double sub_parameter_weighted_average = insentavization * 0.5;
            gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra,
                    Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst3b zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst3b COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst3bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.Gst3B_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String absval=rsGst14aa.getString("absval");
            double t_score = rsGst14aa.getDouble("score_of_parameter") ;
            median = rsGst14aa.getDouble("median_col26");
            Double numerator_3b = rsGst14aa.getDouble("col26");


            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks3b(totalScore);
            int insentavization = score.marks3b(totalScore);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }
            int Zonal_rank = 0;
            String gst = "no";

            Double sub_parameter_weighted_average = insentavization * 0.5 ;
            gsta=new GSTCUS(zoneName, commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst3b all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst3b ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst3bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.Gst3B_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String absval=rsGst14aa.getString("absval");
            double t_score = rsGst14aa.getDouble("score_of_parameter") ;
            median = rsGst14aa.getDouble("median_col26");
            Double numerator_3b = rsGst14aa.getDouble("col26");


            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks3b(totalScore);
            int insentavization = score.marks3b(totalScore);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }
            int Zonal_rank = 0;
            String gst = "no";

            Double sub_parameter_weighted_average = insentavization * 0.5 ;
            gsta=new GSTCUS(zoneName, commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst3b all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4a zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4aZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname="ALL";
            String ra=RelevantAspect.GST4A_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zoneName = rsGst14aa.getString("ZONE_NAME");

            int col13=rsGst14aa.getInt("col13");
            int col1=rsGst14aa.getInt("col1");
            Double t_score = rsGst14aa.getDouble("total_score");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";

            // int insentavization = 0;
            // int sub_parameter_weighted_average = 0;
            String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

            //Double t_score = rsGst14aa.getDouble("score_of_parameter");
            median = rsGst14aa.getDouble("median_4a");
            Double numerator_3b = rsGst14aa.getDouble("col13");


            int way_to_grade = score.marks4a(totalScore);
            int insentavization = score.marks4a(totalScore);
            // System.out.println("insentavization3b :-" + insentavization);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }

            //System.out.println("insentavization3b after :-" + insentavization);

            // int Zonal_rank = 0;
            // String gst = "no";

            double sub_parameter_weighted_average_bfore = insentavization * 0.3 ;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            //double sub_parameter_weighted_average = 0.00 ;

            gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst4a zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4a COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra=RelevantAspect.GST4A_RA;
            String commname=rsGst14aa.getString("COMM_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            Double t_score = rsGst14aa.getDouble("score_of_parameter4a");
            int col13=rsGst14aa.getInt("col13");
            int col1=rsGst14aa.getInt("col1");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";

            // int insentavization = 0;
            // int sub_parameter_weighted_average = 0;
            String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

            //Double t_score = rsGst14aa.getDouble("score_of_parameter");
            median = rsGst14aa.getDouble("median_4a");
            Double numerator_3b = rsGst14aa.getDouble("col13");


            int way_to_grade = score.marks4a(totalScore);
            int insentavization = score.marks4a(totalScore);
            // System.out.println("insentavization3b :-" + insentavization);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }

            //System.out.println("insentavization3b after :-" + insentavization);

            // int Zonal_rank = 0;
            // String gst = "no";

            double sub_parameter_weighted_average_bfore = insentavization * 0.3 ;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            //double sub_parameter_weighted_average = 0.00 ;

            gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst4a all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4a ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra=RelevantAspect.GST4A_RA;
            String commname=rsGst14aa.getString("COMM_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            Double t_score = rsGst14aa.getDouble("score_of_parameter4a");
            int col13=rsGst14aa.getInt("col13");
            int col1=rsGst14aa.getInt("col1");
            // Double t_score = rsGst14aa.getDouble("total_score");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";

            // int insentavization = 0;
            // int sub_parameter_weighted_average = 0;
            String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

            //Double t_score = rsGst14aa.getDouble("score_of_parameter");
            median = rsGst14aa.getDouble("median_4a");
            Double numerator_3b = rsGst14aa.getDouble("col13");


            int way_to_grade = score.marks4a(totalScore);
            int insentavization = score.marks4a(totalScore);
            // System.out.println("insentavization3b :-" + insentavization);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }

            //System.out.println("insentavization3b after :-" + insentavization);

            // int Zonal_rank = 0;
            // String gst = "no";

            double sub_parameter_weighted_average_bfore = insentavization * 0.3 ;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            //double sub_parameter_weighted_average = 0.00 ;

            gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst4a all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4b zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4bZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.Gst4B_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col29 = rsGst14aa.getInt("col29");
            int col31 = rsGst14aa.getInt("col31");
            int col25 = rsGst14aa.getInt("col25");
            Double t_score = rsGst14aa.getDouble("total_score4b");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";
            // int way_to_grade = 0;
            int insentavization = 0;
//                    int sub_parameter_weighted_average = 0;
            //  int way_to_grade = score.marks4b(totalScore);
//                    int sub_parameter_weighted_average = way_to_grade*0.3;
//                    if (col25 != 0) {
//                        total = (((double) (col29 + col31) * 100) / col25);
//                    }
            //}
            //double totalScore = 0;
            int way_to_grade = score.marks4b(totalScore);
            double sub_parameter_weighted_average_bfore = way_to_grade * 0.3;
            // String formattedTotal = String.format("%.2f", total);
            totalScore = Double.parseDouble(formattedTotal);
            String absval = String.valueOf(col29 + col31) + "/" + String.valueOf(col25);
            gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore, absval, zoneCode, ra,
                    Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average_bfore);
            allGstaList.add(gsta);
        }
        System.out.println("gst4b zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4b COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.Gst4B_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col29=rsGst14aa.getInt("col29");
            int col31=rsGst14aa.getInt("col31");
            int col25=rsGst14aa.getInt("col25");
            Double t_score = rsGst14aa.getDouble("total_score4b");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";
            // int way_to_grade = 0;
            int insentavization = 0;
            //  int sub_parameter_weighted_average = 0;
            String absval = String.valueOf(col29+col31) + "/" + String.valueOf(col25);
            // if(col25!=0) {
            //total=(((double) (col29+col31) * 100) / col25);
            //}
            //}
            int way_to_grade = score.marks4b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.3;
            // String formattedTotal = String.format("%.2f", total);
            //double totalScore = Double.parseDouble(formattedTotal);

            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);

            allGstaList.add(gsta);
        }
        System.out.println("gst4b all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4b ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.Gst4B_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            int col29=rsGst14aa.getInt("col29");
            int col31=rsGst14aa.getInt("col31");
            int col25=rsGst14aa.getInt("col25");
            Double t_score = rsGst14aa.getDouble("total_score4b");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";
            //int way_to_grade = 0;
            int insentavization = 0;
            // int sub_parameter_weighted_average = 0;
            String absval = String.valueOf(col29+col31) + "/" + String.valueOf(col25);
            // if(col25!=0) {
            // total=(((double) (col29+col31) * 100) / col25);
            //}
            //}
            int way_to_grade = score.marks4b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.3;
            //  String formattedTotal = String.format("%.2f", total);
            // double totalScore = Double.parseDouble(formattedTotal);

            gsta=new GSTCUS(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);

            allGstaList.add(gsta);
        }
        System.out.println("gst4b all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4c zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4cZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra=RelevantAspect.Gst4C_RA;
            String commname="ALL";
            String zoneName =rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            Double t_score = rsGst14aa.getDouble("score_of_parameter4c");
            String absval = rsGst14aa.getString("avsvl");
            //double col1_7=rsGst14aa.getInt("col1_7");
            // double col1_8=rsGst14aa.getInt("col1_8");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";

            // int insentavization = 0;
            // int sub_parameter_weighted_average = 0;
            //String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

            //Double t_score = rsGst14aa.getDouble("score_of_parameter");
            median = rsGst14aa.getDouble("median_4c");
            Double numerator_3b = rsGst14aa.getDouble("col1_7");


            int way_to_grade = score.marks4c(totalScore);
            int insentavization = score.marks4c(totalScore);
            // System.out.println("insentavization3b :-" + insentavization);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }

            //System.out.println("insentavization3b after :-" + insentavization);

            // int Zonal_rank = 0;
            // String gst = "no";

            double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            //double sub_parameter_weighted_average = 0.00 ;

            gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst4c zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4c COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4cZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.Gst4C_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            Double t_score = rsGst14aa.getDouble("score_of_subparameter4c");
            String absval = rsGst14aa.getString("avsvl");
            median = rsGst14aa.getDouble("median");
            int col1_7=rsGst14aa.getInt("col1_7");
            // int col1_8=rsGst14aa.getInt("col1_8");

            //rank=score.marks4c(total);
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks4c(totalScore);
            int insentavization = score.marks4c(totalScore);

            if (col1_7 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            int Zonal_rank = 0;
            String gst = "no";
            double sub_parameter_weighted_average = insentavization * 0.2;
            sub_parameter_weighted_average = Double.parseDouble(String.format("%.2f", sub_parameter_weighted_average));
            // rank = score.marks6a(total);
            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst4c all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4c ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4cAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.Gst4C_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            Double t_score = rsGst14aa.getDouble("score_of_subparameter4c");
            String absval = rsGst14aa.getString("avsvl");
            median = rsGst14aa.getDouble("median");
            int col1_7=rsGst14aa.getInt("col1_7");
            // int col1_8=rsGst14aa.getInt("col1_8");

            //rank=score.marks4c(total);
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks4c(totalScore);
            int insentavization = score.marks4c(totalScore);

            if (col1_7 > median && way_to_grade < 10) {
                insentavization += 1;
            }
            int Zonal_rank = 0;
            String gst = "no";
            double sub_parameter_weighted_average = insentavization * 0.2;
            sub_parameter_weighted_average = Double.parseDouble(String.format("%.2f", sub_parameter_weighted_average));
            // rank = score.marks6a(total);
            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst4c all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 4D zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public  List<GSTCUS> gst4dZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra=RelevantAspect.GST4D_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String commname="ALL";
            Double t_score = rsGst14aa.getDouble("total_score");
            String absval = rsGst14aa.getString("absval");
            double col6_1=rsGst14aa.getInt("col6_1");
            double col6_3=rsGst14aa.getInt("col6_3");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";

            // int insentavization = 0;
            // int sub_parameter_weighted_average = 0;
//                    String absval=String.valueOf(col6_1)+"/"+String.valueOf(col6_3);

            //Double t_score = rsGst14aa.getDouble("score_of_parameter");
             median = rsGst14aa.getDouble("median_4d");
            Double numerator_3b = rsGst14aa.getDouble("col6_1");


            int way_to_grade = score.marks4d(totalScore);
            int insentavization = score.marks4d(totalScore);
            // System.out.println("insentavization3b :-" + insentavization);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }

            //System.out.println("insentavization3b after :-" + insentavization);

            // int Zonal_rank = 0;
            // String gst = "no";

            double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            //double sub_parameter_weighted_average = 0.00 ;

            gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst4d zone wise median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4d COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4dZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.GST4D_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            Double t_score = rsGst14aa.getDouble("total_score");
            String absval = rsGst14aa.getString("absvl");
            double col6_1=rsGst14aa.getInt("col6_1");
            double col6_3=rsGst14aa.getInt("col6_3");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";

            // int insentavization = 0;
            // int sub_parameter_weighted_average = 0;
            //String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

            //Double t_score = rsGst14aa.getDouble("score_of_parameter");
             median = rsGst14aa.getDouble("median_4d");
            Double numerator_3b = rsGst14aa.getDouble("col6_1");
//                    String absval=String.valueOf(col6_1)+"/"+String.valueOf(col6_3);


            int way_to_grade = score.marks4d(totalScore);
            int insentavization = score.marks4d(totalScore);
            // System.out.println("insentavization3b :-" + insentavization);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }

            //System.out.println("insentavization3b after :-" + insentavization);

            // int Zonal_rank = 0;
            // String gst = "no";

            double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            //double sub_parameter_weighted_average = 0.00 ;

            gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst4d all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst4d ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst4dAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.GST4D_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            Double t_score = rsGst14aa.getDouble("total_score");
            String absval = rsGst14aa.getString("absvl");
            double col6_1=rsGst14aa.getInt("col6_1");
            double col6_3=rsGst14aa.getInt("col6_3");
            String formattedTotal = String.format("%.2f", t_score);
            double totalScore = Double.parseDouble(formattedTotal);
            int Zonal_rank = 0;
            String gst = "no";

            // int insentavization = 0;
            // int sub_parameter_weighted_average = 0;
            //String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

            //Double t_score = rsGst14aa.getDouble("score_of_parameter");
             median = rsGst14aa.getDouble("median_4d");
            Double numerator_3b = rsGst14aa.getDouble("col6_1");
//                    String absval=String.valueOf(col6_1)+"/"+String.valueOf(col6_3);


            int way_to_grade = score.marks4d(totalScore);
            int insentavization = score.marks4d(totalScore);
            // System.out.println("insentavization3b :-" + insentavization);

            if (numerator_3b > median && way_to_grade < 10) {
                insentavization += 1;
            }

            //System.out.println("insentavization3b after :-" + insentavization);

            // int Zonal_rank = 0;
            // String gst = "no";

            double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
            //double sub_parameter_weighted_average = 0.00 ;

            gsta = new GSTCUS(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst4d all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst5a zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst5aZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST5A_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname = "All";
            int col10 = rsGst14aa.getInt("col10");
            int col4 = rsGst14aa.getInt("col4");
            Double numerator_5a = rsGst14aa.getDouble("col10");
            median = rsGst14aa.getDouble("median5a");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = String.valueOf(col10) + "/" + String.valueOf(col4);
            total = rsGst14aa.getDouble("score_of_subparameter5a");

            // rank = score.marks5a(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.marks5a(totalScore);
            int insentavization = score.marks5a(totalScore);
            if (numerator_5a > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.5 ;

            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst5a zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst5a COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst5aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            total = rsGst14aa.getDouble("score_of_subparameter_5a");
            median = rsGst14aa.getDouble("median_5a");
            Double numerator_5a = rsGst14aa.getDouble("numerator_5a");
            String ra = RelevantAspect.GST5A_RA;
            int col10 = rsGst14aa.getInt("numerator_5a");
            int col4 = rsGst14aa.getInt("col4");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = rsGst14aa.getString("absvl_5a");
            //String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

            // rank = score.marks5a(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.marks5a(totalScore);
            int insentavization = score.marks5a(totalScore);
            if (numerator_5a > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.5 ;

            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst5a all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst5a ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst5aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String commname=rsGst14aa.getString("COMM_NAME");
            total = rsGst14aa.getDouble("score_of_subparameter");
            median = rsGst14aa.getDouble("median");
            Double numerator_5a = rsGst14aa.getDouble("col10");
            String ra = RelevantAspect.GST5A_RA;
            int col10 = rsGst14aa.getInt("col10");
            int col4 = rsGst14aa.getInt("col4");
            int Zonal_rank = 0;
            String gst = "no";
            String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

            // rank = score.marks5a(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);

            int way_to_grade = score.marks5a(totalScore);
            int insentavization = score.marks5a(totalScore);
            if (numerator_5a > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average = insentavization * 0.5 ;

            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst5a all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst5b zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst5bZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.Gst5B_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            total = rsGst14aa.getDouble("total_score");
            String commname="ALL";
            int col22 = rsGst14aa.getInt("col22");
            int col23 = rsGst14aa.getInt("col23");
            int col16 = rsGst14aa.getInt("col16");
            int Zonal_rank = 0;
            String gst = "no";

            int insentavization = 0;
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks5b(totalScore);
            double sub_parameter_weighted_average = way_to_grade * 0.5 ;
            String absval = String.valueOf(col22 + col23) + "/" + String.valueOf(col16);
            gsta = new GSTCUS(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst5b zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst5b COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst5bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.Gst5B_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            total = rsGst14aa.getDouble("score_of_parameter");
            int col22=rsGst14aa.getInt("col22");
            int col23=rsGst14aa.getInt("col23");
            int col16=rsGst14aa.getInt("col16");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;

//                    if (col16 != 0) {
//                        total=(((double) (col22+col23) * 100)/ (col16));
//                    }
            //rank=score.marks5b(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal) ;
            int way_to_grade = score.marks5b(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.5;
            String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst5b all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst5b ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst5bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {
            String commname=rsGst14aa.getString("COMM_NAME");
            String ra=RelevantAspect.Gst5B_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            total = rsGst14aa.getDouble("score_of_parameter");
            int col22=rsGst14aa.getInt("col22");
            int col23=rsGst14aa.getInt("col23");
            int col16=rsGst14aa.getInt("col16");
            int Zonal_rank = 0;
            String gst = "no";
            int insentavization = 0;

//                    if (col16 != 0) {
//                        total=(((double) (col22+col23) * 100)/ (col16));}
            //rank=score.marks5b(total);
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.marks5b(totalScore);
            Double sub_parameter_weighted_average = way_to_grade * 0.5;
            String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
            gsta = new GSTCUS(zoneName, commname, totalScore,absval,zoneCode,ra,
                    Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        System.out.println("gst5b all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6a zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6aZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6a zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6a COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6a all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6a ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6a all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6b zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6bZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6b zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6b COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6b all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6b ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6b all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6c zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6cZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6c zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6c COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6cZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6c all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6c ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6cAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6c all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6d zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6dZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6d zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6d COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6dZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6d all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst6d ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst6dAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst6d all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst7 zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst7Zone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst7 zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst7 COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst7ZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst7 all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst7 ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst7AllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst7 all_cmsnry_median :" + median);
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
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10a zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10aZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10a zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10a COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10a all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10a ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10a all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10b zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10bZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10b zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10b COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10b all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10b ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10b all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10c zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10cZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10c zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10c COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10cZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10c all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10c ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10cAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10c all_cmsnry_median :" + median);
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10d zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10dZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10d zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10d COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10dZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10d all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst10d ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst10dAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst10d all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11a zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11aZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11a zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11a COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11aZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11a all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11a ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11aAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11a all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11b zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11bZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11b zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11b COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11bZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11b all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11b ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11bAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11b all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11c zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11cZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11c zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11c COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11cZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11c all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11c ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11cAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11c all_cmsnry_median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11d zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11dZone(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11d zone wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11d COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11dZoneWiseCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11d all_cmsnry_median wise median :" + median);
        return allGstaList;
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= gst11d ALL COMMI wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public  List<GSTCUS> gst11dAllCommissionary(ResultSet rsGst14aa) throws SQLException {
        double median = 0;
        List<GSTCUS> allGstaList = new ArrayList<>();
        while(rsGst14aa.next()) {

        }
        System.out.println("gst11d all_cmsnry_median :" + median);
        return allGstaList;
    }

}

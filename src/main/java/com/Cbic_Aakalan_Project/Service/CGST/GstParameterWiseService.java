package com.Cbic_Aakalan_Project.Service.CGST;

import com.Cbic_Aakalan_Project.Service.DateCalculate;
import com.Cbic_Aakalan_Project.entity.GSTCUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GstParameterWiseService {



    // ***********************************GST 2 parameter wise *****************************************************************
    // this query will show all zone || 1no url
    public List<GSTCUS> ReturnFilling_2_ZoneWise(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rsGst14aa.next()) {

        }
        return allGstaList;
    }
    // for 2no url , all india rank will show in this query
    public List<GSTCUS> ReturnFilling_2_ParticularZoneWise(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rsGst14aa.next()) {

        }
        return allGstaList;
    }
    //  for perticular subparameter wise ||  3 no url
    public List<GSTCUS> ReturnFilling_2_ParticularSubparameterWise(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rsGst14aa.next()) {

        }
        return allGstaList;
    }
    //  for all commissary wise || for 4no url
    public List<GSTCUS> ReturnFilling_2_AllCommissary(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rsGst14aa.next()) {

        }
        return allGstaList;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public List<GSTCUS> ReturnFilling_2_ParticularCommissonaryInSubparameter(ResultSet rsGst14aa) throws SQLException {
        List<GSTCUS> allGstaList = new ArrayList<>();
        while (rsGst14aa.next()) {

        }
        return allGstaList;
    }
    // ***********************************GST 5 parameter wise *****************************************************************

    // ***********************************GST 6 parameter wise *****************************************************************

    // ***********************************GST 7 parameter wise *****************************************************************

    // ***********************************GST 11 parameter wise *****************************************************************



}

package com.Cbic_Aaklan_Project.Service;

import java.util.List;

public class MedianCalculator {
    public static double calculateMedian(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("List of values cannot be null or empty.");
        }

        values.sort(Double::compareTo);
        int size = values.size();
        if (size % 2 == 0) {
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        } else {
            return values.get(size / 2);
        }
    }
}
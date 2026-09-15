package com.kairav.hotelapi.dto;

public class DemandForecastResponse {
    private long bookingsInLast7Days;
    private double movingAveragePerDay;
    private long predictedBookingsNext7Days;
    private String recommendation;
    public DemandForecastResponse() {}
    public DemandForecastResponse(long bookingsInLast7Days,double movingAveragePerDay,long predictedBookingsNext7Days,String recommendation) {
        this.bookingsInLast7Days =bookingsInLast7Days;
        this.movingAveragePerDay =movingAveragePerDay;
        this.predictedBookingsNext7Days =predictedBookingsNext7Days;
        this.recommendation =recommendation;
    }
    public long getBookingsInLast7Days() {
        return bookingsInLast7Days;
    }
    public void setBookingsInLast7Days(long v) {
        this.bookingsInLast7Days =v;
    }
    public double getMovingAveragePerDay() {
        return movingAveragePerDay;
    }
    public void setMovingAveragePerDay(double v) {
        this.movingAveragePerDay =v;
    }
    public long getPredictedBookingsNext7Days() {
        return predictedBookingsNext7Days;
    }
    public void setPredictedBookingsNext7Days(long v) {
        this.predictedBookingsNext7Days =v;
    }
    public String getRecommendation() {
        return recommendation;
    }
    public void setRecommendation(String recommendation) {
        this.recommendation =recommendation;
    }
}

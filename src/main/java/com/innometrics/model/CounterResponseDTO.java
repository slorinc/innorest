package com.innometrics.model;

/**
 * Created by s_lor_000 on 10/7/2015.
 */
public class CounterResponseDTO {

    private String counterName;
    private int counterValue;

    public CounterResponseDTO(String counterName, int counterValue) {
        this.counterName = counterName;
        this.counterValue = counterValue;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public int getCounterValue() {
        return counterValue;
    }

    public void setCounterValue(int counterValue) {
        this.counterValue = counterValue;
    }

    @Override
    public String toString() {
        return "CounterResponseDTO{" +
                "counterName='" + counterName + '\'' +
                ", counterValue=" + counterValue +
                '}';
    }
}

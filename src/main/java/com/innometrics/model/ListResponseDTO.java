package com.innometrics.model;

import java.util.List;

/**
 * Created by s_lor_000 on 10/7/2015.
 */
public class ListResponseDTO {

    private List<String> counterNames;

    public ListResponseDTO(List<String> counterNames) {
        this.counterNames = counterNames;
    }

    public List<String> getCounterNames() {
        return counterNames;
    }

    public void setCounterNames(List<String> counterNames) {
        this.counterNames = counterNames;
    }

    @Override
    public String toString() {
        return "ListResponseDTO{" +
                "counterNames=" + counterNames +
                '}';
    }
}

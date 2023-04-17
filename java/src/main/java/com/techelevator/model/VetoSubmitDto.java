package com.techelevator.model;

public class VetoSubmitDto {
    private boolean submitted;
    private boolean vetoed;

    public VetoSubmitDto() {
    }

    public VetoSubmitDto(boolean submitted, boolean vetoed) {
        this.submitted = submitted;
        this.vetoed = vetoed;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public boolean isVetoed() {
        return vetoed;
    }

    public void setVetoed(boolean vetoed) {
        this.vetoed = vetoed;
    }

    @Override
    public String toString() {
        return "VetoSubmitDto{" +
                "submitted=" + submitted +
                ", vetoed=" + vetoed +
                '}';
    }
}

package com.scg.beans;

public interface BenefitListener {
    public void dentialCancellation(BenefitEvent event);
    public void dentialEnrollment(BenefitEvent event);
    public void medicalCancellation(BenefitEvent event);
    public void medicalEnrollment(BenefitEvent event);
}

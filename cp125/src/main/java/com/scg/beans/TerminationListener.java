package com.scg.beans;

public interface TerminationListener {
    public void forcedTermination(TerminationEvent evt);
    public void voluntaryTermination(TerminationEvent evt);
}

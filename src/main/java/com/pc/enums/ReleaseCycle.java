package com.pc.enums;

public enum ReleaseCycle
{
    DAILY("Daily"), WEEKLY("Weekly"), BI_WEEKLY("Bi-Weekly"), MONTHLY("Monthly"), QUARTERLY("Quarterly"), BI_ANNUALLY("Bi-Annually"), ANNUALLY("Annually"), OTHER("Other");
    
    private String display;
    
    public String getDisplay()
    {
        return display;
    }
    
    ReleaseCycle(String display)
    {
        this.display = display;
    }
}

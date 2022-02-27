package utils;

public class ProgressBar {
    
    private double procent;
    private String bar;
    private int maxBar;
    
    public ProgressBar(double procent, int maxBar) {
        this.procent = procent;
        this.maxBar = maxBar;
        bar = "";
        for (int i = 0; i < maxBar; i++) {
            bar = bar + "🟥🟥";
        }
    }
    
    public String nextBar() {
        bar = bar.replaceFirst("🟥🟥", "🟩🟩");
        return bar;
    }
    
    public Double nextProcent() {
        procent += 100 / maxBar;
        return procent;
    }
    
    public String getBar() {
        return bar;
    }
    
    public double getProcent() {
        return procent;
    }
}

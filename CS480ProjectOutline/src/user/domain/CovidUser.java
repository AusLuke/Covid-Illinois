package user.domain;

public class CovidUser {
    
    // fields for county_covid table
    private String date;
    private String county;
    private String state;
    private int fips;
    private int cases;
    private int deaths;
    
    // additional fields for county_info
    private int countyNum;
    private int popEst;
    
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
    
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    
    
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    
    
    public int getFips() {
        return fips;
    }
    public void setFips(int fips) {
        this.fips = fips;
    }
    
    
    public int getCases() {
        return cases;
    }
    public void setCases(int cases) {
        this.cases = cases;
    }
    
    
    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    
    
    public int getCountyNum() {
        return countyNum;
    }
    public void setCountyNum(int countyNum) {
        this.countyNum = countyNum;
    }
    
    
    public int getPopEst() {
        return popEst;
    }
    public void setPopEst(int popEst) {
        this.popEst = popEst;
    }

}
package user.domain;

public class CovidUser {
    
    // fields for county_covid table
    private String date;
    private String county;
    private String state;
    private String casesS;
    private String casesS2;
    private String deathsS;
    private String populationS;
    private int fips;
    private int cases;
    private int cases2;
    private int deaths;
    private int deaths2;
    
    // additional fields for county_info
    private int countyNum;
    private int popEst;
    
    //additional fields for complex queries
    private String deathRate;
    private String infectionRate;
    
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
    
    
    public String getCasesS() {
        return casesS;
    }
    public void setCasesS(String casesS) {
        this.casesS = casesS;
    }
    
    public int getCases() {
        return cases;
    }
    public void setCases(int cases) {
        this.cases = cases;
    }
    
    public String getCasesS2() {
        return casesS2;
    }
    public void setCasesS2(String casesS2) {
        this.casesS2 = casesS2;
    }
    
    public int getCases2() {
        return cases2;
    }
    public void setCases2(int cases2) {
        this.cases2 = cases2;
    }
    
    public String getDeathsS() {
        return deathsS;
    }
    public void setDeathsS(String deathsS) {
        this.deathsS = deathsS;
    }
    
    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    
    public int getDeaths2() {
        return deaths2;
    }
    public void setDeaths2(int deaths2) {
        this.deaths2 = deaths2;
    }
    
    
    public int getCountyNum() {
        return countyNum;
    }
    public void setCountyNum(int countyNum) {
        this.countyNum = countyNum;
    }
    
    
    public String getPopulationS() {
        return populationS;
    }
    public void setPopulationS(String populationS) {
        this.populationS = populationS;
    }
    
    public int getPopEst() {
        return popEst;
    }
    public void setPopEst(int popEst) {
        this.popEst = popEst;
    }
    
    public String getDeathRate() {
        return deathRate;
    }
    public void setDeathRate(String deathRate) {
        this.deathRate = deathRate;
    }
    
    public String getInfectionRate() {
        return infectionRate;
    }
    public void setInfectionRate(String infectionRate) {
        this.infectionRate = infectionRate;
    }

}
public class Intrebare {
    private String intrebare,raspunsA,raspunsB,raspunsC;
    private boolean amRaspuns,corectA,corectB,corectC;
    private int numarIntrebare;
    
    public Intrebare(String ti,String tra,String trb,String trc){
        intrebare = ti;
        raspunsA = tra;
        raspunsB = trb;
        raspunsC = trc;
        amRaspuns = corectA = corectB = corectC = false;
    }

    public String getIntrebare() {
        return intrebare;
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    public String getRaspunsA() {
        return raspunsA;
    }

    public void setRaspunsA(String raspunsA) {
        this.raspunsA = raspunsA;
    }

    public String getRaspunsB() {
        return raspunsB;
    }

    public void setRaspunsB(String raspunsB) {
        this.raspunsB = raspunsB;
    }

    public String getRaspunsC() {
        return raspunsC;
    }

    public void setRaspunsC(String raspunsC) {
        this.raspunsC = raspunsC;
    }
    
    public int getNumarIntrebare() {
        return numarIntrebare;
    }

    public void setNumarIntrebare(int i) {
        this.numarIntrebare = i;
    }

    public boolean isCorectA() {
        return corectA;
    }

    public void setCorectA() {
        corectA = true;
    }

    public boolean isCorectB() {
        return corectB;
    }

    public void setCorectB() {
        corectB = true;
    }

    public boolean isCorectC() {
        return corectC;
    }

    public void setCorectC() {
        corectC = true;
    }
    
    public void setAmRaspuns(){
        amRaspuns = true;
    }
    
    public boolean amRaspuns(){
        return amRaspuns;
    }
    
}

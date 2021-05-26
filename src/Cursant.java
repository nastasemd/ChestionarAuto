public class Cursant {
    private String nume, prenume, id, parola, categorie;
 
    public Cursant(){}
    Cursant(String nume, String prenume, String id, String parola, String categorie){
        this.nume=nume;
        this.prenume=prenume;
        this.id=id;
        this.parola=parola;
        this.categorie=categorie;
    }
    
    public String getNume(){
        return nume;
    }
    
    public String getPrenume(){
        return prenume;
    }
    
    public String getID(){
        return id;
    }
    
    public String getParola(){
        return parola;
    }
    
    public String getCategorie(){
        return categorie;
    }
    
    public void setNume(String x){
        nume=x;
    }
    
    public void setPrenume(String x){
        prenume=x;
    }
    
    public void setID(String x){
        id=x;
    }
    
    public void setParola(String x){
        parola=x;
    }
    
    public void setCategorie(String x){
        categorie=x;
    }
    
    public String toString(){
        return nume+" "+prenume+" "+id+" "+parola+" "+categorie;
    }
}

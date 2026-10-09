package business;

public class CafeModel {
	
    private String name;
    private String ort;
    private String beschreibung;
    private String mitBaeckerei;
    private String[] kaffeeprodukte;
    
    public CafeModel(String name, String ort, String beschreibung,
       	String mitBaeckerei, String[] kaffeeprodukte){
    	this.name = name;
      	this.ort = ort;
       	this.beschreibung = beschreibung;
       	this.mitBaeckerei = mitBaeckerei;
       	this.kaffeeprodukte = kaffeeprodukte;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getMitBaeckerei() {
		return mitBaeckerei;
	}

	public void setMitBaeckerei(String mitBaeckerei) {
		this.mitBaeckerei = mitBaeckerei;
	}

	public String[] getKaffeeprodukte() {
		return kaffeeprodukte;
	}

	public void setKaffeeprodukte(String[] kaffeeprodukte) {
		this.kaffeeprodukte = kaffeeprodukte;
	}
	
 	public String getKaffeeprodukteAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for(i = 0; i < this.getKaffeeprodukte().length - 1; i++) {
			ergebnis = ergebnis + this.getKaffeeprodukte()[i] + trenner; 
		}
		return ergebnis	+ this.getKaffeeprodukte()[i];
	}
	
	public String gibCafeZurueck(char trenner){
  		return this.getName() + trenner 
  			+ this.getOrt() + trenner
  			+ this.getBeschreibung() + trenner
  		    + this.getMitBaeckerei() + trenner + "\n"
  		    + this.getKaffeeprodukteAlsString(trenner) + "\n";
  	}
}


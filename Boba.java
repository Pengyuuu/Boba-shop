public class Boba {

    private String name;

    private String place;

    private String date;

    // perfect, okay, bad
    // powdery, watery, too sweet, not sweet
    private String rateDrink;

    // if not perfect; hard, too soft, slimy
    private String rateBoba;

    private String reviewer;

    public Boba() {

        name = "";
        place = "";
        date = "";
        rateDrink = "";
        rateBoba = "";
        reviewer = "";
    } 

    public Boba (String name, String place, String date, String rateDrink, String rateBoba, String reviewer){

        this.name = name;

        this.place = place;

        this.date = date;

        this.rateDrink = rateDrink;

        this.rateBoba = rateBoba;

        this.reviewer = reviewer;
    }

    public String getName(){

        return name;
    }

    public String getPlace(){

        return place;
    }

    public String getDate(){

        return date;
    }

    public String getRateDrink(){

        return rateDrink;
    }

    public String getReviewer(){

        return reviewer;
    }

    public String getRateBoba(){

        return rateBoba;
    }
    
    @Override
    public String toString() {
    	
    	return name + ";" + place + ";" + date + ";" + rateDrink + ";" + rateBoba + ";" + reviewer;
    }

}

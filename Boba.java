public class Boba {

    private String name;

    private String place;

    private String date;

    // perfect, okay, bad
    // powdery, watery, too sweet, not sweet
    private String rateDrink;

    private String reviewer;

    // if not perfect; hard, too soft, slimy
    private String rateBoba;

    public Boba (String a, String b, String c, String d, String e, String f){

        name = a;

        place = b;

        date = c;

        rateDrink = d;

        reviewer = e;

        rateBoba = f;
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

}

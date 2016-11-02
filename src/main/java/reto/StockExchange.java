package reto;

/**
 * Created by rk on 02.11.16.
 */
public class StockExchange {

    // technische instanzvariable fuer constructor injection
    private Database db = new Database();       //per Default die "reale" Klasse (kann via Constructor ja anders gesetzt werden)
    private WebService ws = new WebService();   //per Default die "reale" Klasse (kann via Constructor ja anders gesetzt werden)


    // fachliche instanzvariable
    private String asset;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    //constructors
    public StockExchange() {
        this.asset = "NESN";  // per default werden Nestle gehandelt!
    }
    public StockExchange(String asset) {
        this.asset = asset;
    }
    public StockExchange(WebService ws, String asset) {
        this.ws = ws;
        this.asset = asset;
    }
    public StockExchange(Database db, String asset) {
        this.db = db;
        this.asset = asset;
    }
    public StockExchange(Database db, WebService ws, String asset) {
        this.db = db;
        this.ws = ws;
        this.asset = asset;
    }
    public StockExchange(Database db, WebService ws) {
        this.db = db;
        this.ws = ws;
    }
    public StockExchange(Database db) {
        this.db = db;
    }
    public StockExchange( WebService ws) {
        this.ws = ws;
    }


    /**
     * Kaufen
     */
    public void buy(){
        System.out.println("1. Start: BUY Asset " +this.asset +" ...");

        System.out.println("2. Get current price from WEBSERVICE ...");
        WebService ws = this.ws; //new WebService();
        Double price  = ws.getCurrentPrice();
        System.out.println("2. Get current price from WEBSERVICE is "+price);

        System.out.println("3. Save the trade to the DATABASE  ...");
        Database db = this.db; //new Database();
        db.save(this.asset, price);

    }



}

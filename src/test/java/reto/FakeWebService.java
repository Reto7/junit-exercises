package reto;

/**
 * Created by rk on 02.11.16.
 */
public class FakeWebService extends WebService {
    @Override
    public double getCurrentPrice() {
        System.out.println("...not calling any webservice. Fake price is 100.00");
        return 100.00; //Fake Price
    }
}

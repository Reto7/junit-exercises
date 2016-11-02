package reto;

/**
 * Created by rk on 02.11.16.
 */
public class FakeDatabase extends Database{
    @Override
    public void save(String asset, Double price) {
        System.out.println("...not saving, only fake. Asset="+asset +",Price="+price);

    }
}

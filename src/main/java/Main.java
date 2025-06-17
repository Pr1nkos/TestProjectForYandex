import entity.ATM;
import enums.Banknot;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Banknot, Long> mapOfBanknots = new HashMap<>();
        mapOfBanknots.put(Banknot.BANKNOT_50, 100L);
        mapOfBanknots.put(Banknot.BANKNOT_100, 100L);
        mapOfBanknots.put(Banknot.BANKNOT_500, 100L);
        mapOfBanknots.put(Banknot.BANKNOT_1000, 100L);
        mapOfBanknots.put(Banknot.BANKNOT_5000, 100L);
        ATM atm = new ATM(mapOfBanknots);
        Map<Banknot, Long> vidacha = atm.vidacha(420101L);
        System.out.println(vidacha.toString());
        System.out.println(atm.getBanknotMap());
    }

}

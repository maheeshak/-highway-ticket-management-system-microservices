package lk.ijse.ticket_ticket_service.util.location;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class CostCalculator {
    private static final Map<String, Integer> distances = new HashMap<>();

    static {
        distances.put("KOTTAWA_KAHATHUDUWA", 8);
        distances.put("KAHATHUDUWA_GELANIGAMA", 9);
        distances.put("GELANIGAMA_DODANGODA", 12);
        distances.put("DODANGODA_WELIPENNA", 13);
        distances.put("WELIPENNA_KURUNDUGAHAHETEKMA", 8);
        distances.put("KURUNDUGAHAHETEKMA_BADDEGAMA", 12);
        distances.put("BADDEGAMA_PINNADUWA", 10);
        distances.put("PINNADUWA_IMADUWA", 10);
        distances.put("IMADUWA_KOKMADUWA", 7);
        distances.put("KOKMADUWA_GODAGAMA", 7);
        distances.put("ATHURUGIRIYA_KADAWATHA", 18);
        distances.put("KADAWATHA_KERAWALAPITIYA", 9);
        distances.put("KERAWALAPITIYA_PELIYAGODA", 5);
        distances.put("PELIYAGODA_JAELA", 15);
        distances.put("JAELA_KATUNAYAKE", 10);
        distances.put("KATUNAYAKE_MIRIGAMA", 25);
    }

    public static double getDistance(Location start, Location end) {
        String key = start.name() + "_" + end.name();
        return distances.getOrDefault(key, -1);

    }

    public static double calculateCost(Location start, Location end){
        double distance = getDistance(start, end);
        if (distance == -1){
            return -1;
        }
        return distance * 10;
    }


}

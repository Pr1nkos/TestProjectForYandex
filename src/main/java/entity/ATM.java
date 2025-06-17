package entity;

import enums.Banknot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 * ========== cutline =========
 * Другие валюты и номиналы должны легко добавляться разработчиками в будущем.
 * Многопоточные сценарии могут быть добавлены позже (например резервирование).
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ATM {
    Map<Banknot, Long> banknotMap;

    public Map<Banknot, Long> vidacha(Long sum) { //9999
        Map<Banknot, Long> naVidachu = new HashMap<>();
        naVidachu.put(Banknot.BANKNOT_50, 0L);
        naVidachu.put(Banknot.BANKNOT_100, 0L);
        naVidachu.put(Banknot.BANKNOT_500, 0L);
        naVidachu.put(Banknot.BANKNOT_1000, 0L);
        naVidachu.put(Banknot.BANKNOT_5000, 0L);

        while (true) {
            Optional<Banknot> minusValueOptional = findValueForMinusValue(sum);
            Long minusValue = 0L;

            if (minusValueOptional.isPresent()) {
                if (banknotMap.get(minusValueOptional.get()) > 0){
                    minusValue = minusValueOptional.get().getValue();
                    addToMap(naVidachu, minusValueOptional);
                    banknotMap.put(minusValueOptional.get(), banknotMap.get(minusValueOptional.get()) - 1L);
                }
            }
            else {
                break;
            }

            sum = sum - minusValue;
        }
        return naVidachu;
    }

    private Optional<Banknot> findValueForMinusValue(Long sum) {
        return banknotMap.keySet().stream()
                .sorted((o1, o2) -> Math.toIntExact(o2.getValue() - o1.getValue()))
                .filter(banknot -> banknot.getValue() <= sum)
                .findFirst();
    }

    private void addToMap(Map<Banknot, Long> banknotMap, Optional<Banknot> minusValueOptional) {
        banknotMap.put(minusValueOptional.get(), banknotMap.get(minusValueOptional.get()) + 1);
    }
}

package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Banknot {
    BANKNOT_50 (50),
    BANKNOT_100 (100),
    BANKNOT_500 (500),
    BANKNOT_1000 (1000),
    BANKNOT_5000 (5000);
    final long value;
}

package bankService.model;

import java.util.Random;

public enum AtmLocation {
    ATM_101_MAIN_ST("ATM #101 - Main St"),
    ATM_102_DOWNTOWN("ATM #102 - Downtown"),
    ATM_103_AIRPORT("ATM #103 - Airport");

    private final String displayName;

    AtmLocation(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    private static final Random RAND = new Random();

    public static AtmLocation getRandomLocation() {
        return values()[RAND.nextInt(values().length)];
    }
}
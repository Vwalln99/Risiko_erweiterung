package Config;

import java.util.Map;

public class NeighborRelations {

    //Board 1
    public static void addCountryNeighbors1(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("A1", new String[] {"A2", "A4"});
        countryNeighbors.put("A2", new String[] {"A1", "A3", "A5"});
        countryNeighbors.put("A3", new String[] {"A2", "A6", "B1"});
        countryNeighbors.put("A4", new String[] {"A1", "A5", "C1"});
        countryNeighbors.put("A5", new String[] {"A2", "A4", "A6", "C2"});
        countryNeighbors.put("A6", new String[] {"A3", "A5", "B4", "C3"});

        countryNeighbors.put("B1", new String[] {"A3", "B2", "B4"});
        countryNeighbors.put("B2", new String[] {"B1", "B3", "B5"});
        countryNeighbors.put("B3", new String[] {"B2", "B6"});
        countryNeighbors.put("B4", new String[] {"B1", "B5", "A6", "D1"});
        countryNeighbors.put("B5", new String[] {"B2", "B4", "B6", "D2"});
        countryNeighbors.put("B6", new String[] {"B3", "B5", "D3"});

        countryNeighbors.put("C1", new String[] {"C2", "C4", "A4"});
        countryNeighbors.put("C2", new String[] {"C1", "C3", "C5", "A5"});
        countryNeighbors.put("C3", new String[] {"C2", "C6", "A6", "D1"});
        countryNeighbors.put("C4", new String[] {"C1", "C5"});
        countryNeighbors.put("C5", new String[] {"C2", "C4", "C6"});
        countryNeighbors.put("C6", new String[] {"C3", "C5", "D4"});

        countryNeighbors.put("D1", new String[] {"D2", "D4", "B4", "C3"});
        countryNeighbors.put("D2", new String[] {"D1", "D3", "D5", "B5"});
        countryNeighbors.put("D3", new String[] {"D2", "D6", "B6"});
        countryNeighbors.put("D4", new String[] {"D1", "D5", "C6"});
        countryNeighbors.put("D5", new String[] {"D2", "D4", "D6"});
        countryNeighbors.put("D6", new String[] {"D3", "D5"});
    }

    //Board 2
    public static void addCountryNeighbors2(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("A1", new String[] {"A3"});
        countryNeighbors.put("A2", new String[] {"A3", "A4"});
        countryNeighbors.put("A3", new String[] {"A1", "A2", "A5", "A6"});
        countryNeighbors.put("A4", new String[] {"A2", "A6", "B3"});
        countryNeighbors.put("A5", new String[] {"A3"});
        countryNeighbors.put("A6", new String[] {"A3", "A4","C1"});

        countryNeighbors.put("B1", new String[] {"B2", "B3"});
        countryNeighbors.put("B2", new String[] {"B1", "B4"});
        countryNeighbors.put("B3", new String[] {"B1", "B5", "A4"});
        countryNeighbors.put("B4", new String[] {"B2", "B6"});
        countryNeighbors.put("B5", new String[] {"B3", "B6", "D1"});
        countryNeighbors.put("B6", new String[] {"B4", "B5", "D2"});

        countryNeighbors.put("C1", new String[] {"C2", "A6", "D1"});
        countryNeighbors.put("C2", new String[] {"C1", "C4", "C5"});
        countryNeighbors.put("C3", new String[] {"C4"});
        countryNeighbors.put("C4", new String[] {"C2", "C3", "C6"});
        countryNeighbors.put("C5", new String[] {"C2", "C6", "D5"});
        countryNeighbors.put("C6", new String[] {"C4", "C5"});

        countryNeighbors.put("D1", new String[] {"D3", "B5", "C1"});
        countryNeighbors.put("D2", new String[] {"D4", "B6"});
        countryNeighbors.put("D3", new String[] {"D1", "D4", "D5"});
        countryNeighbors.put("D4", new String[] {"D2", "D3", "D6"});
        countryNeighbors.put("D5", new String[] {"D3", "D6", "C5"});
        countryNeighbors.put("D6", new String[] {"D4", "D5"});
    }

    //Board 3
    public static void addCountryNeighbors3(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("A1", new String[] {"A2", "B1"});
        countryNeighbors.put("A2", new String[] {"A1", "A3", "A4"});
        countryNeighbors.put("A3", new String[] {"A2", "C2"});
        countryNeighbors.put("A4", new String[] {"A2", "A5", "A6"});
        countryNeighbors.put("A5", new String[] {"A4", "B3"});
        countryNeighbors.put("A6", new String[] {"A4","C1"});

        countryNeighbors.put("B1", new String[] {"B2", "A1"});
        countryNeighbors.put("B2", new String[] {"B1", "B4", "B5"});
        countryNeighbors.put("B3", new String[] {"B4", "A5"});
        countryNeighbors.put("B4", new String[] {"B2", "B3", "B6"});
        countryNeighbors.put("B5", new String[] {"B2", "D4"});
        countryNeighbors.put("B6", new String[] {"B4", "D1"});

        countryNeighbors.put("C1", new String[] {"C3", "A6"});
        countryNeighbors.put("C2", new String[] {"C5", "A3"});
        countryNeighbors.put("C3", new String[] {"C1", "C4", "C5"});
        countryNeighbors.put("C4", new String[] {"C3", "D2"});
        countryNeighbors.put("C5", new String[] {"C2", "C3", "C6"});
        countryNeighbors.put("C6", new String[] {"C5", "D6"});

        countryNeighbors.put("D1", new String[] {"D3", "B6"});
        countryNeighbors.put("D2", new String[] {"D3", "C4"});
        countryNeighbors.put("D3", new String[] {"D1", "D2", "D5"});
        countryNeighbors.put("D4", new String[] {"D5", "B5"});
        countryNeighbors.put("D5", new String[] {"D3", "D4", "D6"});
        countryNeighbors.put("D6", new String[] {"D5", "C6"});
    }

    public static void addCountryNeighbors4(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("A1", new String[] {"A2", "B1"});
        countryNeighbors.put("A2", new String[] {"A1", "A3", "B2"});
        countryNeighbors.put("A3", new String[] {"A2", "A4", "B3"});
        countryNeighbors.put("A4", new String[] {"A3", "A5", "B4"});
        countryNeighbors.put("A5", new String[] {"A4", "A6", "B5"});
        countryNeighbors.put("A6", new String[] {"A5", "B6"});

        countryNeighbors.put("B1", new String[] {"A1", "B2", "C1"});
        countryNeighbors.put("B2", new String[] {"A2", "B1", "B3", "C2"});
        countryNeighbors.put("B3", new String[] {"A3", "B2", "B4", "C3"});
        countryNeighbors.put("B4", new String[] {"A4", "B3", "B5", "C4"});
        countryNeighbors.put("B5", new String[] {"A5", "B4", "B6", "C5"});
        countryNeighbors.put("B6", new String[] {"A6", "B5", "C6"});

        countryNeighbors.put("C1", new String[] {"B1", "C2", "D1"});
        countryNeighbors.put("C2", new String[] {"B2", "C1", "C3", "D2"});
        countryNeighbors.put("C3", new String[] {"B3", "C2", "C4", "D3"});
        countryNeighbors.put("C4", new String[] {"B4", "C3", "C5", "D4"});
        countryNeighbors.put("C5", new String[] {"B5", "C4", "C6", "D5"});
        countryNeighbors.put("C6", new String[] {"B6", "C5", "D6"});

        countryNeighbors.put("D1", new String[] {"C1", "D2", "E1"});
        countryNeighbors.put("D2", new String[] {"C2", "D1", "D3", "E2"});
        countryNeighbors.put("D3", new String[] {"C3", "D2", "D4", "E3"});
        countryNeighbors.put("D4", new String[] {"C4", "D3", "D5", "E4"});
        countryNeighbors.put("D5", new String[] {"C5", "D4", "D6", "E5"});
        countryNeighbors.put("D6", new String[] {"C6", "D5", "E6"});

        countryNeighbors.put("E1", new String[] {"D1", "E2"});
        countryNeighbors.put("E2", new String[] {"D2", "E1", "E3"});
        countryNeighbors.put("E3", new String[] {"D3", "E2", "E4"});
        countryNeighbors.put("E4", new String[] {"D4", "E3", "E5"});
        countryNeighbors.put("E5", new String[] {"D5", "E4", "E6"});
        countryNeighbors.put("E6", new String[] {"D6", "E5"});
    }

    // Board 5
    public static void addCountryNeighbors5(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("A1", new String[] {"A2", "B1", "C1"});
        countryNeighbors.put("A2", new String[] {"A1", "A3", "B2"});
        countryNeighbors.put("A3", new String[] {"A2", "A4", "B3"});
        countryNeighbors.put("A4", new String[] {"A3", "A5", "B4"});
        countryNeighbors.put("A5", new String[] {"A4", "A6", "B5"});
        countryNeighbors.put("A6", new String[] {"A5", "B6"});

        countryNeighbors.put("B1", new String[] {"A1", "B2", "C1"});
        countryNeighbors.put("B2", new String[] {"A2", "B1", "B3", "C2"});
        countryNeighbors.put("B3", new String[] {"A3", "B2", "B4", "C3"});
        countryNeighbors.put("B4", new String[] {"A4", "B3", "B5", "C4"});
        countryNeighbors.put("B5", new String[] {"A5", "B4", "B6", "C5"});
        countryNeighbors.put("B6", new String[] {"A6", "B5", "C6"});

        countryNeighbors.put("C1", new String[] {"A1", "B1", "C2", "D1"});
        countryNeighbors.put("C2", new String[] {"B2", "C1", "C3", "D2"});
        countryNeighbors.put("C3", new String[] {"B3", "C2", "C4", "D3"});
        countryNeighbors.put("C4", new String[] {"B4", "C3", "C5", "D4"});
        countryNeighbors.put("C5", new String[] {"B5", "C4", "C6", "D5"});
        countryNeighbors.put("C6", new String[] {"B6", "C5", "D6"});

        countryNeighbors.put("D1", new String[] {"C1", "D2", "E1"});
        countryNeighbors.put("D2", new String[] {"C2", "D1", "D3", "E2"});
        countryNeighbors.put("D3", new String[] {"C3", "D2", "D4", "E3"});
        countryNeighbors.put("D4", new String[] {"C4", "D3", "D5", "E4"});
        countryNeighbors.put("D5", new String[] {"C5", "D4", "D6", "E5"});
        countryNeighbors.put("D6", new String[] {"C6", "D5", "E6"});

        countryNeighbors.put("E1", new String[] {"D1", "E2", "F1"});
        countryNeighbors.put("E2", new String[] {"D2", "E1", "E3", "F2"});
        countryNeighbors.put("E3", new String[] {"D3", "E2", "E4", "F3"});
        countryNeighbors.put("E4", new String[] {"D4", "E3", "E5", "F4"});
        countryNeighbors.put("E5", new String[] {"D5", "E4", "E6", "F5"});
        countryNeighbors.put("E6", new String[] {"D6", "E5", "F6"});

        countryNeighbors.put("F1", new String[] {"E1", "F2"});
        countryNeighbors.put("F2", new String[] {"E2", "F1", "F3"});
        countryNeighbors.put("F3", new String[] {"E3", "F2", "F4"});
        countryNeighbors.put("F4", new String[] {"E4", "F3", "F5"});
        countryNeighbors.put("F5", new String[] {"E5", "F4", "F6"});
        countryNeighbors.put("F6", new String[] {"E6", "F5"});
    }
    //Board 6 testboard
    public static void addCountryNeighbors6(Map<String, String[]> countryNeighbors) {
        countryNeighbors.put("A1", new String[] {"A2", "B1", "C1"});
        countryNeighbors.put("A2", new String[] {"A1", "A3", "B2"});
        countryNeighbors.put("A3", new String[] {"A2", "A4", "B3"});
        countryNeighbors.put("A4", new String[] {"A3", "A5", "B4"});
        countryNeighbors.put("A5", new String[] {"A4", "A6", "B5"});
        countryNeighbors.put("A6", new String[] {"A5", "B6"});

        countryNeighbors.put("B1", new String[] {"A1", "B2", "C1"});
        countryNeighbors.put("B2", new String[] {"A2", "B1", "B3", "C2"});
        countryNeighbors.put("B3", new String[] {"A3", "B2", "B4", "C3"});
        countryNeighbors.put("B4", new String[] {"A4", "B3", "B5", "C4"});
        countryNeighbors.put("B5", new String[] {"A5", "B4", "B6", "C5"});
        countryNeighbors.put("B6", new String[] {"A6", "B5", "C6"});

        countryNeighbors.put("C1", new String[] {"A1", "B1", "C2", "D1"});
        countryNeighbors.put("C2", new String[] {"B2", "C1", "C3", "D2"});
        countryNeighbors.put("C3", new String[] {"B3", "C2", "C4", "D3"});
        countryNeighbors.put("C4", new String[] {"B4", "C3", "C5", "D4"});
        countryNeighbors.put("C5", new String[] {"B5", "C4", "C6", "D5"});
        countryNeighbors.put("C6", new String[] {"B6", "C5", "D6"});

    }
}

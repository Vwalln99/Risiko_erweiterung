package Config;

import java.awt.*;

public class Helper {

    // Constraints builder for every GridBagLayout
    public static GridBagConstraints buildBoardConstraints(GridBagConstraints constraints, int row, int col, int rowspan, int colspan) {
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = row;
        constraints.gridx = col;
        constraints.gridwidth = colspan;
        constraints.gridheight = rowspan;
        return constraints;
    }

    // Converts all dice values to a string for the output label
    public static String setLabelContent(Integer[] rolls) {
        StringBuilder labelContent = new StringBuilder();
        for (int i = 0; i < rolls.length; i++) {
            if(i == rolls.length - 1){
                labelContent.append(rolls[i]);
            }
            else {
                labelContent.append(rolls[i]).append(" | ");
            }
        }
        return labelContent.toString();
    }
}

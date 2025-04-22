package Vista.Util;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;

public class CustomSpinnerDate extends BasicSpinnerUI {

    private final Color color = new Color(245, 156, 107);

    @Override
    protected Component createNextButton() {
        BasicArrowButton btn = new BasicArrowButton(
                BasicArrowButton.NORTH,
                color,                 // background normal
                color.darker(),        // shadow
                color.brighter(),      // highlight
                color                  // flecha
        );
        btn.setName("Spinner.nextButton");
        installNextButtonListeners(btn);
        return btn;
    }

    @Override
    protected Component createPreviousButton() {
        BasicArrowButton btn = new BasicArrowButton(
                BasicArrowButton.SOUTH,
                color,
                color.darker(),
                color.brighter(),
                color
        );
        btn.setName("Spinner.previousButton");
        installPreviousButtonListeners(btn);
        return btn;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        // opcional: cambiar también el borde del editor
        c.setBorder(BorderFactory.createLineBorder(color, 2));
    }

    public static JSpinner crearHoraSpinner() {
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        spinner.setUI(new CustomSpinnerDate());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);
        spinner.setFont(new Font("Arial", Font.PLAIN, 14));
        return spinner;
    }
}

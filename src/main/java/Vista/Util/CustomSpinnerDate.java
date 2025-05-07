package Vista.Util;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;
import java.util.Date;

/**
 * Clase personalizada para un JSpinner que muestra la hora.
 * Extiende BasicSpinnerUI para personalizar los botones de incremento y decremento.
 */
public class CustomSpinnerDate extends BasicSpinnerUI {

    private final Color color = new Color(245, 156, 107);

    /**
     * Crea el botón de incremento del spinner.
     * @return El botón de incremento.
     */
    @Override
    protected Component createNextButton() {
        BasicArrowButton btn = new BasicArrowButton(
                BasicArrowButton.NORTH,
                color,
                color.darker(),
                color.brighter(),
                color
        );
        btn.setName("Spinner.nextButton");
        installNextButtonListeners(btn);
        return btn;
    }

    /**
     * Crea el botón de decremento del spinner.
     * @return El botón de decremento.
     */
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

    /**
     * Instala el UI en el componente.
     * @param c El componente en el que se instala el UI.
     */
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        c.setBorder(BorderFactory.createLineBorder(color, 2));
    }

    /**
     * Crea un JSpinner configurado para mostrar la hora.
     * @return El JSpinner configurado.
     */
    public static JSpinner crearHoraSpinner() {
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY);
        JSpinner spinner = new JSpinner(model);
        spinner.setUI(new CustomSpinnerDate());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);
        spinner.setFont(new Font("Arial", Font.PLAIN, 14));
        return spinner;
    }
}

package BackUtil;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase detecta la inactividad del usuario y ejecuta una acción definida si el usuario no realiza ninguna actividad.
 */
public class MonitorInactividad {
    private Timer temporizador;
    private final long tiempoEsperaMilisegundos;
    private final Runnable accionInactividad;
    private Timer contadorInactividad;
    private AWTEventListener listener;

    /**
     * @param tiempoEspera Tiempo de espera en milisegundos antes de considerar al usuario inactivo.
     * @param accInactividad Acción a ejecutar cuando se detecta inactividad.
     */
    public MonitorInactividad(long tiempoEspera, Runnable accInactividad) {
        this.tiempoEsperaMilisegundos = tiempoEspera;
        this.accionInactividad = accInactividad;
        configurarEscuchador();
        reiniciarTemporizador();
    }

    /**
     * Configura el escuchador global de eventos del teclado y ratón.
     */
    private void configurarEscuchador() {
        listener = evento -> {
            if (evento instanceof MouseEvent || evento instanceof KeyEvent) {
                reiniciarTemporizador();
            }
        };

        Toolkit.getDefaultToolkit().addAWTEventListener(
                listener, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.KEY_EVENT_MASK
        );
    }

    /**
     * Reinicia el temporizador cada vez que se detecta actividad.
     */
    private void reiniciarTemporizador() {
        if (temporizador != null) temporizador.cancel();
        if (contadorInactividad != null) contadorInactividad.cancel();

        temporizador = new Timer();
        temporizador.schedule(new TimerTask() {
            @Override
            public void run() {
                accionInactividad.run();
            }
        }, tiempoEsperaMilisegundos);

        contadorInactividad = new Timer();
        contadorInactividad.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            }
        }, 1000, 1000);
    }

    /**
     * Detiene el monitor de inactividad.
     */
    public void detener() {
        if (temporizador != null) temporizador.cancel();
        if (contadorInactividad != null) contadorInactividad.cancel();

        if (listener != null) {
            Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
            listener = null;
        }
    }
}
package selectableTree;
 
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Aakash Garg
 */
public class ExitListener extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent event) {
        System.exit(0);
    }
}

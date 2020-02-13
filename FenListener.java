import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;
public class FenListener implements WindowListener
{
  Tupper fenPrinc;
  FenetreNombre fenNombre;
  FenetreMessage fenMessage;
  int a;
  public FenListener(Tupper fenPrinc, FenetreNombre fenNombre, int a)
  {
    this.fenPrinc = fenPrinc;
    this.fenNombre = fenNombre;
    this.a = a;
  }
  public FenListener(Tupper fenPrinc, FenetreMessage fenMessage, int a)
  {
    this.fenPrinc = fenPrinc;
    this.fenMessage = fenMessage;
    this.a = a;
  }
  public void windowActivated(WindowEvent e)
  {
  }
  public void windowClosed(WindowEvent e)
  {
    if (a == 1)
    {
      fenPrinc.actualiserNombreADessiner(fenNombre.getNumber());
    }
    else if (a == 2)
    {
      fenPrinc.actualiserMessageADessiner(fenMessage.getMessage(), fenMessage.getFirstLine());
    }
  }
  public void windowClosing(WindowEvent e)
  {
  }
  public void windowDeactivated(WindowEvent e)
  {
  }
  public void windowDeiconified(WindowEvent e)
  {
  }
  public void windowIconified(WindowEvent e)
  {
  }
  public void windowOpened(WindowEvent e)
  {
  }
}

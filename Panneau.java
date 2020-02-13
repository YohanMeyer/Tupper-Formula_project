import javax.swing.*;
import java.awt.*;
public class Panneau extends JPanel
{
  private double jFrameWidth;
  private double jFrameHeight;

  public void paintComponent(Graphics g) // trace le rectangle rouge
  {
    g.setColor(Color.red);
    g.drawLine((int)(189*jFrameHeight), (int)(324*jFrameWidth), (int)(1251*jFrameHeight), (int)(324*jFrameWidth));
    g.drawLine((int)(189*jFrameHeight), (int)(324*jFrameWidth), (int)(189*jFrameHeight), (int)(496*jFrameWidth));
    g.drawLine((int)(189*jFrameHeight), (int)(496*jFrameWidth), (int)(1251*jFrameHeight), (int)(496*jFrameWidth));
    g.drawLine((int)(1251*jFrameHeight), (int)(324*jFrameWidth), (int)(1251*jFrameHeight), (int)(496*jFrameWidth));
  }

  public void paintPoint(Graphics g, Point point) // trace un point avec sa bonne couleur
  {
    g.setColor(point.getColor());
    g.fillRect((int)(point.getX()*jFrameWidth), (int)(point.getY()*jFrameHeight),(int)(10*jFrameWidth),(int)(10*jFrameHeight));
  }

  public void getScreenSize(double[] dim) // permet de récupérer les dimensions de l'écran
  {
    this.jFrameWidth = dim[0];
    this.jFrameHeight = dim[1];
  }
}

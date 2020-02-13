import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

class SourisMotionListener implements MouseMotionListener // gère les déplacements de la souris
{
  private int[] lastCoord = new int[2];
  private Point ecran[][] = new Point[106][17];
  private Tupper fen;
  private Panneau pan;

  private boolean gomme = false;
  private boolean pinceau = false;
  private boolean normal = true;

  private Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
  private double jFrameWidth = screenDimension.width/1440;
  private double jFrameHeight = screenDimension.height/800; // permettent d'adapter la taille de tous les composants à l'écran
  private final int X = (int)jFrameWidth * 190;
  private final int XMAX = (int)jFrameWidth * 1250; // 190 + 10*106
  private final int Y = (int)jFrameHeight * 325;
  private final int YMAX = (int)jFrameHeight * 495; // 325 + 10*17

  public SourisMotionListener(Tupper fen, Panneau pan)
  {
    this.fen = fen;
    this.pan = pan;
    lastCoord[0] = -1;
    lastCoord[1] = -1;
  }

  public void mouseMoved(MouseEvent event)
  {
    if (lastCoord[0] == -1 && lastCoord[1] == -1)
    {
      lastCoord[0] = 0;
      lastCoord[1] = 0;
      //pan.paintComponent(fen.getGraphics(), jFrameWidth, jFrameHeight);
    }
    Point pointi = ecran[lastCoord[0]][lastCoord[1]];
    // ecran[lastCoord[0]][lastCoord[1]] est le Point correspondant aux lastCoord
    if (lastCoord[0] != (event.getX() - X)/10 || lastCoord[1] != (event.getY() - Y)/10)
    {
      lastCoord[0] = (event.getX() - X)/10;
      lastCoord[1] = (event.getY() - Y)/10;

      if (lastCoord[0] < 0)
      {
        lastCoord[0] = 0;
      }
      if (lastCoord[0] > 105) //0 <= x < 106
      {
        lastCoord[0] = 105;
      }
      if (lastCoord[1] < 0)
      {
        lastCoord[1] = 0;
      }
      if (lastCoord[1] > 16) // 0 <= y < 17
      {
        lastCoord[1] = 16;
      }

      if (!(pointi.getColor().equals(Color.black)))
      {
        pointi.setColor(Color.white);
        pan.paintPoint(fen.getGraphics(), pointi);
      }
      pointi = ecran[lastCoord[0]][lastCoord[1]];
      if (!(pointi.getColor().equals(Color.black)))
      {
        pointi.setColor(Color.gray);
      }
      pan.paintPoint(fen.getGraphics(), pointi);
    }
    fen.actualiserEcran(this.ecran, this.lastCoord);
  }
  public void mouseDragged(MouseEvent event)
  {
    if (lastCoord[0] == -1 && lastCoord[1] == -1)
    {
      lastCoord[0] = 0;
      lastCoord[1] = 0;
    }
    Point pointi = ecran[lastCoord[0]][lastCoord[1]];

    // ecran[lastCoord[0]][lastCoord[1]] est le Point correspondant aux lastCoord
    if (lastCoord[0] != (event.getX() - X)/10 || lastCoord[1] != (event.getY() - Y)/10)
    {
      lastCoord[0] = (event.getX() - X)/10;
      lastCoord[1] = (event.getY() - Y)/10;

      if (lastCoord[0] < 0)
      {
        lastCoord[0] = 0;
      }
      if (lastCoord[0] > 105) //0 <= x < 106
      {
        lastCoord[0] = 105;
      }
      if (lastCoord[1] < 0)
      {
        lastCoord[1] = 0;
      }
      if (lastCoord[1] > 16) // 0 <= y < 17
      {
        lastCoord[1] = 16;
      }

      if (pointi.getColor().equals(Color.gray))
      {
        pointi.setColor(Color.white);
        pan.paintPoint(fen.getGraphics(), pointi);
      }
      pointi = ecran[lastCoord[0]][lastCoord[1]];
      if (gomme)
      {
        pointi.setColor(Color.white);
      }
      else if(pinceau)
      {
        pointi.setColor(Color.black);
      }
      else
      {
        pointi.setColor(Color.gray);
      }
      pan.paintPoint(fen.getGraphics(), pointi);
    }
    fen.actualiserEcran(this.ecran, this.lastCoord);
  }
  public void pinceau()
  {
    pinceau = true;
    normal = false;
    gomme = false;
  }
  public void gomme()
  {
    pinceau = true;
    normal = false;
    gomme = true;
  }

  public void normal()
  {
    pinceau = false;
    normal = true;
    gomme = false;
  }

  public void setEcran(Point[][] ecran)
  {
    this.ecran = ecran;
  }
  public void setEcran(Point[][] ecran, int[] lastCoord)
  {
    this.ecran = ecran;
    this.lastCoord = lastCoord;
  }
}

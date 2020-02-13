import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class SourisListener implements MouseListener // gère le clic/drag de la souris
{
  private int[] lastCoord = new int[2];
  private Point ecran[][] = new Point[106][17];
  private Tupper fen;
  private Panneau pan;

  private boolean gomme = false;
  private boolean pinceau = false;
  private boolean normal = true;

  public SourisListener(Tupper fen, Panneau pan)
  {
    this.fen = fen;
    this.pan = pan;
    lastCoord[0] = -1;
    lastCoord[1] = -1;
  }

  public void mouseClicked(MouseEvent event)
  {
  }
  public void mousePressed(MouseEvent event)
  {
    if (gomme)
    {
      ecran[lastCoord[0]][lastCoord[1]].setColor(Color.white);
      pan.paintPoint(fen.getGraphics(), ecran[lastCoord[0]][lastCoord[1]]);
    }
    else if (pinceau)
    {
      ecran[lastCoord[0]][lastCoord[1]].setColor(Color.black);
      pan.paintPoint(fen.getGraphics(), ecran[lastCoord[0]][lastCoord[1]]);
    }
    else if(normal && ecran[lastCoord[0]][lastCoord[1]].getColor().equals(Color.black))
    {
      ecran[lastCoord[0]][lastCoord[1]].setColor(Color.white);
      pan.paintPoint(fen.getGraphics(), ecran[lastCoord[0]][lastCoord[1]]);

    }
    else
    {
      ecran[lastCoord[0]][lastCoord[1]].setColor(Color.black);
      pan.paintPoint(fen.getGraphics(), ecran[lastCoord[0]][lastCoord[1]]);

    }
    fen.actualiserEcran(this.ecran, this.lastCoord);
  }
  public void mouseReleased(MouseEvent event)
  {
  }
  public void mouseEntered(MouseEvent event)
  {
  }
  public void mouseExited(MouseEvent event)
  {
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

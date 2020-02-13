import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class BoutonListener implements ActionListener
{
  private Tupper fen;
  private FenetreNombre fen2;
  private FenetreMessage fen3;
  private FenetreWarning fen4;
  private SourisListener sousou;
  private SourisMotionListener sou;
  private int a;

  public BoutonListener(int a, Tupper fen, SourisListener sousou, SourisMotionListener sou)
  {
    this.a = a;
    this.fen = fen;
    this.sousou = sousou;
    this.sou = sou;
  }
  public BoutonListener(int a, FenetreNombre fen2)
  {
    this.a = a;
    this.fen2 = fen2;
  }
  public BoutonListener(int a, FenetreMessage fen3)
  {
    this.a = a;
    this.fen3 = fen3;
  }
  public BoutonListener(int a, FenetreWarning fen4, FenetreMessage fen5)
  {
    this.a = a;
    this.fen4 = fen4;
    this.fen3 = fen5;
  }
  public void actionPerformed(ActionEvent e)
  {
    if(a == 1) // si on clique sur le bouton "pinceau" de Tupper
    {
      sousou.pinceau();
      sou.pinceau();
    }
    else if (a == 2) // si on clique sur le bouton "gomme" de Tupper
    {
      sousou.gomme();
      sou.gomme();
    }
    else if (a == 3) // si on clique sur le bouton "normal" de Tupper
    {
      sousou.normal();
      sou.normal();
    }
    else if (a == 4) // si on clique sur le bouton "get number" de Tupper
    {
      fen.nombreADonner();
    }
    else if (a == 5) // si on clique sur le bouton "give number" de Tupper
    {
      fen.nombreARecevoir();
    }
    else if (a == 6) // si on clique sur le bouton "message" de Tupper
    {
      fen.message();
    }
    else if (a == 7) // si on clique sur le bouton "binary number" de FenetreNombre
    {
      fen2.nombreBinaire();
      fen2.dispose();
    }
    else if (a == 8) // si on clique sur le bouton "decimal number" de FenetreNombre
    {
      fen2.nombreDecimal();
      fen2.dispose();
    }
    else if (a == 9) // si on clique sur le bouton "fermer" de FenetreMessage
    {
      fen3.fermer();
    }
    else if (a == 10) // si on clique sur le bouton "oui" de FenetreWarning
    {
      fen4.dispose(); // on ferme la FenetreWarning
      fen3.dispose(); // on ferme la FenetreMessage
    }
    else if (a == 11) // si on clique sur le bouton "non" de FenetreWarning
    {
      fen4.dispose(); // on ferme la FenetreWarning
    }
  }
}

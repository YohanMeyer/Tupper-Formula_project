import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreMessage extends JFrame
{
  private JTextArea affichage;
  private JButton fermer = new JButton("Fermer");
  private JLabel consigne = new JLabel("  Type your own message (30 letters max, blanks included) ; characters accepted : all letters, '!', '?', ':)'");

  FenetreWarning fenError;

  private String message = new String();
  private String[] mots;
  private int premiereLigne;

  private JPanel container = new JPanel();
  private JPanel bot = new JPanel();

  private Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
  private double jFrameWidth = screenDimension.width/1440;
  private double jFrameHeight = screenDimension.height/800; // permettent d'adapter la taille de tous les composants à l'écran

  public FenetreMessage()
  {
    this.setTitle("Give your own message");
    this.setSize((int)(jFrameWidth*1000), (int)(jFrameHeight*700));
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);

    this.setContentPane(container);
    container.setLayout(new BorderLayout());

    affichage = new JTextArea((int)jFrameWidth*200, (int)jFrameHeight*25);
    container.add(affichage, BorderLayout.CENTER);
    container.add(consigne, BorderLayout.NORTH);

    consigne.setPreferredSize(new Dimension((int)(jFrameWidth*500), (int)(jFrameHeight*26)));
    fermer.setPreferredSize(new Dimension((int)(jFrameWidth*200), (int)(jFrameHeight*26)));

    fermer.addActionListener(new BoutonListener(9, this));

    bot.add(fermer);
    container.add(bot, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public String[] getMessage()
  {
    return this.mots;
  }
  public int getFirstLine()
  {
    return this.premiereLigne;
  }
  public void fermer()
  {
    message = affichage.getText().toUpperCase();

    mots = message.split(" ");
    if (message.length() == 0)
    {
      message = " ";
      mots = new String[1];
      mots[0] = " ";
    }

    premiereLigne = 0;
    int secondeLigne = 0;
    int somme = 0;
    boolean warning = false;

    for(int loop = 0 ; loop < mots.length ; loop++)
    {
      somme += mots[loop].length();
      if (mots[loop].length() > 17)
      {
        message = " ";
        mots = new String[1];
        mots[0] = " ";
        fenError = new FenetreWarning(this);
        warning = true;
        break;
      }
      if ((somme + loop) > 17 && premiereLigne == 0) // ne rentre pas sur une ligne
      {
        premiereLigne = loop;
        somme = mots[loop].length();
      }
      else if((somme + loop - premiereLigne) > 17 && premiereLigne != 0)
      {
        message = " ";
        mots = new String[1];
        mots[0] = " ";
        fenError = new FenetreWarning(this);
        warning = true;
        break;
      }
    }
    if (premiereLigne == 0)
    {
      premiereLigne = mots.length;
    }
    if (!warning)
    {
      dispose();
    }
    else
    {
      affichage.setText(message);
    }
  }
}

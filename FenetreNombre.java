import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class FenetreNombre extends JFrame
{
  private JPanel container = new JPanel();
  private JPanel bot = new JPanel();

  private JButton boutonNombreBinaire = new JButton("Binary number");
  private JButton boutonNombreDecimal = new JButton("Decimal number");

  private JTextArea affichage;

  private String tupperNumber = new String();

  private Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
  private double jFrameWidth = screenDimension.width/1440;
  private double jFrameHeight = screenDimension.height/800; // permettent d'adapter la taille de tous les composants à l'écran

  public FenetreNombre()
  {
    this.setTitle("Give your own number");
    this.setSize((int)(jFrameWidth*1000), (int)(jFrameHeight*700));
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);

    this.setContentPane(container);
    container.setLayout(new BorderLayout());

    affichage = new JTextArea((int)jFrameWidth*200, (int)jFrameHeight*25);

    affichage.setText("Tapez votre nombre ici :\n");
    container.add(affichage, BorderLayout.CENTER);
    boutonNombreBinaire.setPreferredSize(new Dimension((int)(jFrameWidth*150), (int)(jFrameHeight*26)));
    boutonNombreDecimal.setPreferredSize(new Dimension((int)(jFrameWidth*150), (int)(jFrameHeight*26)));

    boutonNombreBinaire.addActionListener(new BoutonListener(7, this));
    boutonNombreDecimal.addActionListener(new BoutonListener(8, this));

    bot.add(boutonNombreBinaire);
    bot.add(boutonNombreDecimal);

    container.add(bot, BorderLayout.SOUTH);

    this.setVisible(true);
  }
  public String getNumber()
  {
    return(tupperNumber);
  }
  public void nombreBinaire()
  {
    tupperNumber = affichage.getText(); // on recupere le nombre
    int loop = 0;
    while (loop < tupperNumber.length())
    {
      if (!Character.isDigit(tupperNumber.charAt(loop))) // si le char n'est pas un nombre
      {
        tupperNumber = (String)tupperNumber.subSequence(0, loop) + (String)tupperNumber.subSequence(loop+1,tupperNumber.length()); // on l'enlève du String
      }
      else
      {
        loop++;
      }
    }

    if (tupperNumber.length() > 1802)
    {
      System.out.println("Error : your number is too big.");
      tupperNumber = new String();
    }
    else if (tupperNumber.length() == 0)
    {
      System.out.println("Error : you didn't write a number.");
      tupperNumber = "0";
    }
    else if (tupperNumber.length() < 1802)
    {
      int len = tupperNumber.length();
      for(int loop2 = 0 ; loop2 < (1802 - len) ; loop2++)
      {
        tupperNumber = "0" + tupperNumber;
      }
    }
  }
  public void nombreDecimal()
  {
    tupperNumber = affichage.getText(); // pareil qu'au-dessus

    int loop = 0;
    while (loop < tupperNumber.length())
    {
      if (!Character.isDigit(tupperNumber.charAt(loop))) // si le char n'est pas un nombre
      {
        tupperNumber = (String)tupperNumber.subSequence(0, loop) + (String)tupperNumber.subSequence(loop+1,tupperNumber.length()); // on l'enlève du String
      }
      else
      {
        loop++;
      }
    }
    if (tupperNumber.length() == 0)
    {
      System.out.println("Error : you didn't write a number.");
      tupperNumber = "0";
    }
    else if (tupperNumber.length() > 543)
    {
      System.out.println("Error : your number is too big.");
      tupperNumber = "0";
    }
    // conversion du nombre en binaire
    BigInteger deux;
    BigInteger nombre = new BigInteger(tupperNumber);
    nombre = nombre.divide(new BigInteger("17"));
    tupperNumber = new String();

    for(int loop2 = 1801 ; loop2 >= 0 ; loop2--)
    {
      deux = new BigInteger("2");
      deux = deux.pow(loop2);
      BigInteger test = nombre.subtract(deux);
      if ((test.abs()).equals(test)) // si test est positif
      {
        nombre = nombre.subtract(deux);
        tupperNumber += "1";
      }
      else
      {
        tupperNumber += "0";
      }
    }
  }
}

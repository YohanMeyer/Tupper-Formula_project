import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;
public class Tupper extends JFrame
{
  private JPanel container = new JPanel();
  private Panneau pan = new Panneau();
  private JPanel mid = new JPanel();
  private JPanel bot = new JPanel();
  private Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
  private double jFrameWidth = screenDimension.width/1440;
  private double jFrameHeight = screenDimension.height/800; // permettent d'adapter la taille de tous les composants à l'écran
  private Point ecran[][] = new Point[106][17];
  private final int X = (int)jFrameWidth * 190;
  private final int Y = (int)jFrameHeight * 325;
  private boolean smiley = false;

  private SourisListener sousou = new SourisListener(this, pan);
  private SourisMotionListener sou = new SourisMotionListener(this, pan);

  private JButton boutonPinceau = new JButton("pinceau");
  private JButton boutonGomme = new JButton("gomme");
  private JButton boutonNormal = new JButton("normal");
  private JButton boutonNombre = new JButton("get number");
  private JButton boutonNombreADonner = new JButton("give number");
  private JButton boutonMessage = new JButton("message");

  private String nombreADessiner = new String();

  public Tupper()
  {
    this.setTitle("Tupper's self-referential formula");
    this.setSize((int)(jFrameWidth*1440), (int)(jFrameHeight*820));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);

    container.setLayout(new BorderLayout());
    container.add(pan);
    mid.setBackground(Color.white);
    bot.setBackground(Color.white);

    this.addMouseListener(sousou); // gère les clics
    this.addMouseMotionListener(sou); // gère le déplacement

    boutonPinceau.addActionListener(new BoutonListener(1, this, sousou, sou));
    boutonGomme.addActionListener(new BoutonListener(2, this, sousou, sou));
    boutonNormal.addActionListener(new BoutonListener(3, this, sousou, sou));
    boutonNombre.addActionListener(new BoutonListener(4, this, sousou, sou));
    boutonNombreADonner.addActionListener(new BoutonListener(5, this, sousou, sou));
    boutonMessage.addActionListener(new BoutonListener(6, this, sousou, sou));

    bot.add(boutonNormal);
    bot.add(boutonPinceau);
    bot.add(boutonGomme);
    bot.add(boutonNombre);
    bot.add(boutonNombreADonner);
    bot.add(boutonMessage);


    container.add(mid, BorderLayout.CENTER);
    container.add(bot, BorderLayout.SOUTH);

    this.setContentPane(container);

    for(int loop = 0 ; loop < ecran.length ; loop++)
    {
      for(int loop2 = 0 ; loop2 < ecran[0].length ; loop2++)
      {
        ecran[loop][loop2] = new Point(X+10*loop, Y+10*loop2, Color.white);
      }
    }
    actualiserEcran(ecran);
    this.setVisible(true);

    double[] dim = new double[2]; // pour récupérer les dimensions de l'écran dans le panneau
    dim[0] = jFrameWidth;
    dim[1] = jFrameHeight;
    pan.getScreenSize(dim);
    try
    {
      Thread.sleep(100);
    }
    catch(Exception e)
    {}
    pan.paintComponent(this.getGraphics()); // trace le rectangle rouge
  }

  public void actualiserNombreADessiner(String nombre)
  {
    nombreADessiner = nombre;
    dessiner();
  }
  public void dessiner()
  {
    for(int loop = 0 ; loop < nombreADessiner.length() ; loop++)
    {
      int x = loop/17;
      int y = 16 - loop%17;

      if (nombreADessiner.charAt(loop) == '1')
      {
        ecran[x][y].setColor(Color.black);
      }
      else
      {
        ecran[x][y].setColor(Color.white);
      }
      pan.paintPoint(this.getGraphics(), ecran[x][y]);
    }
    actualiserEcran(ecran);
  }

  public void actualiserMessageADessiner(String[] message, int premiereLigne)
  {
    String reset = "";
    for(int loop = 0 ; loop < 1802 ; loop++)
    {
      reset += "0";
    }
    actualiserNombreADessiner(reset); // pour remettre l'écran tout blanc

    boolean[][] coordLettre = new boolean[7][5];
    int x1 = 0;
    for(int loop = 0 ; loop < premiereLigne ; loop++)
    {
      x1 += message[loop].length(); // longueur totale du message à afficher
    }                                   //sur la première ligne

    x1 = (106 - x1*6 - (premiereLigne - 1)*3)/2;

    int[] lastCoordLettre = {x1,1};

    for(int loop = 0 ; loop < premiereLigne ; loop++)
    {
      for(int pos = 0 ; pos < message[loop].length() ; pos++)
      {
        char c = message[loop].charAt(pos);
        coordLettre = getCoordLettre(c);
        int x = 0;
        int y = 0;
        for(int loop2 = 0 ; loop2 < 7 ; loop2++)
        {
          for(int loop3 = 0 ; loop3 < 5 ; loop3++)
          {
            if (coordLettre[loop2][loop3])
            {
              x = lastCoordLettre[0]+loop3;
              y = lastCoordLettre[1]+loop2;
              ecran[x][y].setColor(Color.black);
              pan.paintPoint(this.getGraphics(), ecran[x][y]);
            }
          }
        }
        lastCoordLettre[0] += 6;
      }
      lastCoordLettre[0] += 3;
    }

    int secondeLigne;
    try
    {
      secondeLigne = message.length - premiereLigne;
    }
    catch(NullPointerException e)
    {
      secondeLigne = 0;
    }
    x1 = 0;
    for(int loop = premiereLigne ; loop < (secondeLigne + premiereLigne) ; loop++)
    {
      x1 += message[loop].length(); // longueur totale du message à afficher
    }                                   //sur la première ligne

    x1 = (106 - x1*6 - (secondeLigne - 1)*3)/2;


    lastCoordLettre[0] = x1;
    lastCoordLettre[1] = 9;

    for(int loop = premiereLigne ; loop < message.length ; loop++)
    {
      for(int pos = 0 ; pos < message[loop].length() ; pos++)
      {
        char c = message[loop].charAt(pos);
        coordLettre = getCoordLettre(c);
        int x = 0;
        int y = 0;
        for(int loop2 = 0 ; loop2 < 7 ; loop2++)
        {
          for(int loop3 = 0 ; loop3 < 5 ; loop3++)
          {
            if (coordLettre[loop2][loop3])
            {
              x = lastCoordLettre[0]+loop3;
              y = lastCoordLettre[1]+loop2;
              ecran[x][y].setColor(Color.black);
              pan.paintPoint(this.getGraphics(), ecran[x][y]);
            }
          }
        }
        lastCoordLettre[0] += 6;
      }
      lastCoordLettre[0] += 3;
    }
  }
  public boolean[][] getCoordLettre(char c)
  {
    boolean[][] coord = new boolean[7][5]; // a lire [y][x]
    switch(c)
    {
      case 'A':
        for(int loop = 1 ; loop < 4 ; loop++)
        {
          coord[0][loop] = true;
          coord[3][loop] = true;
        }
        for(int loop = 1 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        break;
      case 'B':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
        }
        for(int loop = 1 ; loop < 4 ; loop++)
        {
          coord[0][loop] = true;
          coord[3][loop] = true;
          coord[6][loop] = true;
        }
        coord[1][4] = true;
        coord[2][4] = true;
        coord[4][4] = true;
        coord[5][4] = true;
        break;
      case 'C':
        for(int loop = 1 ; loop < 5 ; loop++)
        {
          coord[0][loop] = true;
          coord[6][loop] = true;
        }
        for(int loop = 1 ; loop < 6 ; loop++)
        {
          coord[loop][0] = true;
        }
        break;
      case 'D':
        for(int loop = 0 ; loop < 4 ; loop++)
        {
          coord[0][loop] = true;
          coord[6][loop] = true;
        }
        for(int loop = 1 ; loop < 6 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        break;
      case 'E':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
        }
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[0][loop] = true;
          coord[3][loop] = true;
          coord[6][loop] = true;
        }
        coord[3][4] = false;
        break;
      case 'F':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
        }
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[0][loop] = true;
          coord[3][loop] = true;
        }
        coord[3][4] = false;
        break;
      case 'G':
        for(int loop = 1 ; loop < 5 ; loop++)
        {
          coord[0][loop] = true;
          coord[loop][0] = true;
          coord[6][loop] = true;
        }
        coord[5][0] = true;
        coord[6][4] = false;
        for(int loop = 3 ; loop < 6 ; loop++)
        {
          coord[loop][4] = true;
        }
        coord[3][3] = true;
        break;
      case 'H':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        for(int loop = 1 ; loop < 4 ; loop++)
        {
          coord[3][loop] = true;
        }
        break;
      case 'I':
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[0][loop] = true;
          coord[6][loop] = true;
        }
        for(int loop = 1 ; loop < 6 ; loop++)
        {
          coord[loop][2] = true;
        }
        break;
      case 'J':
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[0][loop] = true;
          coord[loop+1][2] = true;
        }
        for(int loop = 0 ; loop < 3 ; loop++)
        {
          coord[6][loop] = true;
        }
        coord[5][0] = true;
        break;
      case 'K':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
        }
        for(int loop = 1 ; loop < 5 ; loop++)
        {
          coord[2+loop][loop] = true;
          coord[4-loop][loop] = true;
        }
        break;
      case 'L':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
        }
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[6][loop] = true;
        }
        break;
      case 'M':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        coord[1][1] = true;
        coord[2][2] = true;
        coord[1][3] = true;
        break;
      case 'N':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        coord[1][1] = true;
        coord[2][1] = true;
        coord[3][2] = true;
        coord[4][3] = true;
        coord[5][3] = true;
        break;
      case 'O':
        for(int loop = 1; loop < 6 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        for(int loop = 1 ; loop < 4 ; loop++)
        {
          coord[0][loop] = true;
          coord[6][loop] = true;
        }
        break;
      case 'P':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
        }
        for(int loop = 1 ; loop < 4 ; loop++)
        {
          coord[0][loop] = true;
          coord[4][loop] = true;
          coord[loop][4] = true;
        }
        break;
      case 'Q':
        for(int loop = 1 ; loop < 4 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
          coord[0][loop] = true;
          coord[5][loop] = true;
        }
        coord[4][2] = true;
        coord[6][3] = true;
        coord[6][4] = true;
        coord[4][0] = true;
        coord[4][4] = true;
        break;
      case 'R':
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][0] = true;
        }
        for(int loop = 1 ; loop < 4 ; loop++)
        {
          coord[0][loop] = true;
          coord[3][loop] = true;
          coord[loop][4] = true;
        }
        coord[3][4] = false;
        for(int loop = 4 ; loop < 7 ; loop++)
        {
          coord[loop][loop-2] = true;
        }
        break;
      case 'S':
        for(int loop = 0 ; loop < 4 ; loop++)
        {
          coord[0][1+loop] = true;
          coord[3][1+loop] = true;
          coord[6][loop] = true;
        }
        coord[3][4] = false;
        for(int loop = 0 ; loop < 2 ; loop++)
        {
          coord[1+loop][0] = true;
          coord[4+loop][4] = true;
        }
        break;
      case 'T':
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[0][loop] = true;
        }
        for(int loop = 0 ; loop < 7 ; loop++)
        {
          coord[loop][2] = true;
        }
        break;
      case 'U':
        for(int loop = 0 ; loop < 6 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        for(int loop = 1 ; loop < 4 ; loop++)
        {
          coord[6][loop] = true;
        }
        break;
      case 'V':
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        coord[5][1] = true;
        coord[6][2] = true;
        coord[5][3] = true;
        break;
      case 'W':
        for(int loop = 0 ; loop < 6 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        coord[6][1] = true;
        coord[5][2] = true;
        coord[4][2] = true;
        coord[6][3] = true;
        break;
      case 'X':
        coord[0][0] = true;
        coord[6][4] = true;
        coord[6][0] = true;
        coord[0][4] = true;
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[1+loop][loop] = true;
          coord[5-loop][loop] = true;
        }
        break;
      case 'Y':
        for(int loop = 0 ; loop < 3 ; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
          coord[loop+4][2] = true;
        }
        coord[3][1] = true;
        coord[3][3] = true;
        break;
      case 'Z':
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[0][loop] = true;
          coord[6][loop] = true;
          coord[5-loop][loop] = true;
        }
      case '?':
        for(int loop = 1 ; loop < 4 ; loop++)
        {
          coord[0][loop] = true;
        }
        for(int loop = 1 ; loop < 3; loop++)
        {
          coord[loop][0] = true;
          coord[loop][4] = true;
        }
        coord[3][3] = true;
        coord[4][2] = true;
        coord[6][2] = true;
        break;
      case '!':
        for(int loop = 0 ; loop < 5 ; loop++)
        {
          coord[loop][2] = true;
        }
        coord[6][2] = true;
        break;
    }
    if (c == ':')
    {
      smiley = true;
    }
    else if (smiley && c == ')')
    {
      for(int loop = 1 ; loop < 3 ; loop++)
      {
        coord[loop][1] = true;
        coord[loop][3] = true;
        coord[loop+3][0] = true;
        coord[loop+3][4] = true;
      }
      for(int loop = 1 ; loop < 4 ; loop++)
      {
        coord[6][loop] = true;
      }
    }
    else
    {
      smiley = false;
    }
    return(coord);
  }

  public void nombreADonner()
  {
    String tupperNumber = new String();

    // lire l'écran de gauche à droite, du bas vers le haut en binaire puis convertir en décimal
    for(int loop = 0 ; loop < 106 ; loop++)
    {
      for(int loop2 = 16 ; loop2 >= 0 ; loop2--)
      {
        if (ecran[loop][loop2].getColor().equals(Color.black))
        {
          tupperNumber += '1';
        }
        else
        {
          tupperNumber += '0';
        }
      }
    }
    System.out.println("Binary number : \n"+tupperNumber);
    BigInteger tupperNum = new BigInteger("0");
    for(int loop = 0 ; loop < tupperNumber.length() ; loop++)
    {
      BigInteger a;
      if (tupperNumber.charAt(tupperNumber.length() - 1 - loop) == '1')
      {
        a = new BigInteger("2");
        a = a.pow(loop);
      }
      else
      {
        a = new BigInteger("0");
      }

      tupperNum = tupperNum.add(a);
    }
    tupperNum = tupperNum.multiply(new BigInteger("17"));
    System.out.println("Decimal number : \n"+tupperNum);
  }

  public void nombreARecevoir()
  {
    FenetreNombre fen = new FenetreNombre();
    fen.addWindowListener(new FenListener(this, fen, 1));
  }

  public void message()
  {
    FenetreMessage fen = new FenetreMessage();
    fen.addWindowListener(new FenListener(this, fen, 2));
  }
  public void actualiserEcran(Point[][] ecran)
  {
    sou.setEcran(ecran);
    sousou.setEcran(ecran);
  }
  public void actualiserEcran(Point[][] ecran, int[] lastCoord)
  {
    sou.setEcran(ecran, lastCoord);
    sousou.setEcran(ecran, lastCoord);
  }
}

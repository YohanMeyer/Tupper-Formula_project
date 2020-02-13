import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreWarning extends JFrame
{
  private JButton oui = new JButton("Yes");
  private JButton non = new JButton("No");
  private JLabel affichage = new JLabel("<html><div style='text-align: center;'>Warning : your message is too long (or contains a very long word) and can't be displayed. Do you really wish to close the message window ?</div></html>");

  private JPanel container = new JPanel();
  private JPanel bot = new JPanel();

  private FenetreMessage fen;

  private Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
  private double jFrameWidth = screenDimension.width/1440;
  private double jFrameHeight = screenDimension.height/800; // permettent d'adapter la taille de tous les composants à l'écran

  public FenetreWarning(FenetreMessage fen)
  {
    this.setTitle("Warning");
    this.setSize((int)(jFrameWidth*600), (int)(jFrameHeight*200));
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);

    this.setContentPane(container);
    container.setLayout(new BorderLayout());
    container.add(affichage, BorderLayout.CENTER);
    bot.add(oui);
    bot.add(non);
    container.add(bot, BorderLayout.SOUTH);

    oui.addActionListener(new BoutonListener(10, this, fen));
    non.addActionListener(new BoutonListener(11, this, fen));

    this.fen = fen;

    this.setVisible(true);
  }
}

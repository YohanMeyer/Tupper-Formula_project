import java.awt.*;
public class Point
{
  private int[] x = new int[10];
  private int[] y = new int[10];
  Color color;
  public Point(int x, int y, Color color)
  {
    this.x[0] = x;
    this.y[0] = y;
    this.color = color;
  }
  public int getX()
  {
    return x[0];
  }
  public int getY()
  {
    return y[0];
  }
  public Color getColor()
  {
    return color;
  }
  public void setColor(Color color)
  {
    this.color = color;
  }

}

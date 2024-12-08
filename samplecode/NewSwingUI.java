import java.awt.Graphics;
import java.awt.*;
public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;
  private NewSwingUI() {
  }
  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }
  public  void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }
  public void drawLabel(String s, Point p) {
    if (p != null) {
      if (s != null) {
        graphics.drawString(s, (int) p.getX(), (int) p.getY());
      }
    }
    int length = graphics.getFontMetrics().stringWidth(s);
    graphics.drawString("_", (int)p.getX() + length, (int)p.getY());
  }
  public void drawLine(Point point1,  Point point2) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (point1 != null) {
      i1 = Math.round((float) (point1.getX()));
      i2 = Math.round((float) (point1.getY()));
      if (point2 != null) {
        i3 = Math.round((float) (point2.getX()));
        i4 = Math.round((float) (point2.getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }

  public void drawTriangle(Point point1,  Point point2, Point point3) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    int i5 = 0;
    int i6 = 0;
    int[] xpoints = new int[3];
    int[] ypoints = new int[3];
    if (point1 != null) {
      i1 = Math.round((float) (point1.getX()));
      i2 = Math.round((float) (point1.getY()));
      xpoints[0] = i1;
      ypoints[0] = i2;
      if (point2 != null) {
        i3 = Math.round((float) (point2.getX()));
        i4 = Math.round((float) (point2.getY()));
        xpoints[1] = i3;
        ypoints[1] = i4;

          if (point3 != null) {
            i5 = Math.round((float) (point3.getX()));
            i6 = Math.round((float) (point3.getY()));
            xpoints[2] = i5;
            ypoints[2] = i6;
          } else {
            i5 = i3;
            i6 = i4;
            xpoints[2] = i5;
            ypoints[2] = i6;
          }
      } else {
        xpoints[1] = i1;
        ypoints[1] = i2;
        xpoints[2] = i1;
        ypoints[2] = i2;
      }
      graphics.drawPolygon(xpoints, ypoints, 3);
    }
  }

  public void drawPolygon(Point[] list) {
    int length = list.length;
    if (length != 0) {
      int[] xpoints = new int[length];
      int[] ypoints = new int[length];

      int index = 0;
      for ( Point i : list){
        if (i != null) {
          int x = Math.round((float) (i.getX()));
          int y = Math.round((float) (i.getY()));
          xpoints[index] = x;
          ypoints[index] = y;
          index++;
        }
        
      }
      graphics.drawPolygon(xpoints, ypoints, index);
    }
  }
}
import java.awt.*;
import java.text.*;
public class PolygonCommand extends Command {
  private Polygon polygon;
  private int pointCount;
  public PolygonCommand() {
    polygon = new Polygon();
    pointCount = 0;
  }
  public PolygonCommand(Point[] list) {
    polygon = new Polygon(list);
    pointCount = list.length;
  }
   public PolygonCommand(Point point) {
    polygon = new Polygon();
    polygon.addPoint(point);
    pointCount = 1;
  }
  public void setPolygonPoint(Point point) {
    polygon.addPoint(point);
  }
  public void execute() {
    model.addItem(polygon);
  }
  public boolean undo() {
    model.removeItem(polygon);
    return true;
  }
  public boolean redo() {
    execute();
    return true;
  }
  public boolean end() {
    if (polygon.getPointFirst() == null) {
      undo();
      return false;
    }
    if (polygon.getPointLast() == null) {
       polygon.addPoint(polygon.getPointFirst());
    }
    return true;
  }
}
import java.awt.*;
import java.util.ArrayList;

public class Polygon extends Item {
  private ArrayList<Point> pointList;

  // Constructor for arrays
  public Polygon(Point[] List) {
    pointList = new ArrayList<Point>();
    for (Point p : List) {
        this.pointList.add(p);
    }
  }
  
  // Constructor for none
  public Polygon() {
pointList = new ArrayList<Point>();
  }

  @Override
  public void moveTo(Point newPosition){
    if (pointList.isEmpty()) return; // No points to move

    Point firstPoint = getPointFirst();
    int dx = newPosition.x - firstPoint.x;
    int dy = newPosition.y - firstPoint.y;

    // Update each point in the list
    for (Point point : pointList){
      point.translate(dx, dy);
    }
  }

  public boolean includes(Point point) {
    Point lastPoint = pointList.get(pointList.size() - 1);
    Point firstPoint = pointList.get(0);
    return ((distance(point, lastPoint) < 10.0) || (distance(point, firstPoint)< 10.0));
  }
  public void render(UIContext uiContext) {
    uiContext.drawPolygon(this.getPoints());
  }
  public void addPoint(Point point){
    pointList.add(point);
  }
  public Point[] getPoints() {
    int count = pointList.size();
    Point[] array = new Point[count];
    int index = 0;
    for(Point p : pointList) {
      array[index] = p;
      index++;
    }    
    return array;
  }
  public Point getPointFirst(){
    return pointList.get(0);
  }
  public Point getPointLast(){
    return pointList.get(pointList.size()-1);
  }
  public String toString() {
    String string = "Polygon has points: ";
    for(Point p: pointList) string = string + ", " + p;
    return string;
  }
}
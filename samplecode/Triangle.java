import java.awt.*;
public class Triangle extends Item {
  private Point point1;
  private Point point2;
  private Point point3;

  public Triangle(Point point1, Point point2, Point point3) {
    this.point1 = point1;
    this.point2 = point2;
    this.point3 = point3;
  }
    public Triangle(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
    this.point3 = null;
  }
  public Triangle(Point point1) {
    this.point1 = point1;
point2 = null;
    point3 = null;
  }
  public Triangle() {
point1 = null;
point2 = null;
    point3 = null;
  }

  @Override
  public void moveTo(Point newPosition){
    int dx = newPosition.x - point1.x;
    int dy = newPosition.y - point1.y;
    point1.translate(dx, dy);
    point2.translate(dx, dy);
    point3.translate(dx, dy);
  }

  public boolean includes(Point point) {
    return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0) || (distance(point, point3)< 10.0));
  }

  // Needs to be edited externally
  public void render(UIContext uiContext) {
    uiContext.drawTriangle(point1, point2, point3);
  }
  public void setPoint1(Point point) {
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public void setPoint3(Point point) {
    point3 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }
  public Point getPoint3() {
    return point3;
  }
  public String toString() {
    return "Triangle with points, " + point1 + ", " + point2 + ", " + point3;
  }
}
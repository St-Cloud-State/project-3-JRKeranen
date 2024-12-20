import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class PolygonButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private PolygonCommand polygonCommand;
  private UndoManager undoManager;
  public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("Polygon");
    this.undoManager = undoManager;
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    // Change cursor when button is clicked
    drawingPanel.addMouseListener(mouseHandler);
  // Start listening for mouseclicks on the drawing panel
  }
  private class MouseHandler extends MouseAdapter {
    private int pointCount = 0;
    public void mouseClicked(MouseEvent event) {
      int ButtonClicked = event.getButton();

      if ((ButtonClicked == 1) && (pointCount <= 2)) {
        ++pointCount;
      }
      else if (pointCount >= 3) {
        ++pointCount;
      }

      if (pointCount == 1) {
        polygonCommand = new PolygonCommand(View.mapPoint(event.getPoint()));
        undoManager.beginCommand(polygonCommand);
        } 
      else if (pointCount <= 3) {
        polygonCommand.setPolygonPoint(View.mapPoint(event.getPoint()));
        undoManager.beginCommand(polygonCommand);
         } 
      else if ((pointCount > 3) && (ButtonClicked == 1)) {
        // add another point and line to the polygon
        polygonCommand.setPolygonPoint(View.mapPoint(event.getPoint()));
        undoManager.beginCommand(polygonCommand);
           } 
      else if ((pointCount > 3) && (ButtonClicked ==  3)) {
        // end get first point and add last point add line and end
        pointCount = 0;
        drawingPanel.removeMouseListener(this);
        view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        undoManager.endCommand(polygonCommand);
      }
    }
  }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class MoveButton extends JButton implements ActionListener {
    private JPanel drawingPanel;
    private View view;
    private Model model; // Add this
    private UndoManager undoManager;
    private MouseHandler mouseHandler;

    public MoveButton(UndoManager undoManager, View view, JPanel drawingPanel, Model model) {
        super("Move");
        this.undoManager = undoManager;
        this.view = view;
        this.drawingPanel = drawingPanel;
        this.model = model; // Save the Model instance
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawingPanel.addMouseListener(mouseHandler = new MouseHandler());
    }

    private class MouseHandler extends MouseAdapter {
        private Item selectedItem;
        private Point originalPosition;

        @Override
        public void mousePressed(MouseEvent e) {
            Point clickPoint = View.mapPoint(e.getPoint());
            Enumeration items = model.getItems(); // Use the model instance here
            while (items.hasMoreElements()) {
                Item item = (Item) items.nextElement();
                if (item.includes(clickPoint)) {
                    selectedItem = item;
                    originalPosition = clickPoint;
                    break;
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (selectedItem != null) {
                Point newPosition = View.mapPoint(e.getPoint());
                selectedItem.moveTo(newPosition);
                view.refresh();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (selectedItem != null) {
                Point finalPosition = View.mapPoint(e.getPoint());
                MoveCommand moveCommand = new MoveCommand(selectedItem, originalPosition, finalPosition);
                undoManager.beginCommand(moveCommand);
                undoManager.endCommand(moveCommand);
                view.refresh();
                drawingPanel.removeMouseListener(this);
            }
        }
    }
}
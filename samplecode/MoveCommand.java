import java.awt.Point;

public class MoveCommand extends Command {
    private Item item;
    private Point originalPosition;
    private Point newPosition;

    public MoveCommand(Item item, Point originalPosition, Point newPosition) {
        this.item = item;
        this.originalPosition = originalPosition;
        this.newPosition = newPosition;
    }

    @Override
    public void execute() {
        item.moveTo(newPosition);
        model.setChanged();
    }

    @Override
    public boolean undo() {
        item.moveTo(originalPosition);
        model.setChanged();
        return true;
    }

    @Override
    public boolean redo() {
        item.moveTo(newPosition);
        model.setChanged();
        return true;
    }
}
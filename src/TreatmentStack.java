public class TreatmentStack {

    private class SNode {
        TreatmentRecord record;
        SNode next;

        SNode(TreatmentRecord record) {
            this.record = record;
        }
    }

    private SNode top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    public void push(TreatmentRecord record) {
        SNode newNode = new SNode(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("   Treatment history is empty. Nothing to remove.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    public void displayStack() {
        if (isEmpty()) {
            System.out.println("   No treatment records yet.");
            return;
        }
        SNode current = top;
        while (current != null) {
            System.out.println("   " + current.record);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
}

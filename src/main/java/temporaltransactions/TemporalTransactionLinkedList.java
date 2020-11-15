package temporaltransactions;

public class TemporalTransactionLinkedList {
    private TemporalTransactionNode head;
    private TemporalTransactionNode tail;
    private int size;

    public TemporalTransactionLinkedList() {
        this.size = 0;
        this.head = new TemporalTransactionNode(null, null, 0);
        this.tail = new TemporalTransactionNode(null, null, 0);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public TemporalTransactionNode getHead() {
        return head;
    }

    public void append(TemporalTransactionNode temporalTransactionNode) {
        temporalTransactionNode.prev = tail.prev;
        temporalTransactionNode.next = temporalTransactionNode.prev.next;
        temporalTransactionNode.next.prev = temporalTransactionNode;
        temporalTransactionNode.prev.next = temporalTransactionNode;
        this.size +=1;
    }

    public void addToFront(TemporalTransactionNode temporalTransactionNode) {
        temporalTransactionNode.next = head.next;
        temporalTransactionNode.prev = head;
        temporalTransactionNode.next.prev = temporalTransactionNode;
        temporalTransactionNode.prev.next = temporalTransactionNode;
        this.size+=1;
    }

    public TemporalTransactionNode getNode(TemporalTransactionNode temporalTransactionNode) {
        TemporalTransactionNode i = this.head;
        while (i.next.getDate()!= null) {
            if (i.next.getDate().equals(temporalTransactionNode.getDate()) && i.next.getVendor().equals(temporalTransactionNode.getVendor()) && i.next.getDollarAmount() == temporalTransactionNode.getDollarAmount()) {
                return i.next;
            }
            i = i.next;
        }
        return null;
    }

    public void setNode(TemporalTransactionNode originalNode, TemporalTransactionNode newNode) {
        TemporalTransactionNode toBeSetNode = getNode(originalNode);
        if (toBeSetNode != null) {
            toBeSetNode.setDate(newNode.getDate());
            toBeSetNode.setVendor(newNode.getVendor());
            toBeSetNode.setDollarAmount(newNode.getDollarAmount());
        } else {
            System.out.println("Element you want to update does not exist");
        }
    }

    public void remove(TemporalTransactionNode temporalTransactionNode) {
        TemporalTransactionNode nodeToBeRemoved = getNode(temporalTransactionNode);
        if (nodeToBeRemoved != null) {
            nodeToBeRemoved.next.prev = nodeToBeRemoved.prev;
            nodeToBeRemoved.prev.next = nodeToBeRemoved.next;
            nodeToBeRemoved.prev = null;
            nodeToBeRemoved.next = null;
            this.size-=1;
        } else {
            System.out.println("Element does not exits");
        }
    }

    public void print(){
        TemporalTransactionNode iterator = this.head;
        while (iterator.next.getDate() != null){
            System.out.println("Tansaction Date: " + iterator.next.getDate() + " || Vendor: " + iterator.next.getVendor() + " || Transaction Amount: " + iterator.next.getDollarAmount());
            iterator = iterator.next;
        }
    }

    public int size() {
        return size;
    }
}

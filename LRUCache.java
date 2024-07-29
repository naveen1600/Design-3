// Time Complexity: O(1)
// Space Complexity: O(n)

class LRUCache {
    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }

    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>();
    }

    private void removeNode(Node curr) {
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.next = curr.prev = null;
    }

    private void addNode(Node curr) {
        curr.next = this.head.next;
        curr.prev = this.head;
        this.head.next = curr;
        curr.next.prev = curr;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addNode(node);
            return;
        }
        if (map.size() == capacity) {
            Node node = this.tail.prev;
            map.remove(node.key);
            removeNode(node);
        }
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        addNode(newNode);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
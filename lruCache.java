class LRUCache {
    //Time Complexity - O(1) for both get and put.
    //space - O(n) 
    
    // Inner class representing a node in the doubly linked list
    class Node {
        Node next;
        Node prev;
        int key;
        int val;

        // Constructor to initialize a new node
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // HashMap to store key-node pairs for O(1) access
    HashMap<Integer, Node> map;
    // Dummy head and tail nodes to facilitate easy addition and removal of nodes
    private Node head;
    private Node tail;
    // Capacity of the LRU cache
    private int capacity;

    // Constructor to initialize the LRUCache with a given capacity
    public LRUCache(int capacity) {
        this.capacity = capacity;
        // Initialize dummy head and tail nodes
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        // Initialize the map
        this.map = new HashMap<>();
    }

    // Helper method to remove a node from the doubly linked list
    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    // Helper method to add a node right after the head node
    private void addToHead(Node node) {
        node.next = this.head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    // Method to retrieve a value from the cache
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1; // Key not found
        } else {
            Node currentNode = map.get(key);
            // Remove the node from its current position
            removeNode(currentNode);
            // Add the node to the head (most recently used position)
            addToHead(currentNode);
            // Return the value
            return currentNode.val;
        }
    }

    // Method to insert or update a value in the cache
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Key already exists, update the value and move the node to the head
            Node currentNode = map.get(key);
            currentNode.val = value;
            removeNode(currentNode);
            addToHead(currentNode);
        } else {
            // Key does not exist
            if (map.size() == capacity) {
                // Cache is at full capacity, remove the least recently used node
                Node tailNode = tail.prev;
                removeNode(tailNode);
                map.remove(tailNode.key);
            }
            // Add the new node to the head
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key, value);
 */

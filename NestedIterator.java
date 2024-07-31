// hasNext() Time Complexity: 
// O(n) in the worst case, where n is the total number of elements (integers and nested lists) in the nested structure.
// next() Time Complexity: O(1).
// Space Complexity: 
// O(d), where d is the maximum depth of the nested structure.

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    // Stack to keep track of the iterators of the nested lists
    Stack<Iterator<NestedInteger>> stack;
    // Current element that the iterator is pointing to
    NestedInteger currentElement;

    // Constructor to initialize the iterator with a nested list
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
        // Push the iterator of the nested list onto the stack
        stack.add(nestedList.iterator());
    }

    // Method to return the next integer in the flattened list
    @Override
    public Integer next() {
        return currentElement.getInteger();
    }

    // Method to check if there are more integers in the flattened list
    @Override
    public boolean hasNext() {
        // While the stack is not empty
        while (!stack.isEmpty()) {
            // If the iterator on the top of the stack has no more elements, pop it
            if (!stack.peek().hasNext()) {
                stack.pop();
            }
            // If the next element in the top iterator is an integer
            else if ((this.currentElement = stack.peek().next()).isInteger()) {
                return true;
            }
            // If the next element in the top iterator is a nested list
            else {
                // Push the iterator of the nested list onto the stack
                stack.add(currentElement.getList().iterator());
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

// Time Complexity: O(n)
// Space Complexity: O(n)

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
        this.stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();

    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else if ((nextEl = stack.peek().next()).isInteger()) {
                return true;
            } else {
                stack.push(nextEl.getList().iterator());
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
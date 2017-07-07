package crackingTheCodingInterview;

import java.util.Stack;

/**
 * from origial books.
 * @param <T>
 */
public class P835_TwoStackMakeMyQueue<T> {
	/**
	 * stackNewest存放最新的数据; stackOldest存放最旧的数据.
	 * 先移除最旧的元素, 如果old为空了,那么就从new转移所有的数据.
	 */
	Stack<T> stackNewest, stackOldest;
	
	public P835_TwoStackMakeMyQueue() {
		stackNewest = new Stack<T>();
		stackOldest = new Stack<T>();
	}
	
	public int size() {
		return stackNewest.size() + stackOldest.size();
	}
	
	public void add(T value) {
		// Push onto stack1
		stackNewest.push(value);
	}
	
	/* Move elements from stackNewest into stackOldest. This is usually done so that we can
	 * do operations on stackOldest.
	 */
	private void shiftStacks() {
		if (stackOldest.isEmpty()) { 
			while (!stackNewest.isEmpty()) {
				stackOldest.push(stackNewest.pop());
			}
		}
	}
	
	public T peek() {
		shiftStacks();
		return stackOldest.peek(); // retrieve the oldest item.
	}
	
	public T remove() {
		shiftStacks();
		return stackOldest.pop(); // pop the oldest item.
	}
}

package binary_trees.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.Stack;

class BinaryTree<T extends Comparable<T>> {

  private BinaryTreeNode<T> root;

  /* ==================================
     = Insertion ======================
     ================================== */
  public void insert(T data) {
    root = insert(root, data);
  }

  private BinaryTreeNode<T> insert(BinaryTreeNode<T> current, T data) {
    // Subtree empty
    if (current == null) {
      return new BinaryTreeNode<T>(data);
    }

    // Data exists in a node already and policy is "no duplicates allowed"
    if (data.compareTo(current.data) == 0) {
      return current;
    }

    // Insert to the left/right and set new left/right subtree
    // left/right pointer only changes for leaves, from null to new node, created on line 19
    if (data.compareTo(current.data) < 0) {
       current.left = insert(current.left, data);
    } else { // Or to the right
      current.right = insert(current.right, data);
    }

    return current;
  }

  public void delete(T data) {
    root = delete(root, data);
  }

  private BinaryTreeNode<T> delete(BinaryTreeNode<T> current, T data) {
    if (current == null) {
      // Do something such as
      throw new RuntimeException("Not found");
    }

    if (data.compareTo(current.data) < 0) {
      // Should be on the left
      current.left = delete(current.left, data);
    } else if (data.compareTo(current.data) > 0) {
      // Should be on the right
      current.right = delete(current.right, data);
    } else {
      // It's a match. We need to remove the current node

      // If one of the children is null,
      // we just replace this node with the non-null child
      if (current.left == null) {
        return current.right;
      } else if (current.right == null) {
        return current.left;
      } else {
        // Otherwise
        // - get the largest child of the left subtree
        //   (which, "in-order" speaking comes right before the current node)
        // - put it here
        // - remove it from the left subtree
        root.data = getLargestChild(current.left).data;
        root.left = delete(root.left, root.data);
      }
    }

    return current;
  }

  /**
   * We assume here that root is not null
   */
  private BinaryTreeNode<T> getLargestChild(BinaryTreeNode<T> root) {
    while (root.right != null) {
      root = root.right;
    }
    return root;
  }

  /* ==================================
     = Traversals =====================
     ================================== */
  public Iterator<T> inOrderTraversal() {
    return new InOrderIterator();
  }

  /**
   * In-order: left, root, right
   *
   * 1. Create an empty stack
   * 2. current = root
   * 3. While current != null
   *    a. stack.push(current)
   *    b. current = current.left
   * 4. p = stack.pop(). Print p
   * 5. current = p.right
   * 6. Goto 3
   */
  private class InOrderIterator implements Iterator<T> {

    Stack<BinaryTreeNode<T>> stack = new Stack<>();
    BinaryTreeNode<T> c;

    public InOrderIterator() {
      c = root;
    }

    @Override
    public boolean hasNext() {
      return c != null || !stack.isEmpty();
    }

    @Override
    public T next() {
      // push all left children
      while (c != null) {
        stack.push(c);
        c = c.left;
      }

      // element to return is the leftmost child
      c = stack.pop();

      // If c.right is null, it's a mark that we're done with this branch
      // Next time we won't go into the while loop above
      c = c.right;

      return c.data;
    }
  }

  /**
   * Pre-order: root, left, right
   *
   * 1. Create an empty stack and push root node to stack
   * 2. While stack is non empty
   *    a. Pop an item from stack and print it
   *    b. Push right child of popped item to stack
   *    c. Push left child of popped item to stack
   *
   * Right child is pushed before left child to make sure that left subtree is processed first
   */
  private class PreOrderIterator implements Iterator<T> {

    Stack<BinaryTreeNode<T>> stack = new Stack<>();

    public PreOrderIterator() {
      if (root != null) {
        stack.push(root);
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public T next() {
      BinaryTreeNode<T> c = stack.pop();
      if (c.right != null) {
        stack.push(c.right);
      }
      if (c.left != null) {
        stack.push(c.left);
      }
      return c.data;
    }
  }

  // root, right, left
  private class PreOrderTwistedIterator implements Iterator<T> {

    Stack<BinaryTreeNode<T>> stack = new Stack<>();

    public PreOrderTwistedIterator() {
      if (root != null) {
        stack.push(root);
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public T next() {
      BinaryTreeNode<T> c = stack.pop();
      if (c.left != null) {
        stack.push(c.left);
      }
      if (c.right != null) {
        stack.push(c.right);
      }
      return c.data;
    }
  }

  /**
   * Post-order: left, right, root
   *
   * Trick: achieved by
   * 1. Create pre-order-like traversal sequence, with a twist:
   *    root, right, left instead of root, left, right
   * 2. Reverse the sequence
   */
  private class PostOrderIterator implements Iterator<T> {

   Iterator<T> it = ImmutableList.copyOf(new PreOrderTwistedIterator()).reverse().iterator();

    @Override
    public boolean hasNext() {
      return it.hasNext();
    }

    @Override
    public T next() {
      return it.next();
    }
  }

  private class BinaryTreeNode<T extends Comparable<T>> {
     private T data;
     private BinaryTreeNode<T> left, right;

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public BinaryTreeNode(T data) {
       this(data, null, null);
     }
  } // end of BinaryTreeNode


  
} // end of BinaryTree
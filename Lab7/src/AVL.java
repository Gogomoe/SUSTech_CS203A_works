import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AVL {

    private Node root = null;

    public void insert(int value) {
        root = insert(root, value);
    }

    public void remove(int value) {
        root = remove(root, value);
    }

    public int max() {
        return maximum(root).value;
    }

    public int min() {
        return minimum(root).value;
    }

    public ArrayList<Integer> toArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        toArrayList(list, root);
        return list;
    }

    private int size() {
        return size(root);
    }

    private static class Node {
        int value;
        Node left;
        Node right;
        Node parent;
        int height;
        int size = 1;

        public Node(int value) {
            this.value = value;
        }
    }

    private static int height(Node node) {
        if (node != null) {
            return node.height;
        }
        return -1;
    }

    private static int size(Node node) {
        if (node != null) {
            return node.size;
        }
        return 0;
    }


    private static Node LL(Node root) {
        Node left = root.left;
        Node leftRight = left.right;

        root.left = leftRight;
        if (leftRight != null)
            leftRight.parent = root;
        left.right = root;
        root.parent = left;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        left.height = Math.max(height(left.left), height(left.right)) + 1;
        root.size = size(root.left) + size(root.right) + 1;
        left.size = size(left.left) + size(left.right) + 1;

        left.parent = null;
        return left;
    }

    private static Node RR(Node root) {
        Node right = root.right;
        Node rightLeft = right.left;

        root.right = rightLeft;
        if (rightLeft != null)
            rightLeft.parent = root;
        right.left = root;
        root.parent = right;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        right.height = Math.max(height(right.left), height(right.right)) + 1;
        root.size = size(root.left) + size(root.right) + 1;
        right.size = size(right.left) + size(right.right) + 1;

        right.parent = null;
        return right;
    }

    private static Node LR(Node root) {
        Node left = root.left;
        Node newLeft = RR(left);

        newLeft.parent = root;
        root.left = newLeft;
        return LL(root);
    }

    private static Node RL(Node root) {
        Node right = root.right;
        Node newRight = LL(right);

        newRight.parent = root;
        root.right = newRight;
        return RR(root);
    }

    private static Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        if (value < root.value) {
            Node newLeft = insert(root.left, value);
            newLeft.parent = root;
            root.left = newLeft;

            if (height(root.left) - height(root.right) == 2) {
                if (value < root.left.value) {
                    root = LL(root);
                } else {
                    root = LR(root);
                }
            }
        } else {
            Node newRight = insert(root.right, value);
            newRight.parent = root;
            root.right = newRight;

            if (height(root.right) - height(root.left) == 2) {
                if (value < root.right.value) {
                    root = RL(root);
                } else {
                    root = RR(root);
                }
            }
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    private static Node remove(Node root, int toDelete) {
        if (root == null) {
            return null;
        }

        if (toDelete < root.value) {
            Node newLeft = remove(root.left, toDelete);
            if (newLeft != null)
                newLeft.parent = root;
            root.left = newLeft;

            if (height(root.left) - height(root.right) == -2) {
                Node right = root.right;
                if (height(right.left) > height(right.right)) {
                    root = RL(root);
                } else {
                    root = RR(root);
                }
            }
        } else if (toDelete > root.value) {
            Node newRight = remove(root.right, toDelete);
            if (newRight != null)
                newRight.parent = root;
            root.right = newRight;

            if (height(root.right) - height(root.left) == -2) {
                Node left = root.left;
                if (height(left.left) > height(left.right)) {
                    root = LL(root);
                } else {
                    root = LR(root);
                }
            }
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                root = root.right;
                root.parent = null;
            } else if (root.right == null) {
                root = root.left;
                root.parent = null;
            } else {
                if (root.left.height > root.right.height) {
                    int max = maximum(root.left).value;
                    Node newLeft = remove(root.left, max);

                    root.value = max;
                    if (newLeft != null)
                        newLeft.parent = root;
                    root.left = newLeft;
                } else {
                    int min = minimum(root.right).value;
                    Node newRight = remove(root.right, min);

                    root.value = min;
                    if (newRight != null)
                        newRight.parent = root;
                    root.right = newRight;
                }
            }
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }


    private static Node maximum(Node tree) {
        if (tree == null)
            return null;

        while (tree.right != null)
            tree = tree.right;
        return tree;
    }


    private static Node minimum(Node tree) {
        if (tree == null)
            return null;

        while (tree.left != null)
            tree = tree.left;
        return tree;
    }

    private static void toArrayList(ArrayList<Integer> list, Node node) {
        if (node == null) {
            return;
        }
        toArrayList(list, node.left);
        list.add(node.value);
        toArrayList(list, node.right);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(100000);
            while (list.contains(num)) {
                num = random.nextInt(100000);
            }
            list.add(num);
        }
        AVL avl = new AVL();
        ArrayList<Integer> sorted = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            avl.insert(list.get(i));
            sorted.add(list.get(i));
            Collections.sort(sorted);

            if (!avl.toArrayList().equals(sorted)) {
                System.out.println("insert error");
            }
            if (avl.size() != sorted.size() || size(avl.root.left) + size(avl.root.right) != sorted.size() - 1) {
                System.out.println("size error");
            }
            if (avl.min() != sorted.get(0)) {
                System.out.println("min error");
            }
            if (avl.max() != sorted.get(sorted.size() - 1)) {
                System.out.println("max error");
            }
        }
        System.out.println("insert finish");
        System.out.println("height " + avl.root.height);
        System.out.println("left " + avl.root.left.size);
        System.out.println("right " + avl.root.right.size);

        for (int i = 30; i < 60; i++) {
            avl.remove(list.get(i));
            sorted.remove(list.get(i));
            if (!avl.toArrayList().equals(sorted)) {
                System.out.println("remove error");
            }

            if (avl.size() != sorted.size() || size(avl.root.left) + size(avl.root.right) != sorted.size() - 1) {
                System.out.println("size error");
            }
            if (avl.min() != sorted.get(0)) {
                System.out.println("min error");
            }
            if (avl.max() != sorted.get(sorted.size() - 1)) {
                System.out.println("max error");
            }
        }
        System.out.println("remove finish");


    }
}

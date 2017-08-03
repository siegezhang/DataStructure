package basic;

/**
 * Created by siege on 2017/8/3.
 * 后序遍历的实现，每个节点都是在遍历其子节点之后才遍历
 */
public class PostOrderTraversalOfBinaryTree {
    public static class BinaryTreeNode {
        BinaryTreeNode leftNode;
        BinaryTreeNode rightNode;

        int data;

        BinaryTreeNode(int data) {
            this.data = data;
        }
    }

    public void postorderTraversalMethod(BinaryTreeNode binaryTreeNode){
        if (binaryTreeNode!=null){
            postorderTraversalMethod(binaryTreeNode.leftNode);
            postorderTraversalMethod(binaryTreeNode.rightNode);
            System.out.print(binaryTreeNode.data+" ");
        }
    }

    public static void main(String[] args) {
        PostOrderTraversalOfBinaryTree postOrderTraversalOfBinaryTree=new PostOrderTraversalOfBinaryTree();
        //root node is 1
        BinaryTreeNode binaryTreeNode1=new BinaryTreeNode(1);

        BinaryTreeNode node2=new BinaryTreeNode(2);
        binaryTreeNode1.leftNode=node2;

        BinaryTreeNode node3=new BinaryTreeNode(3);
        binaryTreeNode1.rightNode=node3;

        BinaryTreeNode node4=new BinaryTreeNode(4);
        node2.leftNode=node4;
        BinaryTreeNode node5=new BinaryTreeNode(5);
        node2.rightNode=node5;

        BinaryTreeNode node6=new BinaryTreeNode(6);
        node3.leftNode=node6;
        BinaryTreeNode node7=new BinaryTreeNode(7);
        node3.rightNode=node7;

        postOrderTraversalOfBinaryTree.postorderTraversalMethod(binaryTreeNode1);
    }

}

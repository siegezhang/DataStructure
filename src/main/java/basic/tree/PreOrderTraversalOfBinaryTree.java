package basic.tree;

/**
 * Created by siege on 2017/8/5.
 * 先序遍历，先遍历自身节点，再遍历其子节点
 */
public class PreOrderTraversalOfBinaryTree {
    public static class BinaryTreeNode {
        BinaryTreeNode leftNode;
        BinaryTreeNode rightNode;

        int data;

        public BinaryTreeNode(int data) {
            this.data = data;
        }
    }

    public void preorderTraversalMethod(BinaryTreeNode binaryTreeNode){
        if (binaryTreeNode!=null){
            System.out.print(binaryTreeNode.data+" ");
            preorderTraversalMethod(binaryTreeNode.leftNode);
            preorderTraversalMethod(binaryTreeNode.rightNode);
        }

    }

    public static void main(String[] args) {
        PreOrderTraversalOfBinaryTree preOrderTraversalOfBinaryTree=new PreOrderTraversalOfBinaryTree();
        BinaryTreeNode binaryTreeNode=new BinaryTreeNode(1);

        BinaryTreeNode node2=new BinaryTreeNode(2);
        BinaryTreeNode node3=new BinaryTreeNode(3);

        binaryTreeNode.leftNode=node2;
        binaryTreeNode.rightNode=node3;


        BinaryTreeNode node4=new BinaryTreeNode(4);
        BinaryTreeNode node5=new BinaryTreeNode(5);
        node2.leftNode=node4;
        node2.rightNode=node5;

        BinaryTreeNode node6=new BinaryTreeNode(6);
        BinaryTreeNode node7=new BinaryTreeNode(7);
        node3.leftNode=node6;
        node3.rightNode=node7;

        preOrderTraversalOfBinaryTree.preorderTraversalMethod(binaryTreeNode);
    }
}

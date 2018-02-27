package basic.tree;

/**
 * Created by siege on 2017/8/3.
 * 中序遍历，先遍历左节点，再遍历当前节点，最后遍历右节点
 */
public class InOrderTraversalOfBinaryTree {
    public static class BinaryTreeNode{
        BinaryTreeNode leftNode;
        BinaryTreeNode rightNode;
        int data;

        public BinaryTreeNode(int data) {
            this.data = data;
        }
    }

    public void inorderTraversalMethod(BinaryTreeNode binaryTreeNode){
        if (binaryTreeNode!=null){
            inorderTraversalMethod(binaryTreeNode.leftNode);
            System.out.print(binaryTreeNode.data+" ");
            inorderTraversalMethod(binaryTreeNode.rightNode);
        }
    }

    public static void main(String[] args) {
        InOrderTraversalOfBinaryTree inOrderTraversalOfBinaryTree=new InOrderTraversalOfBinaryTree();
        BinaryTreeNode binaryTreeNode1=new BinaryTreeNode(1);
        BinaryTreeNode node2=new BinaryTreeNode(2);
        BinaryTreeNode node3=new BinaryTreeNode(3);
        binaryTreeNode1.leftNode=node2;
        binaryTreeNode1.rightNode=node3;

        BinaryTreeNode node4=new BinaryTreeNode(4);
        BinaryTreeNode node5=new BinaryTreeNode(5);
        node2.leftNode=node4;
        node2.rightNode=node5;

        BinaryTreeNode node6=new BinaryTreeNode(6);
        BinaryTreeNode node7=new BinaryTreeNode(7);

        node3.leftNode=node6;
        node3.rightNode=node7;

        inOrderTraversalOfBinaryTree.inorderTraversalMethod(binaryTreeNode1);

    }
}

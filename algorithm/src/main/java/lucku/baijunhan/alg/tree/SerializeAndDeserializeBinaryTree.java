package lucku.baijunhan.alg.tree;

import lucku.baijunhan.alg.structure.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 序列化方案:
 *   例如一颗二叉树:
 *         1
 *        / \
 *       9   2
 *      / \
 *     8  10
 *   序列化后为:
 *   1<*9<**8>**10>*2
 *   其中, ‘<’代表左子树, '>'代表右子树, '*'的个数代表层数
 *
 */
public class SerializeAndDeserializeBinaryTree {


    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = codec.deserialize("1<*9<**8>**10>*2");
        System.out.println(codec.serialize(root));
    }

    static class Codec {

        /** 表示左子树 */
        private static final byte LEFT = 0;
        /** 表示右子树 */
        private static final byte RIGHT = 1;

        // 序列化二叉树
        public String serialize(TreeNode root) {
            if (root == null) return null;
            StringBuilder s = new StringBuilder();
            s.append(root.val);
            serialize(root.left, 1, LEFT, s);
            serialize(root.right, 1, RIGHT, s);
            return s.toString();
        }

        /**
         * 先序遍历二叉树, 获得序列化字符串
         *
         * @param node     当前节点
         * @param level    层数
         * @param position 当前节点是左子树还是右子树
         * @param result   保存递归的序列化结果
         */
        private void serialize(TreeNode node, int level, byte position, StringBuilder result) {
            if (node == null) return;
            char posCh = position == LEFT ? '<' : '>';
            result.append(posCh);
            result.append("*".repeat(level));
            result.append(node.val);
            serialize(node.left, level + 1, LEFT, result);
            serialize(node.right, level + 1, RIGHT, result);
        }

        // 反序列化二叉树
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) return null;
            AtomicInteger index = new AtomicInteger();
            int val = readValue(data, index);
            TreeNode root = new TreeNode(val);
            root.left = deserialize(data, 1, LEFT, index);
            root.right = deserialize(data, 1, RIGHT, index);
            return root;
        }

        /**
         * 递归方式反序列化二叉树
         *
         * @param data     序列化字符串
         * @param level    当前层数
         * @param position 当前节点为左子树还是右子树
         * @param index    当前读取到的序列化字符串的位置, 由于java没有指针, 所以用{@link AtomicInteger}对象保存
         */
        private TreeNode deserialize(String data, int level, byte position, AtomicInteger index) {
            if (data.length() == index.get()) return null;
            byte pos = readPosition(data, index);
            int currentLevel = readLevel(data, index);
            if (currentLevel != level || pos != position) {
                index.set(index.get() - currentLevel - 1);
                return null;
            }
            int val = readValue(data, index);
            TreeNode node = new TreeNode(val);
            node.left = deserialize(data, level + 1, LEFT, index);
            node.right = deserialize(data, level + 1, RIGHT, index);
            return node;
        }

        // 读取当前子节点位置
        private byte readPosition(String data, AtomicInteger index) {
            int i = index.get();
            index.set(i + 1);
            if (i < data.length() && data.charAt(i) == '<') return LEFT;
            if (i < data.length() && data.charAt(i) == '>') return RIGHT;
            throw new IllegalArgumentException("wrong identify '" + data.charAt(i) + "'");
        }

        // 读取当前节点数据
        private int readValue(String str, AtomicInteger index) {
            int i = index.get();
            int n = 0;
            boolean positive = true;
            while (i < str.length() && (str.charAt(i) != '<' && str.charAt(i) != '>')) {
                if (str.charAt(i) == '-') {
                    positive = false;
                    i++;
                    continue;
                }
                n = n * 10 + (str.charAt(i) - '0');
                i++;
            }
            index.set(i);
            return positive ? n : -n;
        }

        // 读取当前节点层数
        private int readLevel(String str, AtomicInteger index) {
            int i = index.get();
            int level = 0;
            while (i < str.length() && str.charAt(i) == '*') {
                level++;
                i++;
            }
            index.set(i);
            return level;
        }
    }

}

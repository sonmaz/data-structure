import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: sonmaz
 * Date: Dec 27, 2006
 * Time: 3:06:10 PM
 * To change this template use File | Settings | File Templates.
 */

public class Huffman {

    static Node[] vec;
    static Node[] BFS;
    static Node[] ourBFS;
    static int[] array;
    static int number;
    int NUM2;
    static int maxLevel;
    static Node root;
    static boolean huffman;

    class Node {
        public Node() {
            this.left = null;
            this.right = null;
            this.baba = null;
            this.level = 0;
            this.check = 0;
            this.leaf = 0;
        }

        Node left;
        Node right;
        Node baba;
        int level;
        int key;
        int check;
        int leaf;
    }

    public void checkH(Node root2, int[] array2, int nums) {
        if(!huffman)
                return;
        int help, j;
        int f = 0;
        createBFS(root2);

        //checking Ls & Rs
        help = 0;
        for (j = root2.level; j <= maxLevel; j++) {
            int m, flag = 0;
            for (m = help; m < help + getNum(j, root2) - 1; m++) {
                if (getROL(root2, findNode(array2[m])) != getROL(root2, findNode(array2[m + 1]))) {
                    flag++;
                }
            }
            if (flag >= 2) {
                huffman = false;
                return;
            }
            help = help + getNum(j, root2);
        }

         for (j = root2.level; j <= maxLevel; j++) {
            if (getNum(j, root2) == 0) {
                f++;
            }
            help = help + getNum(j, root2);
        }
        if (f == maxLevel - root2.level)
            return;
        Node temp = root2.right;
            root2.right = root2.left;
            root2.left = temp;
        } */

        /*TODO AVALE EZAFE*/

        if (getROL(root2, findNode(array2[0]))) {
            Node temp = root2.right;
            root2.right = root2.left;
            root2.left = temp;
        }

        //check kardane left boodane avvalie bache ha dar har sath

        help = 0;
        for (j = root2.level; j <= maxLevel; j++) {
            int m, flag = 0;
            for (m = help; m < help + getNum(j, root2) - 1; m++) {  //FARSHAD: -2 KARDAM BIROON MIIZAD!!!
                if (getROL(root2, findNode(array2[m])) != getROL(root2, findNode(array2[m + 1]))) {
                    flag++;
                }
            }
            if ((flag >= 1) && (getROL(root2, findNode(array2[help]))))
            {
                huffman = false;
                return;
            }
            if ((flag < 2) || (getROL(root2, findNode(array2[help])))) {
            } else {
                huffman = false;
                return;
            }

            /*if (flag >= 2) {
               huffman = false;
           } */
            help = help + getNum(j, root2);
        }

        /*TODO AKHARE EZAFE*/

        int[] arr2 = new int[NUM2];  //right
        int[] arr3 = new int[NUM2];  //left
        int i, s2 = 0, s3 = 0;
        for (i = 0; i < nums; i++) {           // FARSHAD:FOR TA NUM2 BOOD AZ ARRAY2 MIZAD BIROON
            if (getROL(root2, findNode(array2[i]))) {
                arr2[s2] = array2[i];
                s2++;
            } else {
                arr3[s3] = array2[i];
                s3++;
            }
        }
        if ((root2.right != null) && (s2 != 0))
            checkH(root2.right, arr2, s2);
        if ((root2.left != null) && (s3 != 0))
            checkH(root2.left, arr3, s3);
    }

    //Nodi ke key aan key ast barmigardanad
    public Node findNode(int key) {
        int i;
        for (i = 0; i < number; i++) {
            if (vec[i].key == key) {
                return vec[i];
            }
        }
        return null;
    }

    //false baraie LEFT va true baraie RIGHT
    public boolean getROL(Node nodeRoot, Node nodeChild) {
        int i;
        Node temp;
        temp = nodeChild;
        for (i = 0; i < nodeChild.level - nodeRoot.level - 1; i++) {
            temp = temp.baba;
        }
        return temp != nodeRoot.left;
    }

    //tedade barg haie ba level
    public int getNum(int level, Node root2) {
        int i, num = 0;
        for (i = 0; i < number; i++) {
            if ((vec[i].level == level) && (vec[i].leaf == 1) && (getRFC(root2, vec[i])))
                num++;
        }
        return num;
    }

    //true agar nodeChild bacheie nodeRoot bashad
    public boolean getRFC(Node nodeRoot, Node nodeChild) {
        int i;
        Node temp;
        temp = nodeChild;
        for (i = 0; i < nodeChild.level - nodeRoot.level; i++) {
            temp = temp.baba;
        }
        return temp == nodeRoot;
    }

    public void createBFS(Node root2) {
        int i, j, k = 0;
        for (j = 0; j <= maxLevel; j++) {
            for (i = 0; i < number; i++) {
                if ((vec[i].level == j) && (vec[i].leaf == 1) && (getRFC(root2, vec[i]))) {
                    BFS[k] = vec[i];
                    k++;
                }
            }
        }
    }

    public void changeLevel(Node node) {
        node.level++;
        if (node.right != null)
            changeLevel(node.right);
        if (node.left != null)
            changeLevel(node.left);
    }

    public void add() {
        int i, j;
        for (i = 0; i < number; i++) {
            if (vec[i].check == 0) {
                break;
            }
        }

        for (j = i + 1; j < number; j++) {
            if (vec[j].check == 0) {
                break;
            }
        }

        Node node = new Node();
        node.key = vec[i].key + vec[j].key;
        node.left = vec[i];
        node.right = vec[j];
        vec[number] = node;

        vec[i].baba = node;
        vec[j].baba = node;
        changeLevel(vec[i]);
        changeLevel(vec[j]);
        vec[i].check = 1;
        vec[j].check = 1;

        number++;
    }

    public boolean check() {
        int i, flag = 0;
        for (i = 0; i < number; i++) {
            if (vec[i].baba == null) {
                flag++;
            }
        }

        return flag != 1;
    }

    public void sort() {
        int i, j;
        for (i = 0; i < number; i++) {
            for (j = i; j < number; j++) {
                if (vec[i].key > vec[j].key) {
                    Node temp = vec[i];
                    vec[i] = vec[j];
                    vec[j] = temp;
                }
            }
        }
    }

    public void huffman() throws IOException {
        RandomAccessFile input = new RandomAccessFile("src/input.txt", "rw");
        int NUM = Integer.parseInt(input.readLine());
        int i;
        huffman = true;
        for (i = 0; i < NUM; i++) {
            huffman = true;
            number = 0;
            NUM2 = Integer.parseInt(input.readLine());
            String str = input.readLine();
            StringTokenizer tokenizer = new StringTokenizer(str, " ");
            array = new int[NUM2];
            int j;
            for (j = 0; j < NUM2; j++) {
                tokenizer.nextToken();
                array[j] = Integer.parseInt(tokenizer.nextToken());
            }
            //Arrays.sort(array);

            vec = new Node[2 * NUM2];
            BFS = new Node[NUM2];
            ourBFS = new Node[NUM2];

            //NUM2 = tedade barg haa
            for (j = 0; j < NUM2; j++) {
                Node node = new Node();
                node.key = array[j];
                node.leaf = 1;
                vec[j] = node;
            }
            number = NUM2;
            maxLevel = 0;

            //ezafe kardane node haie jadid be vec
            while (check()) {
                sort();
                add();
            }
            root = vec[number - 1];

            for (j = 0; j < number; j++) {
                if (vec[j].level > maxLevel) {
                    maxLevel = vec[j].level;
                }
            }
            createBFS(root);
            for (j = 0; j < NUM2; j++) {
                ourBFS[j] = BFS[j];
            }

            //check (1) : check kardane dorost boodane tedade sath haa
            int help = 0, k, flag2 = 0;
            for (j = 0; j <= maxLevel; j++) {
                int m, flag = 0;
                for (k = help; k < help + getNum(j, root); k++) {
                    for (m = help; m < help + getNum(j, root); m++) {
                        if (array[k] == BFS[m].key) {
                            flag++;
                        }
                    }
                }
                if (flag == getNum(j, root)) {
                    flag2++;
                }
                help = help + getNum(j, root);
            }
            if (flag2 != maxLevel + 1) {
                huffman = false;
            }

            //check (2) : check kardane pishe ham boodane Ls & Rs
            checkH(root, array, NUM2);
            if (huffman)
                System.out.println("YES");
            else
                System.out.println("NO");
        }//end of test case
    }
}

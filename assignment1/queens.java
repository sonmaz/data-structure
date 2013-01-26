import java.io.RandomAccessFile;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: szehtabi
 * Date: Oct 8, 2006
 * Time: 2:18:26 PM
 * To change this template use File | Settings | File Templates.
 */

class chess {
    static int queens = 0;
    static int answers = 0;
    static int answers2 = 0;

    public static void main(String[] args) throws IOException {
        RandomAccessFile input, output;
        input = new RandomAccessFile("src/queen.in", "rw");
        output = new RandomAccessFile("src/queen.out", "rw");
        int m = Integer.parseInt(input.readLine());
        int x;
        for (x = 0; x < m; x++) {
            int n;
            n = Integer.parseInt(input.readLine());
            answers = 0;
            answers2 = 0;
            queens = 0;
            int [][] arr;
            arr = new int[n][n];
            int i, j;
            for (i = 0; i < n; i++)
                for (j = 0; j < n; j++)
                    arr[i][j] = 0; // empty squares

            float start = System.nanoTime();
            if (n % 2 == 0) {
                for (j = 0; j < n / 2; j++) {
                    queens++;
                    int k;
                    i = 0;
                    k = 1;
                    while ((i + k != n) && (j + k != n)) {
                        arr[i + k][j + k]++;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j + k != -1)) {
                        arr[i + k][j + k]++;
                        k--;
                    }
                    k = 1;
                    while ((i + k != n) && (j - k != -1)) {
                        arr[i + k][j - k]++;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j - k != n)) {
                        arr[i + k][j - k]++;
                        k--;
                    }
                    for (k = 0; k < n; k++)
                        arr[i][k]++;
                    for (k = 0; k < n; k++)
                        arr[k][j]++;
                    arr[i][j] = 20; 

                    fill(n, 1, arr);

                    k = 1;
                    while ((i + k != n) && (j + k != n)) {
                        arr[i + k][j + k]--;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j + k != -1)) {
                        arr[i + k][j + k]--;
                        k--;
                    }
                    k = 1;
                    while ((i + k != n) && (j - k != -1)) {
                        arr[i + k][j - k]--;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j - k != n)) {
                        arr[i + k][j - k]--;
                        k--;
                    }
                    for (k = 0; k < n; k++)
                        arr[i][k]--;
                    for (k = 0; k < n; k++)
                        arr[k][j]--;

                    arr[i][j] = 0;

                    queens--;
                }
                answers = answers * 2;

            } else {
                for (j = 0; j < (n / 2) + 1; j++) {
                    queens++;
                    int k;
                    i = 0;
                    k = 1;
                    while ((i + k != n) && (j + k != n)) {
                        arr[i + k][j + k]++;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j + k != -1)) {
                        arr[i + k][j + k]++;
                        k--;
                    }
                    k = 1;
                    while ((i + k != n) && (j - k != -1)) {
                        arr[i + k][j - k]++;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j - k != n)) {
                        arr[i + k][j - k]++;
                        k--;
                    }
                    for (k = 0; k < n; k++)
                        arr[i][k]++;
                    for (k = 0; k < n; k++)
                        arr[k][j]++;
                    arr[i][j] = 20; 

                    if (n != 1)
                        fill(n, 1, arr);
                    else
                        fill(n, 0, arr);

                    k = 1;
                    while ((i + k != n) && (j + k != n)) {
                        arr[i + k][j + k]--;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j + k != -1)) {
                        arr[i + k][j + k]--;
                        k--;
                    }
                    k = 1;
                    while ((i + k != n) && (j - k != -1)) {
                        arr[i + k][j - k]--;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j - k != n)) {
                        arr[i + k][j - k]--;
                        k--;
                    }
                    for (k = 0; k < n; k++)
                        arr[i][k]--;
                    for (k = 0; k < n; k++)
                        arr[k][j]--;

                    arr[i][j] = 0;

                    if ((j + 1) == n / 2)
                        answers2 = answers;
                    queens--;
                }
                answers = answers + answers2;
            }
            float finish = System.nanoTime();
        }
    }

    static void fill(int n, int i, int [][] arr) {
        int j;
        for (j = 0; j < n; j++) {
            if (arr[i][j] == 0) {
                queens++;
                int k;

                //hazf kardane khanehaei ke tavasote vazir tahdid mishavand
                k = 1;
                while ((i + k != n) && (j + k != n)) {
                    arr[i + k][j + k]++;
                    k++;
                }
                k = -1;
                while ((i + k != -1) && (j + k != -1)) {
                    arr[i + k][j + k]++;
                    k--;
                }
                k = 1;
                while ((i + k != n) && (j - k != -1)) {
                    arr[i + k][j - k]++;
                    k++;
                }
                k = -1;
                while ((i + k != -1) && (j - k != n)) {
                    arr[i + k][j - k]++;
                    k--;
                }
                for (k = 0; k < n; k++)
                    arr[i][k]++;
                for (k = 0; k < n; k++)
                    arr[k][j]++;

                arr[i][j] = 20;
                if (queens != n) {
                    fill(n, i + 1, arr);
                    k = 1;
                    while ((i + k != n) && (j + k != n)) {
                        arr[i + k][j + k]--;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j + k != -1)) {
                        arr[i + k][j + k]--;
                        k--;
                    }
                    k = 1;
                    while ((i + k != n) && (j - k != -1)) {
                        arr[i + k][j - k]--;
                        k++;
                    }
                    k = -1;
                    while ((i + k != -1) && (j - k != n)) {
                        arr[i + k][j - k]--;
                        k--;
                    }
                    for (k = 0; k < n; k++)
                        arr[i][k]--;
                    for (k = 0; k < n; k++)
                        arr[k][j]--;

                    arr[i][j] = 0;
                    queens--;
                }
            }

            if (queens == n) {
                answers++;
                int k;
                k = 1;
                while ((i + k != n) && (j + k != n)) {
                    arr[i + k][j + k]--;
                    k++;
                }
                k = -1;
                while ((i + k != -1) && (j + k != -1)) {
                    arr[i + k][j + k]--;
                    k--;
                }
                k = 1;
                while ((i + k != n) && (j - k != -1)) {
                    arr[i + k][j - k]--;
                    k++;
                }
                k = -1;
                while ((i + k != -1) && (j - k != n)) {
                    arr[i + k][j - k]--;
                    k--;
                }
                for (k = 0; k < n; k++)
                    arr[i][k]--;
                for (k = 0; k < n; k++)
                    arr[k][j]--;

                arr[i][j] = 0;
                queens--;
            }
        }
    }
}
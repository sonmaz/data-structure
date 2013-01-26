import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: sonmaz
 * Date: Nov 26, 2006
 * Time: 2:56:52 PM
 * To change this template use File | Settings |
 * File Templates.
 */
public class Parking {
    public static void main(String[] args) throws IOException {
        RandomAccessFile input, output;
        input = new RandomAccessFile("src/parking.in", "rw");
        output = new RandomAccessFile("src/parking.out", "rw");
        String n = "sonmaz";
        int max = 100;
        int current = 0;
        float array[] = new float[max];
        float numbers[] = new float[max];
        numbers[1] = 0;
        numbers[0] = 0;
        array[1] = 0;
        array[0] = 0;

        int time = 0;
        while (n != null) {
            n = input.readLine();
            if (n != null) {
                char temp;
                temp = n.charAt(n.length() - 1);
                if (temp == ':') {
                    StringTokenizer tokenizer = new StringTokenizer(n, ":");
                    time = Integer.parseInt(tokenizer.nextToken());
                } else {
                    temp = n.charAt(0);
                    if (temp == 'F') {
                        StringTokenizer tokenizer = new StringTokenizer(n, " ");
                        String temp1;
                        int free_cars;
                        temp1 = tokenizer.nextToken();
                        free_cars = Integer.parseInt(tokenizer.nextToken());
                        int index;
                        output.writeBytes(time + ":\n");
                        for (index = 0; index < free_cars; index++) {
                            output.writeBytes(numbers[1] + "\n");
                            int current2 = 1;
                            array[1] = array[current];
                            numbers[1] = numbers[current];
                            current--;
                            int flag = 1;
                            while (flag == 1) {
                                if ((array[current2] < array[current2 * 2]) || (array[current2] < array[current2 * 2 + 1])) {
                                    if (array[current2 * 2] > array[current2 * 2 + 1]) {
                                        if (current2 < 2 * current) {
                                            float temp3 = array[current2];
                                            array[current2] = array[current2 * 2];
                                            array[current2 * 2] = temp3;
                                            //swap num
                                            float temp2 = numbers[current2];
                                            numbers[current2] = numbers[current2 * 2];
                                            numbers[current2 * 2] = temp2;
                                            current2 = current2 * 2;
                                        } else
                                            flag = 0;
                                    } else {
                                        if (current2 < 2 * current) {
                                            
                                            float temp3 = array[current2];
                                            array[current2] = array[current2 * 2 + 1];
                                            array[current2 * 2 + 1] = temp3;
                                            //swap num
                                            float temp2 = numbers[current2];
                                            numbers[current2] = numbers[current2 * 2 + 1];
                                            numbers[current2 * 2 + 1] = temp2;
                                            current2 = current2 * 2;
                                        } else
                                            flag = 0;
                                    }
                                } else
                                    flag = 0;
                            }
                        }
                    } else {
                        float imp, pri, number;
                        StringTokenizer tokenizer = new StringTokenizer(n, " ");
                        number = Float.parseFloat(tokenizer.nextToken());
                        imp = Float.parseFloat(tokenizer.nextToken());
                        pri = imp * 2 - time;
                        if (array[1] == 0) {
                            numbers[1] = number;
                            array[1] = pri;
                            current = 1;
                        } else {
                            if (current >= max) {
                                float array2[] = new float[max * 2];
                                float numbers2[] = new float[max * 2];
                                int i;
                                for (i = 0; i < max; i++) {
                                    array2[i] = array[i];
                                    numbers2[i] = numbers[i];
                                }
                                array = array2;
                                numbers = numbers2;
                            }
                            current++;
                            numbers[current] = number;
                            array[current] = pri;
                            int current2 = current;
                            int flag = 1;
                            while (flag == 1) {
                                if (array[current2] > array[current2 / 2]) {
                                    if (current2 != 1) {
                                        //swape pri
                                        float temp1 = array[current2];
                                        array[current2] = array[current2 / 2];
                                        array[current2 / 2] = temp1;
                                        //swap num
                                        float temp2 = numbers[current2];
                                        numbers[current2] = numbers[current2 / 2];
                                        numbers[current2 / 2] = temp2;
                                        current2 = current2 / 2;
                                    } else
                                        flag = 0;
                                } else
                                    flag = 0;
                            }
                        }
                    }
                }
            }
        }
    }
}

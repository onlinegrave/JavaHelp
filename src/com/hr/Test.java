package com.hr;

import com.hr.temporary.WSystem;
import sun.tools.jconsole.OutputViewer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import static com.hr.temporary.WSystem.println;

public class Test {

    public static void main(String[] args) {
        if (System.getProperty("jconsole.showOutputViewer") != null) {
            OutputViewer.init();
        }
        try(PrintStream printStream = new PrintStream(new OutputStream() {

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                if (b == null) {
                    throw new NullPointerException();
                } else if (off < 0 || off > b.length || len < 0 || (off + len) > b.length || (off + len) < 0) {
                    throw new IndexOutOfBoundsException();
                } else if (len == 0) {
                    return;
                }
            }

            @Override
            public void write(int b) throws IOException {
                write(new byte[]{(byte) b}, 0, 1);
            }

            @Override
            public void flush() throws IOException {
            }
        })) {
            printStream.println("DEFAULTER");
            println("What");
        }

        int[] numbers = new int[]{1, 2, 2, 2, 2, 2, 3, 3};

        System.out.println(Arrays.toString(range(numbers, 2)));

        int[] n1 = new int[]{2};
        System.out.println(Arrays.toString(range(n1, 2)));

        int[] n2 = new int[]{2, 2};
        System.out.println(Arrays.toString(range(n2, 2)));

        int[] n3 = new int[100_000_00];
        final Random random = new Random();
        for (int i = 0; i < 100_000_00; i++) {
            int n = random.nextInt(4);
            n3[i] = n;
//            if(n==2) {
//                System.out.println()
//            }
        }
        Arrays.sort(n3);



        int[] result = range(n3, 2);
        System.out.println(Arrays.toString(result));
        System.out.println(n3[result[0]]);
        System.out.println(n3[result[1]]);
        System.out.println(n3[result[0]-1]);
        System.out.println(n3[result[1]+1]);

    }

    static int[] range(int[] arr, int n) {
        int mid = binSearch(arr, n);
        if (mid == -1) return new int[]{0, 0};

        int lowerBound = mid;
        while (true) {
            int tlb = binSearch(Arrays.copyOfRange(arr, 0, lowerBound), n);
            if (tlb != -1) {
                lowerBound = tlb;
            } else {
                break;
            }
        }

        int upperBound = mid;
        while (true) {
            int tlb = binSearch(Arrays.copyOfRange(arr, upperBound + 1, arr.length), n);
            if (tlb != -1) {
                upperBound += tlb + 1;
            } else {
                break;
            }
        }
        return new int[]{lowerBound, upperBound};
    }

    static int binSearch(int[] arr, int x) {
        return binSearch(arr, 0, arr.length - 1, x);
    }

    static int binSearch(int[] arr, int first, int last, int x) {
        if (last >= first) {
            int mid = first + (last - first) / 2;

            if (arr[mid] == x)
                return mid;

            if (arr[mid] > x)
                return binSearch(arr, first, mid - 1, x);

            return binSearch(arr, mid + 1, last, x);
        }

        return -1;
    }
}

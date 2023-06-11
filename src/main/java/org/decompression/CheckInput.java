package org.decompression;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CheckInput {
    short leaves;
    byte  flag;
    byte last;
    byte  sum;
    boolean encryption;
    byte compLevel;
    final RandomAccessFile input;
    int sumInt;
    public boolean ifCheckIsCorrect = true;
    public CheckInput(RandomAccessFile file) throws IOException {
        this.input = file;

        file.seek(2);
        sum = file.readByte();
        sum ^= 0xFF;
        sumInt = sum;
        sumInt ^= 0b11111111;
        if(checkSum() != 1) {
            this.ifCheckIsCorrect = false;
        } else {
            file.seek(3);

            flag = file.readByte();
            byte tmp1 = file.readByte();
            byte tmp2 = file.readByte();
            leaves <<= 8;
            leaves += tmp2;
            leaves <<= 8;
            leaves += tmp1;
            checkFlag();
        }
    }
    public static boolean isEncryptRequired(String fileName) throws IOException {
            RandomAccessFile file = new RandomAccessFile(fileName, "r");
            byte maskEncrypt = 0b00100000;
            byte flag;
            file.seek(3);
            flag = file.readByte();
            int tmp = flag & maskEncrypt;
            tmp>>=5;
            file.close();
            return tmp == 1;
    }
    private void checkFlag() {
        byte maskSzyfr = 0b00100000;
        byte maskMask =  0b00001111;
        byte maskComp =  (byte)0b11000000;
        int tmp = flag & maskSzyfr;
        tmp >>= 5;
        encryption = tmp == 1;
        tmp = flag & maskComp;
        tmp >>= 6;
        this.compLevel = (byte)tmp; //1 - 8bit, 2 - 12bit, 3 - 16bit
        tmp = flag & maskMask;
        this.last = (byte)tmp;
    }
    private int checkSum() throws IOException {
        long cntr = 0;
        input.seek(0);
        try {
            long len = input.length();
            for (int i = 0; i < 2; i++) {
                sumInt = input.readByte() ^ sumInt;
                sumInt ^= 0xFF;
                sumInt ^= 0b11111111;
                System.out.println(sumInt);
                cntr++;
            }
            input.seek(input.getFilePointer() + 1);

            for(cntr = 3; cntr < len; cntr++) {
                sumInt = input.readByte() ^ sumInt;
                sumInt ^= 0xFF;
                sumInt ^= 0b11111111;
            }
            sumInt &= 0xFF;
        } catch (IOException e) {
            throw new IOException("Warning! There was an I/O error while checking input file integrity.");
        }
        System.out.println(sumInt);
        System.out.println(cntr);
        if(sumInt == 69) {
            return 1;
        } else {
            return 0;
        }

    }
}

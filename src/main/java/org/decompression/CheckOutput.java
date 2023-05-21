package org.decompression;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CheckOutput {
    short leaves;
    byte  flag;
    byte last;
    byte  sum;
    boolean encryption;
    byte compLevel;
    RandomAccessFile input;
    int sumInt;
    public CheckOutput(RandomAccessFile file) throws IOException {
        this.input = file;

        file.seek(2);
        sum = file.readByte();
        sum ^= 0xFF;
        sumInt = sum;
        sumInt ^= 0b11111111;
        if(checkSum() != 1) {
            System.out.println("File error!");
            System.exit(-1);
        } else {
            file.seek(3);
        }
        flag = file.readByte();
        byte tmp1 = file.readByte();
        byte tmp2 = file.readByte();
        leaves <<= 8;
        leaves += tmp2;
        leaves <<= 8;
        leaves += tmp1;
        checkFlag();
    }
    public void checkFlag() {
        byte maskSzyfr = 0b00100000;
        byte maskMask =  0b00001111;
        byte maskComp =  (byte)0b11000000;
        int tmp = flag & maskSzyfr;
        tmp >>= 5;
        if(tmp == 1) {
            encryption = true;
        } else {
            encryption = false;
        }
        tmp = flag & maskComp;
        tmp >>= 6;
        this.compLevel = (byte)tmp; //1 - 8bit, 2 - 12bit, 3 - 16bit
        tmp = flag & maskMask;
        this.last = (byte)tmp;
    }
    public int checkSum() throws IOException {
        int x ;
        int cntr = 0;
        input.seek(0);
        for (int i = 0; i < 2; i++) {
            if((x = input.readByte()) != -1) {
                sumInt = x ^ sumInt;
                sumInt ^= 0xFF;
                sumInt ^= 0b11111111;
                System.out.println(sumInt);
                cntr++;
            } else {
                System.out.println("There was an error while reading the file. Aborting.");
                return -1;
            }
        }
        input.seek(input.getFilePointer() + 1);
        try {
           byte [] bajty = new byte[(int)(input.length())-3];
           input.readFully(bajty);
            /*
            while((x = input.readByte()) != -1) {
                sumInt = x ^ sumInt;
                sumInt ^= 0xFF;
                sumInt ^= 0b11111111;
                System.out.println(sumInt);
                cntr++;
            }
            */

            for(cntr = 3; cntr < input.length(); cntr++) {
                sumInt = bajty[cntr-3] ^ sumInt;
                sumInt ^= 0xFF;
                sumInt ^= 0b11111111;
                System.out.println(sumInt);
            }
            sumInt &= 0xFF;
        } catch (EOFException e) {
            sumInt &= 0b11111111;
        }
        // do usunięcia
        System.out.println(sumInt);
        System.out.println(cntr);
        if(sumInt == 69) {
            return 1;
        } else {
            return 0;
        }

    }
}

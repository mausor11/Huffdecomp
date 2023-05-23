package org.decompression;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Decompression extends Menu{
    public Decompression () throws Exception {
        super();
        this.input = new RandomAccessFile(super.inputFile, "rw");
        this.output = new RandomAccessFile(super.outputFile, "rw");
        Tree tree = new Tree(input, output);
        this.encryption = tree.encryption;

        if(this.encryption) {
            super.passwordRequired();
            XOR();
        }
        this.tree = tree;
        this.tree.readtree(input);
        this.cntr = tree.cntr;
        this.union = tree.union;
        this.input = tree.input;
        this.compLevel = tree.compLevel;
    }
    RandomAccessFile input;
    RandomAccessFile output;
    int cntr;
    int compLevel;
    boolean encryption;
    Union union;
    Tree tree;
    public void decode() throws IOException{
        if(compLevel == 1) {
            tree.decodefile();
            this.output.close();
            this.input.close();
        } else {
            System.out.println("Decompressor works only with 8-bit compressed files!");
        }
    }
    public void XOR() throws IOException {
        input.seek(6);
        short haslo = 0;
        for (int i = 0; i < password.length(); i++) {
            haslo ^= password.charAt(i);
        }
        byte tmp;
        //todo: to jest do poprawy (nie podoba mi sie to)
        long fileLength = input.length() - 6;
        long y;
        input.seek(6);
        try {
            for (y = 0; y < fileLength; y++) {
                //input.seek(6 + y)
                tmp = input.readByte();
                tmp ^= haslo;
                input.seek(input.getFilePointer() - 1);
                input.writeByte(tmp);
            }
        }
        catch(IOException e) {
            throw new IOException("Warning! There was an I/O error while decoding the file.");
        }
        input.seek(2);
    }

}

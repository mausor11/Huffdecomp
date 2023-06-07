package org.decompression;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Decompression {
    public Decompression (String inputFile, String outputFile, String password) throws Exception {
        this.input = new RandomAccessFile(inputFile, "rw");
        this.output = new RandomAccessFile(outputFile, "rw");
        this.password = password;
        Tree tree = new Tree(input, output);
        this.encryption = tree.encryption;

        if(this.encryption) {
           XOR();
        }
        this.tree = tree;
        this.tree.readtree(input);
        this.input = tree.input;
        this.compLevel = tree.compLevel;
    }
    RandomAccessFile input;
    RandomAccessFile output;
    String password;
    int compLevel;
    boolean encryption;
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
    private void XOR() throws IOException {
        input.seek(6);
        short haslo = 0;
        for (int i = 0; i < password.length(); i++) {
            haslo ^= password.charAt(i);
        }
        byte tmp;
        long fileLength = input.length() - 6;
        long y;
        input.seek(6);
        try {
            for (y = 0; y < fileLength; y++) {
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

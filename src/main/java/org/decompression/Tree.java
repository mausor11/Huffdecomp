package org.decompression;

import java.io.IOException;
import java.io.RandomAccessFile;
public class Tree{
    public Tree(RandomAccessFile file, RandomAccessFile output) throws IOException {
        CheckInput check = new CheckInput(file);
        this.union = new Union();
        this.leaves = check.leaves;
        this.ifCheckIsCorrect = check.ifCheckIsCorrect;
        this.cntr = 8;
        this.output = output;
        this.last = check.last;
        this.encryption = check.encryption;
        this.compLevel = check.compLevel;
    }
    public boolean ifCheckIsCorrect = true;
    short leaves;
    byte last;
    boolean encryption;
    int compLevel;
    RandomAccessFile output;
    RandomAccessFile input;
    Node root;
    Node lastroot;
    Union union;
    int cntr;
    //int indexTree;
    public Tree() {
    }
    public void readtree(RandomAccessFile file) throws IOException{
        file.seek(6);
        union.A = file.readByte();
        union.B = file.readByte();
        this.root = readTreeFromFile(file);
        this.input = file;
    }
    private Node readTreeFromFile(RandomAccessFile file) throws IOException{
        if(leaves != 0) {
            Node nroot = new Node();
            int currentBit;
            if(cntr == 0) {
                union.B = file.readByte();
                union.B &= 0b11111111;
                cntr+= 8;
            }

            currentBit = Union.bit(union.A, 7);
            union.A <<= 1;
            union.A &= 0b11111111;
            int tmp2 = Union.bit(union.B, 7);
            union.B <<= 1;
            union.B &= 0b11111111;
            union.A += tmp2;
            cntr--;
            if(currentBit == 0) {
                nroot.left = readTreeFromFile(file);
                nroot.right = readTreeFromFile(file);
            }
            else {
                nroot.sign = union.A;
                nroot.counter = -1;
                leaves--;

                union.moveBits(cntr);

                union.B = file.readByte();

                union.moveBits(8-cntr);
            }
            return nroot;

        }
        return null;

    }
    public void decodefile() throws IOException {
        lastroot = decodeFile(root);
        last += cntr;
        lastBytes();
    }
    private Node decodeFile(Node root) throws IOException{
        int number, i;
        byte received, cahr = 0;
        byte[] buf = new byte[100];
        Node nroot = root;
        while((number = input.read(buf)) != -1) {
            i = 0;
            while(i < number) {
                cahr = 0;
                while(cahr == 0) {
                    if(this.cntr == 0) {
                        if(i < number) {
                            int tmp = buf[i];
                            tmp &= 0xFF;
                            tmp &= 0b11111111;
                            union.B = (byte)tmp;
                            i++;
                            this.cntr += 8;
                        }
                        else {
                            break;
                        }
                    }
                    nroot = decode(nroot);
                    if(nroot.counter != 0) {
                        received = nroot.sign;
                        cahr = received;
                        output.writeByte(cahr);
                        nroot = root;
                    }
                }
            }
        }
        if(cahr != 0) {
            nroot = root;
        }
        return nroot;

    }
    private Node decode(Node root) {
        Node nroot = root;
        while(this.cntr != 0) {
            if(Union.bit(union.A, 7) == 0) {
                union.moveBits(1);

                this.cntr--;
                nroot = nroot.left;
            } else {
                union.moveBits(1);

                this.cntr--;
                nroot = nroot.right;
            }
            if(nroot.counter != 0) {
                return nroot;
            }
        }
        return nroot;
    }
    private void lastBytes() throws IOException {
        while(last != 0) {
            if(lastroot.counter != 0) {
                output.writeByte(lastroot.sign);
                lastroot = root;
            }
            if(Union.bit(union.A, 7) == 0) {
                union.moveBits(1);

                last--;
                lastroot = lastroot.left;
            } else {
                union.moveBits(1);

                last--;
                lastroot = lastroot.right;
            }
        }
        output.writeByte(lastroot.sign);
    }





}

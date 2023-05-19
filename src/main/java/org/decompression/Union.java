package org.decompression;

public class Union {
    byte B;
    byte A;
    public Union() {
        B = 0;
        A = 0;
    }
    public static int bit(byte resource, int which) {
        return (resource >> which) & 1;
    }
    public static byte bits(byte b, int numBits) {
        int mask = (1 << numBits) - 1;
        int shift = Byte.SIZE - numBits;
        return (byte) ((b & (mask << shift)) >>> shift);
    }
    public void moveBits(int many) {
        if(many == 1) {
            this.A <<= 1;
            this.A &= 0b11111111;
            int tmp2 = Union.bit(this.B, 7);
            this.B <<= 1;
            this.B &= 0b11111111;
            this.A += tmp2;
        } else {
            this.A <<= many;
            this.A &= 0b11111111;
            int tmp = Union.bits(this.B, many);
            this.B <<= many;
            this.B &= 0b11111111;
            this.A += tmp;
        }

    }
}

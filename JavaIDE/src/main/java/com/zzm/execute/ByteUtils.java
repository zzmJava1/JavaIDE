package com.zzm.execute;

public class ByteUtils {

    /**
     * byte类型的数字要&0xff再赋值给int类型，其本质原因就是想保持二进制补码的一致性。

     当byte要转化为int的时候，高的24位必然会补1，这样，其
     二进制补码其实已经不一致了，&0xff可以将高的24位置为0，
     低8位保持原样。这样做的目的就是为了保证二进制数据的一致性。
     * @param b
     * @param start
     * @param len
     * @return
     */
    public static int byte2Int(byte[] b, int start, int len) {
        int res = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int cur = ((int) b[i]) & 0xff;
            cur <<= (--len) * 8;//表示将cur左移8位
            res += cur;
        }
        return res;
    }

    public static byte[] int2Byte(int num, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((num >> (8 * i)) & 0xff);
        }
        return b;
    }

    public static String byte2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    public static byte[] string2Byte(String str) {
        return str.getBytes();
    }

    public static byte[] byteReplace(byte[] oldBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[oldBytes.length + replaceBytes.length - len];
        System.arraycopy(oldBytes, 0, newBytes, 0, offset);
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
        System.arraycopy(oldBytes, offset + len, newBytes, offset + replaceBytes.length,
                oldBytes.length - offset - len);
        return newBytes;
    }
}

package com.zqh.rxjava.commutilslibrary;

import android.util.Log;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Des3Utils {

    public static byte[] iv_bytes = {0, 0, 0, 0, 0, 0, 0, 0};

    public byte[] DesCbcEncode(byte[] key_buffer, byte[] init_val,
                               byte[] org_buffer, int org_bytes) {
        SecretKey key = null;
        SecretKeyFactory keyFactory = null;
        SecretKeySpec keyspec = null;
        Cipher cipher = null;
        DESKeySpec deskeyspec;

        byte[] my_key_buffer = new byte[8];
        byte[] my_init_buffer = new byte[8];
        byte[] my_des_buffer = null;
        byte[] my_org_buffer = new byte[org_bytes];

        if (org_buffer.length < org_bytes)
            return null;
        System.arraycopy(org_buffer, 0, my_org_buffer, 0, org_bytes);

        if (init_val.length != 8)
            return null;
        System.arraycopy(init_val, 0, my_init_buffer, 0, 8);

        if (key_buffer.length != 8)
            return null;
        System.arraycopy(key_buffer, 0, my_key_buffer, 0, 8);

        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 某些java平台使用此方案
        // deskeyspec = new DESKeySpec(my_key_buffer);
        // 某些java平台使用次方案
        keyspec = new SecretKeySpec(my_key_buffer, "DES");

        try {
            // key = keyFactory.generateSecret(deskeyspec);
            key = keyFactory.generateSecret(keyspec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        IvParameterSpec iv_spec = new IvParameterSpec(my_init_buffer);
        try {
            cipher = Cipher.getInstance("DES/CBC/NOPadding");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 加密初始化
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv_spec);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } // ECB中无参数iv_spec
        // 执行加密
        try {
            my_des_buffer = cipher.doFinal(my_org_buffer);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return my_des_buffer;
    }

    public byte[] DesCbcDecode(byte[] key_buffer, byte[] init_val,
                               byte[] org_buffer, int org_bytes) {
        SecretKey key = null;
        SecretKeyFactory keyFactory = null;
        SecretKeySpec keyspec = null;
        Cipher cipher = null;
        DESKeySpec deskeyspec;

        byte[] my_key_buffer = new byte[8];
        byte[] my_init_buffer = new byte[8];
        byte[] my_des_buffer = null;
        byte[] my_org_buffer = new byte[org_bytes];

        if (org_buffer.length < org_bytes)
            return null;
        System.arraycopy(org_buffer, 0, my_org_buffer, 0, org_bytes);
        if (init_val.length != 8)
            return null;
        System.arraycopy(init_val, 0, my_init_buffer, 0, 8);
        if (key_buffer.length != 8)
            return null;
        System.arraycopy(key_buffer, 0, my_key_buffer, 0, 8);
        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 某些java平台使用此方案
        // deskeyspec = new DESKeySpec(my_key_buffer);
        // 某些java平台使用次方案
        keyspec = new SecretKeySpec(my_key_buffer, "DES");

        try {
            // key = keyFactory.generateSecret(deskeyspec);
            key = keyFactory.generateSecret(keyspec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        IvParameterSpec iv_spec = new IvParameterSpec(my_init_buffer);
        try {
            cipher = Cipher.getInstance("DES/CBC/NOPadding");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 解密初始化
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, iv_spec);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 执行加密
        try {
            my_des_buffer = cipher.doFinal(my_org_buffer);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return my_des_buffer;
    }

    public byte[] DesEcbEncode(byte[] key_buffer, byte[] org_buffer,
                               int org_bytes) {
        SecretKey key = null;
        SecretKeyFactory keyFactory = null;
        SecretKeySpec keyspec = null;
        Cipher cipher = null;
        DESKeySpec deskeyspec;
        byte[] my_key_buffer = new byte[8];
        byte[] my_des_buffer = null;
        byte[] my_org_buffer = new byte[org_bytes];

        if (org_buffer.length < org_bytes)
            return null;
        System.arraycopy(org_buffer, 0, my_org_buffer, 0, org_bytes);

        if (key_buffer.length != 8)
            return null;
        System.arraycopy(key_buffer, 0, my_key_buffer, 0, 8);

        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 某些java平台使用此方案
        // deskeyspec = new DESKeySpec(my_key_buffer);
        // 某些java平台使用次方案
        keyspec = new SecretKeySpec(my_key_buffer, "DES");

        try {
            // key = keyFactory.generateSecret(deskeyspec);
            key = keyFactory.generateSecret(keyspec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        try {
            cipher = Cipher.getInstance("DES/ECB/NOPadding");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 加密初始化
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        // 执行加密
        try {
            my_des_buffer = cipher.doFinal(my_org_buffer);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return my_des_buffer;
    }

    public byte[] DesEcbDecode(byte[] key_buffer, byte[] org_buffer,
                               int org_bytes) {
        SecretKey key = null;
        SecretKeyFactory keyFactory = null;
        SecretKeySpec keyspec = null;
        Cipher cipher = null;
        DESKeySpec deskeyspec;
        byte[] my_key_buffer = new byte[8];
        byte[] my_des_buffer = null;
        byte[] my_org_buffer = new byte[org_bytes];

        if (org_buffer.length < org_bytes)
            return null;
        System.arraycopy(org_buffer, 0, my_org_buffer, 0, org_bytes);

        if (key_buffer.length != 8)
            return null;
        System.arraycopy(key_buffer, 0, my_key_buffer, 0, 8);

        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 某些java平台使用此方案
        // deskeyspec = new DESKeySpec(my_key_buffer);
        // 某些java平台使用次方案
        keyspec = new SecretKeySpec(my_key_buffer, "DES");

        try {
            // key = keyFactory.generateSecret(deskeyspec);
            key = keyFactory.generateSecret(keyspec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        try {
            cipher = Cipher.getInstance("DES/ECB/NOPadding");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 解密初始化
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        // 执行加密
        try {
            my_des_buffer = cipher.doFinal(my_org_buffer);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return my_des_buffer;
    }

    public static byte[] TrippleDesCbcEncode(byte[] key_buffer,
                                             byte[] init_val, byte[] org_buffer, int org_bytes) {
        SecretKey key = null;
        SecretKeyFactory keyFactory = null;
        SecretKeySpec keyspec = null;
        Cipher cipher = null;
        DESedeKeySpec desedekeyspec;

        byte[] my_key_buffer = new byte[24];
        byte[] my_init_buffer = new byte[8];
        byte[] my_des_buffer = null;
        byte[] my_org_buffer = new byte[org_bytes];

        if (org_buffer.length < org_bytes)
            return null;
        System.arraycopy(org_buffer, 0, my_org_buffer, 0, org_bytes);

        if (init_val.length != 8)
            return null;
        System.arraycopy(init_val, 0, my_init_buffer, 0, 8);

        if (key_buffer.length < 16)
            return null;
        System.arraycopy(key_buffer, 0, my_key_buffer, 0, 16);
        if (key_buffer.length != 24) {
            System.arraycopy(key_buffer, 0, my_key_buffer, 16, 8);
        }
        try {
            keyFactory = SecretKeyFactory.getInstance("DESede");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 某些java平台使用此方案
        // desedekeyspec = new DESedeKeySpec(my_key_buffer);
        // 某些java平台使用次方案
        keyspec = new SecretKeySpec(my_key_buffer, "DESede");
        try {
            // key = keyFactory.generateSecret(desedekeyspec);
            key = keyFactory.generateSecret(keyspec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        IvParameterSpec iv_spec = new IvParameterSpec(my_init_buffer);
        try {
            cipher = Cipher.getInstance("DESede/CBC/NOPadding");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 加密初始化
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv_spec);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } // ECB中无参数iv_spec
        // 执行加密
        try {
            my_des_buffer = cipher.doFinal(my_org_buffer);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return my_des_buffer;
    }

    public static byte[] TrippleDesCbcDecode(byte[] key_buffer,
                                             byte[] init_val, byte[] org_buffer, int org_bytes) {
        SecretKey key = null;
        SecretKeyFactory keyFactory = null;
        SecretKeySpec keyspec = null;
        Cipher cipher = null;
        DESedeKeySpec desedekeyspec;

        byte[] my_key_buffer = new byte[24];
        byte[] my_init_buffer = new byte[8];
        byte[] my_des_buffer = null;
        byte[] my_org_buffer = new byte[org_bytes];

        if (org_buffer.length < org_bytes)
            return null;
        System.arraycopy(org_buffer, 0, my_org_buffer, 0, org_bytes);

        if (init_val.length != 8)
            return null;
        System.arraycopy(init_val, 0, my_init_buffer, 0, 8);
        if (key_buffer.length < 16)
            return null;
        System.arraycopy(key_buffer, 0, my_key_buffer, 0, 16);
        if (key_buffer.length != 24) {
            System.arraycopy(key_buffer, 0, my_key_buffer, 16, 8);
        }
        try {
            keyFactory = SecretKeyFactory.getInstance("DESede");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 某些java平台使用此方案
        // desedekeyspec = new DESedeKeySpec(my_key_buffer);
        // 某些java平台使用次方案
        keyspec = new SecretKeySpec(my_key_buffer, "DESede");
        try {
            // key = keyFactory.generateSecret(desedekeyspec);
            key = keyFactory.generateSecret(keyspec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        IvParameterSpec iv_spec = new IvParameterSpec(my_init_buffer);
        try {
            cipher = Cipher.getInstance("DESede/CBC/NOPadding");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 解密初始化
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, iv_spec);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } // ECB中无参数iv_spec
        // 执行加密
        try {
            my_des_buffer = cipher.doFinal(my_org_buffer);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return my_des_buffer;
    }

    public byte[] TrippleDesEcbEncode(byte[] key_buffer, byte[] org_buffer,
                                      int org_bytes) {
        SecretKey key = null;
        SecretKeyFactory keyFactory = null;
        SecretKeySpec keyspec = null;
        Cipher cipher = null;
        DESedeKeySpec desedekeyspec;
        byte[] my_key_buffer = new byte[24];
        byte[] my_des_buffer = null;
        byte[] my_org_buffer = new byte[org_bytes];

        if (org_buffer.length < org_bytes)
            return null;
        System.arraycopy(org_buffer, 0, my_org_buffer, 0, org_bytes);

        if (key_buffer.length < 16)
            return null;
        System.arraycopy(key_buffer, 0, my_key_buffer, 0, 16);
        if (key_buffer.length != 24) {
            System.arraycopy(key_buffer, 0, my_key_buffer, 16, 8);
        }
        try {
            keyFactory = SecretKeyFactory.getInstance("DESede");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 某些java平台使用此方案
        // desedekeyspec = new DESedeKeySpec(my_key_buffer);
        // 某些java平台使用次方案
        keyspec = new SecretKeySpec(my_key_buffer, "DESede");
        try {
            // key = keyFactory.generateSecret(desedekeyspec);
            key = keyFactory.generateSecret(keyspec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        try {
            cipher = Cipher.getInstance("DESede/ECB/NOPadding");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 加密初始化
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 执行加密
        try {
            my_des_buffer = cipher.doFinal(my_org_buffer);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return my_des_buffer;
    }

    public byte[] TrippleDesEcbDecode(byte[] key_buffer, byte[] org_buffer,
                                      int org_bytes) {
        SecretKey key = null;
        SecretKeyFactory keyFactory = null;
        SecretKeySpec keyspec = null;
        Cipher cipher = null;
        DESedeKeySpec desedekeyspec;

        byte[] my_key_buffer = new byte[24];
        byte[] my_des_buffer = null;
        byte[] my_org_buffer = new byte[org_bytes];

        if (org_buffer.length < org_bytes)
            return null;
        System.arraycopy(org_buffer, 0, my_org_buffer, 0, org_bytes);

        if (key_buffer.length < 16)
            return null;
        System.arraycopy(key_buffer, 0, my_key_buffer, 0, 16);
        if (key_buffer.length != 24) {
            System.arraycopy(key_buffer, 0, my_key_buffer, 16, 8);
        }
        try {
            keyFactory = SecretKeyFactory.getInstance("DESede");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 某些java平台使用此方案
        // desedekeyspec = new DESedeKeySpec(my_key_buffer);
        // 某些java平台使用次方案
        keyspec = new SecretKeySpec(my_key_buffer, "DESede");
        try {
            // key = keyFactory.generateSecret(desedekeyspec);
            key = keyFactory.generateSecret(keyspec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        try {
            cipher = Cipher.getInstance("DESede/ECB/NOPadding");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 解密初始化
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 执行加密
        try {
            my_des_buffer = cipher.doFinal(my_org_buffer);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return my_des_buffer;
    }

    public void destest() {
        byte[] plain_text = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
                0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
        byte[] des_key = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
                0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
        byte[] iv_buf = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
        byte[] encode_text = TrippleDesCbcEncode(des_key, iv_buf, plain_text,
                plain_text.length);
        Log.d("BCTC_TEST", "cbd encode data::");
        Log.d("BCTC_TEST", LogBuffer(encode_text, encode_text.length));
        plain_text = TrippleDesCbcDecode(des_key, iv_buf, encode_text,
                encode_text.length);
        Log.d("BCTC_TEST", "cbd decode data::");
        Log.d("BCTC_TEST", LogBuffer(plain_text, plain_text.length));
    }

    public static String LogBuffer(byte[] pbuf, int bytes) {
        StringBuffer sb = new StringBuffer(bytes * 2);
        String sTemp;
        for (int i = 0; i < bytes; i++) {
            sTemp = Integer.toHexString(0xFF & pbuf[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
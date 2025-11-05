package com.donny.algorithm;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Paillier 同态加密算法实现
 * 
 * <p>Paillier 加密是一种支持同态加法的公钥加密方案，具有以下特性：
 * <ul>
 *   <li>支持密文之间的加法运算（同态加法）</li>
 *   <li>支持标量与密文的乘法运算（同态标量乘法）</li>
 *   <li>语义安全性（Semantic Security）</li>
 * </ul>
 * 
 * <p>算法原理：
 * <ul>
 *   <li>公钥：(n, g)，其中 n = p * q，g = n + 1</li>
 *   <li>私钥：(lambda, mu)，其中 lambda = lcm(p-1, q-1)，mu = L(g^lambda mod n^2)^(-1) mod n</li>
 *   <li>加密：E(m) = g^m * r^n mod n^2，其中 r 是随机数</li>
 *   <li>解密：m = L(c^lambda mod n^2) * mu mod n</li>
 *   <li>同态加法：E(m1) * E(m2) = E(m1 + m2)</li>
 * </ul>
 * 
 * <p>使用示例：
 * <pre>
 * PaillierHomomorphic paillier = new PaillierHomomorphic(512);
 * BigInteger m1 = new BigInteger("123");
 * BigInteger m2 = new BigInteger("456");
 * 
 * BigInteger encrypted1 = paillier.encrypt(m1);
 * BigInteger encrypted2 = paillier.encrypt(m2);
 * 
 * // 同态加法：在密文上直接相加
 * BigInteger encryptedSum = paillier.homomorphicAdd(encrypted1, encrypted2);
 * BigInteger decryptedSum = paillier.decrypt(encryptedSum);
 * // decryptedSum = m1 + m2 = 579
 * </pre>
 * 
 * @author donnyyan
 * @date 2024/6/5
 * @version 1.0
 */
public class PaillierHomomorphic {
    
    /** 默认的随机数位长度 */
    private static final int DEFAULT_RANDOM_BIT_LENGTH = 256;
    
    /** 素数 p */
    private final BigInteger p;
    
    /** 素数 q */
    private final BigInteger q;
    
    /** 私钥参数 lambda = lcm(p-1, q-1) */
    private final BigInteger lambda;
    
    /** 公钥参数 n = p * q */
    private final BigInteger n;
    
    /** n 的平方，用于加密计算 */
    private final BigInteger nsquare;
    
    /** 公钥参数 g = n + 1 */
    private final BigInteger g;
    
    /** 私钥参数 mu，用于解密 */
    private final BigInteger mu;
    
    /** 安全随机数生成器 */
    private final SecureRandom random;
    
    /**
     * 构造函数，生成 Paillier 密钥对
     * 
     * @param bitLength 密钥长度（比特），建议使用 512、1024 或 2048
     * @throws IllegalArgumentException 如果 bitLength 小于 64 或不是偶数
     */
    public PaillierHomomorphic(int bitLength) {
        if (bitLength < 64) {
            throw new IllegalArgumentException("Bit length must be at least 64, got: " + bitLength);
        }
        if (bitLength % 2 != 0) {
            throw new IllegalArgumentException("Bit length must be even, got: " + bitLength);
        }
        
        this.random = new SecureRandom();
        
        // 生成两个大素数 p 和 q
        int primeLength = bitLength / 2;
        BigInteger pTemp = generatePrime(primeLength);
        BigInteger qTemp = generatePrime(primeLength);
        
        // 确保 p 和 q 不相等（虽然概率极低）
        while (pTemp.equals(qTemp)) {
            qTemp = generatePrime(primeLength);
        }
        
        this.p = pTemp;
        this.q = qTemp;
        
        // 计算公钥参数
        this.n = p.multiply(q);
        this.nsquare = n.multiply(n);
        
        // 计算私钥参数 lambda = lcm(p-1, q-1)
        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        BigInteger qMinusOne = q.subtract(BigInteger.ONE);
        BigInteger gcd = pMinusOne.gcd(qMinusOne);
        this.lambda = pMinusOne.multiply(qMinusOne).divide(gcd);
        
        // 设置 g = n + 1（这是 Paillier 加密的常用选择）
        this.g = n.add(BigInteger.ONE);
        
        // 计算 mu = L(g^lambda mod n^2)^(-1) mod n
        // 其中 L(x) = (x - 1) / n
        BigInteger gLambda = g.modPow(lambda, nsquare);
        BigInteger lValue = gLambda.subtract(BigInteger.ONE).divide(n);
        this.mu = lValue.modInverse(n);
    }
    
    /**
     * 生成指定长度的素数
     * 
     * @param bitLength 素数长度（比特）
     * @return 生成的素数
     */
    private BigInteger generatePrime(int bitLength) {
        return BigInteger.probablePrime(bitLength, random);
    }
    
    /**
     * 加密明文
     * 
     * <p>加密公式：E(m) = g^m * r^n mod n^2
     * 其中 r 是随机选择的随机数
     * 
     * @param plaintext 要加密的明文，必须满足 0 <= m < n
     * @return 加密后的密文
     * @throws IllegalArgumentException 如果明文小于 0 或大于等于 n
     * @throws NullPointerException 如果明文为 null
     */
    public BigInteger encrypt(BigInteger plaintext) {
        if (plaintext == null) {
            throw new NullPointerException("Plaintext cannot be null");
        }
        if (plaintext.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Plaintext must be non-negative, got: " + plaintext);
        }
        if (plaintext.compareTo(n) >= 0) {
            throw new IllegalArgumentException(
                "Plaintext must be less than n (" + n + "), got: " + plaintext);
        }
        
        // 生成随机数 r，确保 0 < r < n
        BigInteger r;
        do {
            r = new BigInteger(DEFAULT_RANDOM_BIT_LENGTH, random);
            r = r.mod(n);
        } while (r.equals(BigInteger.ZERO));
        
        // 计算 g^m mod n^2
        BigInteger gm = g.modPow(plaintext, nsquare);
        
        // 计算 r^n mod n^2
        BigInteger rn = r.modPow(n, nsquare);
        
        // 计算密文：E(m) = g^m * r^n mod n^2
        return gm.multiply(rn).mod(nsquare);
    }
    
    /**
     * 解密密文
     * 
     * <p>解密公式：m = L(c^lambda mod n^2) * mu mod n
     * 其中 L(x) = (x - 1) / n
     * 
     * @param ciphertext 要解密的密文
     * @return 解密后的明文
     * @throws IllegalArgumentException 如果密文无效
     * @throws NullPointerException 如果密文为 null
     */
    public BigInteger decrypt(BigInteger ciphertext) {
        if (ciphertext == null) {
            throw new NullPointerException("Ciphertext cannot be null");
        }
        if (ciphertext.compareTo(BigInteger.ZERO) < 0 || ciphertext.compareTo(nsquare) >= 0) {
            throw new IllegalArgumentException(
                "Ciphertext must be in range [0, n^2), got: " + ciphertext);
        }
        
        // 计算 c^lambda mod n^2
        BigInteger cLambda = ciphertext.modPow(lambda, nsquare);
        
        // 计算 L(c^lambda mod n^2) = (c^lambda - 1) / n
        BigInteger lValue = cLambda.subtract(BigInteger.ONE).divide(n);
        
        // 计算明文：m = L(c^lambda mod n^2) * mu mod n
        return lValue.multiply(mu).mod(n);
    }
    
    /**
     * 同态加法：在密文上执行加法运算
     * 
     * <p>同态性质：E(m1) * E(m2) = E(m1 + m2)
     * 
     * @param ciphertext1 第一个密文
     * @param ciphertext2 第二个密文
     * @return 加密后的和，即 E(m1 + m2)
     * @throws NullPointerException 如果任一密文为 null
     */
    public BigInteger homomorphicAdd(BigInteger ciphertext1, BigInteger ciphertext2) {
        if (ciphertext1 == null || ciphertext2 == null) {
            throw new NullPointerException("Ciphertexts cannot be null");
        }
        
        // 同态加法：E(m1 + m2) = E(m1) * E(m2) mod n^2
        return ciphertext1.multiply(ciphertext2).mod(nsquare);
    }
    
    /**
     * 同态标量乘法：密文与标量的乘法
     * 
     * <p>同态性质：E(m)^k = E(k * m)
     * 
     * @param ciphertext 密文
     * @param scalar 标量（乘数）
     * @return 加密后的乘积，即 E(k * m)
     * @throws NullPointerException 如果密文为 null
     */
    public BigInteger homomorphicScalarMultiply(BigInteger ciphertext, BigInteger scalar) {
        if (ciphertext == null || scalar == null) {
            throw new NullPointerException("Ciphertext and scalar cannot be null");
        }
        
        // 同态标量乘法：E(k * m) = E(m)^k mod n^2
        return ciphertext.modPow(scalar, nsquare);
    }
    
    /**
     * 同态减法（通过加法和标量乘法实现）
     * 
     * <p>E(m1 - m2) = E(m1) * E(m2)^(-1) mod n^2
     * 
     * @param ciphertext1 第一个密文
     * @param ciphertext2 第二个密文
     * @return 加密后的差，即 E(m1 - m2)
     * @throws NullPointerException 如果任一密文为 null
     */
    public BigInteger homomorphicSubtract(BigInteger ciphertext1, BigInteger ciphertext2) {
        if (ciphertext1 == null || ciphertext2 == null) {
            throw new NullPointerException("Ciphertexts cannot be null");
        }
        
        // E(m2)^(-1) = E(m2)^(n^2 - 1) mod n^2
        BigInteger c2Inverse = ciphertext2.modPow(nsquare.subtract(BigInteger.ONE), nsquare);
        
        // E(m1 - m2) = E(m1) * E(m2)^(-1) mod n^2
        return ciphertext1.multiply(c2Inverse).mod(nsquare);
    }
    
    /**
     * 获取公钥参数 n
     * 
     * @return 公钥参数 n
     */
    public BigInteger getPublicKeyN() {
        return n;
    }
    
    /**
     * 获取公钥参数 g
     * 
     * @return 公钥参数 g
     */
    public BigInteger getPublicKeyG() {
        return g;
    }
    
    /**
     * 获取 n 的平方（用于加密计算）
     * 
     * @return n 的平方
     */
    public BigInteger getNSquare() {
        return nsquare;
    }
    
    /**
     * 测试主方法
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("========== Paillier 同态加密测试 ==========\n");
        
        // 创建 Paillier 加密实例（512位密钥）
        PaillierHomomorphic paillier = new PaillierHomomorphic(512);
        
        // 测试数据
        BigInteger m1 = new BigInteger("123");
        BigInteger m2 = new BigInteger("456");
        BigInteger scalar = new BigInteger("3");
        
        System.out.println("原始数据:");
        System.out.println("  m1 = " + m1);
        System.out.println("  m2 = " + m2);
        System.out.println("  scalar = " + scalar);
        System.out.println();
        
        // 加密
        BigInteger encrypted1 = paillier.encrypt(m1);
        BigInteger encrypted2 = paillier.encrypt(m2);
        
        System.out.println("加密结果:");
        System.out.println("  E(m1) = " + encrypted1);
        System.out.println("  E(m2) = " + encrypted2);
        System.out.println();
        
        // 测试同态加法
        BigInteger encryptedSum = paillier.homomorphicAdd(encrypted1, encrypted2);
        BigInteger decryptedSum = paillier.decrypt(encryptedSum);
        BigInteger expectedSum = m1.add(m2);
        
        System.out.println("同态加法测试:");
        System.out.println("  E(m1) * E(m2) = E(m1 + m2)");
        System.out.println("  解密结果: " + decryptedSum);
        System.out.println("  预期结果: " + expectedSum);
        System.out.println("  测试" + (decryptedSum.equals(expectedSum) ? "通过" : "失败"));
        System.out.println();
        
        // 测试同态标量乘法
        BigInteger encryptedProduct = paillier.homomorphicScalarMultiply(encrypted1, scalar);
        BigInteger decryptedProduct = paillier.decrypt(encryptedProduct);
        BigInteger expectedProduct = m1.multiply(scalar);
        
        System.out.println("同态标量乘法测试:");
        System.out.println("  E(m1)^" + scalar + " = E(" + scalar + " * m1)");
        System.out.println("  解密结果: " + decryptedProduct);
        System.out.println("  预期结果: " + expectedProduct);
        System.out.println("  测试" + (decryptedProduct.equals(expectedProduct) ? "通过" : "失败"));
        System.out.println();
        
        // 测试同态减法
        BigInteger encryptedDiff = paillier.homomorphicSubtract(encrypted1, encrypted2);
        BigInteger decryptedDiff = paillier.decrypt(encryptedDiff);
        BigInteger expectedDiff = m1.subtract(m2);
        
        // 处理负数情况（模运算）
        BigInteger actualDiff = decryptedDiff;
        if (actualDiff.compareTo(paillier.getPublicKeyN().divide(BigInteger.valueOf(2))) > 0) {
            actualDiff = actualDiff.subtract(paillier.getPublicKeyN());
        }
        
        System.out.println("同态减法测试:");
        System.out.println("  E(m1) / E(m2) = E(m1 - m2)");
        System.out.println("  解密结果: " + actualDiff);
        System.out.println("  预期结果: " + expectedDiff);
        System.out.println("  测试" + (actualDiff.equals(expectedDiff) ? "通过" : "失败"));
        System.out.println();
        
        System.out.println("========== 测试完成 ==========");
    }
}

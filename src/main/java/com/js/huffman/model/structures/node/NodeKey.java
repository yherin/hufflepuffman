package com.js.huffman.model.structures.node;

/**
 * Enums used for comparing Nodes.
 * @author jack
 */
public enum NodeKey {

    /**
     * ZERO BIT
     */
    ZERO(0, 0b0, "0"),

    /**
     * ONE BIT.
     */
    ONE(1, 0b1, "1"),

    /**
     * FAKE BIT.
     */
    FAKE(-1, -1, "-1");

    private final int numberRep;
    private final int binaryRep;
    private final String charRep;

    NodeKey(int number, int binary, String cs) {
        this.numberRep = number;
        this.binaryRep = binary;
        this.charRep = cs;
    }

    /**
     * toString
     * @return string representation of this NodeKey.
     */
    @Override
    public String toString() {
        return this.charRep;
    }
}

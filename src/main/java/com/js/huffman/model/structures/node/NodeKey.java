/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node;

/**
 *
 * @author jack
 */
public enum NodeKey {
    
    
    ZERO (0,0b0,"0"),
    ONE  (1,0b1,"1");
    
    private int numberRep;
    private int binaryRep;
    private String charRep;
    
    
    NodeKey(int number, int binary, String cs){
        this.numberRep = number;
        this.binaryRep = binary;
        this.charRep = cs;
    }
    
    @Override
    public String toString(){
        return this.charRep;
    }
}

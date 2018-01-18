/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tree;

/**
 *
 * @author jack
 */
public abstract class Node {
    
    private Node left;
    private Node right;
       
    private Integer freq;
    
    public Node(){
        this.left = null;
        this.right = null;
    }
    
    
}

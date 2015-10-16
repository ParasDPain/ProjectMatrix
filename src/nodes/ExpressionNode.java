/*
 * This software and all files contained in it are distrubted under the MIT license.
 * 
 * Copyright (c) 2013 Cogito Learning Ltd
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package nodes;

import framework.Matrix;

/**
 * An interface for expression nodes.
 * 
 * Every concrete type of expression node has to implement this interface.
 */
public interface ExpressionNode
{
  /** Node id for matrix nodes (remain constant) */
  public static final int MATRIX_NODE = 1;
  /** Node id for addition nodes */
  public static final int ADDITION_NODE = 2;
  /** Node id for multiplication nodes */
  public static final int MULTIPLICATION_NODE = 3;

  /**
   * Returns the type of the node.ExpressionNode
   *
   * Each class derived from ExpressionNode representing a specific
   * role in the expression should return the type according to that
   * role.
   * 
   * @return type of the node
   */
  public int getType();
  
  /**
   * Calculates and returns the value of the sub-expression represented by
   * the node.
   * 
   * @return value of expression
   */
  public Matrix getValue();
  
  /**
   * Method needed for the visitor design pattern
   * 
   * @param visitor
   *          the visitor
   */
  public void accept(ExpressionNodeVisitor visitor);

}

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
import framework.Operator;
import nodes.SequenceExpressionNode.Term;

/**
 * An ExpressionNode that handles multiplications and divisions. The node can hold
 * an arbitrary number of factors that are either multiplied or divided to the product.
 * 
 */
public class MultiplicationExpressionNode extends SequenceExpressionNode
{
	/**
	 * Default constructor.
	 */
	public MultiplicationExpressionNode()
	{}

	/**
	 * Constructor to create a multiplication with the first term already added.
	 * 
	 * @param node
	 *          the term to be added
	 * @param positive
	 *          a flag indicating whether the term is multiplied or divided
	 */
	public MultiplicationExpressionNode(ExpressionNode a, boolean positive)
	{
		super(a, positive);
	}

	/**
	 * Returns the type of the node, in this case ExpressionNode.MULTIPLICATION_NODE
	 */
	public int getType()
	{
		return ExpressionNode.MULTIPLICATION_NODE;
	}

	/**
	 * Returns the value of the sub-expression that is rooted at this node.
	 * 
	 * All the terms are evaluated and multiplied or divided to the product.
	 */
	public Matrix getValue()
	{
		// First matrix in the sequence
		Matrix prod = new Matrix(terms.get(0).expression.getValue().getGrid());
		// Start from 1 because the first term is already accounted for
		for (int i = 1; i < terms.size(); i++)
		{
			if (terms.get(i).positive)
				// Start operating from the second term onwards
				prod = Operator.Multiply(prod, terms.get(i).expression.getValue());
			else
				prod = Operator.Divide(prod, terms.get(i).expression.getValue());
		}
		return prod;
	}

	/**
	 * Implementation of the visitor design pattern.
	 * 
	 * Calls visit on the visitor and then passes the visitor on to the accept
	 * method of all the terms in the product.
	 * 
	 * @param visitor
	 *          the visitor
	 */
	public void accept(ExpressionNodeVisitor visitor)
	{
		visitor.visit(this);  
		for (Term t: terms)
			t.expression.accept(visitor);
	}
}

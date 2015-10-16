/*
 * This software and all files contained in it are distributed under the MIT license.
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

package parser;

import java.util.LinkedList;

import nodes.AdditionExpressionNode;
import nodes.MatrixExpressionNode;
import nodes.ExpressionNode;
import nodes.MultiplicationExpressionNode;

/**
 * A parser for mathematical expressions. The parser class defines a method
 * parse() which takes a string and returns an ExpressionNode that holds a
 * representation of the expression.
 * 
 * Parsing is implemented in the form of a recursive descent parser.
 * 
 */
public class Parser
{
  /** the tokens to parse */
  LinkedList<Token> tokens;
  /** the next token */
  Token lookahead;

  /**
   * Parse a mathematical expression in a string and return an ExpressionNode.
   * 
   * This is a convenience method that first converts the string into a linked
   * list of tokens using the expression tokenizer provided by the Tokenizer
   * class.
   * 
   * @param expression
   *          the string holding the input
   * @return the internal representation of the expression in form of an
   *         expression tree made out of ExpressionNode objects
   */
  public ExpressionNode parse(String expression)
  {
    Tokenizer tokenizer = Tokenizer.getExpressionTokenizer();
    tokenizer.tokenize(expression);
    LinkedList<Token> tokens = tokenizer.getTokens();
    return this.parse(tokens);
  }

  /**
   * Parse a mathematical expression in contained in a list of tokens and return
   * an ExpressionNode.
   * 
   * @param tokens
   *          a list of tokens holding the tokenized input
   * @return the internal representation of the expression in form of an
   *         expression tree made out of ExpressionNode objects
   */
  public ExpressionNode parse(LinkedList<Token> tokens)
  {
    // implementing a recursive descent parser
    this.tokens = (LinkedList<Token>) tokens.clone();
    lookahead = this.tokens.getFirst();

    // top level non-terminal is expression
    ExpressionNode expr = expression();
    
    if (lookahead.token != Token.EPSILON)
      throw new ParserException("Unexpected symbol %s found", lookahead);
    
    return expr;
  }

  /** handles the non-terminal expression */
  private ExpressionNode expression()
  {
    // only one rule
    // expression -> signed_term sum_op
    ExpressionNode expr = signedTerm();
    expr = sum(expr);
    return expr;
  }

  /** handles the non-terminal sum */
  private ExpressionNode sum(ExpressionNode expr)
  {
    // sum -> PLUSMINUS term sum
    if (lookahead.token == Token.PLUSMINUS)
    {
      AdditionExpressionNode sum;
      // This means we are actually dealing with a sum
      // If expr is not already a sum, we have to create one
      if (expr.getType() == ExpressionNode.ADDITION_NODE)
        sum = (AdditionExpressionNode) expr;
      else
        sum = new AdditionExpressionNode(expr, true);

      // reduce the input and recursively call sum_op
      boolean positive = lookahead.sequence.equals("+");
      nextToken();
      ExpressionNode t = term();
      sum.add(t, positive);

      return sum(sum);
    }

    // sum -> EPSILON
    return expr;
  }

  /** handles the non-terminal signed_term */
  private ExpressionNode signedTerm()
  {
    // signed_term -> PLUSMINUS term
    if (lookahead.token == Token.PLUSMINUS)
    {
      boolean positive = lookahead.sequence.equals("+");
      nextToken();
      ExpressionNode t = term();
      if (positive)
        return t;
      else
        return new AdditionExpressionNode(t, false);
    }

    // signed_term -> term
    return term();
  }

  /** handles the non-terminal term */
  private ExpressionNode term()
  {
    // term -> factor term_op
    ExpressionNode f = argument();
    return prod(f);
  }

  /** handles the non-terminal product */
  private ExpressionNode prod(ExpressionNode expr)
  {
    // prod -> MULTDIV argument prod
    if (lookahead.token == Token.MULTDIV)
    {
      MultiplicationExpressionNode p;

      // This means we are actually dealing with a product
      // If expr is not already a PRODUCT, we have to create one
      if (expr.getType() == ExpressionNode.MULTIPLICATION_NODE)
        p = (MultiplicationExpressionNode) expr;
      else
        p = new MultiplicationExpressionNode(expr, true);

      // reduce the input and recursively call sum_op
      boolean positive = lookahead.sequence.equals("*");
      nextToken();
      ExpressionNode a = argument();
      p.add(a, positive);

      return prod(p);
    }

    // prod -> EPSILON
    return expr;
  }
  
  /** handles the non-terminal argument */
  private ExpressionNode argument()
  {
    // argument -> OPEN_BRACKET sum CLOSE_BRACKET
    if (lookahead.token == Token.OPEN_BRACKET)
    {
      nextToken();
      ExpressionNode expr = expression();
      if (lookahead.token != Token.CLOSE_BRACKET)
        throw new ParserException("Closing brackets expected", lookahead);
      nextToken();
      return expr;
    }

    // argument -> value
    return value();
  }

  /** handles the non-terminal value */
  private ExpressionNode value()
  {
    // argument -> MATRIX
    if (lookahead.token == Token.MATRIX)
    {
      ExpressionNode expr = new MatrixExpressionNode(lookahead.sequence);
      nextToken();
      return expr;
    }

    if (lookahead.token == Token.EPSILON)
      throw new ParserException("Unexpected end of input");
    else
      throw new ParserException("Unexpected symbol %s found", lookahead);
  }

  /**
   * Remove the first token from the list and store the next token in lookahead
   */
  private void nextToken()
  {
    tokens.pop();
    // at the end of input we return an epsilon token
    if (tokens.isEmpty())
      lookahead = new Token(Token.EPSILON, "", -1);
    else
      lookahead = tokens.getFirst();
  }
}

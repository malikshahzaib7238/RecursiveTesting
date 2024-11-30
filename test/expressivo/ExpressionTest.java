/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   TODO
    
    //TODO should all methods be tested on expression returned by every producer?
    //TODO write test cases for hashcode of +/* two large integer values
    //TODO should I test evaluation of test cases containing numbers largely differing in magnitude?
    
    //EPSILON determines the precision with which double values are compared.
    private static final double EPSILON = 0.0;
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    @Test
    public void testEqualsFor_SingleVariable(){
        Expression a1 = Expression.parse("a");
        
        Expression b1 = Expression.parse("A");
        
        assertEquals(a1,a1);
        assertNotEquals(a1,b1);
        
        assertNotEquals(b1, a1);
        assertEquals(b1, b1);
    }
    
    @Test
    public void testEqualsFor_SingleNumber(){
        Expression a1 = Expression.parse("1");
        Expression a2 = Expression.parse("1.00");
        
        assertEquals(a1, a1);
        assertEquals(a1, a2);
        assertEquals(a2, a1);
        assertEquals(a2, a2);
    }
    
    @Test
    public void testEqualsFor_A_Plus_B(){
        Expression a1 = Expression.parse("a+b");
        
        Expression b1 = Expression.parse("b+a");
        
        assertNotEquals(a1, b1);
    }
    
    @Test
    public void testEqualsFor_A_Plus_A(){
        Expression a1 = Expression.parse("a+a");
        
        Expression b1 = Expression.parse("2*a");
        
        Expression c1 = Expression.parse("a+a+a");
        
        assertNotEquals(a1, b1);
        assertNotEquals(a1, c1);
        assertNotEquals(b1, a1);
        assertNotEquals(c1, a1);
    }
    
    @Test
    public void testEqualsFor_A_Plus_0(){
        Expression a1 = Expression.parse("a+0");
        
        Expression b1 = Expression.parse("a");
        
        Expression c1 = Expression.parse("0+a");
        
        assertNotEquals(a1, b1);
        assertNotEquals(a1, c1);
        assertNotEquals(b1, a1);
        assertNotEquals(c1, a1);
    }
    
  
    
    @Test
    public void testEqualsFor_A_Times_B(){
        Expression a1 = Expression.parse("a*b");
        
        Expression b1 = Expression.parse("b*a");
        
        assertNotEquals(a1, b1);
        assertNotEquals(b1, a1);
    }
    
    

    

    
    @Test
    public void testToStringFor_Product_Plus_Term(){
        Expression a1 = Expression.parse("a*b+c");
        Expression a2 = Expression.parse("(a*b)+c");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_Term_Times_Sum(){
        Expression a1 = Expression.parse("a*(b+c)");
        Expression a2 = Expression.parse("((a)*((b+c)))");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    
    @Test
    public void testToStringFor_Product_Times_Term(){
        Expression a1 = Expression.parse("a*b*c");
        Expression a2 = Expression.parse("(a*b)*c");
        
        assertEquals(Expression.parse(a1.toString()), a1);
        assertEquals(a1.toString(), a2.toString());
    }
    


    
    @Test
    public void testParse_CaseSensitivity(){
        Expression a1 = Expression.parse("aB");
        Expression a2 = Expression.parse("ab");
        
        assertNotEquals(a1, a2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_EmptyExpression(){
        Expression.parse("");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidCharacter(){
        Expression.parse("$");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidVariableName1(){
        Expression.parse("x1");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_InvalidVariableName2(){
        Expression.parse("x_y");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_MissingOperatorNumberTimesVariable(){
        Expression.parse("3x");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_MissingOperatorBetweenVariables(){
        Expression.parse("x y");
    }
    

    
    @Test(expected = IllegalArgumentException.class)
    public void testParse_UnmatchedBracket2(){
        Expression.parse("3)");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testParse_EmptyBracket(){
        Expression.parse("()");
    }
    

    

    
}
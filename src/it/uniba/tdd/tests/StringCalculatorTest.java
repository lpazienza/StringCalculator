package it.uniba.tdd.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniba.tdd.*;

public class StringCalculatorTest {
	 StringCalculator firstSC;
	 int result;
	 
	 @Before
	public void setUp(){
		firstSC = new StringCalculator();
		result = 0;
	}
	
	@Test
	public void emptyStringReturnsSum0() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("");
		//Assert
		assertEquals(0,result);
	}
	
	@Test
	public void StringReturnsTheOperandWithOneInput() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("2");
		//Assert
		assertEquals(2+0,result);
	}
	
	@Test
	public void StringReturnsTheSumOfThe2NumbersInInput() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("2,5");
		//Assert
		assertEquals(2+5,result);
	}
	
	@Test
	public void StringReturnsTheSumOfAllNumbersInInput() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("2,5,5,0,2,3,51,9");
		//Assert
		assertEquals(2+5+5+0+2+3+51+9,result);
	}
	
	@Test
	public void StringReturnsTheSumOfAllNumbersInInputWithNewLineAsDelimiter() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("1\n2,3");
		//Assert
		assertEquals(1+2+3,result);
	}
	
	@Test(expected = StringCalculatorException.class)
	public void throwsExceptionWithDelimitersInSequence() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("1,\n");
	}

	@Test(expected = StringCalculatorException.class)
	public void throwsExceptionWithCustomDelimitersInSequence() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("//;\n1;3;;5");
	}
	
	@Test
	public void StringReturnsTheSumOfAllNumbersInInputWithCustomDelimiter() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("//del\n2del3del4del7");
		//Assert
		assertEquals(2+3+4+7,result);
	}
	
	@Test(expected = StringCalculatorException.class)
	public void throwsExceptionWithNegativeNumber() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("-56");
	}
	
	@Test
	public void numbersBiggerThen1000AreIgnored() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("2,1001");
		//Assert
		assertEquals(2+0,result);
	}
	
	@Test
	public void delimiterCanBeOfAnyLength() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("//***\n1***2***3");
		//Assert
		assertEquals(1+2+3,result);
	}
	
	@Test
	public void CalculateSumWithMultipleDelimeters() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("//[%][del]\n1%2del3");
		//Assert
		assertEquals(1+2+3,result);
	}
	
	@Test
	public void CalculateSumWithMultipleDelimetersLength() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("//[%][del][asderta]\n1%2del3asderta78asderta7");
		//Assert
		assertEquals(1+2+3+78+7,result);
	}
	
	@Test(expected = StringCalculatorException.class)
	public void ThrowsExceptionWithCustomDelimeterRepeated() throws StringCalculatorException{
		//Arrange in setUp
		//Act
		result = firstSC.add("//[%][del][asderta]\n1%2del3asdertaasderta");
	}
}

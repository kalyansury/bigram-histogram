package com.kalyansury;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BigramTest {

	Bigram bigram;

	@Before
	public void beforeEachTest() {
		bigram = new Bigram();
	}

	@Test
	public void testFileReadForFailure() {
		String expected = "Hello World";
		String fileName = "testReadFile.txt";
		Assert.assertNotEquals(expected, bigram.readFile(fileName).trim());
	}

	@Test
	public void testFileReadForSuccess() {
		String expected = "First line of file";
		String fileName = "testReadFile.txt";
		Assert.assertEquals(expected, bigram.readFile(fileName).trim());
	}

	@Test
	public void testCountForOne() {
		Assert.assertEquals(1l, bigram.count("test one"));
	}

	@Test
	public void testCountForTwo() {
		String testVal = "test two";
		bigram.count(testVal);
		Assert.assertEquals(2l, bigram.count(testVal));
	}

	@Test
	public void testCountForRandom() {
		Random rand = new Random();
		int iVal = rand.nextInt(50) + 1;
		String testVal = "test " + iVal;
		for (int i = 0; i < iVal - 1; i++) {
			bigram.count(testVal);
		}
		Assert.assertEquals(iVal, bigram.count(testVal));
	}

	public void testCountForCaseInsensitivity() {
		bigram.count("test case");
		bigram.count("TEST CASE");
		Assert.assertEquals(3, bigram.count("Test Case"));
	}

	@Test
	public void testIteratorForUniqueBigramCount() {
		String testData = "first sample is a good first sample";
		int expected = 5;
		Assert.assertEquals(expected, bigram.iterator(testData));
	}

	@Test
	public void testGetHistogram() {
		String testData = "first sample is a good first sample";
		String expected = "{first sample=2, a good=1, sample is=1, good first=1, is a=1}";
		bigram.iterator(testData);
		Assert.assertEquals(expected, bigram.getHistogram());
	}
}
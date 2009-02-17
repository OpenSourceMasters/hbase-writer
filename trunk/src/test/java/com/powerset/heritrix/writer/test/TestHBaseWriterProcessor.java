package com.powerset.heritrix.writer.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.powerset.heritrix.writer.HBaseWriterProcessor;

// TODO: Auto-generated Javadoc
/**
 * The Class TestHBaseWriterProcessor.
 * 
 * @author rsmith
 * 
 * TODO: mock objects should be used here to test the api integrity.
 */
public class TestHBaseWriterProcessor {
	
	/** The hwproc. */
	HBaseWriterProcessor hwproc;

	/**
	 * Creates the h base writer processor.
	 */
	@BeforeClass()
	public void createHBaseWriterProcessor() {
		hwproc = new HBaseWriterProcessor();
	}

	/**
	 * Test h base writer processor integrity.
	 */
	@Test()
	public void testHBaseWriterProcessorIntegrity() {
		Assert.assertNotNull(hwproc);
		Assert.assertEquals(hwproc.getURICount(), 0);
	}
	
	
}

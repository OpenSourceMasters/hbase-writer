package com.powerset.heritrix.writer.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.powerset.heritrix.writer.HBaseWriter;

public class TestHBaseWriter {
	String master = "locahost";
	String table = "test";
	int poolMaximumActive = 10;
	int poolMaximumWait = 20;
	
	HBaseWriter hw;
	
	@BeforeClass()
	public void createHBaseWriter() throws IOException {
		hw = new HBaseWriter(master, table);
	}
	
	@Test()
	public void testHBaseWriterIntegrity() {
		Assert.assertNotNull(hw);
	}
}

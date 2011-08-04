package edu.luc.clearing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CheckParserTest {

private CheckParser parser;

@Before
public void setUp() {
parser = new CheckParser();
}

@Test
public void shouldIgnoreCase() throws Exception {

assertEquals(300, parser.processCheckString("Three dollars").intValue());
}

@Test
public void shouldHandleZero() throws Exception {
assertEquals(0, parser.processCheckString("zero dollars").intValue());
}

@Test
public void shouldHandleCompoundNumbers() throws Exception {
assertEquals(3300, parser.processCheckString("THIRTY-THREE AND 0/100 CENTS").intValue());
}
}



package com.whs.demo.finder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestJUnit {
	
	@Test
    public  void nameFinderTest() throws Exception{
        NameFinder nameFinder = new NameFinder();
        nameFinder.findName("Where is Charlie and Mike. They are with Tom.",null);
    }

    @Test
    public  void locationFinderTest() throws Exception{
        LocationFinder locFinder = new LocationFinder();
        locFinder.findLocation("Charlie is in California but I don't about Mike. Mike is in Orlando.",null);

    }
}

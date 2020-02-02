package com.gbastos.JavaDesignPatterns.Singleton;

import static org.junit.Assert.assertEquals;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class SingletonSerializedTest {

	private static final String FILENAME = "filename.ser";

	@Test
	public void singletonSerializedTest()  throws FileNotFoundException, IOException, ClassNotFoundException{
		
		DateUtil instanceOne = DateUtil.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(FILENAME));
        out.writeObject(instanceOne);
        out.close();
	        
        // Deserailize from file to object
        ObjectInput in = new ObjectInputStream(new FileInputStream(FILENAME));
        DateUtil instanceTwo = (DateUtil) in.readObject();
        in.close();
        
		assertEquals(instanceOne, instanceTwo);
	}
	
}

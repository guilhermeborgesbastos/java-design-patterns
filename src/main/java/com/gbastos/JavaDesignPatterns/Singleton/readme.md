# Singleton Pattern

**Java Singleton Pattern** is one of the *Gangs of Four Design* patterns and comes in the *Creational Design Pattern* category.

From the definition, it seems to be a very simple design pattern but when it comes to implementation, it comes with a lot of implementation concerns.

The implementation of Java Singleton pattern has always been a controversial topic among developers. Here we will learn about Singleton design pattern principles, different ways to implement the Singleton design pattern and some of the best practices for its usage.

## Additional information
- Singleton pattern restricts the instantiation of a class and ensures that only one instance of the class exists in the java virtual machine.
- The singleton class must provide a global access point to get the instance of the class.
- Singleton pattern is used for `logging`,` drivers objects`,` caching` and `thread pool`.
- Singleton design pattern is also used in other design patterns like `Abstract Factory`, `Builder`, `Prototype`, `Facade`, etc.
- Singleton design pattern is used in core java classes also, for example `java.lang.Runtime`, `java.awt.Desktop`.

## Java Singleton Pattern Implementation

To implement a Singleton pattern, we have different approaches but all of them have the following common concepts.

- Private constructor to restrict instantiation of the class from other classes.
- Private static variable of the same class that is the only instance of the class.
- Public static method that returns the instance of the class, this is the global access point for outer world to get the instance of the singleton class. 

In further sections, we will learn different approaches of Singleton pattern implementation and design concerns with the implementation.

- [Eager initialization](#eager-initialization)
- [Static block initialization](#static-block-initialization)
- [Lazy Initialization](#lazy-Initialization)
- [Thread Safe Singleton](#thread-safe-singleton)
- [Bill Pugh Singleton Implementation](#bill-pugh-singleton-implementation)
- [Using Reflection to destroy Singleton Pattern](#using-reflection-to-destroy-singleton-pattern)
- [Enum Singleton](#enum-singleton)
- [Serialization and Singleton](#serialization-and-singleton)

## Eager initialization
In eager initialization, the instance of Singleton Class is created at the time of class loading, this is the easiest method to create a singleton class but it has a drawback that instance is created even though client application might not be using it.

Here is the implementation of the static initialization singleton class.

## Eager initialization
In eager initialization, the instance of Singleton Class is created at the time of class loading, this is the easiest method to create a singleton class but it has a drawback that instance is created even though client application might not be using it.

Here is the implementation of the static initialization singleton class.

```
package com.gbastos.JavaDesignPatterns.Singleton;

public class EagerInitializedSingleton {
    
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();
    
    //private constructor to avoid client applications to use constructor
    private EagerInitializedSingleton(){}

    public static EagerInitializedSingleton getInstance(){
        return instance;
    }
}
```

If your singleton class is not using a lot of resources, this is the approach to use. But in most of the scenarios, Singleton classes are created for resources such as File System, Database connections, etc. We should avoid the instantiation until unless client calls the getInstance method. Also, this method doesn’t provide any options for exception handling.


## Static block initialization
Static block initialization implementation is similar to eager initialization, except that instance of class is created in the static block that provides option for exception handling.

```
package com.gbastos.JavaDesignPatterns.Singleton;

public class StaticBlockSingleton {

    private static StaticBlockSingleton instance;
    
    private StaticBlockSingleton(){}
    
    //static block initialization for exception handling
    static{
        try{
            instance = new StaticBlockSingleton();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    
    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}
```

Both eager initialization and static block initialization creates the instance even before it’s being used and that is not the best practice to use. So in further sections, we will learn how to create a Singleton class that supports lazy initialization.



## Lazy Initialization
Lazy initialization method to implement Singleton pattern creates the instance in the global access method. Here is the sample code for creating Singleton class with this approach.

```
package com.gbastos.JavaDesignPatterns.Singleton;

public class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;
    
    private LazyInitializedSingleton(){}
    
    public static LazyInitializedSingleton getInstance(){
        if(instance == null){
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}
```

The above implementation works fine in case of the single-threaded environment but when it comes to multithreaded systems, it can cause issues if multiple threads are inside the if condition at the same time. It will destroy the singleton pattern and both threads will get the different instances of the singleton class. In next section, we will see different ways to create a thread-safe singleton class.


## Thread Safe Singleton
The easier way to create a thread-safe singleton class is to make the global access method synchronized, so that only one thread can execute this method at a time. General implementation of this approach is like the below class.

```
package com.gbastos.JavaDesignPatterns.Singleton;

public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton(){}
    
    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance == null){
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
    
}
```

Above implementation works fine and provides thread-safety but it reduces the performance because of the cost associated with the synchronized method, although we need it only for the first few threads who might create the separate instances (Read: Java Synchronization). To avoid this extra overhead every time, double checked locking principle is used. In this approach, the synchronized block is used inside the if condition with an additional check to ensure that only one instance of a singleton class is created.

The following code snippet provides the double-checked locking implementation.

```
public static ThreadSafeSingleton getInstanceUsingDoubleLocking(){
    if(instance == null){
        synchronized (ThreadSafeSingleton.class) {
            if(instance == null){
                instance = new ThreadSafeSingleton();
            }
        }
    }
    return instance;
}
```


## Bill Pugh Singleton Implementation
Prior to Java 5, java memory model had a lot of issues and the above approaches used to fail in certain scenarios where too many threads try to get the instance of the Singleton class simultaneously. So Bill Pugh came up with a different approach to create the Singleton class using an inner static helper class. The Bill Pugh Singleton implementation goes like this;

```
package com.gbastos.JavaDesignPatterns.Singleton;

public class BillPughSingleton {

    private BillPughSingleton(){}
    
    private static class SingletonHelper{
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    public static BillPughSingleton getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
```

Notice the private inner static class that contains the instance of the singleton class. When the singleton class is loaded, SingletonHelper class is not loaded into memory and only when someone calls the getInstance method, this class gets loaded and creates the Singleton class instance.

This is the most widely used approach for Singleton class as it doesn’t require synchronization. I am using this approach in many of my projects and it’s easy to understand and implement also.


## Using Reflection to destroy Singleton Pattern
Reflection can be used to destroy all the above singleton implementation approaches. Let’s see this with an example class.

```
package com.gbastos.JavaDesignPatterns.Singleton;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Constructor;

import org.junit.Test;

public class ReflectionSingletonTest {

    public static void main(String[] args) {
        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;
        try {
            Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                //Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        assertEquals(instanceOne, instanceTwo);
    }

}
```

When you run the above test class, you will notice that hashCode of both the instances is not same that destroys the singleton pattern. Reflection is very powerful and used in a lot of frameworks like Spring and Hibernate, do check out Java Reflection Tutorial.


## Enum Singleton
To overcome this situation with Reflection, Joshua Bloch suggests the use of Enum to implement Singleton design pattern as Java ensures that any enum value is instantiated only once in a Java program. Since Java Enum values are globally accessible, so is the singleton. The drawback is that the enum type is somewhat inflexible; for example, it does not allow lazy initialization.

```
package com.gbastos.JavaDesignPatterns.Singleton;

public enum EnumSingleton {

    INSTANCE;
    
    public static void doSomething(){
        //do something
    }
}
```

## Serialization and Singleton
Sometimes in distributed systems, we need to implement Serializable interface in Singleton class so that we can store its state in the file system and retrieve it at a later point of time. Here is a small singleton class that implements Serializable interface also.

```
package com.gbastos.JavaDesignPatterns.Singleton;

import java.io.Serializable;

public class SerializedSingleton implements Serializable{

    private static final long serialVersionUID = -7604766932017737115L;

    private SerializedSingleton(){}
    
    private static class SingletonHelper{
        private static final SerializedSingleton instance = new SerializedSingleton();
    }
    
    public static SerializedSingleton getInstance(){
        return SingletonHelper.instance;
    }
    
}
```

The problem with serialized singleton class is that whenever we deserialize it, it will create a new instance of the class. Let’s see it with a simple program.

```
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
```

The output of the above program is;


instanceOne hashCode=2011117821
instanceTwo hashCode=109647522
So it destroys the singleton pattern, to overcome this scenario all we need to do it provide the implementation of readResolve() method.

```
protected Object readResolve() {
    return getInstance();
}
```

After this, you will notice that hashCode of both the instances is same in the test program.

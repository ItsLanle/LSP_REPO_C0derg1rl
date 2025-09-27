Reflection: Comparing Assignment 2 and Assignment 3


In both Assignment 2 and Assignment 3, the programs perform the same task: read data from a CSV file, apply transformations, and write the results to a new CSV file. The main difference lies in how the code is structured.

In Assignment 2, all the logic was in a single class using static methods for extract, transform, and load. The data was stored in lists of objects, and the transformation rules were directly implemented inside the methods. While this worked, the design was tightly coupled, making the program harder to maintain or extend.

Assignment 3 improves the design by separating responsibilities into multiple classes. I created an Extractor class for reading CSV files, a Transformer class for applying transformations, a Loader class for writing the transformed data, and two data classes: Product and ProductTransformed. Each class has a clear role: the Extractor handles reading and validation, the Transformer applies all transformation rules, the Loader writes the CSV file, and the data classes encapsulate the product information.

This redesign is more object-oriented. Assignment 3 uses objects and classes instead of static methods. Each product is represented as an object with private fields, and I used encapsulation by providing getters and setters. While I did not heavily use inheritance or polymorphism, the design allows future extensions, such as creating specialized transformers for different product categories.

To ensure Assignment 3 works the same as Assignment 2, I tested both programs with the same input file. I verified that the number of rows read, transformed, and skipped matched, and I compared the output CSV files line by line. The results were identical, confirming that the object-oriented redesign did not change the program’s behavior.

In summary, Assignment 2 followed a procedural approach, while Assignment 3 is broken into objects and classes. The new design is cleaner, easier to maintain, and aligns more closely with object-oriented programming principles. 
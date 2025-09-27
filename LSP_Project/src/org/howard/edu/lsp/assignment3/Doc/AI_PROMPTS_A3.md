AI Prompts Transcript – Assignment 3
Author: Ayotunde Ogunruku

Purpose: Document AI prompts used to redesign Assignment 2 into a more object-oriented Assignment 3.


Prompt 1: Redesign ETL pipeline to be more object-oriented
Excerpt of AI Response:
Suggested separating the pipeline into multiple classes: Extractor, Transformer, Loader, Product, ProductTransformed, and ETLPipeline. Explained responsibilities for each class and recommended using encapsulation and potential polymorphism for future extensions.

1. Key Ideas for Object-Oriented Redesign
Current Issues
	- Your current ETLPipeline class handles all three steps (extract, transform, load) in one place.
	- Product and ProductTransformed are simple data holders, not fully encapsulated.
	- The logic for transformations is tightly coupled inside a static method.
OOP Goals
Separate responsibilities into classes:
	- Product – represents raw product data.
	- ProductTransformed – represents transformed product data.
	- Extractor – handles reading CSV and producing Product objects.
	- Transformer – handles all transformation rules.
	- Loader – handles writing transformed data to CSV.
	- ETLPipeline – coordinates the ETL process.
Use encapsulation:
	- Make fields private, expose getters/setters if needed.
Consider polymorphism/inheritance:
	- If you have different types of transformers (e.g., Electronics vs Others), you can use a base Transformerinterface or abstract class.

Suggested Class Design:

ETLPipeline
│
├── Extractor
│    └── List<Product> extract(String path)
│
├── Transformer
│    └── List<ProductTransformed> transform(List<Product> products)
│
├── Loader
│    └── void load(List<ProductTransformed> products, String path)
│
├── Product
│    └── id, name, price, category
│
└── ProductTransformed
     └── id, name, price, category, priceRange

Responsibilities
Extractor
- Reads CSV.
- Validates rows.
- Keeps track of skipped rows.
Transformer
- Handles all rules (uppercase name, discounts, premium category, price range).
- Can be extended with polymorphism if rules grow more complex.
Loader
- Writes header + transformed rows to CSV.
ETLPipeline
	Main orchestrator:
	- Calls Extractor.extract()
	- Calls Transformer.transform()
	- Calls Loader.load()
	- Prints summary


Prompt 2: Handle empty input CSV file like Assignment 2
Excerpt of AI Response:
Updated Extractor.extract() to check if the first line is null, print "Input file is empty. Creating output with header only." and return an empty list so the Loader can still write the header.

Prompt 3: Create Javadoc for all classes
Excerpt of AI Response:
Provided Javadoc templates for Extractor, Transformer, Loader, Product, and ProductTransformed including method descriptions and parameter explanations.

Prompt 4: Help polish reflection comparing Assignment 2 and Assignment 3
Excerpt of AI Response:
Suggested edits and rewording to make the reflection clearer and more concise. Highlighted differences in design, object-oriented improvements, use of OO concepts (classes, objects, encapsulation, potential inheritance/polymorphism), and ensured the testing explanation clearly showed functional equivalence between Assignment 2 and Assignment 3




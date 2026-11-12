Reflection Questions
1. Which solutions were easier to read: loop-based or stream-based?
I found stream-based solutions easier to read because the code is shorter and simpler.

In Part 1, I used a for loop and an if condition to filter people.

In Part 2, used stream() and filter() to select active people.

Both methods are useful. Loops helped me understand the logic step by step, while streams made the code shorter.

2. Which operations felt like transformations and which felt like side effects?
Data Processing and Transformations:

filter() - used it to select active people.
map() - used it to get names from Person objects.
sorted() - used it to sort people by age.
count() - used it to count adults.
findFirst() - used it to find the first active person living in Stockholm.
Side Effects:

System.out.println() - used it to print results in the console.
forEach() - used it to print each person's information.
PersonAction - used it to print a person's name and an email message.
3. Where did Predicate, Function, and Consumer fit naturally?
Predicate:

My PersonRule functional interface works like a Predicate.

I used it to check whether a person is active, an adult, or lives in Stockholm.

I also used the Predicate idea with filter() in Part 2.

Function:

I used the Function idea with map().

It takes a Person object and returns the person's name.

Consumer:

My PersonAction functional interface works like a Consumer.

I used it to print a person's name and display an email message.

I also used the Consumer idea with forEach() to print results.

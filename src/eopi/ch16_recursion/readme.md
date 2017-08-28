## preface

- Recursion is a method where the solution to a problem depends partially on solutions 
to smaller instances of related problems.
- success of using recursion
    - identifying the base cases
    - recursion converges to the solution
- **divide-and-conquer algorithm**
    - repeatedly decomposing a problem into two or more smaller **independent subproblems**
     of the **same kind**, until it gets to instances that are **simple enough to be solved directly**. 
    - The solutions to the subproblems are then **combined** to give a solution to the original problem.
    - **MergeSort and QuickSort** are classical examples of divide-and-conquer
- `Divide-and-conquer` vs `recursion`
    - In divide-and-conquer, the problem is divided into two or more independent smaller problems 
    that are of the same type as the original problem. 
    -  Recursion is more general -- there may be a **single subproblem**, e.g.,binary search, the subproblems 
    may **not be independent**, e.g., dynamic programming, and they may **not be of the same type** as the
    original, e.g.,egular expression matching.
    - a divide-and-conquer algorithm is implemented using **iteration** instead of recursion. 
    improve runtime or reduce space complexity

    
----

    
## Recursion

- Recursion is especially suitable when the **input is expressed using recursive rules**. [Problem 25.26]
- Recursion is a good choice for **search, enumeration, and divide-and-conquer**. [Problems 16.2, 16.9, and 25.27]
- Use recursion as alternative to **deeply nested iteration loops**. For example,
recursion is much better when you have an undefined number of levels, such 
as the IP address problem generalized to k substrings. [Problem 7.10]
- If you are asked to remove recursion from a program, consider mimicking(模仿) call
stack with the stack data structure(Stack). [Problem 25.13]
- Recursion can be easily removed from a **tail-recursive** program by using a **whileloop** -- no stack is needed. (Optimizing compilers do this.) [Problem 5.7]
- If a recursive function may end up being **called with the same arguments more than once**, ]
**cache** the results—this is the idea behind Dynamic Programming (Chapter 17).


    
   
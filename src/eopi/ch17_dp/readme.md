## preface
- DP is a general technique for solving optimization, search, 
and counting problems that can be decomposed into **subproblems**.
- Like **divide-and-conquer**, DP solves the problem by combining 
the solutions of **multiple smaller problems**, but what makes DP 
different is that the **same subproblem may reoccur**. Therefore, 
a key to making DP efficient is **caching the results of intermediate computations**. 

- The key to solving a DP problem efficiently is finding a way to break the problem 
into **subproblems** such that
    - the original problem can be **solved relatively easily** once solutions to the 
    subproblems are available, and
    - these **subproblem solutions are cached**.
    
- problems lies that the subproblems are easy to identify.
    
- some explains
    - Consider using DP whenever you have to make choices to arrive at the solution,
    specifically, when the solution relates to subproblems.
    - In addition to optimization problems, DP is also applicable to **counting and
    decision problems** -- any problem where you can express a solution recursively
    in terms of the same computation on smaller instances.(递归的使用迭代的子问题构建解决方案)
    - Although conceptually DP involves recursion, often for efficiency the cache is
    built "bottom-up", i.e., iteratively. [Problem 17.3]. (缓存自底向上的构建)
    - To save space, cache space may be recycled once it is known that a set of entries
    will not be looked up again. [Problems 17.1 and 17.2] (可重复使用的缓存)
    - Sometimes, recursion may out-perform a bottom-up DP solution, e.g., when
    the solution is found early or subproblems can be pruned through bounding.[Problem 17.5].(
    涉及到剪枝或者子问题很早就被发现是整个问题的解,可能递归更快)
    

  
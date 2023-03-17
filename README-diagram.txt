
Correctness in the system indicates that the maximum enrollment of a course is not exceeded.
Let ce be the current enrollment of a desired course for both Student1 and Student2.
Let the max enrollment of the course be 1. 
ce	S1	S2	    Notes
========================
0			    initial
→ → 0		    S1: reads ce.
                S1: ce<me (0<1), the course can be added
    → → 0       S2: reads ce.
                S2: ce<me (0<1), the course can be added
1 ← ←			Course adds S1 to the course, writing ce+1 to ce
2 ← ← 			Course adds S2 to the course, writing ce+1 to ce

The maximum enrollment is exceeded.
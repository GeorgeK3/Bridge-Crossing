# Bridge Crossing

Bridge Crossing is an AI project that implements the A* algorithm to solve the classic bridge crossing puzzle. The algorithm uses a cost function, f = g + h, where g is the elapsed time and h is a heuristic based on the slowest individual waiting to cross and the fastest available to return. The project generates all possible crossing pairs when on the right bank and selects the fastest returner on the left, outputting the optimal crossing sequence or "No solution found!" if no valid solution exists within the allotted time.

## Overview

- **A\* Algorithm:**  
  Utilizes f = g + h, with g representing the total elapsed time and h estimating the remaining time based on key individuals.

- **Heuristic Function:**  
  Considers the slowest person yet to cross and the fastest person available for return to optimize decisions.

- **Stopping Criteria:**  
  The search terminates when all individuals have crossed or if the time limit expires.

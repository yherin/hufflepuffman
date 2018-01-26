# Complexity analysis

## Assumptions
An input size of _n_.  
An alphabet of _k_ symbols.

## Time Complexity
Huffman Coding time complexity relative to _n_ is good, as the limiting factor of the algorithm is _k_, the number of symbols.


#### Reading and sorting the file.

- Read each character once [O(1)] and HashMap insert/update[O(1)] _n_ times. Total [O(n)]. _In the worst case, HashMap performance is technically O(k) (every insert produces a collision), but this is not likely for any decent implementation._
- Build objects from the HashMap, inserting into PriorityQueue. One operation is performed for each symbol in the alphabet of _k_ symbols. So O(log k) k times. Total [O(k log k)].

Reading and sorting total: O( k(log k) + n)

#### Building the tree.
- My implementation of the building the tree is as follows:
  - While the queue has two or more elements:
   - Take the first two elements from the queue (lowest weight first).
   - Add them together, creating a new Node having the previously    removed nodes as children.
   - Insert this node back into the queue.
  - Now we have the root node of our tree.

```java
while (que.size() >=2){ // Do the operation at most k/2 times.

    Node firstNode = que.poll(); //O(log k)
    Node secondNode = que.poll(); //O(log k)

    Node joint = new Node(firstNode, secondNode); //O(1)
    que.add(joint); //O(log k)
}

root = que.poll();

```
Building total: O( k/2(log k + log k + log k + 1)) = O (k log k)

#### Encoding and decoding.

- When we encode a symbol, we need to find it in our tree. This takes O(log k) - the height of the tree. We follow each node down choosing either 0 or 1, leading us to the next node, until we reach the bottom.
- When we decode a symbol, it requires the same complexity. So O(log k).

Total encoding or decoding a file of size _n_ : O(n log k)


#### Encoding a file from scratch

O((k log k) + n + (k log k) + (n log k))

It is likely that _k_ stays quite low relative to _n_. For example, an English language text where _n_=1000 will likely have the same amount of symbols (_k_=30-35) as an English language text where _n_=100000.

So in this sense, we might suppose _k_ to be somewhat constant. If we do suppose that, then our algorithm will run in linear time relative to to n [O(n)], with the most time-complex operation being reading the file.

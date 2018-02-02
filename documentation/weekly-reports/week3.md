# Week 3

## What have I done this week?
- Implementation of building the HuffmanTree.
- Implementation of writing the huffman codes within bytes using Java bit operators.
- First steps of decoding the bits back to text.

## How has the program progressed?
- The program now is able to compress files quite well. My sample text file can be
compressed from ~8mb to ~4.8mb in 10 seconds.

## What have I learned this week?
- I learned a lot of Java byte functionality and in general became more familiar
with file handling and Byte/bit operators and functionality in Java.

## What remains unclear / has brought difficulties?
- I had some trouble understand the actual compression process. At first, I was just writing
Java characters to file, and obviously replacing a single character with a large code made the files
much much bigger. Now it's clear though. Once I understood the idea of putting the codes as bits within bytes,
everything started to come together.
- Approximate benchmarks:
 - 8mb file, 79 symbols: 10 seconds
 - 500mb file, ~55000 symbols: 11 minutes.
- I feel that I could still optimise the file operations much more.
- 79 -> 55000 symbols is an increase by a factor of 696, the file size increased by a factor of 62, the running time increased by approximately a factor of 66. So it would seem that complexity is mostly related to IO operations and linearly dependent on file size.

## What will I do next?
- Continue my implementation, the next step being to decode from binary back to text.

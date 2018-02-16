# Week 5

## What have I done this week?
- Refactored and improved the documentation of my code.
- Implemented a much better IO structure and handling.
- Fixed some critical bugs, one in particular.
- Almost finished the implementation of encoding the Huffman tree data structure in the compressed file, so that it can be decompressed.

## How has the program progressed?
- The IO refactoring and improvements that I did increased the speed of the algorithm by approximately 100 times. I'm now using ByteBuffer and FileChannel, which seems to be very efficient and nice to use.
- The core, essential part of the algorithm now works completely, at least from my testing. I've used `cmp -b output input` (Unix terminal) which does a byte-by-byte value comparison across the files and compares them. I compressed and decompressed a 17.8mb text document to 9.9mb binary and back to text with no bugs at all, input and output completely identical, total time for both operations was 3 seconds (with some logging still on). I'm quite pleased with performance.
- Metadata of the compression codes is now included in the header of the compressed file (but is not yet used in the algorithm). This is the way to split the compression and decompression operations and is my next goal. Metadata takes the following form:
 - First 4 bytes, int with the size of the metadata
 - Next 2 bytes, number of 'empty bits' in the tree encoding and EOF byte.
 - _n_ bytes of tree encoding (See binary array in log below at TreeBuilder <init>)
 - _m_ bytes of symbols used in the document, each symbol taking 2 bytes (UTF-8). (String below in log, TreeBuilder <init>).
 - More documentation in MetadataBuilder.

## What have I learned this week?
- A lot more about IO principles, practices and such kind of things.
- I learned that I need to commit more time to the project still, I feel that I am just about on schedule, if not slightly behind. I didn't yet start any work on HashMap or PriorityQueue, which I still need to implement.

## What remains unclear / has brought difficulties?
- I had again quite many obstacles this week, but I overcame them now, just really ran out of time towards the end of the week as the deadline approached.

## What will I do next?
- I still really need to improve my unit test coverage. Currently PIT reports it as 70% line coverage, 46% mutation coverage.
- I need to be able to seperate the compress and decompress operations and successfully decode the metadata that I store in the compressed file.
- Take first steps towards HashMap implemenation.


```
Feb 16, 2018 11:29:57 PM com.js.huffman.io.MetadataBuilder logInfo
INFO: MD SIZE:63
Feb 16, 2018 11:29:57 PM com.js.huffman.io.MetadataBuilder logInfo
INFO: TREE REP SIZE: 11
Feb 16, 2018 11:29:57 PM com.js.huffman.io.MetadataBuilder logInfo
INFO: SYMBOLS SIZE: 42
Feb 16, 2018 11:29:57 PM com.js.huffman.io.BitOutputStream flush
INFO: 3 bits to be written in final byte.
Feb 16, 2018 11:29:57 PM com.js.huffman.io.BitOutputStream fillFinalByte
INFO: 3 extra 0 bits written to byte at EOF.
Feb 16, 2018 11:29:57 PM com.js.huffman.io.BitOutputStream forceWriteBuffer
INFO: Forcing buffer to file with 363 bytes
Feb 16, 2018 11:29:57 PM com.js.huffman.model.count.HuffmanEncoder encodeBits
INFO: Total 53611 bytes written.
Feb 16, 2018 11:29:57 PM com.js.huffman.io.IOHandler write
INFO: Encoding done.
Feb 16, 2018 11:29:57 PM com.js.huffman.io.BitInputStream readSingleByte
INFO: FINAL BIT
Feb 16, 2018 11:29:57 PM com.js.huffman.model.process.BitUtils decodeBits
WARNING: Discarding 3 fake bits. This message should only appear once.
Feb 16, 2018 11:29:57 PM com.js.huffman.io.BitInputStream readByte
INFO: EOF
Feb 16, 2018 11:29:57 PM com.js.huffman.io.IOHandler readAndDecode
INFO: Decoding done.
Feb 16, 2018 11:29:57 PM com.js.huffman.model.process.TreeBuilder <init>
INFO: rdPIQO;LEAx
venl,fDMSjCcast op.umhNUFVbqgi
Feb 16, 2018 11:29:57 PM com.js.huffman.model.process.TreeBuilder <init>
INFO: [0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1]
Done.
Build data time: 76ms.
Write data time: 78ms.
Decompression time: 31ms.
Execution time: 218ms.
```

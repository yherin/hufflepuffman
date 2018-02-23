# hufflepuffman
## Huffman coding implementation - aineopintojen harjoitustyö: Tietorakenteet ja algoritmit



A Huffman code is a particular type of optimal prefix code that is commonly used for lossless data compression. The process of finding and/or using such a code proceeds by means of Huffman coding.


Read more: https://en.wikipedia.org/wiki/Huffman_coding

## Usage

Clean & build, then run. Basic GUI where you can specify a file to compress or decompress. Only _absolute_ file paths are accepted. The program outputs in the following way: compressed binary gets `.huff` file extension. Decompressed files get  `originalFileName_decomp.txt`.

## Status
- To inspect the compressed binary, use `hexdump inputFileName.huff`
- To compare the input file and decoded binary, use `cmp -b inputFileName decoded_binary.txt`
 - No output indicates that the files are identical. To test, modify the files, so that one is different, and then re-execute the command.
 - Use `ls -al` to inspect the sizes of the files.

## Known issues
- Fails to write new line at EOF when decompressing.

## Docs

[Specs](documentation/specification.md)

[Algorithm complexity](documentation/complexity.md)

[Week 1 report](documentation/weekly-reports/week1.md)

[Week 2 report](documentation/weekly-reports/week2.md)

[Week 3 report](documentation/weekly-reports/week3.md)

[Week 4 report](documentation/weekly-reports/week4.md)

[Week 5 report](documentation/weekly-reports/week5.md)

[Week 6 report](documentation/weekly-reports/week6.md)

[Working hours log](documentation/weekly-reports/log.md)

[Implementation document](documentation/huffman-impl.pdf)

# hufflepuffman
## Huffman coding implementation - aineopintojen harjoitustyö: Tietorakenteet ja algoritmit



A Huffman code is a particular type of optimal prefix code that is commonly used for lossless data compression. The process of finding and/or using such a code proceeds by means of Huffman coding.


Read more: https://en.wikipedia.org/wiki/Huffman_coding

## Usage

Entry point is `src/main/java/com/js/huffman/Runner#execute()`.
Preset file is `lorem`, which is 1kb of lorem ipsum. To supply your own, simply give the file path as a String in the entry point above.
Clean & build. Then Run.



## Status
Compression and decompression is performed in one operation, when clicking the button in the UI.
- To inspect the compressed binary, use `hexdump inputFileName.huff`
- To compare the input file and decoded binary, use `cmp -b inputFileName decoded_binary.txt`
 - No output indicates that the files are identical. To test, modify the files, so that one is different, and then re-execute the command.
 - Use `ls -al` to inspect the sizes of the files.


## Docs

[Specs](documentation/specification.md)

[Algorithm complexity](documentation/complexity.md)

[Week 1 report](documentation/weekly-reports/week1.md)

[Week 2 report](documentation/weekly-reports/week2.md)

[Week 3 report](documentation/weekly-reports/week3.md)

[Week 4 report](documentation/weekly-reports/week4.md)

[Week 5 report](documentation/weekly-reports/week5.md)

[Working hours log](documentation/weekly-reports/log.md)

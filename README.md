# hufflepuffman
## Huffman coding implementation - aineopintojen harjoitustyö: Tietorakenteet ja algoritmit



A Huffman code is a particular type of optimal prefix code that is commonly used for lossless data compression. The process of finding and/or using such a code proceeds by means of Huffman coding.


Read more: https://en.wikipedia.org/wiki/Huffman_coding

## Usage

Basic GUI where you can specify a file to compress or decompress. Only _absolute_ file paths are accepted. The program outputs in the following way: compressed binary gets `.huff` file extension. Decompressed files get  `originalFileName-decomp.extension`.

## Status
- To inspect the compressed binary, use `hexdump inputFileName.huff`
- To compare the input file and decoded binary, use `cmp -b inputFileName decoded_binary.txt`
 - No output indicates that the files are identical. To test, modify the files, so that one is different, and then re-execute the command.
 - Use `ls -al` to inspect the sizes of the files.

## Known issues
- The difference between new line characters may cause issues for some combinations of files.
  - The program uses whichever line seperator the user's system specifies. So if a file written on a Windows system is compressed and then decompressed on Linux, it will contain Linux style new line characters.
  - Most programs are smart enough to display these new lines (even on Windows), but there are some notable exceptions (Notepad, for example).
  - The may cause the size of the decompressed file to differ from the original (Windows uses 2 bytes, Linux only 1).

## Docs

[JavaDoc](https://htmlpreview.github.io/?https://github.com/yherin/hufflepuffman/blob/master/documentation/apidocs/index.html)

[Implementation and test document](documentation/huffman-impl.pdf)

[Latest PIT report](http://htmlpreview.github.io/?https://github.com/yherin/hufflepuffman/blob/master/documentation/PIT/index.html)

[Initial and rough specs](documentation/specification.md)

[Algorithm complexity](documentation/complexity.md)

[Week 1 report](documentation/weekly-reports/week1.md)

[Week 2 report](documentation/weekly-reports/week2.md)

[Week 3 report](documentation/weekly-reports/week3.md)

[Week 4 report](documentation/weekly-reports/week4.md)

[Week 5 report](documentation/weekly-reports/week5.md)

[Week 6 report](documentation/weekly-reports/week6.md)

[Working hours log](documentation/weekly-reports/log.md)




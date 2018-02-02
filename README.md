# hufflepuffman
## Huffman coding implementation - aineopintojen harjoitustyö: Tietorakenteet ja algoritmit



A Huffman code is a particular type of optimal prefix code that is commonly used for lossless data compression. The process of finding and/or using such a code proceeds by means of Huffman coding.


Read more: https://en.wikipedia.org/wiki/Huffman_coding

## Usage

Entry point is `src/main/java/com/js/huffman/Runner#execute()`.
Preset files are specified for execution. To supply your own, simply give the file path as a String.
Clean & build. Then Run.
Tested as working for 500mb file (~11 minute compression time) compressed to ~300mb. The is one of the worst cases (the alphabet is so large).


## Status
Currently only compression is implemented. The output file is binary. No way to decode from the binary yet. If you want to examine the binary, please look at something like `hexdump` for Unix. I'm sure Windows alternatives are easily available.

## Docs

[Specs](documentation/specification.md)
[Algorithm complexity](documentation/complexity.md)

[Week 1 report](documentation/weekly-reports/week1.md)
[Week 2 report](documentation/weekly-reports/week2.md)
[Week 3 report](documentation/weekly-reports/week3.md)
[Working hours log](documentation/weekly-reports/log.md)

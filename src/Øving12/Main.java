package Øving12;

public class Main {
    public static void main(String[] args) throws Exception{
        String input = "src/Øving12/opg12.txt";
        String compressed = "src/Øving12/compressed.txt";
        String decompressed = "src/Øving12/decompressed.txt";
        Compressor.compress(input,compressed);
        Decompressor.decompress(compressed,decompressed);
    }
}

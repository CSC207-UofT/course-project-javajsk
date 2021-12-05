package UseCasesTest.daitesters;

import businessrules.dai.Hasher;

public class RAMHashRepository implements Hasher {
    @Override
    public String hash(String plainText) {
        return plainText;
    }
}
